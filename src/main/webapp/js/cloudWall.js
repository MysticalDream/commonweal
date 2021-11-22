window.addEventListener('load', () => {
    const rwrite = document.querySelector('.rwrite');
    const lwrite = document.querySelector('.lwrite');
    const middle = document.querySelector('.wrap .middle');
    const left = document.querySelector('.wrap .left');
    const right = document.querySelector('.wrap .right');
    const rbox = document.querySelector('.right .box');
    const rcards = document.querySelector('.right .cards');
    const lbox = document.querySelector('.left .box');
    const lcards = document.querySelector('.left .cards');
    rwrite.addEventListener('click', () => {
        window.location.href = 'cloudWall2.html';
    })
    lwrite.addEventListener('click', () => {
        window.location.href = 'cloudWall2.html';
    })
    window.addEventListener('mousewheel', (e) => {
        let eve = e || window.event;
        // 阻止默认行为
        eve.preventDefault();
        // 如果是正常进来的页面
        // console.log(middle.offsetLeft);
        // console.log(left.offsetLeft);
        // console.log(right.offsetLeft);
        if (middle.offsetLeft === 0) {
            if (eve.deltaY > 0) { //向下滚动
                console.info("向下滚动");
                left.style.left = '0';
                middle.style.left = '100%';
                right.style.left = '200%';
            } else { //向上滚动
                console.info("向上滚动");
                left.style.left = '-200%'
                middle.style.left = '-100%';
                right.style.left = '0';
            }
        } else if (left.offsetLeft === 0) { //这个时候展示左边的页面
            if (eve.deltaY < 0) {
                left.style.left = '-100%';
                middle.style.left = '0';
                right.style.left = '100%'
            }
        } else {
            if (eve.deltaY > 0) { //这个时候展示右边的页面
                left.style.left = '-100%';
                middle.style.left = '0';
                right.style.left = '100%';
            }
        }
    }, { passive: false });
    rbox.addEventListener('mousewheel', (e) => {
        let evt = e || window.event;
        evt.stopPropagation();
        if (evt.deltaY < 0) { //鼠标滚轮向上滚动
            console.log(rcards.offsetLeft + 5);
            rcards.style.left = rcards.offsetLeft - 100 + 'px';
        } else { //鼠标滚轮向下滚动
            console.log(2);
            if (rcards.offsetLeft >= 0) {
                rcards.style.left = '0px';
            } else {
                rcards.style.left = rcards.offsetLeft + 100 + 'px';
            }
        }
    })
    lbox.addEventListener('mousewheel', (e) => {
        let evt = e || window.event;
        evt.stopPropagation();
        if (evt.deltaY < 0) {
            lcards.style.left = lcards.offsetLeft - 100 + 'px';
        } else {
            console.log(lcards.offsetLeft);
            if (lcards.offsetLeft >= 0) {
                lcards.style.left = '0px'
            } else {
                lcards.style.left = lcards.offsetLeft + 100 + 'px';
            }
        }
    })
})