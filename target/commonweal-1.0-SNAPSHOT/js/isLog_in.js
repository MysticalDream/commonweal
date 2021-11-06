window.addEventListener('load', () => {
    // 首先先请空吧
    // 用户已登录
    if (getCookie("userId") !== "undefined") {
        let username = document.querySelector('.username');
        let pic = document.createElement('div');
        pic.classList.add('pic');
        let img_u = document.createElement('img');
        img_u.classList.add('user_img');
        img_u.src = getCookie("avatarUrl");
        pic.appendChild(img_u);
        let span = document.createElement('span');
        span.innerText = getCookie("username");
        let operation = document.createElement('div');
        operation.classList.add('operation');
        let my = document.createElement('div');
        my.classList.add('my');
        my.innerHTML = '我的';
        let incoWode = document.createElement('i');
        incoWode.classList.add('iconfont');
        incoWode.classList.add('icon-wode');
        my.appendChild(incoWode);
        let exit = document.createElement('div');
        exit.classList.add('exit');
        exit.innerHTML = '退出';
        let incoTuichu = document.createElement('i');
        incoTuichu.classList.add('iconfont');
        incoTuichu.classList.add('icon-tuichu');
        exit.appendChild(incoTuichu);
        operation.appendChild(my);
        operation.appendChild(exit);
        let triangle = document.createElement('div');
        triangle.classList.add('triangle');
        username.appendChild(pic);
        username.appendChild(span);
        username.appendChild(operation);
        username.appendChild(triangle);
        pic.addEventListener('mousemove', () => {
            operation.style.display = 'block';
            triangle.style.display = 'block';
        })
        username.addEventListener('mouseleave', () => {
            operation.style.display = 'none';
            triangle.style.display = 'none';
        })
        my.addEventListener('click', () => {
            window.location.href = 'pages/myArea/my.html';
        })
        exit.addEventListener('click', () => {
            username.remove(pic);
            username.remove(span);
            username.remove(operation);
            username.remove(triangle);
            let slideshow = document.querySelector('.slideshow');
            let log_in = document.createElement('div');
            log_in.innerText = "登录 | 注册";
            log_in.classList.add('log_in');
            slideshow.appendChild(log_in);
            log_in.addEventListener('click', () => {
                window.location.href = 'pages/login/come.html'
            })
        })
    } else { //用户未登录
        let slideshow = document.querySelector('.slideshow');
        let log_in = document.createElement('div');
        log_in.innerText = "登录 | 注册";
        log_in.classList.add('log_in');
        slideshow.appendChild(log_in);
        log_in.addEventListener('click', () => {
            window.location.href = 'pages/login/come.html'
        })
    }

})