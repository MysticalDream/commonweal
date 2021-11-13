/**
 * 获取DOM元素的方法
 * @param {*} matcher
 * @returns
 */
function $(matcher) {
    const doms = document.querySelectorAll(matcher);
    return doms.length === 1 ? doms[0] : doms;
}

/**
 * 判断数据类型
 * @returns
 */
function judge() {
    return {
        isArray: (o) => Object.prototype.toString.call(o) == "[object Array]",
        isObj: (o) => Object.prototype.toString.call(o) == "[object Object]",
        isNull: (o) => Object.prototype.toString.call(o) == "[object Null]",
        isFunction: (o) => Object.prototype.toString.call(o) == "[object Function]",
        isDate: (o) => Object.prototype.toString.call(o) == "[object Date]",
        isDocument: (o) => Object.prototype.toString.call(o) == "[object Document]" || Object.prototype.toString.call(o) == "[object HTMLDocument]",
        isNumber: (o) => Object.prototype.toString.call(o) == "[object Number]",
        isString: (o) => Object.prototype.toString.call(o) == "[object String]",
        isUndefined: (o) => Object.prototype.toString.call(o) == "[object Undefined]",
        isBoolean: (o) => Object.prototype.toString.call(o) == "[object Boolean]",
        isHTMLCollection: (o) => Object.prototype.toString.call(o) == "[object HTMLCollection]",
        isHTMLLIElement: (o) => Object.prototype.toString.call(o) == "[object HTMLLIElement]"
    }
}

Object.defineProperty(HTMLFormElement.prototype, 'jsondata', {
    get() {
        const jsonData = {};
        const formData = new FormData(this);
        formData.forEach((value, key) => {
            if (!jsonData[key]) {
                const temp = formData.getAll(key).length > 1 ? formData.getAll(key) : formData.get(key);
                if (judge().isArray(temp)) {
                    temp.filter(e => !e)
                    if (temp.length > 1) {
                        jsonData[key] = temp;
                    }
                } else {
                    if (temp.trim() !== "") {
                        jsonData[key] = temp;
                    }
                }
            }
        });
        return jsonData;
    }
});
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
;!function (win) {
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

;!function (win) {

    win.cookieManager = {
        /**
         * 写入 cookie
         * @param key
         * @param val
         * @param time  天数（1 = 1天）
         */
        setCookie: function setCookie(key, val, time) {
            var date = new Date();
            var expiresDays = time ? time : 7;
            date.setTime(date.getTime() + expiresDays * 24 * 3600 * 1000);
            document.cookie = key + "=" + escape(val) + ";expires=" + date.toGMTString() + ";path=/";
        },
        /**
         *  读取 cookie
         * @param key
         * @returns {string}
         */
        getCookie: function getCookie(key) {
            var getCookie = document.cookie.replace(/[ ]/g, "");
            var arrCookie = getCookie.split(";")
            var tips;
            for (var i = 0; i < arrCookie.length; i++) {
                var arr = arrCookie[i].split("=");
                if (key == arr[0]) {
                    tips = arr[1];
                    break;
                }
            }
            return decodeURI(tips);
        },

        /**
         * 删除 cookie
         * @param key
         */
        delCookie: function delCookie(key) {
            var date = new Date(); //获取当前时间
            date.setTime(date.getTime() - 10000); //将date设置为过去的时间
            document.cookie = key + "=v; expires =" + date.toGMTString() + ";path=/"; //设置cookie
        }
    }
}(window);

//计数器
(function (global, factory) {
    typeof exports === "object" && typeof module !== "undefined"
        ? (module.exports = factory())
        : typeof define === "function" && define.amd
        ? define(factory)
        : ((global = global || self), (global.myCounter = factory()));
})(
    this, function () {
        //辅助函数

        function $(matcher) {
            return document.querySelectorAll(matcher);
        }

        function isElementNode(node) {
            return node.nodeType === 1;
        }

        /**
         * 防抖函数 执行最后一次
         * @param {*} func 业务代码
         * @param {*} delay 延时
         * @returns
         */
        function antiShake(func, delay) {
            var t = null;
            return function () {
                if (t !== null) {
                    clearTimeout(t);
                }
                t = setTimeout(() => {
                    func.call(this);
                }, delay);
            };
        }

        /**
         * 节流函数 减少执行次数
         * @param {*} func 业务代码
         * @param {*} delay 延时
         */
        function throttle(func, delay) {
            var flag = true;
            return function () {
                if (flag) {
                    setTimeout(() => {
                        func.call(this);
                        flag = true;
                    }, delay);
                }
                flag = false;
            };
        }

        /**
         *
         * @param opt 显示标签 监听对象 限制字数 文字模板
         */
        function myCounter(opt) {
            const tag = isElementNode(opt.tag) ? opt.tag : $(opt.tag)[0];
            const listenObj = isElementNode(opt.el) ? opt.el : $(opt.el)[0];
            const txtTemplate = opt.txtTemplate || "?";
            tag.innerText = txtTemplate.replace(/[?？]/g, listenObj.value.length);
            //限制字数
            this.limit = opt.limit;
            const that = this;
            'maxLength' in listenObj && listenObj.setAttribute("maxlength", this.limit);
            listenObj.addEventListener(
                "input",
                throttle(function () {
                    if (this.value.length > that.limit) {
                        this.value = this.value.substring(0, that.limit);
                    }
                    tag.innerText = txtTemplate.replace(/[?？]/g, this.value.length);
                }, 200)
            );
        }

        return myCounter;
    });
/**
 * 校验只要是数字（包含正负整数，0以及正负浮点数）就返回true
 **/
;

function isNumber(val) {
    const reg = /^[0-9]+\.?[0-9]*$/;
    return reg.test(val);
};
/**
 * 获取地址栏参数键值对形式
 */
;

function getUrlParamObject(url) {
    const params = url.slice(url.indexOf('?') + 1, url.length);
    const group = params.split('&');
    const data = new Object();
    for (const index in group) {
        const arr = group[index].split('=');
        data[arr[0]] = isNumber(arr[1]) ? parseFloat(arr[1]) : arr[1];
    }
    return data;
};

const urlManager = {
    get(key) {
        return getUrlParamObject(window.location.search)[key];

    }
};