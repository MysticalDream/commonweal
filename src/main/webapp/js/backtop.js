;(
    function (win) {
        "use strict";

        //辅助函数
        function $(mac) {
            return document.querySelectorAll(mac);
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

        var button_back = $('#button_back')[0];
        win.addEventListener('scroll', throttle(function () {
            if (win.scrollY > 300) {
                button_back.classList.add("show");
            } else {
                button_back.classList.remove("show");
            }
        }, 300));
        button_back.addEventListener('click', (e) => {
            e.preventDefault();
            win.scrollTo({top: 0, behavior: "smooth"});
        });
    }
)(window);