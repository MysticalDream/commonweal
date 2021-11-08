window.addEventListener('load', function () {
    var curIndex = 0;

    function $(ele) {
        return document.querySelectorAll(ele);
    }

    var al = $('.wrap>div');
    var wrap = $('.wrap')[0];
    var left_flag = $('.left_flag')[0];
    var right_flag = $('.right_flag')[0];
    var left_btn = $('.creat_project')[0];
    var right_btn = $('.join_team')[0];
    var nd_p = $('.nd_p')[0];
    var show_rd = $('.show_rd>div');
    var recu = $('.recruitment_nd')[0];
    var pic = $('.pic')[0];
    // var h3=$('.right>h3')[0];
    // var rp=$('.right>p')[0];
    var r_btn = $('.right>button')[0];
    var circle = $('.circle');
    var ad = $('.adopt_rd')[0];
    var btn_item = $('.btn_items>button');
    var juxing = $('.rectangle');
    var btn_ul = $('.children_rd>ul>li');
    wrap.addEventListener('wheel', mo);

    //导航
    var navDivs = $('.menu-item');
    //设置下标
    Array.from(navDivs).forEach((e, index) => {
        e.addEventListener("click", () => {
            curIndex = index;
            wrap.className = 'wrap active' + curIndex;
            // wrap.dispatchEvent(new Event("wheel",{bubbles: true,cancelable: false}));
        });

    });

    function mo(e) {
        e.deltaY < 0 ? up() : down();
        go();
        //移除监听 避免频繁触发
        wrap.removeEventListener('wheel', mo);
        setTimeout(function () {
            wrap.addEventListener('wheel', mo)
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
        var a, b, c, d;
        if (curIndex == 1) {
            a = setTimeout(function () {
                nd_p.classList.add('btn_up');
            }, 1000);
            b = setTimeout(function () {
                left_btn.classList.add('btn_up');
            }, 1500);
            c = setTimeout(function () {
                right_btn.classList.add('btn_up');
            }, 1800);
        }

        if (curIndex == 2) {
            clearTimeout(a), clearTimeout(b), clearTimeout(c);
            a = setTimeout(function () {
                left_flag.classList.add('flag_up');
            }, 1000)
            b = setTimeout(function () {
                right_flag.classList.add('right_up');
            }, 1500)
        }
        if (curIndex == 3) {
            clearTimeout(a), clearTimeout(b);
            a = setTimeout(function () {
                show_rd[0].classList.add('btn_up');
            }, 1000)
            b = setTimeout(function () {
                show_rd[1].classList.add('btn_up');
            }, 1200)
            c = setTimeout(function () {
                show_rd[2].classList.add('btn_up');
            }, 1400)
        }
        if (curIndex == 4) {
            clearTimeout(a), clearTimeout(b), clearTimeout(c);
            a = setTimeout(function () {
                recu.classList.add('btn_up');
            }, 1000)
            b = setTimeout(function () {
                pic.classList.add('left_come');
            }, 1300)
            c = setTimeout(function () {
                r_btn.classList.add('btn_up');
            }, 1600)
        }
        if (curIndex == 5) {
            clearTimeout(a), clearTimeout(b), clearTimeout(c);
            a = setTimeout(function () {
                circle[0].classList.add('btn_up');
            }, 1000)
            b = setTimeout(function () {
                circle[1].classList.add('btn_up');
            }, 1200)
            c = setTimeout(function () {
                juxing[0].classList.add('btn_up');
            }, 1000)
            d = setTimeout(function () {
                juxing[1].classList.add('btn_up');
            }, 1200)
        }
        if (curIndex == 6) {
            clearTimeout(a), clearTimeout(b), clearTimeout(c), clearTimeout(d);
            a = setTimeout(function () {
                ad.classList.add('btn_up');
            }, 1000)
        }
        if (curIndex == 7) {
            clearTimeout(a);
            a = setTimeout(function () {
                btn_item[0].classList.add('btn_up');
                btn_item[1].classList.add('btn_up');
                btn_item[2].classList.add('btn_up');
            }, 1000)
            b = setTimeout(function () {
                btn_ul[0].classList.add('btn_up');
                btn_ul[1].classList.add('btn_up');
                btn_ul[2].classList.add('btn_up');
                btn_ul[3].classList.add('btn_up');
            }, 1400)
        }
       // clearTimeout(a), clearTimeout(b);
    };
})
