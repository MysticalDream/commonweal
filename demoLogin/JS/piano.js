window.addEventListener('load',function(){
    let piano=this.document.querySelector('.piano');
    let pianoWidth=piano.offsetWidth;
    let li_p=this.document.querySelectorAll('.piano>ul>li');
    let liWidth=li_p[0].offsetWidth;
    let title_small=this.document.querySelectorAll('.title_small');
    let titleWidth=title_small[0].offsetWidth;
    console.log(titleWidth);
    let inner_text_p=this.document.querySelectorAll('.inner_text>p');
    let flex_div=this.document.querySelectorAll('.title_small>div');
    // 手风琴的位置初始化
    for (let i = 0; i < li_p.length; i++) {
        if (i == 0) {
            li_p[i].style.left = 0 + 'px';
        } else {
            li_p[i].style.left = (liWidth + (i - 1) * titleWidth) + 'px';
        }
        li_p[i].style.zIndex = i + 1;
    }

    // 滑动函数
    
    function slideAppear() {
        if (current < aim) {
            for (var i = current; i < aim; i++) {
                li_p[i].style.left = i * titleWidth + 'px';
                li_p[aim].style.left = titleWidth * aim + 'px';
                // title_div[aim].style.display = 'none';
                // introduce[aim].style.display = 'block';
            }
        } else {
            for (var i = current; i > aim; i--) {
               li_p[i].style.left = pianoWidth - (li_p.length - i) * titleWidth + 'px';
                // title_div[aim].style.display = 'none';
                // introduce[aim].style.display = 'block';
            }
        }
        current = aim;
    }

    var current = 0;
    var aim = 0;
    for(let k=0;k<li_p.length;k++){
        li_p[k].index=k;
}
flex_div[0].style.display='none';
inner_text_p[0].style.display='block';
    for (let i = 0; i < li_p.length; i++) {
        // li_p[i].index = i;
        li_p[i].addEventListener('mouseover', function() {
            aim = this.index;
            for (var j = 0; j < flex_div.length; j++) {
                flex_div[j].style.display='flex';
                inner_text_p[j].style.display='none';
            }
           flex_div[this.index].style.display='none';
           inner_text_p[this.index].style.display='block';
            if (current != aim) {
                slideAppear();
            }

        })
    }
})