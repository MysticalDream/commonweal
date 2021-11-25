;(function (win) {
    const ws = new mySocket('ws://' + win.location.host + '/livechat/' + urlManager.get('liveId'));
    const video = $('#remote_video');
    //WebRTC连接
    let peerConnection;
    let timer;
    const $living = $('.living>span:nth-child(2)');
    const $livingText = $('.living>span:nth-child(1)');

    let flip = false;

    video.addEventListener("loadedmetadata", () => {
        // let hour, minute, second;
        // hour = minute = second = 0;
        // $livingText.innerText = "直播中";
        // timer = win.setInterval(function () {
        //     second += 1;
        //     if (second >= 60) {
        //         second = 0;
        //         minute += 1;
        //     }
        //     if (minute >= 60) {
        //         hour += 1;
        //     }
        //     $living.textContent = (hour < 10 ? '0' + hour : hour) + ':' + (minute < 10 ? '0' + minute : minute) + ':' + (second < 10 ? '0' + second : second);
        // }, 1000);
        let second = 0;
        $livingText.innerText = "直播中";
        timer = setInterval(() => {
            second++;
            $living.textContent = getTimeText(second);
        }, 1000);
    });

    function clearTimer() {
        win.clearInterval(timer);
        $livingText.innerText = "直播结束";
        $living.innerText = "";
    }

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

    //处理发送方
    const handOffer = (data) => {

        peerConnection = new win.RTCPeerConnection();

        peerConnection.addEventListener("track", function (e) {
            if (e && e.streams) {
                if (video.srcObject !== e.streams[0]) {
                    win.console.log("收到流", e.streams[0]);
                    video.srcObject = e.streams[0];
                }
            }
        });

        let {description, iceCandidates} = win.JSON.parse(data.content);

        peerConnection.setRemoteDescription(new win.RTCSessionDescription(description))
            .then(e => {
                peerConnection.createAnswer().then(description1 => {
                    peerConnection.setLocalDescription(description1)
                        .then(r => win.console.log("创建answer并设置本地描述符", r));
                    let d = {
                        type: "answer_data",
                        data: {
                            fromId: cookieManager.getCookie("userId"),
                            content: win.JSON.stringify(description1)
                        }
                    };
                    ws.send(win.JSON.stringify(d));
                });
            }).catch(e => {
            win.console.log("设置远端描述符错误", e);
        });

        iceCandidates.forEach(element => {
            peerConnection.addIceCandidate(element)
                .then(_ => win.console.log("添加远端候选者成功"));
        });
    };
    ws.subscribe("offer_data", handOffer);

    const handleMessage = (data) => {
        message.showUserMessage(data);
    };
    ws.subscribe("message", handleMessage);
    ws.subscribe("info", handleMessage);
    //镜像
    ws.subscribe("rotate", (data) => {
        video.style.transform = flip ? "rotateY(0)" : "rotateY(180deg)";
        flip = !flip;
    });

    let a = {type: "request_offer", data: {fromId: cookieManager.getCookie("userId")}};

    ws.send(win.JSON.stringify(a));

    ws.subscribe("stop_live", () => {
        win.clearInterval(timer);
        peerConnection.close();
        clearTimer();
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
            ws.ws.readyState === 1 && ws.send(win.JSON.stringify(
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