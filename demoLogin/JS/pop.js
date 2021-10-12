window.addEventListener('load',function(){
    //组件
    (function (global, factory) {
        //模块化标准处理
        typeof exports === "object" && typeof module !== "undefined"
            ? (module.exports = factory())
            : typeof define === "function" && define.amd
                ? define(factory)
                : ((global = global || self), (global.myAlert = factory()));
        document.body.innerHTML +=
            '<div class="cd-popup"role="alert"><div class="cd-popup-container"><div></div><ul class="cd-buttons"><li><a href="javascript:void(0)">确认</a></li><li><a href="javascript:void(0)">取消</a></li></ul><a href="javascript:void(0)"class="cd-popup-close img-replace">Close</a></div></div>';
    })(this, function () {
        //辅助函数
        function $(matcher) {
            return document.querySelectorAll(matcher);
        }
        //核心函数
        //{callback:function,cancel:function,btn:object,msg:string}
        function myAlert(opt) {
            var msg = opt.msg || "";
            var btn = opt.btn || undefined;
            //警告外框
            var wrap = $("div.cd-popup")[0];

            function render() {
                wrap.classList.add("is-visible");
                // 确认按钮
                var ensureBtn = $(
                    "div.cd-popup.is-visible > div > ul > li:nth-child(1)"
                )[0];
                // 取消按钮
                var cancelBtn = $(
                    "div.cd-popup.is-visible > div > ul > li:nth-child(2)"
                )[0];
                // 关闭的叉叉
                var closeBtn = $("div.cd-popup.is-visible> div > a")[0];
                // 顶部白色盒子
                var showTxt = $("div.cd-popup.is-visible > div > div")[0];
                // 白色盒子的内部文本，在传入对象时可编辑
                showTxt.innerHTML = msg;
                //点击后确认
                ensureBtn.onclick = function () {
                    wrap.classList.remove("is-visible");
                    clearBtnEvent();
                    opt.callback && opt.callback();
                };
                // 点击后取消
                cancelBtn.onclick = function () {
                    wrap.classList.remove("is-visible");
                    clearBtnEvent();
                    opt.cancel && opt.cancel();
                };
                // 点击后关闭
                closeBtn.onclick = function () {
                    wrap.classList.remove("is-visible");
                    clearBtnEvent();
                    opt.close && opt.close();
                };
                //解除按钮事件
                function clearBtnEvent() {
                    ensureBtn.onclick = undefined;
                    cancelBtn.onclick = undefined;
                    closeBtn.onclick = undefined;
                }
            }
            if (btn) {
                btn.onclick = function () {
                    render();
                };
            } else {
                this.show = render;
            }
        }
        return myAlert;
    });
    /**
     * 测试
     */
    //图片
    // var myAlert1 = new myAlert({
    //     msg: "<img src='https://www.wallpaperup.com/uploads/wallpapers/2014/04/05/323740/ba634021f7c402b84a770b30d971305e-700.jpg' width='200px' height='200px'/>"
    //     ,
    //     btn: document.querySelector("#btn1"),
    //     callback: function () {
    //         console.log("确认");
    //     }, cancel: function () {
    //         console.log("取消");
    //     }, close: function () {
    //         console.log("关闭");
    //     }

    // });
    // //文本
    // var myAlert2 = new myAlert({
    //     msg: "<h1>文本消息</h1>",
    //     btn: document.querySelector("#btn2"),
    //     callback: function () {
    //         console.log("确认");
    //     }, cancel: function () {
    //         console.log("取消");
    //     }, close: function () {
    //         console.log("关闭");
    //     }
    // });
    // //10s后自动触发
    // setTimeout(() => {
    //     new myAlert({
    //         msg: "<h1>10s后自动触发的消息</h1>",
    //         callback: function () {
    //             console.log("确认");
    //         }, cancel: function () {
    //             console.log("取消");
    //         }, close: function () {
    //             console.log("关闭");
    //         }
    //     }).show();
    // }, 10000);

    //  实例
})