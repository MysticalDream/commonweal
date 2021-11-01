; (function () {
    //辅助函数
    function $(exp) {
        return document.querySelectorAll(exp);
    }
    function getTransform(trans) {
        var arr = trans.split(', ');
        arr[0] = arr[0].substring(arr[0].lastIndexOf('(') + 1);
        arr[arr.length - 1] = arr[arr.length - 1].substring(0, arr[arr.length - 1].length - 1);
        return {
            x: arr[4],
            y: arr[5]
        };
    }
    //核心函数
    var menu = $('#floating_menu')[0];
    var point = $("#menu-open")[0];
    var disX = 0, disY = 0;
    var startX = 0, startY = 0;
    var flag = false;
    var tx = 0;
    var ty = 0;
    var mx = 0, mx1 = 0, my = 0, my1 = 0;
    function downFuc(event) {
        var event = event || window.event;
        // 阻止事件冒泡
        if (event && event.stopPropagation) {
            event.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
        // 记录下mousedown点的距离
        flag = true;
        startX = mx = event.pageX;
        startY = my = event.pageY;
    }
    menu.addEventListener("mousedown", downFuc);

    function moveFunc(event) {
        if (flag === false || point.checked) return;
        disX = event.pageX - startX;
        disY = event.pageY - startY;
        var x = disX + tx;
        var y = disY + ty;
        menu.style.transform = 'translate(' + x + 'px ,' + y + 'px )';
    }

    document.addEventListener("mousemove", moveFunc);

    function upFunc(event) {
        mx1 = event.pageX;
        my1 = event.pageY;
        var d = Math.sqrt((mx - mx1) * (mx - mx1) + (my - my1) * (my - my1));
        if (d > 7) {
            point.checked = !point.checked;
        }
        startX = event.pageX;
        startY = event.pageY;
        let oStyle = this.currentStyle ? this.currentStyle : window.getComputedStyle(this, null);
        tx = parseFloat(getTransform(oStyle.transform).x);
        ty = parseFloat(getTransform(oStyle.transform).y);
        flag = false;
    }
    menu.addEventListener("mouseup", upFunc);
})();