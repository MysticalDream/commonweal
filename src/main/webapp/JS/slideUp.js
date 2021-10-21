!function (win) {
    //辅助函数
    function $(ele) {
        return document.querySelectorAll(ele);
    }
    /**
      * 判断浏览器是否支持IntersectionObserver
      * @returns  返回当前浏览器对于IntersectionObserver的支持情况，支持返回true，反之false;
      */
    function isSupportIntersectionObserver() {
        return (typeof IntersectionObserver).toLowerCase() === "function";
    }


    //核心函数
    window.addEventListener("load", function () {
        if (isSupportIntersectionObserver()) {
            hideAll();
            observerFunc();
        } else {
            win.alert("不支持");
        }
    });
//  取到一个数组
    var allDIv = $('.slide_p');
    function hideAll() {
        allDIv.forEach((e) => {
//  透明度设为0
            e.classList.add('opaque');
            judge(e);
        });

    }
    function judge(ele) {
        var h = ele.getBoundingClientRect().top;
        if (h < window.innerHeight) {
            ele.classList.add('already-visible');
        }
    }
    function observerFunc() {
        var observer = new IntersectionObserver(callback);
        var allDIv = $('.slide_p');
        //绑定
        allDIv.forEach((e) => {
            observer.observe(e);
        });

        function callback(entries) {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    var divE = entry.target;
                    divE.classList.remove("opaque");
                    divE.classList.add("come-in");
                    observer.unobserve(divE);
                }
            })
        }
    }
}(window);