window.addEventListener('load', () => {
    // 获取那个小圆圈 点击了就背景颜色就变成红色
    let circle = document.querySelector('.circle');
    circle.addEventListener('click', () => {
        // 判断一下 有一个类 就改变一下样式 没有就不改
        if (circle.classList.contains('spc')) {
            circle.classList.remove('spc');
        } else {
            circle.classList.add('spc');
        }
    })
})