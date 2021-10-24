window.addEventListener('load', () => {
    // 判断有没有userid 有的话页面的登录和注册要改变 没有的话就不做任何操作
    if (getCookie("userId")) {
        // alert(1);
        // 获取登录和注册两个圈圈
        let log_in = document.querySelector('.log_in');
        let sign_in = document.querySelector('.sign_in');
        // 修改注册按钮
        // sign_in.parentNode.removeChild(sign_in);
        sign_in.classList.add('sign_in2');
        sign_in.innerHTML = getCookie("username");
        // 将登录按钮的内容换成头像
        log_in.innerHTML = '';
        log_in.style.background = "rgba(255,255,255,0)"
        let img = document.createElement('img');
        img.src = './images/touxiang.jpeg';
        log_in.appendChild(img);
    }
})