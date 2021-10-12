fadeIn = function (el, time, callBack) {
    el.style.opacity = 0;
    let t = setInterval(function () {
        el.style.opacity = parseFloat(el.style.opacity) + 0.01;
        if (el.style.opacity >= 1) {
            clearInterval(t);
            callBack && callBack();
        }
    }, time);
    return this;
}

fadeOut = function (el, time, callBack) {
    el.style.opacity = 1;
    let t = setInterval(function () {
        el.style.opacity = parseFloat(el.style.opacity) - 0.01;
        if (el.style.opacity <= 0) {
            clearInterval(t);
            callBack && callBack();
        }
    }, time);
    return this;
}

// 助农板块
window.addEventListener('load',function(){
  let one_d=document.querySelectorAll('one_d');
  
})
