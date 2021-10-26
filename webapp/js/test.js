window.addEventListener('load', () => {
    window.addEventListener('scroll', () => {
        // 获取的是浏览器可见区域高度（不滚动的情况下网页的可视区域的高度）
        // let dch = document.documentElement.clientHeight || window.innerHeight;
        let line = document.querySelectorAll('.line');
        let left_line = document.querySelectorAll('.left_line');
        let middle_line = document.querySelectorAll('.middle_line');
        let right_line = document.querySelectorAll('.right_line');
        // 元素顶端到可见区域顶端的距离
        for (let j = 0; j < line.length; j++) {
            let hect = line[j].getBoundingClientRect().top;
            if (hect <= 200) {

                left_line[j].style.left = '0px';
                middle_line[j].style.left = '80px';
                right_line[j].style.left = '235px';
                left_line[j].style.opacity = '1';
                middle_line[j].style.opacity = '1';
                right_line[j].style.opacity = '1';
            }
        }


    })
})