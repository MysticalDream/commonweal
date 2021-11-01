window.addEventListener('load',()=>{
    function $(ele) {
        return document.querySelectorAll(ele);
    }
    let move_d=$('.move_box')[0];
    let pics=$('.move_box>img');
    for(let i=0;i<pics.length;i++){
        pics[i].style.top=i*window.innerHeight+'px';
    }
    // var count=1;
    //  var timerUp=setInterval(function(){
    //     move_d.classList.add('move_up'+count);
    //     count++;
    //     if(count==pics.length-1){
    //         count=0;
    //     }
    //     move_d.classList.remove('move_up'+count);
    // },7000)
})