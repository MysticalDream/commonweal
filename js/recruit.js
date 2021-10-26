window.addEventListener('load', () => {
    // 获取大照片的线
    let left_line = document.querySelector('.big_pic_left_line');
    let right_line = document.querySelector('.big_pic_right_line');
    setTimeout(() => {
        left_line.style.left = '295px';
        left_line.style.opacity = '1';
        right_line.style.right = '295px';
        right_line.style.opacity = '1';
    }, 1000)
})