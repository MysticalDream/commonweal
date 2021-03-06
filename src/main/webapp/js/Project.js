/**
 *
 * @param {*} options 当前翻页实例的数据
 *             currentPage 当前页码
 *             allPage  总页码
 *             wrap    当前的翻页实例插入的位置
 *             changePage 切换页码的回调函数
 */
function TurnPage(options) {
    // 当前页码
    this.currentPage = options.currentPage || 1;
    // 总的页码
    this.allPage = options.allPage || 1;
    // 要插入的地方
    this.wrap = options.wrap || document.body;
    // 回调函数 方便外部使用内部东西
    this.changePage = options.changePage || function () {
    };
}

// 初始化效果的方法
TurnPage.prototype.init = function () {
    this.fillHTML();
    this.bindEvent();
}
// 填充翻页的结构 添加一个if语句过滤一下按钮按了后触发的bug
TurnPage.prototype.fillHTML = function () {
    // 每次调用都将wrap里面的内容置空
    this.wrap.innerHTML = '';
    var turnPageWrapper = document.createElement('ul');
    turnPageWrapper.className = 'turn-page-wrapper';
    // 添加第一页按钮 当前页码数大于1才会添加上一页这个元素
    if (this.currentPage > 1) {
        var oLi = document.createElement('li');
        oLi.innerText = '<';
        oLi.className = 'prev';
        turnPageWrapper.appendChild(oLi);
    }
    // 添加第一页按钮
    var oLi = document.createElement('li');
    oLi.innerText = '1';
    oLi.className = 'num';
    if (this.currentPage == 1) {
        oLi.classList.add('current-page');
    }
    turnPageWrapper.appendChild(oLi);
    // 插入前面的省略号
    if (this.currentPage - 2 > 2) {
        var oSpan = document.createElement('span');
        oSpan.innerText = '...';
        turnPageWrapper.appendChild(oSpan);
    }
    // 创建中间的五页
    for (let i = this.currentPage - 2; i <= this.currentPage + 2; i++) {

        if (i > 1 && i < this.allPage) {
            let oLi = document.createElement('li');
            oLi.innerText = i;
            oLi.className = 'num';
            if (this.currentPage == i) {
                oLi.classList.add('current-page');
            }
            turnPageWrapper.appendChild(oLi);
        }
    }
    // 添加后面的省略号
    if (this.currentPage < this.allPage - 2) {
        let oSpan = document.createElement('span');
        oSpan.innerText = '...';
        turnPageWrapper.appendChild(oSpan);
    }
    // 添加最后一页
    if (this.allPage != 1) {
        let lastLi = document.createElement('li');
        lastLi.innerText = this.allPage;
        lastLi.className = 'num';
        if (this.currentPage == this.allPage) {
            lastLi.classList.add('current-page');
        }
        turnPageWrapper.appendChild(lastLi);
    }
    // 添加下一页
    if (this.currentPage < this.allPage) {
        let oLi = document.createElement('li');
        oLi.innerText = '>';
        oLi.className = 'next';
        turnPageWrapper.appendChild(oLi);
    }
    // 添加跳转到的板块
    var turn_to = document.createElement('span');
    turn_to.innerText = '跳转到';
    var turn_to_text = document.createElement('input');
    turn_to_text.classList.add('turn_to');
    var turn_to_page = document.createElement('span');
    turn_to_page.innerText = '页';
    // 添加一个按钮 点击该按钮 跳转到指定页数
    var turn_to_btn = document.createElement('button');
    turn_to_btn.innerText = '确定';
    turn_to_btn.classList.add('turn_to_btn');
    turnPageWrapper.appendChild(turn_to);
    turnPageWrapper.appendChild(turn_to_text);
    turnPageWrapper.appendChild(turn_to_page);
    turnPageWrapper.appendChild(turn_to_btn);
    this.wrap.appendChild(turnPageWrapper);
}
TurnPage.prototype.bindEvent = function () {
    var self = this;
    // 给当前翻页区绑定事件
    this.wrap.onclick = e => {
        // 点击上一页
        if (e.target.classList.contains('prev')) {
            self.currentPage--;
            self.fillHTML();
            self.changePage(self.currentPage);
        } else if (e.target.classList.contains('next')) {
            self.currentPage++;
            self.fillHTML();
            self.changePage(self.currentPage);
        } else if (e.target.classList.contains('num')) {
            let number = parseInt(e.target.innerText);
            self.currentPage = number;
            // console.log(number);
            self.fillHTML();
            self.changePage(self.currentPage);
        } else if (e.target.classList.contains('turn_to_btn')) {
            let turn_to = document.querySelector('.turn_to');
            // let turn_to_1=document.querySelectorAll('.turn_to')[1];
            let number = parseInt(turn_to.value);
            // 要判断里面的元素是不是可以用的
            if (!number == '') {
                console.log(number);
                self.currentPage = number;
                self.fillHTML();
                self.changePage(self.currentPage)
                turn_to.value = '';
            }

        }
    }
    // 给按钮绑定点击事件 还要判断一下 省略号的情况
    // let btn = document.querySelector('.turn_to_btn');
    // let turn_to = document.querySelector('.turn_to');
    // btn.addEventListener('click', (e) => {
    //     alert(1);
    //     e.stopPropagation();
    //     self.currentPage = turn_to.value;
    //     self.fillHTML();
    //     self.changePage(self.currentPage);
    //     console.log(turn_to.value);
    // })
}


