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
    let picList = ['../../images/cloud24.jpg', '../../images/cloud25.jpg', '../../images/cloud26.jpg', '../../images/cloud27.jpg', '../../images/cloud28.jpg', '../../images/cloud29.jpg', '../../images/cloud30.jpg'];
    let picList2 = ['../../images/cloud1.jpg', '../../images/cloud2.jpg', '../../images/cloud3.jpg', '../../images/cloud4.jpg', '../../images/cloud5.jpg', '../../images/cloud6.jpg', '../../images/cloud7.jpg'];
    let flag = true;
    // 判断是不是第一次滑到左右页面
    let lflag = true;
    let rflag = true;
    // 最大页数
    let lpageNumMax = 1;
    let rpageNumMax = 1;
    let scrolling = false;
    let scrolling2 = false;


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

        window.location.href = 'cloudWall3.html?flag=true';
    })
    rwrite.addEventListener('click', () => {

            window.location.href = 'cloudWall2.html?flag=false';
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
                flag = true;
                if (lflag) { //最大页数 应该是大于等于pages的 如果小于
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
                                for (let k = 0; k < data.data.list.length; k++) {
                                    lcards.innerHTML += `
                                        <li>
                                            <div class="cover">
                                                <img src="${picList2[data.data.list[k].cardId]}">
                                            </div>
                                            <div class="back">
                                                <p>${data.data.list[k].content}</p>
                                                <span>FROM: ${data.data.list[k].signature}</span>
                                            </div>
                                        </li>
                                        `
                                }
                            }
                        }
                    });
                }

            } else { //向上滚动
                console.info("向上滚动");
                left.style.left = '-200%'
                middle.style.left = '-100%';
                right.style.left = '0';
                flag = false;
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
                                for (let k = 0; k < data.data.list.length; k++) {
                                    rcards.innerHTML += `
                                                        <li>
                                                            <div class="cover">
                                                                <img src="${picList[data.data.list[k].cardId]}">
                                                            </div>
                                                            <div class="back">
                                                                <p>${data.data.list[k].content}</p>
                                                                <span>FROM:${data.data.list[k].signature}</span>
                                                            </div>
                                                        </li>
                                                        `
                                }
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
    });
    rgoBack.addEventListener('click', () => {
        left.style.left = '-100%';
        middle.style.left = '0';
        right.style.left = '100%';
    })

    rbox.addEventListener('mousewheel', throttle(function(e) {
        let evt = e || window.event;
        evt.stopPropagation();
        flag = false;
        if (scrolling) {
            return;
        }
        if (evt.deltaY < 0) { //鼠标滚轮向上滚动
            scrolling = true;
            pageNum2--;
            if (pageNum2 == 0) {
                pageNum2 = 1;
                scrolling = false;
                return;
            }
            rcards.style.transform = "translateX(-" + (((pageNum2 - 1) / rpageNumMax) * 100) + "%)";
            scrolling = false;

        } else { //鼠标滚轮向下滚动

            scrolling = true;
            pageNum2++;
            if (rpageNumMax < pageNum2) {
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
                            if (data.data.list.length > 0) {
                                rcards.style.width = pageNum2 * 100 + "%";
                                for (let k = 0; k < data.data.list.length; k++) {
                                    rcards.innerHTML += `
                                                                        <li>
                                                                            <div class="cover">
                                                                                <img src="${picList[data.data.list[k].cardId]}">
                                                                            </div>
                                                                            <div class="back">
                                                                                <p>${data.data.list[k].content}</p>
                                                                                <span>FROM: ${data.data.list[k].signature}</span>
                                                                            </div>
                                                                        </li>
                                                                        `
                                }
                            } else {
                                pageNum2--;
                            }
                            rpageNumMax = pageNum2;
                            rcards.style.transform = "translateX(-" + (((pageNum2 - 1) / rpageNumMax) * 100) + "%)";
                            scrolling = false;
                        }
                    }
                })
            } else {
                rcards.style.transform = "translateX(-" + (((pageNum2 - 1) / rpageNumMax) * 100) + "%)";
                scrolling = false;
            }
        }
    }, 200))

    lbox.addEventListener('mousewheel', throttle(function(e) {
        let evt = e || window.event;
        evt.stopPropagation();
        flag = true;
        if (scrolling2) {
            return
        }
        if (evt.deltaY < 0) { //鼠标滚轮向上滚动
            scrolling2 = true;
            pageNum1--;
            if (pageNum1 == 0) {
                pageNum1 = 1;
                scrolling2 = false;
                return;
            }
            lcards.style.transform = "translateX(-" + (((pageNum1 - 1) / rpageNumMax) * 100) + "%)";
            scrolling2 = false;

        } else { //鼠标滚轮向下滚动

            scrolling2 = true;
            pageNum1++;
            if (lpageNumMax < pageNum1) {
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
                            if (data.data.list.length > 0) {
                                lcards.style.width = pageNum1 * 100 + "%";
                                for (let k = 0; k < data.data.list.length; k++) {
                                    lcards.innerHTML += `
                                                                        <li>
                                                                            <div class="cover">
                                                                                <img src="${picList[data.data.list[k].cardId]}">
                                                                            </div>
                                                                            <div class="back">
                                                                                <p>${data.data.list[k].content}</p>
                                                                                <span>FROM: ${data.data.list[k].signature}</span>
                                                                            </div>
                                                                        </li>
                                                                        `
                                }
                            } else {
                                pageNum1--;
                            }
                            lpageNumMax = pageNum1;
                            lcards.style.transform = "translateX(-" + (((pageNum1 - 1) / lpageNumMax) * 100) + "%)";
                            scrolling2 = false;
                        }
                    }
                })
            } else {
                lcards.style.transform = "translateX(-" + (((pageNum1 - 1) / lpageNumMax) * 100) + "%)";
                scrolling2 = false;
            }
        }
    }, 200))
})