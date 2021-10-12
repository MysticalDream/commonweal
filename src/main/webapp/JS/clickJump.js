window.addEventListener('load', function() {
    //志愿项目
    let part_d = this.document.querySelectorAll('#part');
    let ul_d = this.document.querySelector('.side_ul_d');
    let li_d = ul_d.querySelectorAll('.li_d');
    let toTop = ul_d.querySelector('.li_last');
    for (let i = 0; i < li_d.length; i++) {
        li_d[i].addEventListener('click', function() {
            window.scrollTo({
                top: part_d[i].offsetTop,
                behavior: 'smooth'
            })
        })
    }

    toTop.onclick = function() {
        animateY(window, 0);
    }
})