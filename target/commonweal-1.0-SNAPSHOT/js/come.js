// 登录
const signInBtn = document.getElementById("signIn");
// 注册
const signUpBtn = document.getElementById("signUp");
// 注册页面
const fistForm = document.getElementById("form1");
const signUp = fistForm.querySelector('.btn');
const username = fistForm.querySelector('.username');
const area = fistForm.querySelector('.area');
const phone = fistForm.querySelector('.phone');
const pwd = fistForm.querySelector('.pwd');
const pwd3 = fistForm.querySelector('pwd3');
// 登录页面
const secondForm = document.getElementById("form2");
const signIn = secondForm.querySelector('.btn');
const username2 = secondForm.querySelector('.username2');
const pwd2 = secondForm.querySelector('.pwd2');
// 大盒子
const container = document.querySelector(".container");
// 检查用户名是否正确的正则表达式
const reg1 = /^\w{3,20}$/;
// 检查密码是否正确的正则表达式
const reg2 = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]){8,10}/;
// 检查电话是否正确的正则表达式
const reg3 = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$/;

signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});
// 阻止默认事件
fistForm.addEventListener("submit", (e) => e.preventDefault());
secondForm.addEventListener("submit", (e) => e.preventDefault());
// 点击注册按钮
signUp.addEventListener('click', () => {
    // 先用正则表达式判断用户名 地区 电话 密码是否合法
    if (!reg1.test(username.value)) {
        alert("用户名格式错误");
        return false;
    }
    if (!reg2.test(pwd.value) || !reg2.test(pwd3.value)) {
        alert("密码格式错误");
        return false;
    }
    if (!reg3.test(phone.value)) {
        alert("电话格式错误");
        return false;
    }
    // 执行到这里说明格式是正确的 这个时候可以将数据传给后台了
    ajax({
        type: "get",
        url: "/tokens/signup",
        success: function (response) {
            ajax({
                type: "post",
                url: "/users",
                data: {
                    "username": username.value,
                    "password1": pwd.value,
                    "password2": pwd3.value,
                    "tel": phone.value,
                    "location": area.value
                },
                header: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    success: function (datas) {
                        console.log(datas);
                    }
                }
            })
        }
    });
})
// 点击登录按钮
signIn.addEventListener('click', () => {
    // 先用正则表达式判断用户名 密码是否符合格式
    // alert(1);
    if (!reg1.test(username2.value)) {
        alert('用户名输入不合法');
        return false;
    }
    if (!reg2.test(pwd2.value)) {
        alert('密码输入不合法');
        return false;
    }
    // 折行到这里说明格式是正确的 我们现在吧数据传给后端 判断该用户是否存在
    ajax({
        type: "post",
        url: "/sessions",
        data: {
            "username": username2.value,
            "password": pwd2.value
        },
        header: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        success: function (datas) {
            // // 将数据存储到cookie中
            // setCookie("username", datas.data.username);
            // setCookie("userId", datas.data.userId);
            // setCookie("tel", datas.data.tel);
            // setCookie("avatarUrl", datas.data.avatarUrl);
            // setCookie("location", datas.data.location);
            if (datas.success) {
                window.location.replace("/");
            } else {
                alert(datas.message);
            }
        }
    })
})