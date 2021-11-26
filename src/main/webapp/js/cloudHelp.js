window.addEventListener('load', () => {
    const slideshowUl = document.querySelector('.slideshowUl');
    const next = document.querySelector('.next');
    const pre = document.querySelector('.pre');
    const point = document.querySelector('.point').children;
    const slideshow = document.querySelector('.slideshow');
    let index = 0;
    let time;

    function position() {
        slideshowUl.style.left = (index * -100) + '%';
    }

    function add() {
        if (index >= point.length - 1) {
            index = 0;
        } else {
            index++;
        }
    }

    function desc() {
        if (index < 1) {
            index = point.length - 1;
        } else {
            index--;
        }
    }

    function timer() {
        time = setInterval(() => {
            index++;
            desc();
            add();
            position();
            for (let j = 0; j < point.length; j++) {
                point[j].classList.remove('choice');
            }
            point[index].classList.add('choice');
        }, 3000);
    }
    pre.addEventListener('click', () => {
        desc();
        position();
        clearInterval(time);
        timer();
        // for (let j = 0; j < point.length; j++) {
        //     point[j].classList.remove('choice');
        // }
        // point[i].classList.add('choice');
    })
    next.addEventListener('click', () => {
        add();
        position();
        clearInterval(time);
        timer();
        // for (let j = 0; j < point.length; j++) {
        //     point[j].classList.remove('choice');
        // }
        // point[i].classList.add('choice');
    })
    for (let i = 0; i < point.length; i++) {
        point[i].addEventListener('click', () => {
            for (let j = 0; j < point.length; j++) {
                point[j].classList.remove('choice');
            }
            point[i].classList.add('choice');
            index = i;
            position();
            clearInterval(time);
            timer();
        })
    }
    timer();
    slideshow.addEventListener('mouseover', () => {
        clearInterval(time);
    })
    slideshow.addEventListener('mouseleave', () => {
        timer();
    })
})