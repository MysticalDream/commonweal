window.addEventListener("load", function () {
    ;(function (win) {
        const ws = new mySocket('ws://' + win.location.host + '/livechat/' + urlManager.get('liveId'));
        const video = $('#remote_video');
        let streaming = false;
        let width = $('#room_left').offsetWidth;
        let height;
        const send_btn = $('#send_btn');
        const comment_area = $('#comment_area');
        //WebRTC连接
        let peerConnection;
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
                win.console.log("设置远端描述符错误", e)
            });

            iceCandidates.forEach(element => {
                peerConnection.addIceCandidate(element)
                    .then(_ => win.console.log("添加远端候选者成功"));
            });
        };
        ws.subscribe("offer_data", handOffer);

        let a = {type: "request_offer", data: {fromId: cookieManager.getCookie("userId")}};
        ws.send(win.JSON.stringify(a));
        //消息展示
        const message = {
            el: $('.show_info'),
            showUserInfo(msg) {
                let str = ``;
                if (msg.remark === 'live_own') {
                    str = `<span style="background-color: #DD4A68;border-radius: 4px;color: white;margin-right: 6px;font-size: 10px;padding: 2px;">主播</span>`;
                }
                this.el.innerHTML += `<div class="content_item">
                <span class="username_span">${str}${msg.fromUsername}:</span>
                <span class="info_span">${msg.content}</span>
            </div>`;
            }
            , showNotification(msg) {
                this.el.innerHTML += `<div class="content_item">
                <span class="username_span">${msg.fromUsername}:</span>
                <span class="info_span">${msg.content}</span>
            </div>`;
            }
        };

        const handleMessage = (data) => {
            message.showUserInfo(data);
        };
        ws.subscribe("message", handleMessage);
        ws.subscribe("info", handleMessage);


        ws.subscribe("stop_live", () => {
            peerConnection.close();
        });


        //设置宽高
        video.addEventListener('canplay', function (ev) {
            if (!streaming) {
                height = video.videoHeight / (video.videoWidth / width);
                if (win.isNaN(height)) {
                    height = width / (4 / 3);
                }
                video.setAttribute('width', width);
                video.setAttribute('height', height);
                streaming = true;
                try {
                    video.play();
                } catch (e) {
                    win.console.log(e);
                }
            }
        }, false);
        // 发送信息
        send_btn.addEventListener("click", function () {
            let val = comment_area.value && comment_area.value.trim();
            if (val !== '') {
                ws && ws.send(win.JSON.stringify(
                    {
                        type: "message",
                        data: {
                            content: val
                        }
                    }
                ));
                comment_area.value = "";
                comment_area.dispatchEvent(new win.Event("input", {bubbles: true}))
            }
        });
        myCounter({
            tag: $('.tag'),
            el: $('#comment_area'),
            txtTemplate: '?/20',
            limit: 20
        });
    })(window);
})
;