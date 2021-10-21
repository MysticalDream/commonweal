window.addEventListener('load',function(){
    var curIndex = 0;
    function $(ele) {
        return document.querySelectorAll(ele);
    }
    var al = $('.wrap>div');
    var wrap=$('.wrap')[0];
    var left_flag=$('.left_flag')[0];
    var right_flag=$('.right_flag')[0];
    var left_btn=$('.creat_project')[0];
    var right_btn=$('.join_team')[0];
    var nd_p=$('.nd_p')[0];
    var show_rd=$('.show_rd>div');
    var recu=$('.recruitment_nd')[0];
    var pic=$('.pic')[0];
    // var h3=$('.right>h3')[0];
    // var rp=$('.right>p')[0];
    var r_btn=$('.right>button')[0];
    // var circle=$('.circle');
    var ad=$('.adopt_rd')[0];
    // var btn_item=$('.btn_items');
    wrap.addEventListener('mousewheel', mo);
    function mo(e) {
        e.deltaY < 0 ? up(): down();
        go();
        //移除监听 避免频繁触发
        wrap.removeEventListener('mousewheel', mo);
        setTimeout(function () { 
            wrap.addEventListener('mousewheel', mo) 
        }, 800);
        
    };
    function up() {
        curIndex = --curIndex < 0 ? 0 : curIndex;

    }
    function down() {
        curIndex = ++curIndex == al.length ? al.length - 1 : curIndex;
    }
    function go() {
        wrap.className = 'wrap active' + curIndex;
        if(curIndex==1){
            setTimeout(function(){
               nd_p.classList.add('btn_up');
            },1000)
            setTimeout(function(){
                left_btn.classList.add('btn_up');
            },1500)
            setTimeout(function(){
                right_btn.classList.add('btn_up');
            },1800)
        }
        if(curIndex==2){
            for(let i=0;i<2;i++){
                clearTimeout(i);
            }
            setTimeout(function(){
                left_flag.classList.add('flag_up');
            },1000)
            // 这里处理成3秒 也许就有错落的效果
            setTimeout(function(){
                right_flag.classList.add('right_up');
            },1500)
        }
        if(curIndex==3){
            for(let i=0;i<1;i++){
                clearTimeout(i);
            }
            setTimeout(function(){
               show_rd[0].classList.add('btn_up');
            },1000)
            setTimeout(function(){
                show_rd[1].classList.add('btn_up');
            },1200)
            setTimeout(function(){
                show_rd[2].classList.add('btn_up');
            },1400)
        }
        if(curIndex==4){
            for(let i=0;i<2;i++){
                clearTimeout(i)
            }
            setTimeout(function(){
                recu.classList.add('btn_up');
            },1000)
            setTimeout(function(){
                pic.classList.add('left_come');
             },1300)
             setTimeout(function(){
                r_btn.classList.add('btn_up');
             },1600)
        }
        // if(curIndex==5){
        //     for(let i=0;i<2;i++){
        //         clearTimeout(i)
        //     }
        //     setTimeout(function(){
        //         circle[0].classList.add('left_come');
        //     },1000)
        //     setTimeout(function(){
        //         circle[1].classList.add('left_come');
        //     },1000)
        // }
        if(curIndex==6){
            for(let i=0;i<2;i++){
                clearTimeout(i)
            }
            setTimeout(function(){
               ad.classList.add('btn_up');
            },1000)
        }
    };
})
    