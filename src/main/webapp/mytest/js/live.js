//peerConnection工厂
function peerConnectionFactory() {
    this.localStream = null;
    this.peerConnections = {};
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
            console.log('pc设置本地描述符:' + JSON.stringify(description));
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
        console.log("this", this);
        if (that.localStream) {
            that.localStream.getTracks().forEach(element => {
                console.log('添加轨道到pc');
                pc.addTrack(element, that.localStream);
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
}

//核心逻辑
window.addEventListener("load", function () {
    ;(function (win) {

        let ws = null;
        const local_video = $('#local_video');
        const open_live = $('#open_live');
        let factory = new peerConnectionFactory();

        const handleRequest = (data) => {
            let fromId = data.fromId;
            factory.getRTCPeerConnection(fromId);
        };

        let constraint = {
            video: true,
            audio: true
        };

        /**
         * 开启直播
         */
        function openLive() {
            ajax({
                type: 'post',
                url: '/live',
                data: {
                    title: "测试直播",
                    intro: "测试测试",
                    categoryId: 1
                },
                header: {
                    'Content-Type': 'application/json'
                },
                success: function (data) {
                    win.console.log(data);
                    let uuid = data.data.uuid;
                    ws = new mySocket('ws://' + win.location.host + '/livechat/' + uuid);
                    factory.bindWs(ws);
                    ws.subscribe("request_offer", handleRequest);
                },
                error: function (data) {
                    win.console.log(data);
                }
            });
        }

        open_live.addEventListener("click", (e) => {
            openLive();
            win.navigator.mediaDevices.getUserMedia(constraint).then(
                stream => {
                    console.log("设置媒体流成功发", stream)
                    factory.setLocalStream(stream);
                    local_video.srcObject = stream;
                }
            ).catch(e => {
                win.console.log("摄像头开启失败", e);
            });
        });

    })(window);
});