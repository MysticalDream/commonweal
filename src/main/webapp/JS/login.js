window.addEventListener('load', () => {
    // 检查用户名是否正确的正则表达式
    let reg1 = /^\w{3,20}$/;
    // 检查密码是否正确的正则表达式
    let reg2 = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,10}/;
    // 获取用户名框的值
    let username = document.querySelector('input[name=username]').value;
    // 获取密码框的值
    let password = document.querySelector('input[name=password]').value;
    // 获取第二个用户名框的值
    let username2 = document.querySelector('input[name=username2]').value;
    // 获取注册页面第一个密码框的值
    let password1 = document.querySelector('input[name=password1]').value;
    // 获取注册页面确认密码框的值
    let password2 = document.querySelector('input[name=password2]').value;
    // 获取登录按钮
    let sign_in = document.querySelector('#sign-in');
    // 获取注册按钮
    let register = document.querySelector('#register');
    sign_in.addEventListener('click', () => {
        // 用户名输入错误
        if (!reg1.test(username)) {
            alert('用户名输入错误，用户名是由数字、26个字母或者下划线组成且长度在3到20之间');
            username.value = '';
            return false;
        }
        // 密码输入错误
        if (!reg2.test(password)) {
            alert('密码输入错误，密码是包含大小写字母和数字的组合，不能使用特殊字符，且长度在8-10之间');
            password.value = '';
            return false;
        }
        let data = ajax({
            type: 'post',
            url: 'http://localhost:8080/sessions',
            data: {
                username: username,
                password: password
            },
            header: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            success: function() {

            },
            error: function() {
                alert('密码或用户名错误');
            }
        })
    })
    register.addEventListener('click', () => {
        if (!reg1.test(username2)) {
            alert('用户名输入错误，用户名是由数字、26个字母或者下划线组成且长度在3到20之间');
            username2.value = '';
            return false;
        }
        if (!reg2.test(password1) || !reg2.test(password2)) {
            alert('用户名输入错误，用户名是由数字、26个字母或者下划线组成且长度在3到20之间');
            password1.value = '';
            password2.value = '';
            return false;
        }
        if (password2 != password1) {
            alert('两次输入的密码不一样');
            password2.value = '';
            password1.value = '';
            return false;
        }
    })


})