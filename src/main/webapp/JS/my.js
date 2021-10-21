window.addEventListener('load',function(){
    let li_open=document.querySelectorAll('.bottom_left_d>ul>li');
    console.log(li_open.length);
    let show_box=document.querySelectorAll('.slide_ul_d');
    console.log(show_box.length);
    for(let i=0;i<li_open.length;i++){
        li_open[i].addEventListener('mouseover',function(){
            for(let j=0;j<li_open.length;j++){
                show_box[j].style.width='0px';
                li_open[j].classList.remove('cur_li');
            }
            li_open[i].classList.add('cur_li');
            show_box[i].style.width='170px';
        })
        li_open[i].addEventListener('mouseout',function(){
            show_box[i].style.width='0px';
        })
    }

    let tag_d=document.querySelectorAll('.tag_d');
    let right_ds=document.querySelectorAll('.bottom_right_d');
    for(let i=0;i<tag_d.length;i++){
        tag_d[i].addEventListener('click',function(){
            for(let j=0;j<tag_d.length;j++){
                fadeOut(right_ds[j]);
                right_ds[j].style.zIndex=-1;
            }
            right_ds[i].style.left='243px';
            // right_ds[i].style.display='block';
            fadeIn(right_ds[i]);
            right_ds[i].style.zIndex=1;
            console.log(i);
        })
    }

    // 选中时img和span消失
    let input_d=document.querySelectorAll('.input_out');
    let input_img=document.querySelectorAll('.search_icon_d');
    let input_span=document.querySelectorAll('.search_span_d');
    function show(ele){
        ele.style.display='block';
    }

    function hide(ele){
        ele.style.display='none';
    }

    for(let i=0;i<input_d.length;i++){
        input_d[i].onfocus=function(){
            hide(input_img[i]);
            hide(input_span[i]);
        }
        input_d[i].onblur=function(){
            show(input_img[i]);
            show(input_span[i]);
        }
    }

    // 点击搜索框和图片时input选中
    focus_d=function(ele,input){
        ele.addEventListener('click',function(){
            input.focus();
        })
    }

    for(let i=0;i<input_d.length;i++){
        focus_d(input_img[i],input_d[i]);
        focus_d(input_span[i],input_d[i]);
    }

    // 鼠标滑过外层盒子时，里层盒子旋转
    let out_box=this.document.querySelectorAll('.out_box');
    let inner_box=this.document.querySelectorAll('.inner_hide');
    console.log(out_box.length);
    console.log(inner_box.length);
    for(let i=0;i<out_box.length;i++){
        out_box[i].addEventListener('mouseover',function(){
            inner_box[i].style.transform='rotate(-'+15+'deg)';
        })
        out_box[i].addEventListener('mouseout',function(){
            inner_box[i].style.transform='rotate(-'+0+'deg)';
        })
    }
})