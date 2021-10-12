window.addEventListener('load',function(){
    let box_dd=document.querySelector('.img_box_d');
    let text_dd=box_dd.querySelectorAll('div>h3');
    console.log(text_dd.length);
    let btn_l_d=document.querySelector('.btn-l_d');
    let btn_r_d=document.querySelector('.btn-r_d');
    // 旋转角度
    let ang=0;
    // 设置轮播定时器(自动轮播是向右转)
    let timer_d=setInterval(fun,4000);
    // 左点击事件
    btn_l_d.onclick=function(){
        ang=ang-45;
        // 模板字符串
        box_dd.style.cssText=`transform:rotateY(${ang}deg);`;
    }
    // 右点击
    btn_r_d.addEventListener('click',fun);
    function fun(){
        ang=ang+45;
        box_dd.style.cssText=`transform:rotateY(${ang}deg);`;
    }
    // 很重要的一点就是：鼠标经过按钮和图片时要停止定时器，离开图片时开启定时器
    btn_l_d.addEventListener('mouseover',function(){
        clearInterval(timer_d);
    })
    btn_l_d.addEventListener('mouseout',function(){
        timer_d=setInterval(fun,4000);
    })
    btn_r_d.addEventListener('mouseover',function(){
        clearInterval(timer_d);
    })
    btn_r_d.addEventListener('mouseout',function(){
        timer_d=setInterval(fun,4000);
    })
    box_dd.addEventListener('mouseover',function(){
        clearInterval(timer_d);
        // this.style.cssText=`transform:rotateZ(${500})px;`;
    })
    box_dd.addEventListener('mouseout',function(){
        timer_d=setInterval(fun,4000);
    })
    // for(var i=0;i<text_dd.length;i++){
    //     if(box_dd[i].style.cssText==`transform:rotateY(${0}deg);`){
    //         text_dd[i].style.display='block';
    //     }
    // }

    // 可以来一个滑过这个板块财开启定时器，而窗口的滚动距离若不在此范围内则关闭定时器 
})