window.addEventListener('load', function () {

    // var seek_opt={}

    function $(ele) {
        return document.querySelectorAll(ele);
    }

    let sele_wrapper = $('.sele_wrapper')[0];
    let province_d = $('.province_d')[0];
    // let people_d=$('.people_d')[0];
    let theme_d = $('.theme_d')[0];
    let city;
    let obj_d;
    let move_span;
    let city_div;
    let arr_1 = new Array();
    let arr_2 = new Array();
    city={
        type:'get',
        url:'/js/data.json',
        data:{
          
        },
        success:function(data){
            // if(data.success)
            obj_d=data;
            // 省市的渲染
            for(let i=0;i<obj_d.length;i++){
                // 省部分
                move_span=document.createElement('span');
                move_span.innerText=obj_d[i].name;
                // 渲染时把属性也加上
                move_span.province=obj_d[i].province;
                move_span.classList.add('move_span');
                province_d.appendChild(move_span);
                arr_1.push(move_span);
                // 市部分
                city_div=document.createElement('div');
                city_div.classList.add('hide_city');
               
                let all_span=document.createElement('span');
                all_span.innerText='全部';
                all_span.classList.add('red_bg_d');
                city_div.appendChild(all_span);
                sele_wrapper.insertBefore(city_div,theme_d);
                for(let j=0;j<obj_d[i].children.length;j++){
                    let city_span=document.createElement('span');
                    // city_span.classList.add='city_span';
                    city_div.appendChild(city_span);
                    city_span.innerText=obj_d[i].children[j].name;
                    // 加上城市属性
                    city_span.city=obj_d[i].children[j].city;
                    // 加上area属性
                    city_span.area=obj_d[i].children[j].area;
                }
                // 写在这就把子节点 市的span也装进去
                arr_2.push(city_div);
            }

            // 点击事件
            let contry=$('.red_bg')[0];
          for(let i=0;i<arr_1.length;i++){
              arr_1[i].addEventListener('click',function(){
                    contry.classList.remove('red_bg');
                  for(let j=0;j<arr_2.length;j++){
                    arr_2[j].style.display='none';
                    arr_1[j].classList.remove('red_bg');
                  }
                arr_2[i].style.display='block';
                this.classList.add('red_bg');
                // 新加
                for(let k=0;k<arr_2[i].childNodes.length;k++){
                    arr_2[i].childNodes[k].classList.remove('red_bg_d');
                }
                arr_2[i].firstChild.classList.add('red_bg_d');
              })
            }
            contry.addEventListener('click',function(){
                this.classList.add('red_bg');
                for(let j=0;j<arr_2.length;j++){
                    arr_2[j].style.display='none';
                    arr_1[j].classList.remove('red_bg');
                  }
            })
            
            // 子级的类名切换
            for(let i=0;i<arr_2.length;i++){
                for(let j=0;j<arr_2[i].childNodes.length;j++){
                    arr_2[i].childNodes[j].addEventListener('click',function(){
                        for(let k=0;k<arr_2[i].childNodes.length;k++){
                            arr_2[i].childNodes[k].classList.remove('red_bg_d');
                        }
                       this.classList.add('red_bg_d');
                    })
                }
            }
        },
        error:function(){}
    }
    ajax(city);


    // 主题和个人数的点击事件
    let the_span = $('.item_d');
    let peo_span = $('.peo_d');
    for (let i = 0; i < the_span.length; i++) {
        the_span[i].addEventListener('click', function () {
            for (let j = 0; j < the_span.length; j++) {
                the_span[j].classList.remove('red_bg');
            }
            this.classList.add('red_bg');
        })
    }

    for (let i = 0; i < peo_span.length; i++) {
        peo_span[i].index = i;
        peo_span[i].addEventListener('click', function () {
            for (let j = 0; j < peo_span.length; j++) {
                peo_span[j].classList.remove('red_bg');
            }
            this.classList.add('red_bg');
        })
    }


    // 点击出现下拉框
    let vis_down = $('.vis_down')[0];
    let vis_down_1 = $('.vis_down')[1];
    let vis_ul = $('.inner_ul_d')[0];
    let vis_ul_1 = $('.inner_ul_d')[1];
    let span_left = $('.left_em>span')[0];
    let span_center = $('.center_em>span')[0];
    let span_left_1 = $('.left_em>span')[1];
    let span_center_1 = $('.center_em>span')[1];
    let search_d = $('.pro_search')[0];
    let search_d_1 = $('.pro_search')[1];
    vis_down.addEventListener('click', function (e) {
        e.stopPropagation();
        vis_ul.style.height = '80px';
    })
    vis_down_1.addEventListener('click', function (e) {
        e.stopPropagation();
        vis_ul_1.style.height = '80px';
    })
    vis_ul.addEventListener('click', function () {
        this.style.height = '0px';
    })
    vis_ul_1.addEventListener('click', function () {
        this.style.height = '0px';
    })
    this.document.addEventListener('click', function () {
        vis_ul.style.height = '0px';
        vis_ul_1.style.height = '0px';
    })

    let vis_li = vis_ul.querySelectorAll('li');
    let vis_li_1 = vis_ul_1.querySelectorAll('li');
    for (let i = 0; i < vis_li.length; i++) {
        vis_li[i].addEventListener('click', function () {
            span_left.innerText = vis_li[i].innerText;
            span_center.innerText = vis_li[i].innerText;
        })
    }

    for (let i = 0; i < vis_li_1.length; i++) {
        vis_li_1[i].addEventListener('click', function () {
            span_left_1.innerText = vis_li_1[i].innerText;
            span_center_1.innerText = vis_li_1[i].innerText;
        })
    }

    // 搜索框

    // let left_em=$('.left_em')[0];
    let center_em = $('.center_em')[0];
    let center_em_1 = $('.center_em')[1];
    search_d.onfocus = function () {
        center_em.style.opacity = '0';
    }
    search_d_1.onfocus = function () {
        center_em_1.style.opacity = '0';
    }
    search_d.onblur = function () {
        center_em.style.opacity = '1';
    }
    search_d_1.onblur = function () {
        center_em_1.style.opacity = '1';
    }
    center_em.onclick = function () {
        search_d.focus();
    }
    center_em_1.onclick = function () {
        search_d_1.focus();
    }
    // 传送参数的获取
    //  province:11,
    // city:"01",
    // area:"01",
    // "itemCategory": "社区服务",
    // "numberScope": "1",
    // "pageSize": "9",
    // "pageNum": "1"

    // 动态添加属性
    var seek_opt = new Object();
    seek_opt.type="get";
    seek_opt.url='item/conditions';
    seek_opt.data={};
    seek_opt.header={
        'Content-Type':'application/json'
    }
    seek_opt.success=function(data){
        render(data);
    }
    seek_opt.error=function(){
        
    }
    seek_opt.data.pageNum='1';
    seek_opt.data.pageSize='9';
    // 未点击时第一次发送，里面是未点击的数据，初始页面1和每页容量9
    ajax(seek_opt);

    
    // 进入选择区域 每点击一次都要发送
    sele_wrapper.addEventListener('click', function (e) {
        if (e.target == sele_wrapper) {
            return;
        }
        // 省份的判断
        if (e.target.classList.contains('move_span')) {
            e.target.province ? seek_opt.data.province = e.target.province : null;
        } else {
            if (e.target.parentElement.classList.contains("province_d")) {
                delete seek_opt.data.province;
                delete seek_opt.data.city;
                delete seek_opt.data.area;
            }
        }
        // 城市和地区的判断
        if (e.target.classList.contains('red_bg_d')) {
            if (e.target.city) {
                seek_opt.data.city = e.target.city
            } else {
                delete seek_opt.data.city;
            }
            if (e.target.area) {
                seek_opt.data.area = e.target.area
            } else {
                delete seek_opt.data.area;
            }
        }
        // 主题的判断
        if (e.target.classList.contains('item_d')) {
            seek_opt.data.itemCategory = e.target.innerText;
        }
        // 人数范围的判断
        if (e.target.classList.contains('peo_d')) {
            e.target.index == 0 ? null : seek_opt.data.numberScope = e.target.index;
            // seek_opt.data.numberScope=e.target.index;
        }
        // 搜索框内容获取 编号和主题
        search_d.onchange = function () {
            if (span_center.innerText == '项目名称' && search_d.value.trim() != '') {
                // trim(search_d.value)!=''?seek_opt.data.itemTitle=search_d.value:null;
                seek_opt.data.itemTitle = this.value;
            }
            if (span_center.innerText == '项目编号' && search_d.value.trim() != '') {
                seek_opt.data.itemId = this.value;
            }
        }
        search_d_1.onchange = function () {
            if (span_center_1.innerText == '队伍名称' && this.value.trim() != '') {
                // trim(search_d.value)!=''?seek_opt.data.itemTitle=search_d.value:null;
                seek_opt.data.teamName = this.value;
            }
            if (span_center_1.innerText == '队伍编号' && this.value.trim() != '') {
                seek_opt.data.teamId = this.value;
            }

            // 项目存在时删除队伍信息 队伍名存在时删除项目信息
            if (theme_d.style.display == 'none') {
                seek_opt.data.itemCategory ? delete seek_opt.data.itemCategory : null;
                seek_opt.data.itemId ? delete seek_opt.data.itemId : null;
                seek_opt.data.itemTitle ? delete seek_opt.data.itemTitle : null;
            }
            if (theme_d.style.display == 'block') {
                seek_opt.data.teamId ? delete seek_opt.data.teamId : null;
                seek_opt.data.teamName ? delete seek_opt.data.teamName : null;
            }
        }
        // 获取页码
        if (e.target.classList.contains('num')) {
            seek_opt.data.pageNum = e.target.innerText;
        }
        if (e.target.classList.contains('turn_to') && e.target.value != '') {
            seek_opt.data.pageNum = e.target.value;
        }
        console.log(seek_opt);
        // 发送前打印出来是对的就已经添加data成功了
        // seek_opt.data.json = encodeURIComponent(JSON.stringify(seek_opt.data));
        ajax(seek_opt);
    })

    // 切换项目和队伍
    let tag_li = $('.pro_box>ul>li');
    let tag_box = $('.tagged_box');
    let area_c = $('.area_c')[0];
    let peo_c = $('.peo_c')[0];
    let fir_d = $('.fir_d')[0];
    let item_d = $('.item_d');
    let peo_d = $('.peo_d');
    for (let i = 0; i < tag_li.length; i++) {
        tag_li[i].addEventListener('click', function () {
            for (let j = 0; j < tag_li.length; j++) {
                tag_li[j].className = '';
                tag_box[j].style.display = 'none';
            }
            this.style.display = 'block';
            this.className = 'pro_li_cur';
            tag_box[i].style.display = 'block';
            i == 1 ? area_c.innerText = '队伍地区' : area_c.innerText = '项目地区';
            i == 1 ? theme_d.style.display = 'none' : theme_d.style.display = 'block';
            i == 1 ? peo_c.innerText = '队伍人数' : peo_c.innerText = '项目人数';
            for (let k = 0; k < arr_1.length; k++) {
                arr_1[k].classList.remove('red_bg');
            }

            fir_d.classList.add('red_bg');
            for (let k = 0; k < item_d.length; k++) {
                item_d[k].classList.remove('red_bg');
            }
            item_d[0].classList.add('red_bg');
            for (let k = 0; k < peo_d.length; k++) {
                peo_d[k].classList.remove('red_bg');
            }
            peo_d[0].classList.add('red_bg');
            for (let k = 0; k < arr_2.length; k++) {
                arr_2[k].style.display = 'none';
            }
        })
    }
})