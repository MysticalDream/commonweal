window.addEventListener('load', () => {
    // 判断有没有userid 有的话页面的登录和注册要改变 没有的话就不做任何操作
    let slideshow = document.querySelector('.slideshow');
    if (getCookie("userId") !== "undefined") {
        // 创建登录和注册两个圈圈
        // let log_in = document.querySelector('.log_in');
        // let sign_in = document.querySelector('.sign_in');
        // 注册按钮
        let log_in = document.createElement('div');
        log_in.classList.add('log_in');
        log_in.style.background = "rgba(255,255,255,0)"
        let sign_in = document.createElement('div');
        sign_in.classList.add('sign_in');
        sign_in.classList.add('sign_in2');
        sign_in.innerHTML = getCookie("username");
        // sign_in.parentNode.removeChild(sign_in);
        // 将登录按钮的内容换成头像
        let img = document.createElement('img');
        img.src = getCookie("avatarUrl");
        log_in.appendChild(img);
        // log_in.classList.add("have_login");
        // sign_in.classList.add("have_login");
        slideshow.appendChild(log_in);
        slideshow.appendChild(sign_in);
        log_in.addEventListener('click', () => {
            window.location.href = 'pages/myArea/my.html'
        })
    } else {
        let log_in = document.createElement('div');
        log_in.classList.add('log_in');
        log_in.style.background = '#fff';
        log_in.innerHTML = '登录';
        let sign_in = document.createElement('div');
        sign_in.classList.add('sign_in');
        sign_in.innerHTML = '注册';
        slideshow.appendChild(log_in);
        slideshow.appendChild(sign_in);
        sign_in.addEventListener('click', () => {
            window.location.href = 'pages/login/come.html';
        })
        log_in.addEventListener('click', () => {
            window.location.href = 'pages/login/come.html';
        })
    }
})