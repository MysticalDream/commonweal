window.addEventListener("load", function () {
    //登陆注册
    ;iframeAjax({
        form: $('#login_form'),
        callback: function (data) {
            console.log(data);
        }
    });
    //自动登录
    $('#login_form > input[type=text]:nth-child(1)').value = "common";
    $('#login_form > input[type=text]:nth-child(3)').value = "123456Aa";
    //$('#login_form').submit();
    //连接
    ;!function (win) {
        const url = "ws://localhost:8080/chat/" + cookieManager.getCookie("userId");
        const content = $('#content');
        const send_btn = $('#send');
        const close_btn = $('#close');
        const connect_btn = $('#connect');
        const ws_url = $('#ws_path');
        let video = $('#video');
        let canvas = $('#canvas');
        let photo = $('#photo');
        const close_live = $('#close_live');
        const width = 600;
        let height = 0;
        let streaming = false;
        /**
         * 开启直播
         */
        const open_live = $('#open_live');
        /**
         * 消息展示
         */
        const info = $('#info');

        ws_url.setAttribute("placeholder", url);

        connect_btn.addEventListener("click", function () {
            let $url = ws_url.value;
            let ws = $url ? new WebSocket(ws_url) : new WebSocket(url);
            handleWS(ws);
            close_btn.addEventListener("click", function () {
                switch (ws.readyState) {
                    case WebSocket.CONNECTING:
                    case WebSocket.OPEN:
                        ws.close(1000, "客户端主动关闭");
                        break;
                    case WebSocket.CLOSING:
                        break;
                    case WebSocket.CLOSED:
                        break;
                    default:
                        alert("关闭异常");
                        break;
                }
            });
            send_btn.addEventListener("click", function () {
                let con = content.value;
                con.trim() ? (ws.send(con), info.innerHTML += `<div style="text-align: end;font-size: 18px;white-space: pre-line;" ><p>我:</p>${con}</div>`, content.value = '') : null;
            });

            open_live.addEventListener("click", function () {

                navigator.mediaDevices.getUserMedia({video: true, audio: false})
                    .then(function (stream) {
                        video.srcObject = stream;
                        video.play();
                    })
                    .catch(function (err) {
                        console.log("An error occurred: " + err);
                    });

                video.addEventListener('canplay', function (ev) {
                    if (!streaming) {
                        height = video.videoHeight / (video.videoWidth / width);
                        if (isNaN(height)) {
                            height = width / (4 / 3);
                        }

                        video.setAttribute('width', width);
                        video.setAttribute('height', height);
                        canvas.setAttribute('width', width);
                        canvas.setAttribute('height', height);
                        streaming = true;
                    }
                }, false);
                clearphoto();
            });

            function clearphoto() {
                const context = canvas.getContext('2d');
                context.fillStyle = "#AAA";
                context.fillRect(0, 0, canvas.width, canvas.height);
                var data = canvas.toDataURL('image/png');
                photo.setAttribute('src', data);
            }

            var temp = [];

            function takepicture() {
                var context = canvas.getContext('2d');
                if (width && height) {
                    canvas.width = width;
                    canvas.height = height;
                    context.drawImage(video, 0, 0, width, height);
                    const data = canvas.toDataURL('image/png');
                    if (temp.length < 10) {
                        temp.push(data);
                    } else {
                        ws.send(JSON.stringify(temp));
                        temp = [];
                    }
                } else {
                    clearphoto();
                }
            }

            const timer = setInterval(takepicture, 10);
            close_live.addEventListener("click", function () {
                clearInterval(timer);
            });
        });


        /**
         * 处理websocket
         * @param ws
         */
        function handleWS(ws) {
            ws.addEventListener("open", (event) => {
                console.log("连接成功");
            });

            ws.addEventListener("close", (event) => {
                const code = event.code;
                const reason = event.reason;
                const wasClean = event.wasClean;
                console.log(`连接关闭:${code},${reason},${wasClean}`);
            });


            ws.addEventListener("message", function (event) {
                let data = event.data;
                if (typeof data === "string") {
                    console.log("Received data string");
                    //info.innerHTML += `<div style="text-align: start;font-size: 18px;white-space: pre-line;"><p>服务器:</p>${data}</div>`;
                    data = JSON.parse(data);
                    let i = data.length - 1;
                    let timer = setTimeout(function () {
                        if (i > -1) {
                            photo.setAttribute("src", data[i--]);
                        } else {
                            clearTimeout(timer);
                        }

                    }, 100);

                }

                if (data instanceof ArrayBuffer) {
                    const buffer = data;
                    console.log("Received arraybuffer");
                }
            });
        }

    }(window);

});

