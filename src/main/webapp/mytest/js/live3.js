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
    const $living = $('.living>span>span');
    //侧边选择
    const $li1 = $('aside > ul > li:nth-child(1)');
    const $li2 = $('aside > ul > li:nth-child(2)');
    const $li3 = $('aside > ul > li:nth-child(3)');


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
            message.showUserInfo(data);
        });
        ws.subscribe("info", (data) => {
            message.showNotification(data);
        });
    }).catch(e => win.console.log(e));

//计时开始
    video.addEventListener("loadedmetadata", () => {
        reTime();
        let hour, minute, second;
        hour = minute = second = 0;
        timer = win.setInterval(function () {
            second += 1;
            if (second >= 60) {
                second = 0;
                minute += 1;
            }
            if (minute >= 60) {
                hour += 1;
            }
            $living.textContent = (hour < 10 ? '0' + hour : hour) + ':' + (minute < 10 ? '0' + minute : minute) + ':' + (second < 10 ? '0' + second : second);
        }, 1000);
    });

    function reTime() {
        win.clearInterval(timer);
    }

    /**
     * 获取流
     * @param stream
     * @returns {Promise<MediaDeviceInfo[]>}
     */
    function gotStream(stream) {
        video.srcObject = stream;
        pcFactory.setLocalStream(stream);
        return win.navigator.mediaDevices.enumerateDevices();
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
     * 开启摄像头
     */
    function startCamera() {
        stopTrack();
        // const audioSource = audioInputSelect.value;
        // const videoSource = videoSelect.value;
        // const constraints = {
        //     audio: {deviceId: audioSource ? {exact: audioSource} : undefined},
        //     video: {deviceId: videoSource ? {exact: videoSource} : undefined}
        // };
        const constraints = {
            audio: true,
            video: true
        };
        win.navigator.mediaDevices.getUserMedia(constraints).then(gotStream).catch(handleError);
    };

    startCamera();

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
            stream.addTrack(stream1.getAudioTracks()[0]);
            pcFactory.setLocalStream(stream);
            video.srcObject = stream;
        } catch (e) {
            handleError(e);
        }
    };

    $li1.addEventListener("click", function () {
        if (win.senders.length) {
            win.console.log(win.senders);
        }
    });
    $li2.addEventListener("click", function () {
        if (win.senders.length) {
            win.console.log(win.senders);
        }
    });
    $li3.addEventListener("click", function () {
        startScreen();
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
                        fromId: cookieManager.getCookie("usrId"),
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
            this.el.innerHTML += `<div class="chat_item">
<div class="username">${msg.fromUsername}(${str})</div>
<div class="message_text">${msg.content}</div>
</div>`;
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