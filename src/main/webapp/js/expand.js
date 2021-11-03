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
}

window.addEventListener('load',function(){
    function $(ele) {
        return document.querySelectorAll(ele);
    }

    let expand_p = $('.expand_p')[0];
    let expand_city = $('.expand_city')[0];
    let expand_theme = $('.expand_theme')[0];
    let expand_peo=$('.expand_peo')[0];
    let expand_situ=$('.expand_situation')[0];
    let wrap_expand=$('.wrap_expand')[0];
    let city_e={};
    let obj_e;
    let arr_1=new Array();
    let arr_2=new Array();
    city_e={
        type:'get',
        url:'/js/data.json',
        data:{
          
        },
        success:function(data){
            // if(data.success)
            obj_e=data;
            // 省市的渲染
            for(let i=0;i<obj_e.length;i++){
                // 省部分
                move_span=document.createElement('span');
                move_span.innerText=obj_e[i].name;
                // 渲染时把属性也加上
                move_span.province=obj_e[i].province;
                move_span.classList.add('move_span');
                // expand_p放省份
                expand_p.appendChild(move_span);
                arr_1.push(move_span);
                // 市部分
                city_div=document.createElement('div');
                city_div.classList.add('hide_city');
               
                let all_span=document.createElement('span');
                all_span.innerText='全部';
                all_span.classList.add('red_bg_d');
                city_div.appendChild(all_span);
                expand_city.appendChild(city_div);
                for(let j=0;j<obj_e[i].children.length;j++){
                    let city_span=document.createElement('span');
                    // city_span.classList.add='city_span';
                    city_div.appendChild(city_span);
                    city_span.innerText=obj_e[i].children[j].name;
                    // 加上城市属性
                    city_span.city=obj_e[i].children[j].city;
                    // 加上area属性
                    obj_e[i].children[j].area?city_span.area=obj_e[i].children[j].area:null;
                }
                // 写在这就把子节点 市的span也装进去
                arr_2.push(city_div);
            }

            // 点击事件
            let contry = $('.e_contry')[0];
            for (let i = 0; i < arr_1.length; i++) {
                arr_1[i].addEventListener('click', function () {
                    contry.classList.remove('red_bg');
                    for (let j = 0; j < arr_2.length; j++) {
                        arr_2[j].style.display = 'none';
                        arr_1[j].classList.remove('red_bg');
                    }
                    arr_2[i].style.display = 'block';
                    this.classList.add('red_bg');
                    // 新加
                    for (let k = 0; k < arr_2[i].childNodes.length; k++) {
                        arr_2[i].childNodes[k].classList.remove('red_bg_d');
                    }
                    arr_2[i].firstChild.classList.add('red_bg_d');
                })
            }
            contry.addEventListener('click', function () {
                this.classList.add('red_bg');
                for (let j = 0; j < arr_2.length; j++) {
                    arr_2[j].style.display = 'none';
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

    ajax(city_e);

    // 搜索框点击了隐藏文字
    let expand_search=$('.input_e')[0];
    let expand_div=$('.search_div')[0];
    expand_search.onfocus=function(){
        expand_div.style.display='none';
    }
    expand_search.onblur=function(){
        expand_div.style.display='block';
    }

    // 主题,人数,招募状态的点击事件
    let the_span = $('.item_d');
    let peo_span = $('.peo_d');
    let situ_span=$('.situation');
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

    for (let i = 0; i < situ_span.length; i++) {
        situ_span[i].addEventListener('click', function () {
            for (let j = 0; j < situ_span.length; j++) {
                situ_span[j].classList.remove('red_bg');
            }
            this.classList.add('red_bg');
        })
        // 设置index值作参数
        if(i==0){
            continue;
        }
        else{
            situ_span[i].index=i-1;
        }
    }

    // 从这里开始
    // 分页容器
    var expand_page=$('.expand_page')[0];
    // 内容容器
    var expand_content=$('.expand_content')[0];

    var expand_opt = new Object();
    expand_opt.type = 'get';
    expand_opt.url = '/recruits/conditions';
    expand_opt.data = {};
    expand_opt.success = function (data) {
        // 渲染分页
        render(data);
        // 渲染内容
        renderDom(data);
    };
    expand_opt.error = function () {
    };
    expand_opt.header = {
        'Content-Type': 'application/x-www-form-urlencoded'
    },
    expand_opt.data.pageNum = "1";
    expand_opt.data.pageSize = "9";
    // 页面加载第一次发送数据
    ajax(expand_opt);
    // render(obj_expand);
    // renderDom(obj_expand);

    // 开一个分页实例
    var turnpage_ex;
    function render(data) {
        if (!turnpage_ex) {
            allpage = data.data.pages;
            turnpage_ex = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages, 
                wrap:expand_page,
                changePage: function (page) {
                    expand_opt.data.pageNum = page;
                    console.log(expand_opt.success);
                   ajax(expand_opt);
                }
            });
        }
        else if (allpage != data.data.pages) {
            allpage = data.data.pages;
            turnpage_ex = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages,
                wrap: expand_page,
                changePage: function (page) {
                    expand_opt.data.pageNum = page;
                    ajax(expand_opt);
                }
            });
        }
        turnpage_ex.init();
    }

    //计算距离招募结束的时间
    function getDistanceTime(endTime) {
        var day = (new Date(endTime) - new Date()) / (86400000);
        return day < 0 ? "已结束" : day < 1 ? Math.floor(24 * day) + "小时" : Math.floor(day) + "天";
    }


    // 格式化时间
    Date.prototype.Format = function (fmt) {
         var o = {
              //月份 
              "M+": this.getMonth() + 1,
              //日 
              "d+": this.getDate(),
              //小时 
              "h+": this.getHours(),
              //分
              "m+": this.getMinutes(),
              //秒 
              "s+": this.getSeconds(),
              //季度 
              "q+": Math.floor((this.getMonth() + 3) / 3),
              //毫秒 
              "S": this.getMilliseconds()
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
              if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[
                k]).substr(("" + o[k]).length)));
            return fmt;
    }

    // 判断招募状态
    function getStatus(startTime, endTime) {
         var now = new Date();
         var start = new Date(startTime);
         var end = new Date(endTime);
        return start > now ? "待招募" : now <= end ? "招募中" : "已结束";
    }

     // 渲染数据 
     function renderDom(data) {
        // 容器的内容清空
        expand_content.innerHTML = "";
        for (let i = 0; i < data.data.list.length; i++) {
            let oDiv = document.createElement('div');
            oDiv.classList.add('expand_box');
            let url_1='../../images/beijing.jpg';
            let url_d_1 = '../../images/peo_d.png';
            let url_d_2 = '../../images/join.png';
            let url_pai='../../images/pai.png';
            let str=`
            <div class="expand_imgbox">
                <img src="${url_1}">
            </div>
            <div>
                <img src="${url_pai}">
                <span>${getStatus(data.data.list[i].begin,data.data.list[i].end)}</span>
            </div>
            <h1>${data.data.list[i].title}</h1>
            <ul>
                <li>地点:${data.data.list[i].location}</li>
                <li>招募开始时间:${new Date(data.data.list[i].begin).Format('yyyy年MM月dd日')}</li>
                <li>招募结束时间:${new Date(data.data.list[i].end).Format('yyyy年MM月dd日')}</li>
                <li>距离招募结束:${getDistanceTime(data.data.list[i].end)}</li>
            </ul>
            <div class="foot_mes">
            <img src='${url_d_1}' class="le_dd">
            <span class="le_sp">${data.data.list[i].currentHeadcount}/${data.data.list[i].maximumNumberLimit}</span>
            <img src="${url_d_2}" class="ri_dd">
            <span class="ri_sp">我要加入</span>
            </div>
            `;
            oDiv.innerHTML = str;
            expand_content.appendChild(oDiv);
        }
    }

    // 省份
    expand_p.addEventListener('click', function (e) {
        if (e.target.classList.contains('move_span')) {
            e.target.province ? expand_opt.data.province = e.target.province : null;
        } else {
            if (e.target.parentElement.classList.contains("province_d")) {
                delete expand_opt.data.province;
                delete expand_opt.data.city;
                delete expand_opt.data.area;
            }
        }
        console.log('省份为：' + expand_opt.data.province);
    })

    // 城市县区
    expand_city.addEventListener('click', function (e) {
        if (e.target.classList.contains('red_bg_d')) {
            if (e.target.city) {
                expand_opt.data.city = e.target.city
            } else {
                delete expand_opt.data.city;
            }
            if (e.target.area) {
                expand_opt.data.area = e.target.area
            } else {
                delete expand_opt.data.area;
            }
        }
        console.log('城市为：' + seek_opt.data.city);
        console.log('县区为：' + seek_opt.data.area);
    })

    // 主题
    expand_theme.addEventListener('click', function (e) {
        if (e.target.classList.contains('item_d')) {
            expand_opt.data.recruitCategory = e.target.innerText;
        }
        console.log(expand_opt.data.recruitCategory);
    })

    // 人数
    expand_peo.addEventListener('click', function (e) {
        if (e.target.classList.contains('peo_d')) {
            e.target.index == 0 ? delete  expand_opt.data.numberScope: expand_opt.data.numberScope = e.target.index;
        }
        console.log('人数范围区间为：' + expand_opt.data.numberScope);
    })

    // 招募状态
    expand_situ.addEventListener('click',function(e){
        if(e.target.classList.contains('situation')){
            e.target.index!==undefined?expand_opt.data.status=e.target.index:null;
        }
    })

    // 搜索框(财举还未添加该字段)
    // expand_search.onchange = function () {
    //     if(this.value.trim()!=''){
    //         expand_opt.data.
    //     }
    // }
    wrap_expand.addEventListener('click',function(){
        // 发送前好像还有加一句话,避免分页bug(财举修改的)
        // expand_opt.data.pageNum=1;
        ajax(expand_opt);
    })
})