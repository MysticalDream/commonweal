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
    this.changePage = options.changePage || function() {};
}
// 初始化效果的方法
TurnPage.prototype.init = function() {
        this.fillHTML();
        this.bindEvent();
    }
    // 填充翻页的结构 添加一个if语句过滤一下按钮按了后触发的bug
TurnPage.prototype.fillHTML = function() {
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
TurnPage.prototype.bindEvent = function() {
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
    let wrap_ag = $('.wrap_ag')[0];
    let agri=$('.agri_d')[0];
    let city_ag={};
    let obj_d;
    let arr_1=new Array();
    let arr_2=new Array();
    city_ag={
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
                agri.appendChild(move_span);
                arr_1.push(move_span);
                // 市部分
                city_div=document.createElement('div');
                city_div.classList.add('hide_city');
               
                let all_span=document.createElement('span');
                all_span.innerText='全部';
                all_span.classList.add('red_bg_d');
                city_div.appendChild(all_span);
                wrap_ag.appendChild(city_div);
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
    ajax(city_ag);

    // 动态添加属性
    var ag_opt = new Object();
    ag_opt.type = 'get';
    ag_opt.url = '/agriculture/list';
    ag_opt.data = {};
    ag_opt.success = function (data) {
        // 转向render函数处理
        render(data);
    };
    ag_opt.error = function () {
    };
    ag_opt.header = {
        'Content-Type': 'application/json'
    },
    ag_opt.data.pageNum = "1";
    ag_opt.data.pageSize = "9";
    // 页面加载第一次发送数据
    ajax(ag_opt);

    // 进入选择区域 每点击一次都要发送
    wrap_ag.addEventListener('click', function (e) {
        if (e.target == wrap_ag) {
            return;
        }
        // 省份的判断
        if (e.target.classList.contains('move_span')) {
            e.target.province ? ag_opt.data.province = e.target.province : null;
        } else {
            if (e.target.parentElement.classList.contains("province_d")) {
                delete ag_opt.data.province;
                delete ag_opt.data.city;
                delete ag_opt.data.area;
            }
        }
        // 城市和地区的判断
        if (e.target.classList.contains('red_bg_d')) {
            if (e.target.city) {
                ag_opt.data.city = e.target.city
            } else {
                delete seek_opt.data.city;
            }
            if (e.target.area) {
                ag_opt.data.area = e.target.area
            } else {
                delete ag_opt.data.area;
            }
        }
        // 获取页码
        if (e.target.classList.contains('num')) {
            ag_opt.data.pageNum = e.target.innerText;
        }
        if (e.target.classList.contains('turn_to') && e.target.value != '') {
            ag_opt.data.pageNum = e.target.value;
        }
        console.log(ag_opt);
        // 发送前打印出来是对的就已经添加data成功了
        // seek_opt.data.json = encodeURIComponent(JSON.stringify(seek_opt.data));
        ajax(ag_opt);
    })

    // 申请成功了就渲染
    function render(obj_z) {
        let data = [];
        for (let i = 0; i < obj_z.data.list.length; i++) {
            data.push(obj_z.data.list[i]);
            console.log(data);
        }
        // 渲染容器
        let ag_box = document.querySelectorAll('.ag_content')[0];

        // 每一个页面放的数据数
        let pageSize = 9;
        // 总的页数
        let allPage = Math.ceil(obj_z.data.total/ pageSize);
        let ag_page = document.querySelectorAll('.ag_page')[0];
        let obj = new TurnPage({
            currentPage: obj_z.data.pageNum,
            allPage: allPage,
            wrap: ag_page,
        // 回调
            changePage: function (page) {
                // console.log(page);
                // 我们要渲染到页面上的数据
                var filterData = data.filter((item, index) => {
                    return index >= (page - 1) * pageSize && index < page * pageSize;
                })
                // 渲染过滤后的数据
                renderDom(filterData);
            }
        })

        obj.init();

        // 将过滤后的内容渲染到页面上
        function renderDom(data) {
            // 清空上一个页面的
            ag_box.innerHTML = '';
            for (let i = 0; i < data.length; i++) {
                let oDiv = document.createElement('div');
                oDiv.classList.add('ag_box');
                // let url_d = '../../images/beijing.jpg';
                // let url_d_1 = '../../images/peo_d.png';
                // let url_d_2 = '../../images/join.png';
                let str = `<div>
                    <img src='${url_d}'>
                    <h1>${data[i].itemTitle}</h1>
                    <p>${data[i].itemIntroduction}</p>
                    <img src='${url_d_1}' class="le_dd"></img><span class="le_sp">${data[i].currentHeadcount}/${data[i].maximumNumberLimit}</span>
                    <img src="${url_d_2}" class="ri_dd"></img><span class="ri_sp">我要加入</span>
                    </div>`
                oDiv.innerHTML = str;
                ag_box.appendChild(oDiv);
            }
        }

        renderDom(obj_z.data.list.filter((item, index) => {
            return index < 9;
        }));
    }
})
