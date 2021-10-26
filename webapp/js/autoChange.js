window.addEventListener('load',function(){
    function $(ele) {
        return document.querySelectorAll(ele);
    }
    let imgs_d=$('.move_box>img');
    for(let j=0;j<imgs_d.length;j++){
        imgs_d[j].style.top='0px';
    }
    // let timeContin=true;
    let number = 0;
        let timer = setInterval(function() {
            // if (timeContin==true) {
                for (let j = 0; j < imgs_d.length; j++) {
                    // imgs_d[j].style.display='none';
                    // fadeOut(imgs_d[j],5);
                    imgs_d[j].style.opacity='0';
                    imgs_d[j].classList.remove('fadeIn');
                    // imgs_d[j].style.display='none';
                    imgs_d[j].style.zIndex=1;
                }
                // fadeIn(imgs_d[number],5);
                imgs_d[number].classList.add('fadeIn');
                imgs_d[number].style.opacity='1';
                // imgs_d[number].style.display='block';
                imgs_d[number].style.zIndex=2;
                number++;
                if (number == imgs_d.length) {
                    number = 0;
                }
            // }
        }, 5000)
})