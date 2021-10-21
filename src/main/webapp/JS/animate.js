function animate(obj, target, callback) {
    // 清除定时器
    clearInterval(obj.timer);
    // 定时器
    obj.timer = setInterval(function() {
        // 设置步长
        var step = (target - obj.offsetLeft) / 10;
        // 大于0向上取整 小于0向下取整
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (obj.offsetLeft == target) {
            clearInterval(obj.timer);
            if (callback) {
                callback();
            }
        }
        obj.style.left = obj.offsetLeft + step + 'px';
    }, 15)
}

// 竖向轮播图
function animateVert(obj, target,callback) {
    cancelAnimationFrame(obj.timer);
    obj.timer = requestAnimationFrame(function fn() {
        var step = (target - parseInt(obj.offsetTop)) / 10;
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (obj.offsetTop == target) {
            cancelAnimationFrame(obj.timer);
            if (callback) {
                callback();
            }
        }
        obj.style.top = obj.offsetTop + step + 'px';
        timer=requestAnimationFrame(fn);
    })
}


// 针对页面缓慢滚动回顶部，offsetLeft改成window.pageYOffset而不是offsetTop
function animateY(obj, target, callback) {
    clearInterval(obj.timer);
    obj.timer = setInterval(function() {
        var step = (target - window.pageYOffset) / 10;
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (window.pageYOffset == target) {
            clearInterval(obj.timer);
            if (callback) {
                callback();
            }
        }
        // obj.style.left = obj.offsetLeft + step + 'px';
        window.scroll(0, window.pageYOffset + step);
    }, 15)

}


function animateUp(obj, target,callback) {
    clearInterval(obj.timer);
    obj.timer = setInterval(function() {
        // 这里把window.pageYOffset处理成整数，就不会出现窗口抖动现象
        var step = (target - parseInt(window.pageYOffset)) / 10;
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (window.pageYOffset == target) {
            clearInterval(obj.timer);
            if (callback) {
                callback();
            }
        }
        // obj.style.left = obj.offsetLeft + step + 'px';
        window.scroll(0, window.pageYOffset + step);
    }, 10)

}

function animateFrame(obj, target,callback) {
    // clearInterval(obj.timer);
    cancelAnimationFrame(obj.timer);
    obj.timer = requestAnimationFrame(function fn() {
        // 这里把window.pageYOffset处理成整数，就不会出现窗口抖动现象
        var step = (target - parseInt(window.pageYOffset)) / 10;
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (window.pageYOffset == target) {
            cancelAnimationFrame(obj.timer);
            if (callback) {
                callback();
            }
        }
        window.scroll(0, window.pageYOffset + step);
        timer=requestAnimationFrame(fn);
    })
}