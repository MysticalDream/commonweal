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
 * josn 请求
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

;(function (win) {
    //直播状态
    let living = false;
    //websocket
    let ws;
    //peer工厂
    const pcFactory = new peerConnectionFactory();
    //视频
    const video = $('#local_video');
    //定时器
    let timer;
    //直播计时
    const $living = $('.living>span:nth-child(2)');
    const $livingText = $('.living>span:nth-child(1)');
    //侧边选择
    const $li1 = $('aside > ul > li:nth-child(1)');
    const $li2 = $('aside > ul > li:nth-child(2)');
    const $li3 = $('aside > ul > li:nth-child(3)');
    const $li4 = $('aside > ul > li:nth-child(4)');
    const $li5 = $('aside > ul > li:nth-child(5)');
    //开启直播按钮
    const $button = $('button.start_live');
    //媒体流状态
    let audioStreamState = false;
    let systemAudioState = false;
    let videoStreamState = false;
    let change = false;
    //镜像
    let flip = false;

    let chat_wrap = $('div.model_body > div > div.chat_content');


    const handleRequest = (data) => {
        if (!living) {
            return;
        }
        let fromId = data.fromId;
        pcFactory.getRTCPeerConnection(fromId);
    };
    //获取信息
    get({url: "/live"}).then(data => {
        win.console.log(data);
        let uuid = data.data.uuid;
        ws = new mySocket('ws://' + win.location.host + '/livechat/' + uuid);
        pcFactory.bindWs(ws);
        ws.subscribe("request_offer", handleRequest);

        ws.subscribe("message", (data) => {
            message.showUserMessage(data);
        });
        ws.subscribe("info", (data) => {
            message.showUserMessage(data);
        });
    }).catch(e => win.console.log(e));

    // //计时开始
    // const clocker = () => {
    //     clearTimer();
    //     let hour, minute, second;
    //     hour = minute = second = 0;
    //     $livingText.innerText = "直播中";
    //     timer = win.setInterval(function () {
    //         second += 1;
    //         if (second >= 60) {
    //             second = 0;
    //             minute += 1;
    //         }
    //         if (minute >= 60) {
    //             hour += 1;
    //         }
    //         $living.textContent = (hour < 10 ? '0' + hour : hour) + ':' + (minute < 10 ? '0' + minute : minute) + ':' + (second < 10 ? '0' + second : second);
    //     }, 1000);
    // };

    const clocker = () => {
        // video.addEventListener("timeupdate", () => {
        //     $living.textContent = getTimeText(video.currentTime);
        // });
        let second = 0;
        $livingText.innerText = "直播中";
        timer = setInterval(() => {
            second++;
            $living.textContent = getTimeText(second);
        }, 1000);
    };
    // video.addEventListener("timeupdate", () => {
    //     $living.textContent = getTimeText(video.currentTime);
    // });

    /**
     * 获取时间
     * @param val second 秒
     * @returns {string}
     */
    function getTimeText(val) {
        const floor = win.Math.floor(val);
        let minute = win.Math.floor(floor / 60);
        minute = minute.toString().padStart(2, "0");
        let second = floor % 60;
        second = second.toString().padStart(2, "0");
        return minute + ":" + second;
    }

    function clearTimer() {
        win.clearInterval(timer);
        $livingText.innerText = "直播结束";
        $living.innerText = "";
    }

    /**
     * 获取流
     * @param stream
     * @returns {Promise<MediaDeviceInfo[]>}
     */
    function gotStream(stream) {
        video.srcObject = stream;
        pcFactory.setLocalStream(stream);
        handleStream(stream);
        if (living) {
            ws.send(win.JSON.stringify({
                type: "start_live",
                data: {}
            }));
        }
        // return win.navigator.mediaDevices.enumerateDevices();
    }

    /**
     * 处理流
     * @param stream
     */
    function handleStream(stream) {
        stream.getTracks().forEach(track => {
            switch (track.kind) {
                case "audio":
                    if (track.label === 'System Audio' && track.enabled) {
                        systemAudioState = true;
                    } else if (track.enabled) {
                        if (change) {
                            track.enabled = audioStreamState;
                            return;
                        }
                        audioStreamState = true
                    }
                    break;
                case "video":
                    if (track.enabled) {
                        if (change) {
                            track.enabled = videoStreamState;
                            return;
                        }
                        videoStreamState = true;
                    }
                    break;
            }
        });
    }

    //停止记录
    function stopTrack() {
        if (pcFactory.localStream) {
            pcFactory.localStream.getTracks().forEach(track => {
                track.stop();
            });
        }
    }

    function handleError(error) {
        win.alert(error.message === 'Permission denied' ? "请开启设备权限" : error.message)
        stopTrack();
    };

    /**
     * 处理屏幕共享
     * @param stream
     */
    function handleDisplayStream(stream) {
        stream.getVideoTracks()[0].addEventListener("ended", function () {
            startCamera();
        });
    }

    /**
     * 开启摄像头
     */
    function startCamera() {
        stopTrack();
        const constraints = {
            audio: true,
            video: true
        };
        win.navigator.mediaDevices.getUserMedia(constraints).then(gotStream).catch(handleError);
    };

    /**
     * 开启屏幕共享
     */
    async function startScreen() {
        stopTrack();
        const constraints1 = {
            audio: true
        };
        const constraints2 = {
            audio: true,
            video: true
        };

        try {
            let stream1 = await win.navigator.mediaDevices.getUserMedia(constraints1);
            let stream = await win.navigator.mediaDevices.getDisplayMedia(constraints2);
            handleDisplayStream(stream);
            stream.addTrack(stream1.getAudioTracks()[0]);
            await gotStream(stream);
        } catch (e) {
            handleError(e);
        }
    };

    /**
     * 关闭/开启摄影
     */
    function handleVideo(opt = false) {
        const localStream = pcFactory.localStream;
        if (localStream) {
            localStream.getVideoTracks()[0].enabled = opt;
            videoStreamState = opt;
        }
    }

    /**
     * 开启/关闭麦克风
     */
    function handleAudio(opt = false) {
        const localStream = pcFactory.localStream;
        if (localStream) {
            localStream.getAudioTracks().forEach(stream => {
                switch (stream.label) {
                    case "System Audio":
                        break;
                    default:
                        stream.enabled = opt;
                        audioStreamState = opt;
                }
            });
        }
    }

    startCamera();


    //开启直播
    $button.addEventListener("click", function () {
        if (!ws) {
            return;
        }
        if (ws.ws.readyState !== 1) {
            ws.reconnection();
        }
        //结束直播
        if (living) {
            ws.send(win.JSON.stringify({type: "stop_live", data: {}}));
            ws.close();
            living = false;
            if (pcFactory.localStream) {
                stopTrack();
            }
            clearTimer();
            $button.innerText = "开始直播";
            $button.style.backgroundColor = "#2e78f3";
            audioStreamState = systemAudioState = videoStreamState = false;
            change = false;
            return;
        }
        //开始直播
        ws.send(win.JSON.stringify({
            type: "start_live",
            data: {}
        }));
        living = true;
        $button.innerText = "结束直播";
        $button.style.backgroundColor = "#ff5252";
        //开启计时
        clocker();
    });

    //侧边工具

    //声音
    $li1.addEventListener("click", function () {
        change = true;
        handleAudio(!audioStreamState);
        this.className = (audioStreamState ? "normal" : "muted");
        if (win.senders.length) {
            win.console.log(win.senders);
        }
    });
    //视频
    $li2.addEventListener("click", function () {
        change = true;
        handleVideo(!videoStreamState);
        this.className = (videoStreamState ? "normal" : "muted");
        if (win.senders.length) {
            win.console.log(win.senders);
        }
    });
    //相机
    $li3.addEventListener("click", function () {
        startCamera();
        // ws.send(win.JSON.stringify({
        //     type: "start_live",
        //     data: {}
        // }));
    });
    //共享屏幕
    $li4.addEventListener("click", function () {
        startScreen().then(r => {
            // ws.send(win.JSON.stringify({
            //     type: "start_live",
            //     data: {}
            // }));
        });
    });

    $li5.addEventListener("click", function () {
        this.className = (flip ? "normal" : "muted");
        video.style.transform = flip ? "rotateY(0)" : "rotateY(180deg)";
        flip = !flip;
        ws && ws.ws.readyState === 1 && ws.send(win.JSON.stringify({
            type: "rotate",
            data: {}
        }));
    });

    /**
     * 窗口
     * @param opt
     */
    function windowModel(opt) {
        let title = opt.title || "";
        let template = opt.template || "";
        let btn = opt.el || win.document.createElement("li");
        let events = opt.events || [];
        if (!('content' in win.document.createElement('template'))) {
            return;
        }
        const $template = $('#model_template');
        const templateContent = $template.content;
        const node = templateContent.cloneNode(true);
        const close_btn = node.querySelector(".model_header button.close_model");
        const $title = node.querySelector('.title span');
        const $body = node.querySelector('.model_body');
        const content = node.querySelector("div.model_content_wrap > div.content");
        const modelWrap = node.querySelector(".model_wrap");
        const model_content_wrap = node.querySelector(".model_content_wrap");

        $title.textContent = title;
        $body.innerHTML = template;

        events.forEach(event => {
            let el = event.el;
            let selector = $body.querySelector(el);
            selector && selector.addEventListener(event.event, event.func)
        });
        //模板动画事件
        modelWrap.style.display = "none";
        close_btn.addEventListener("click", function () {
            model_content_wrap.style.overflow = "hidden";
            content.classList.add("action1");
            win.setTimeout(() => {
                modelWrap.style.display = "none";
                content.classList.remove("action1");
                model_content_wrap.style.overflow = "auto";
            }, 500);
        });

        btn.addEventListener("click", function () {
            modelWrap.style.display = "block";
            model_content_wrap.style.overflow = "hidden";
            content.classList.add("action2");
            win.setTimeout(() => {
                content.classList.remove("action2");
                model_content_wrap.style.overflow = "auto";
            }, 500);
        });
        win.document.body.appendChild(node);
        opt.callback && opt.callback();
    }


    //聊天室
    /**
     * 发送聊天消息
     * @param msg
     */
    function sendMsg(msg) {
        const trim = msg.trim();
        if (trim) {
            ws && ws.ws.readyState === 1 && ws.send(win.JSON.stringify(
                {
                    type: "message",
                    data: {
                        fromId: cookieManager.getCookie("userId"),
                        content: trim
                    }
                }
            ));
        }
    }

//消息处理
    const message = {
        el: undefined,
        messageStack: [],
        showUserMessage(msg) {
            if (this.el) {
                this.consume(this.messageStack);
                this.showText(msg);
            } else {
                this.messageStack.push(msg);
                win.console.log("el:" + this.el, "messageStack:" + this.messageStack);
            }
        },
        showText(msg) {
            let str = `观众`;
            if (msg.remark === 'live_own') {
                str = '主播';
            }
            this.el.innerHTML += `<div class="chat_item" style="text-align: ${msg.fromId == cookieManager.getCookie("userId") ? "right" : "left"} ">
<div class="username">${msg.fromUsername}(${str})</div>
<div class="message_text">${msg.content}</div>
</div>`;
            this.el.scrollTop = this.el.scrollHeight;
        },
        consume(msgStack) {
            for (let i = 0; i < msgStack.length; i++) {
                this.showText(msgStack[i]);
            }
            msgStack.length = 0;
        }
    };

    const chat_template = `<div class="chat_room_wrap">
<div class="chat_content"></div>
<div class="chat_bar"></div>
<textarea id="chat_area" class="chat_editor" placeholder="请输入信息，按enter键发送"></textarea>
</div>`;

    const chat_events = [{
        el: "#chat_area",
        event: "keydown",
        func: function (event) {
            if (event.ctrlKey && event.keyCode === 13) {
                this.value = this.value + '\n';
            } else if (event.keyCode === 13) {
                sendMsg(this.value);
                win.setTimeout(() => {
                    this.value = '';
                }, 0);
                event.preventDefault();
            }
        }
    }];
    //聊天窗口
    windowModel({
        title: "聊天室",
        template: chat_template,
        el: $('footer > ul > li.message'),
        events: chat_events,
        callback: function () {
            const $chatContent = $('.chat_content');
            message.el = $chatContent;
        }
    });
    //直播间窗口
    windowModel({
        title: "直播间成员",
        template: `<h1 align="center">暂未开发</h1>`,
        el: $('footer > ul > li.member')
    });
})(window);