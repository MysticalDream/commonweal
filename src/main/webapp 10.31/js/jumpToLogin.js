window.addEventListener('load', () => {
    // 首页的登录按钮
    const log_in = document.querySelector('.log_in');
    // 首页的注册按钮
    const sign_in = document.querySelector('.sign_in');
    // 点击登录按钮
    log_in.addEventListener('click', () => {
        if (!log_in.classList.contains("have_login")) {
            window.location.href = 'pages/login/come.html';
        } else {
            window.location.href = 'pages/myArea/my.html';
        }
    })
    // 点击注册按钮
    sign_in.addEventListener('click', () => {
        if (!log_in.classList.contains("have_login")) {
            window.location.href = 'pages/login/come.html';
        } else {
            window.location.href = 'pages/myArea/my.html';
        }
    })

})