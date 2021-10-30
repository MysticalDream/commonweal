window.addEventListener('load', function() {
    let li_open = document.querySelectorAll('.bottom_left_d>ul>li');
    console.log(li_open.length);
    let show_box = document.querySelectorAll('.slide_ul_d');
    console.log(show_box.length);
    for (let i = 0; i < li_open.length; i++) {
        li_open[i].addEventListener('mouseover', function() {
            for (let j = 0; j < li_open.length; j++) {
                show_box[j].style.width = '0px';
                li_open[j].classList.remove('cur_li');
            }
            li_open[i].classList.add('cur_li');
            show_box[i].style.width = '170px';
        })
        li_open[i].addEventListener('mouseout', function() {
            show_box[i].style.width = '0px';
        })
    }

    let tag_d = document.querySelectorAll('.tag_d');
    let right_ds = document.querySelectorAll('.bottom_right_d');
    for (let i = 0; i < tag_d.length; i++) {
        tag_d[i].addEventListener('click', function() {
            for (let j = 0; j < tag_d.length; j++) {
                fadeOut(right_ds[j]);
                right_ds[j].style.zIndex = -1;
            }
            right_ds[i].style.left = '243px';
            // right_ds[i].style.display='block';
            fadeIn(right_ds[i]);
            right_ds[i].style.zIndex = 1;
            console.log(i);
        })
    }

    // 选中时img和span消失
    let input_d = document.querySelectorAll('.input_out');
    let input_img = document.querySelectorAll('.search_icon_d');
    let input_span = document.querySelectorAll('.search_span_d');

    function show(ele) {
        ele.style.display = 'block';
    }

    function hide(ele) {
        ele.style.display = 'none';
    }

    for (let i = 0; i < input_d.length; i++) {
        input_d[i].onfocus = function() {
            hide(input_img[i]);
            hide(input_span[i]);
        }
        input_d[i].onblur = function() {
            show(input_img[i]);
            show(input_span[i]);
        }
    }

    // 点击搜索框和图片时input选中
    focus_d = function(ele, input) {
        ele.addEventListener('click', function() {
            input.focus();
        })
    }

    for (let i = 0; i < input_d.length; i++) {
        focus_d(input_img[i], input_d[i]);
        focus_d(input_span[i], input_d[i]);
    }

    // 鼠标滑过外层盒子时，里层盒子旋转
    let out_box = this.document.querySelectorAll('.out_box');
    let inner_box = this.document.querySelectorAll('.inner_hide');
    console.log(out_box.length);
    console.log(inner_box.length);
    for (let i = 0; i < out_box.length; i++) {
        out_box[i].addEventListener('mouseover', function() {
                inner_box[i].classList.add('rotate_slowly');
                // inner_box[i].style.transform='rotate(-'+15+'deg)';
            })
            // out_box[i].addEventListener('mouseout',function(){
            //    inner_box[i].style.transform='rotate(-'+0+'deg)';
            // })
    }

    let plus_d = this.document.querySelector('.plus_rota');
    let mask = this.document.querySelector('.mask_s');
    let form_ss = this.document.querySelector('.form_s');
    // 点击加号
    plus_d.addEventListener('click', function() {
        mask.style.display = 'block';
        // 那个框框从上面淡入
        setTimeout(function() {
            form_ss.classList.add('down');
        }, 200)
    })

    // 取消按钮
    let out_d = this.document.querySelector('.put_out');
    out_d.addEventListener('click', function() {
        mask.style.display = 'none';
    })

    // 获取上传文件的那个按钮 和上传文件的框框
    let zero = document.querySelector('.zero');
    let xm_file = document.querySelector('#xm_file');
    zero.addEventListener('click', () => {
        xm_file.click();
    })

    // 点击提交按钮 向后台发送请求
    let put_in = document.querySelector('.put_in');
    // 项目标题
    let itemTitle = this.document.querySelector('#first_text');
    // 项目介绍
    let itemIntroduction = document.querySelector('#fifth_text');
    // 项目封面地址
    let coverUrl;
    // 项目预计进行时间
    let fourth_text = document.querySelector('#fourth_text');
    let xm_time = document.querySelector('#xm_time');
    // let index = xm_time.selectedIndex;
    // 项目分类
    let itemCategory = document.querySelector('#second_select');
    // 最大人数限制
    let maximumNumberLimit = document.querySelector('#second_text');
    // 请求项目封面
    let xm_submit = document.querySelector('#xm_submit');
    xm_file.addEventListener('change', () => {
        iframeAjax({
            form: "#item_cover",
            callback: function(datas) {
                console.log(datas);
                // 拿到图片的路径
                coverUrl = datas.data;
            }
        })
        xm_submit.click();
        let img = document.createElement('img');
        img.src = coverUrl;
        zero.appendChild(img);
    })


    put_in.addEventListener('click', () => {
        // alert(1);
        // console.log(itemTitle.value);
        // console.log(itemIntroduction.value);
        // console.log(fourth_text.value + xm_time.options[xm_time.selectedIndex].text);
        // console.log(itemCategory.options[itemCategory.selectedIndex].text);
        // 打印项目所在省 城市 县区
        // console.log(maximumNumberLimit.value);
        // console.log(infoData.province);
        // console.log(infoData.city);
        // console.log(infoData.area);
        // console.log(coverUrl);
        ajax({
            type: 'post',
            url: '/items',
            data: {
                "itemTitle": itemTitle.value,
                "itemIntroduction": itemIntroduction.value,
                "coverUrl": coverUrl,
                "duration": fourth_text.value + xm_time.options[xm_time.selectedIndex].text,
                "itemCategory": itemCategory.options[itemCategory.selectedIndex].text,
                "maximumNumberLimit": maximumNumberLimit.value,
                "province": infoData.province,
                "city": infoData.city,
                "area": infoData.area
            },
            header: {
                'Content-Type': 'application/json'
            },
            success: function(data) {
                console.log(data);
            },
        })
    })
})