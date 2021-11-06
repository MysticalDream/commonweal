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
    setInterval(function() {
        // if (timeContin==true) {
        for (let j = 0; j < imgs_d.length; j++) {
            imgs_d[j].style.display='none';
        }
        imgs_d[number].style.display='block';
        number++;
        if (number == imgs_d.length) {
            number = 0;
        }
        // }
    }, 5000);
})