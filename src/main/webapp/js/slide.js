window.addEventListener('load', () => {;
    (function(win) {
        'use strict';
        //辅助函数
        function $(exp) {
            return document.querySelectorAll(exp);
        }
        //核心函数
        // 获取小点点
        var points = $("ul.point")[0].children;
        var pointWrap = $('ul.point')[0];
        var index = 0;
        var wrap = $(".pic")[0];
        // 获取照片
        var lists = $(".pic_ul")[0].children;
        var size = lists.length;
        var timer = null;
        Array.from(points).forEach((element, index) => {
            element.dataset.point_index = index;
        });

        pointWrap.addEventListener('click', function(e) {
            var t = e.target;
            if (t !== pointWrap) {
                var i = parseInt(t.dataset.point_index);
                for (var j = 0; j < i; j++) {
                    lists[j].style.cssText = "top:-100%;z-index:0;opacity:0;";
                }

                lists[i].style.cssText = "top:0%;z-index:1;opacity:1;";
                for (var j = i + 1, len = points.length; j < len; j++) {
                    lists[j].style.cssText = "top:100%;z-index:0;opacity:0;";
                }
                index = i;
                showPoint();
            }
        });
        //显示下一个
        function show_next() {
            if (++index > size - 1) {
                index = 0;
                for (var i = 0; i < size; i++) {
                    lists[i].style.cssText = "top:100%;z-index:0;opacity:0;";
                }
                lists[index].style.cssText = "top:0%;z-index:1;opacity:1;";
            } else {
                lists[index - 1].style.cssText = "top:-100%;z-index:0;opacity:0;";
                lists[index].style.cssText = "top:0%;z-index:1;opacity:1;";
            }
            showPoint();
        }
        //显示前一个
        function show_pre() {
            if (--index < 0) {
                index = size - 1;
                for (var i = 0; i < size; i++) {
                    lists[i].style.cssText = "top:-100%;z-index:0;opacity:0;";
                }
                lists[i].style.cssText = "top:0%;z-index:1;opacity:1;";
            } else {
                lists[index + 1].style.cssText = "top:100%;z-index:0;opacity:0;";
                lists[index].style.cssText = "top:0%;z-index:1;opacity:1;";
            }
            showPoint();
        }
        //显示点
        function showPoint() {
            for (var i = 0, len = points.length; i < len; i++) {
                points[i].className = "";
            }
            points[index].className = "light";
        }
        (function() {
            function play() {
                timer = setInterval(function() {
                    show_next();
                }, 2000);
            }
            play();
            wrap.onmouseenter = function() {
                clearInterval(timer); //鼠标悬停清除定时器
            }
            wrap.onmouseleave = function() {
                play();
            }
        })();
    })(window);
})