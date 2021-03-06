window.addEventListener("load", function () {
    //peerConnection工厂
    function peerConnectionFactory() {
        this.localStream = null;
        this.peerConnections = {};
        window.senders = [];
        let that = this;
        //设置媒体流
        this.setLocalStream = (stream) => {
            this.localStream = stream;
        };

        this.bindWs = (ws) => {
            this.ws = ws;
            ws.subscribe('answer_data', (data) => {
                let pc = this.peerConnections[data.fromId];
                pc.setRemoteDescription(new RTCSessionDescription(window.JSON.parse(data.content)));
            });
        };
        //获取peer
        this.getRTCPeerConnection = (toId) => {
            let pc = new RTCPeerConnection();
            this.peerConnections[toId] = pc;
            pc.addEventListener("icecandidate", iceCallback);
            addTrack(pc);
            pc.createOffer().then(description => {
                window.console.log('pc设置本地描述符:' + JSON.stringify(description));
                pc.setLocalDescription(description);
                let offerData = {};
                offerData.description = description;
                offerData.toId = toId;
                pc.offerData = offerData;
            }).catch(d => console.log(d));
            return pc;
        };

        //添加轨迹
        function addTrack(pc) {
            if (that.localStream) {
                that.localStream.getTracks().forEach(element => {
                    console.log('添加轨道到pc', element);
                    window.senders = [];
                    window.senders.push(pc.addTrack(element, that.localStream));
                });
            } else {
                console.log("localStream为空", that.localStream);
            }
        };

        //收集候选人
        function iceCallback(ev) {
            let candidate = ev.candidate;
            let offerData = ev.target.offerData;
            //发送的id
            let to = offerData.toId;
            if (candidate) {
                offerData.iceCandidates ? offerData.iceCandidates.push(candidate) : offerData.iceCandidates = [candidate];
            } else {
                console.log("候选人收集完毕");
                let resultData = {
                    type: "offer_data", data: {
                        fromId: cookieManager.getCookie('userId'),
                        toId: to,
                        content: JSON.stringify(offerData)
                    }
                };
                that.ws.send(JSON.stringify(resultData));
            }
        }
    };

    //辅助函数
    /**
     * get请求
     * @param opt
     * @returns {Promise}
     */
    function get(opt) {
        return new Promise((resolve, reject) => {
            ajax({
                type: 'get',
                url: opt.url || "",
                data: opt.data || {},
                header: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    resolve(data);
                },
                error: function (data) {
                    reject(data);
                }
            });
        });
    }

    /**
     * post请求
     * @param opt
     */
    function post(opt) {

        return new Promise((resolve, reject) => {
            ajax({
                type: 'post',
                url: opt.url,
                data: opt.data,
                header: {
                    'Content-Type': opt['Content-Type'] || 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    resolve(data);
                },
                error: function (data) {
                    reject(data);
                }
            });
        });
    }

    /**
     * josn请求
     * @param opt
     * @returns {Promise}
     */
    function postJson(opt) {
        return new Promise((resolve, reject) => {
            ajax({
                type: 'post',
                url: opt.url,
                data: opt.data,
                header: {
                    'Content-Type': 'application/json'
                },
                success: function (data) {
                    resolve(data);
                },
                error: function (data) {
                    reject(data);
                }
            });
        });
    }

    //计数
    myCounter({
        tag: $('.tag'),
        el: $('#comment_area'),
        txtTemplate: '?/20',
        limit: 20
    });
    //核心
    ;(function (win) {
        let living = false;
        const right_area = $('section.right_area');
        const videoElement = $("#local_video");
        const $startLive = $('.start_live');
        const nav = $('.left_nav').children;
        const model1 = $('#model1');
        const message_area = $('.message_area');
        const setting = $('.setting');
        const select_wrap = $("div.setting>div");
        const send_btn = $('#send_btn');
        const comment_content = $('#comment_area');
        //选择
        const audioInputSelect = $('select#audioSource');
        const videoSelect = $('select#videoSource');
        const selectors = [audioInputSelect, videoSelect];
        let hashRouter = new HashRouter();
        let width = right_area.offsetWidth;
        let height;
        let streaming = false;
        let ws;
        const pcFactory = new peerConnectionFactory();
        const message_btn = $('section.left_area > nav > a:nth-child(2)');
        let wsUrl;

        const pattern = $('#pattern');
        //声音
        const $voice = $('#voice');

        const handleRequest = (data) => {
            if (!living) {
                return;
            }
            let fromId = data.fromId;
            pcFactory.getRTCPeerConnection(fromId);
        };


        $voice.addEventListener("click", () => {
            if ($voice.classList.contains("voice-open")) {
                $voice.classList.remove("voice-open");
                $voice.classList.add("voice-close");
                $voice.title = "开麦";
                if (win.senders.length) {

                }
            } else {
                $voice.classList.add("voice-open");
                $voice.classList.remove("voice-close");
                $voice.title = "闭麦";
            }
        });
        // win.onbeforeunload = function (e) {
        //     e = e || win.event;
        //     const text = '直播即将关闭,确认关闭吗?';
        //     // 兼容IE8和Firefox 4之前的版本
        //     if (e) {
        //         e.returnValue = text;
        //     }
        //
        //     // Chrome, Safari, Firefox 4+, Opera 12+ , IE 9+
        //     return text;
        // };

        /**
         * 开启直播
         */
        get({url: "/live"}).then(data => {
            win.console.log(data);
            let uuid = data.data.uuid;
            wsUrl = 'ws://' + win.location.host + '/livechat/' + uuid;
            ws = new mySocket(wsUrl);
            pcFactory.bindWs(ws);
            ws.subscribe("request_offer", handleRequest);

            ws.subscribe("message", (data) => {
                message.showUserInfo(data);
            });
            ws.subscribe("info", (data) => {
                message.showNotification(data);
            });
        }).catch(e => win.console.log(e));


        videoElement.addEventListener("canplay", function () {
            if (!streaming) {
                height = videoElement.videoHeight / (videoElement.videoWidth / width);
                if (win.isNaN(height)) {
                    height = width / (4 / 3);
                }
                // videoElement.setAttribute('width', width);
                // videoElement.setAttribute('height', height);
                streaming = true;
                try {
                    videoElement.play();
                    $startLive.style.display = "block";
                } catch (e) {
                    win.console.log(e);
                }
            }
        });
        hashRouter.registerIndex(() => {
            nav[0].className = "active";
            nav[1].className = "";
            setting.style.display = "block";
            message_area.style.display = "none";
        });

        hashRouter.register("/setting", () => {
            if (living) {
                win.history.back();
                return;
            }
            nav[0].className = "active";
            nav[1].className = "";
            setting.style.display = "block";
            message_area.style.display = "none";
        });
        hashRouter.register("/message", () => {
            nav[0].className = "";
            nav[1].className = "active";
            setting.style.display = "none";
            message_area.style.display = "block";
        });
        hashRouter.registerError(() => {
            win.console.log("error");
        });
        hashRouter.registerNotFound(() => {
            win.console.log("404");
        });
        hashRouter.load();
        //点击发送消息
        send_btn.addEventListener("click", (e) => {
            let value = comment_content.value && comment_content.value.trim();
            if (value) {
                ws && ws.send(win.JSON.stringify(
                    {
                        type: "message",
                        data: {
                            content: value
                        }
                    }
                ));
                comment_content.value = "";
                comment_content.dispatchEvent(new win.Event("input", {bubbles: true}));
            }
        });
        win.navigator.mediaDevices.enumerateDevices().then(gotDevices).catch(handleError);


        //消息展示
        const message = {
            el: $('.show_info'),
            showUserInfo(msg) {
                let str = ``;
                if (msg.remark === 'live_own') {
                    str = `<span style="background-color: #DD4A68;border-radius: 4px;color: white;margin-right: 6px;padding: 2px;font-size: 10px;">主播</span>`;
                }
                this.el.innerHTML += `<div class="content_item">
                <span class="username_span">${str}${msg.fromUsername}:</span>
                <span class="info_span">${msg.content}</span>
            </div>`;
                this.el.scrollTop = this.el.scrollHeight;
            }
            , showNotification(msg) {
                this.el.innerHTML += `<div class="content_item">
                <span class="username_span">${msg.fromUsername}</span>
                <span class="info_span">${msg.content}</span>
            </div>`;
            }
        };

        //直播按钮
        $startLive.addEventListener("click", (e) => {
            if ($startLive.classList.contains("start_live")) {
                // //开始直播
                if (!ws) {
                    return;
                }
                if (ws.ws.readyState !== 1) {
                    ws.reconnection();
                }
                ws.send(win.JSON.stringify({
                    type: "start_live",
                    data: {}
                }));
                $startLive.innerText = "结束直播";
                $startLive.className = "stop_live";
                living = true;
                message_btn.click();
            } else {
                //直播结束
                if (!ws) {
                    return;
                }
                ws.send(win.JSON.stringify({type: "stop_live", data: {}}));
                $startLive.innerText = "开始直播";
                $startLive.className = "start_live";
                ws.close();
                living = false;
                if (pcFactory.localStream) {
                    pcFactory.localStream.getTracks().forEach(track => {
                        track.stop();
                    });
                }
                model1.style.display = "none";
                $startLive.style.display = "none";
            }
        });
        win.Array.from(select_wrap[0].children).forEach((e, index) => {
            switch (index) {
                case 0:
                    e.addEventListener("click", () => {
                        useCamera(e);
                    });
                    break;
                case 1:
                    e.addEventListener("click", () => {
                        useScreen(e);
                    });
                    break;
                case 2:
                    e.addEventListener("click", () => {
                        usePhotograph(e);
                    });
                    break;
                default:
                    e.addEventListener("click", () => {
                        usePicture(e);
                    });
            }
        });

        function showModel1(opt = false) {
            if (opt) {
                pattern.innerText = "摄像";
                model1.style.display = "block";
                model1.children[1].style.display = "block";
            } else {
                model1.style.display = "block";
                model1.children[1].style.display = "none";
                pattern.innerText = "屏幕分享";
            }
        }

        function closeModel1() {
            model1.style.display = "none";
        }

        //选择素材
        //摄像头
        function useCamera(obj) {
            // videoElement.style.transform = "rotateY(180deg)";
            videoElement.srcObject = null;
            streaming = false;
            startCamera();
            showModel1(true);
        };

        //分享屏幕
        function useScreen(obj) {
            // videoElement.style.transform = "rotateY(0deg)";
            videoElement.srcObject = null;
            streaming = false;
            startScreen();
            showModel1();
        };

        //拍照
        function usePhotograph(obj) {

        };

        //图片
        function usePicture(obj) {

        };

        //获取设备
        function gotDevices(deviceInfos) {
            const values = selectors.map(select => select.value);
            selectors.forEach(select => {
                while (select.firstChild) {
                    select.removeChild(select.firstChild);
                }
            });
            for (let i = 0; i !== deviceInfos.length; i++) {
                const deviceInfo = deviceInfos[i];
                const option = win.document.createElement('option');
                option.value = deviceInfo.deviceId;
                if (deviceInfo.kind === 'audioinput') {
                    option.text = deviceInfo.label || `microphone ${audioInputSelect.length + 1}`;
                    audioInputSelect.appendChild(option);
                } else if (deviceInfo.kind === 'videoinput') {
                    option.text = deviceInfo.label || `camera ${videoSelect.length + 1}`;
                    videoSelect.appendChild(option);
                } else {
                    win.console.log('Some other kind of source/device: ', deviceInfo);
                }
            }
            selectors.forEach((select, selectorIndex) => {
                if (win.Array.prototype.slice.call(select.childNodes).some(n => n.value === values[selectorIndex])) {
                    select.value = values[selectorIndex];
                }
            });
        };

        function handleError(error) {
            win.alert(error.message === 'Permission denied' ? "请开启设备权限" : error.message)
            stopTrack();
            closeModel1();
        };

        /**
         * 获取流
         * @param stream
         * @returns {Promise<MediaDeviceInfo[]>}
         */
        function gotStream(stream) {
            videoElement.srcObject = stream;
            pcFactory.setLocalStream(stream);
            return win.navigator.mediaDevices.enumerateDevices();
        }

        function stopTrack() {
            if (pcFactory.localStream) {
                pcFactory.localStream.getTracks().forEach(track => {
                    track.stop();
                });
            }
        }

        /**
         * 开启摄像头
         */
        function startCamera() {
            stopTrack();
            const audioSource = audioInputSelect.value;
            const videoSource = videoSelect.value;
            const constraints = {
                audio: {deviceId: audioSource ? {exact: audioSource} : undefined},
                video: {deviceId: videoSource ? {exact: videoSource} : undefined}
            };
            win.navigator.mediaDevices.getUserMedia(constraints).then(gotStream).then(gotDevices).catch(handleError);
        };

        audioInputSelect.onchange = (e) => {
            model1.children[0].style.display == 'none' ? startScreen() : startCamera();
        };

        videoSelect.onchange = startCamera;

        /**
         * 开启屏幕共享
         */
        async function startScreen() {

            stopTrack();

            const audioSource = audioInputSelect.value;

            const constraints1 = {
                audio: {deviceId: audioSource ? {exact: audioSource} : undefined},
            };
            const constraints2 = {
                audio: true,
                video: true
            };

            try {
                let stream1 = await win.navigator.mediaDevices.getUserMedia(constraints1);
                let stream = await win.navigator.mediaDevices.getDisplayMedia(constraints2);
                stream.addTrack(stream1.getAudioTracks()[0]);
                pcFactory.setLocalStream(stream);
                videoElement.srcObject = stream;
                win.navigator.mediaDevices.enumerateDevices().then(gotDevices).catch(e => {
                    win.console.log(e)
                });
            } catch (e) {
                handleError(e);
            }
        };
    })(window);
})
;