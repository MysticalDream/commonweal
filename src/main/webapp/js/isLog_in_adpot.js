window.addEventListener('load', () => {
    // 用户已登录
    if (getCookie("userid") != undefined) {
        // 获取username的盒子 为其添加两个子盒子
        let username = document.querySelector('.username');
        let pic = document.createElement('div');
        pic.classList.add('pic');
        let span = document.createElement('span');
        span.innerText = getCookie("username");
        username && username.appendChild(pic);
        username && username.appendChild(span);
    }
})