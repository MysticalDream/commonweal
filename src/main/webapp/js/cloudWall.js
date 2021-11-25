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
    const lgoBack = document.querySelector('.left .goBack');
    const rgoBack = document.querySelector('.right .goBack');
    let pageNum1 = 1;
    let pageNum2 = 1;
    let picList = ['theme.jpg', 'tree.png', 'tuichu.png', 'wu.png', 'wuli.png'];
    let flag = true;
    // 判断是不是第一次滑到左右页面
    let lflag = true;
    let rflag = true;
    // 最大页数
    let lpageNumMax = 1;
    let rpageNumMax = 1;
    /**
     * 节流函数 减少执行次数
     * @param {*} func 业务代码
     * @param {*} delay 延时
     */
    function throttle(func, delay) {
        var flag1 = true;
        return function(e) {
            if (flag1) {
                setTimeout(() => {
                    func.call(this, e);
                    flag1 = true;
                }, delay);
            }
            flag1 = false;
        };
    }

    lwrite.addEventListener('click', () => {

        window.location.href = 'cloudWall3.html?flag=false';
    })
    rwrite.addEventListener('click', () => {

            window.location.href = 'cloudWall2.html?flag=true';
        })
        // 要判断是不是第一页 是第一页的话就不触发

    window.addEventListener('mousewheel', (e) => {
        let eve = e || window.event;
        // 阻止默认行为
        eve.preventDefault();
        // 如果是正常进来的页面
        // console.log(middle.offsetLeft);
        // console.log(left.offsetLeft);
        // console.log(right.offsetLeft);
        if (middle.offsetLeft === 0) {
            if (eve.deltaY > 0) { //向下滚动 左边的盒子 孩子们的愿望
                console.info("向下滚动");
                left.style.left = '0';
                middle.style.left = '100%';
                right.style.left = '200%';
                flag = false;
                if (lflag) {
                    lflag = false;
                    ajax({
                        type: 'get',
                        url: '/wall/list',
                        data: {
                            pageNum: 1,
                            pageSize: 4,
                            flag: flag
                        },
                        header: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        success: function(data) {
                            if (data.code === 200) {
                                console.log(data);
                                lcards.innerHTML += `
                                <li>
                                    <div class="cover">
                                        <img src="${picList[data.data.list[0].cardId]}">
                                    </div>
                                    <div class="back">${data.data.list[0].content}</div>
                                </li>
                                `
                            }
                        }
                    });
                }
            } else { //向上滚动
                console.info("向上滚动");
                left.style.left = '-200%'
                middle.style.left = '-100%';
                right.style.left = '0';
                flag = true;
                if (rflag) {
                    rflag = false;
                    ajax({
                        type: 'get',
                        url: '/wall/list',
                        data: {
                            pageNum: 1,
                            pageSize: 4,
                            flag: flag
                        },
                        header: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        success: function(data) {
                            if (data.code === 200) {
                                console.log(data);
                                rcards.innerHTML += `
                                <li>
                                    <div class="cover">
                                        <img src="${data.data.list[0].cardId}">
                                    </div>
                                    <div class="back">${data.data.list[0].content}</div>
                                </li>
                                `
                            }
                        }
                    });
                }
            }
        }
    }, { passive: false });
    lgoBack.addEventListener('click', () => {
        left.style.left = '-100%';
        middle.style.left = '0';
        right.style.left = '100%';
    })
    rgoBack.addEventListener('click', () => {
        left.style.left = '-100%';
        middle.style.left = '0';
        right.style.left = '100%';
    })
    rbox.addEventListener('mousewheel', throttle(function(e) {
        let evt = e || window.event;
        evt.stopPropagation();
        if (evt.deltaY < 0) { //鼠标滚轮向上滚动
            if (pageNum2++ > rpageNumMax) {
                rpageNumMax = pageNum2;
                // 判断是不是比最大页数大 是的话就发送请求 不是的话就不做处理
                ajax({
                    type: 'get',
                    url: '/wall/list',
                    data: {
                        pageNum: pageNum2,
                        pageSize: 4,
                        flag: flag
                    },
                    header: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    success: function(data) {
                        if (data.code === 200) {
                            console.log(data);
                            for (let k = 0; k < data.data.list.length; k++) {
                                rcards.innerHTML += `
                                <li>
                                    <div class="cover">
                                        <img src="${data.data.list[k].cardId}">
                                    </div>
                                    <div class="back">${data.data.list[k].content}</div>
                                </li>
                                `
                            }
                        }
                    }
                });
            }
            // console.log(rcards.offsetLeft + 100);
            rcards.style.left = rcards.offsetLeft - 1080 + 'px';
        } else { //鼠标滚轮向下滚动
            if (pageNum2 > 1) {
                pageNum2--;
            } else {
                pageNum2 = 1;
            }
            if (rcards.offsetLeft >= 0) {
                rcards.style.left = '0px';
            } else {
                rcards.style.left = rcards.offsetLeft + 1080 + 'px';
            }
        }
    }, 2000))

    lbox.addEventListener('mousewheel', throttle(function(e) {
        let evt = e || window.event;
        evt.stopPropagation();
        if (evt.deltaY < 0) {
            if (pageNum1++ > lpageNumMax) {
                lpageNumMax = pageNum1;
                ajax({
                    type: 'get',
                    url: '/wall/list',
                    data: {
                        pageNum: pageNum1,
                        pageSize: 4,
                        flag: flag
                    },
                    header: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    success: function(data) {
                        if (data.code === 200) {
                            console.log(data);
                            lcards.innerHTML += `
                            <li>
                                <div class="cover">
                                    <img src="${picList[data.data.list[0].cardId]}">
                                </div>
                                <div class="back">${data.data.list[0].content}</div>
                            </li>
                            `
                        }
                    }
                });
            }
            lcards.style.left = lcards.offsetLeft - 1080 + 'px';
        } else {
            if (pageNum1 > 1) {
                pageNum1--;
            } else {
                pageNum1 = 1;
            }
            console.log(lcards.offsetLeft);
            if (lcards.offsetLeft >= 0) {
                lcards.style.left = '0px'
            } else {
                lcards.style.left = lcards.offsetLeft + 1080 + 'px';
            }
        }
    }, 2000))
})