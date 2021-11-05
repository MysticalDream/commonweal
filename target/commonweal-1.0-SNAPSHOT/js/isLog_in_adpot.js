window.addEventListener('load', () => {
    // 用户已登录

    if (getCookie("userid") != "undefined") {
        let username = document.querySelector('.username');
        let pic = document.createElement('div');
        pic.classList.add('pic');
        let span = document.createElement('span');
        span.innerText = getCookie("username");
        username && username.appendChild(pic);
        username && username.appendChild(span);
        pic.addEventListener('click', () => {
            window.location.href = '../myArea/my.html';
        })
    }
})