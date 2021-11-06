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
        let incoWode = document.createElement('i');
        incoWode.classList.add('iconfont');
        incoWode.classList.add('icon-wode');
        let span_o = document.createElement('span');
        span_o.innerText = '我的';
        my.appendChild(incoWode);
        my.appendChild(span_o);
        let exit = document.createElement('div');
        exit.classList.add('exit');
        let incoTuichu = document.createElement('i');
        incoTuichu.classList.add('iconfont');
        incoTuichu.classList.add('icon-tuichu');
        let span_o2 = document.createElement('span');
        span_o2.innerText = '退出';
        exit.appendChild(incoTuichu);
        exit.appendChild(span_o2);
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
    } else { //用户未登录
        let slideshow = document.querySelector('.slideshow');
        let log_in = document.createElement('div');
        log_in.innerText = "登录 | 注册";
        log_in.classList.add('log_in');
        slideshow.appendChild(log_in);
        log_in.addEventListener('click', () => {
            window.location.href = '/pages/login/come.html'
        })
    }

})