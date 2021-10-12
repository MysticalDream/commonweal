window.onload = function() {
    'use strict'
    // 获取大容器
    var rotation_s = document.querySelector('.rotation_s');
    // 获取左右按钮
    var next = document.querySelector('.r_arrow_s');
    var prev = document.querySelector('.l_arrow_s');
    var middle = document.querySelector('.middle');
    var left = document.querySelector('.left');
    var right = document.querySelectorAll('.right');
    var right1 = right[0];
    var right2 = right[1];
    // 获取四个装着照片的盒子
    var ro_contain_s = document.querySelector('.ro_contain_s');
    var ro_contains_s = ro_contain_s.querySelectorAll('div');
    var num = 1;
    var love_s = document.querySelector('.love_s');
    var love_s_lis = love_s.querySelectorAll('li');
    // 给li添加类名
    // 遍历四个盒子
    for (var i = 0; i < ro_contains_s.length; i++) {
        ro_contains_s[i].index = i;
        love_s_lis[i].index = i;
    }
    // 给小爱心改变颜色的函数
    function resetColor(any) {
        for (var i = 0; i < love_s_lis.length; i++) {
            love_s_lis[i].style.color = 'rgba(255, 255, 255, 0.6)';
        }
        love_s_lis[any].style.color = '#E85656';
    }
    // 节流函数 参数一表示节流的方法 参数二表示人为定义的节流的时间
    function throttle(fn, wait) {
        let task = null;
        return function() {
            if (!task) {
                console.log('设置了task定时器');
                task = setTimeout(() => {
                    task = null;
                    fn.apply(this, arguments);
                }, wait);
            }
        }
    }
    // 清除middle类

    next.addEventListener('click', function() {
        if (num < ro_contains_s.length) {
            // 下一张照片索引
            var next_p = middle.index + 1;
            ro_contains_s[middle.index].className = 'left';
            // animate(ro_contains_s[middle.index], -820);
            // ro_contains_s[middle.index].style.left = '-820px';
            ro_contains_s[next_p].className = 'middle';
            animate(ro_contains_s[next_p], 0);
            // ro_contains_s[next_p].style.left = '0px';
            middle.index = next_p;
            num++;
        } else {
            // 这个时候num为3 把num = 1 还原成一开始的状态 这个时候的middle的index就要设置为0然后把他的classname设为middle
            // 先把所有的类改为放在右边 偷偷把第一张的left设置为-820px
            for (var i = 0; i < ro_contains_s.length; i++) {
                ro_contains_s[i].className = 'right';
                // animate(ro_contains_s[i], 820);
                // ro_contains_s[i].style.left = '820px';
            }
            middle.index = 0;
            ro_contains_s[middle.index].className = 'middle';
            // animate(ro_contains_s[middle.index], 0);
            // ro_contains_s[middle.index].style.left = '0px';
            num = 1;
        }
        resetColor(middle.index);
    });
    prev.addEventListener('click', function() {
        if (num > 1) {
            // 点击完这个按钮 有middle类的盒子的类变成right 然后middle的索引号减一的盒子的类变成middle
            var prev_p = middle.index - 1;
            ro_contains_s[middle.index].className = 'right';
            // animate(ro_contains_s[middle.index], 820);
            // ro_contains_s[middle.index].style.left = '820px';
            ro_contains_s[prev_p].className = 'middle';
            // animate(ro_contains_s[prev_p], 0);
            // ro_contains_s[prev_p].style.left = '0px';
            middle.index = prev_p;
            num--;
        } else if (num == 1) {
            // 如果num==1的话 就是在图片的第一张 这个时候需要把最后一张的类变为middle 把其他三张的类变成left 
            for (var i = 0; i < ro_contains_s.length; i++) {
                ro_contains_s[i].className = 'left';
                // animate(ro_contains_s[i], -820);
                // ro_contains_s[i].style.left = '-820px';
            }
            ro_contains_s[ro_contains_s.length - 1].className = 'middle';
            // animate(ro_contains_s[ro_contains_s.length - 1], 0);
            // ro_contains_s[ro_contains_s.length - 1].style.left = '0px';
            num = ro_contains_s.length;
            middle.index = ro_contains_s.length - 1;
        }
        resetColor(middle.index);
    });
    // 给所有小爱心添加点击事件
    for (var i = 0; i < love_s_lis.length; i++) {
        love_s_lis[i].addEventListener('click', function() {
            // 获取小爱心的索引 然后0到索引减1的盒子的类名是left 索引加1到长度减1的索引的类名是right 索引的类名是middle num的值为索引加1
            var loveIndex = this.index;
            for (var j = 0; j < loveIndex; j++) {
                ro_contains_s[j].className = 'left';
                // animate(ro_contains_s[j], -820);
                // ro_contains_s[j].style.left = '-820px';
            }
            for (var k = loveIndex + 1; k < love_s_lis.length; k++) {
                ro_contains_s[k].className = 'right';
                // animate(ro_contains_s[k], 820);
                // ro_contains_s[k].style.left = '820px';
            }
            ro_contains_s[loveIndex].className = 'middle';
            // animate(ro_contains_s[loveIndex], 0);
            // ro_contains_s[loveIndex].style.left = '0px';
            num = num + 1;
            resetColor(loveIndex);
        });
    }
    // 鼠标经过就停止计时器 鼠标离开就开启计时器
    // var timer1 = setInterval(function() {
    //     next.click();
    // }, 1000);
    // rotation_s.addEventListener('mouseleave', function() {
    //     timer1 = setInterval(function() {
    //         next.click();
    //     }, 1000);
    // })
    // rotation_s.addEventListener('mouseenter', function() {
    //     clearInterval(timer1);
    // });





    // 点击的时候获取当前照片的index值
    // 点击下一个的按钮 当前照片的类变成left 下一张照片(index++)的类变成middle
    // 点击上一个的按钮 当前照片的类变成right 上一张照片(index--)的类变成middle
    // 向左滑动的效果

}