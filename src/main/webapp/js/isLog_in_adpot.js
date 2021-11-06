window.addEventListener('load', () => {
    // 用户已登录

    let username = document.querySelector('.username');
    if (getCookie("userId") != 'undefined') {
        let pic = document.createElement('div');
        pic.classList.add('pic');
        let user_img = document.createElement('img');
        user_img.src = getCookie("avatarUrl");
        pic.appendChild(user_img);
        let span = document.createElement('span');
        span.innerText = getCookie("username");
        let operation = document.createElement('div');
        operation.classList.add('operation');
        let my = document.createElement('div');
        my.classList.add('my');
        let incoWode = document.createElement('i');
        incoWode.classList.add('iconfont');
        incoWode.classList.add('icon-wode');
        my.appendChild(incoWode);
        my.innerHTML = '我的';
        let exit = document.createElement('div');
        exit.classList.add('exit');
        let incoTuichu = document.createElement('i');
        incoTuichu.classList.add('iconfont');
        incoTuichu.classList.add('icon-tuichu');
        exit.appendChild(incoTuichu);
        exit.innerHTML = '退出';
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
            window.location.href = '/pages/myArea/my.html';
        })
        exit.addEventListener('click', () => {
            ajax({
                type: 'delete',
                url: '/sessions',
                data: {},
                header: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function(data) {
                    if (data.success) {
                        window.location.href = "/pages/login/come.html";
                    } else {
                        let error_exit = document.querySelector('.error_exit');
                        error_exit.style.display = 'block';
                        let timer_exit = setTimeout(() => {
                            if (timer_exit) {
                                clearTimeout(timer_exit);
                            }
                            error_exit.style.display = 'none'
                        }, 2000);
                    }
                }
            })
        })
    } else {
        let log_in = document.createElement('div');
        log_in.classList.add('log_in');
        log_in.innerText = '登录 | 注册';
        username.appendChild(log_in);
        log_in.addEventListener('click', () => {
            window.location.href = '/../login/come.html';
        })
    }
})