/**
  *  使用说明：
  * 需要在页面的表单元素添加一个id或则自己获取表单的dom元素
  * 比如：
  * <form action="/send/iframe" method="post" id="test">
        <input type="file" name="cover">
        <input type="submit" value="提交">
    </form>
    js调用方法（记得先引入js文件）：
  iframeAjax({
    form: "#test"//或则可以传入document.querySelector("#test"),
    callback: function (data) {
        console.log(data);
    };
});
  * 表单响应异步结果获取
  * @author wcj
  */
; !function (win) {
    //辅助函数
    function $(matcher) {
        return document.querySelectorAll(matcher);
    };

    function isElementNode(node) {
        return node.nodeType === 1;
    };

    /**
     * 获取当前时间戳，精确到毫秒
     * @returns
     */
    function getNowTimeStamp() {
        return (new Date).valueOf();
    }

    //核心函数
    /**
     * 响应结果
     * @param {*} opt
     */
    function responseResult(opt) {
        //创建iframe元素
        var iframe = document.createElement('iframe');
        //回调
        opt.callback = opt.callback || function () {
        };
        //表单
        var formEle = isElementNode(opt.form) ? opt.form : $(opt.form)[0];
        //随机名称
        var name = "" + getNowTimeStamp() + Math.floor(Math.random() * 100 + 1);
        //框架名称
        iframe.name = name;
        //隐藏iframe
        iframe.width = 0;
        iframe.height = 0;
        iframe.style.display = "none";
        iframe.style.visibility = "hidden";
        //添加到body中
        win.document.body.appendChild(iframe);
        //表单的target属性设置为iframe的name
        formEle.target = name;

        //响应
        function response() {
            var result = iframe.contentWindow.document.body.outerText;
            opt.callback(JSON.parse(result));
        }
        //注意：低版本 ie 支持 iframe 的 onload 事件,
        //不过是隐形的(iframe.onload 方式绑定的将不会触发),
        //需要通过 attachEvent 来注册
        if (iframe.attachEvent) {
            iframe.attachEvent("onload", response);
        } else {
            iframe.addEventListener("load", response, false);
        }
    }
    win.iframeAjax = responseResult;
}(window);

