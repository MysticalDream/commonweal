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
    // var turn_to = document.createElement('span');
    // turn_to.innerText = '跳转到';
    // var turn_to_text = document.createElement('input');
    // turn_to_text.classList.add('turn_to');
    // var turn_to_page = document.createElement('span');
    // turn_to_page.innerText = '页';
    // 添加一个按钮 点击该按钮 跳转到指定页数
    var turn_to_btn = document.createElement('button');
    // turn_to_btn.innerText = '确定';
    // turn_to_btn.classList.add('turn_to_btn');
    // turnPageWrapper.appendChild(turn_to);
    // turnPageWrapper.appendChild(turn_to_text);
    // turnPageWrapper.appendChild(turn_to_page);
    // turnPageWrapper.appendChild(turn_to_btn);
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

    //  分页组件容器
    var adpot_page=$('.adpot_page')[0]; 
    // 数据内容容器
    var adpot_box=$('.adpot_box')[0];

    // 从这里开始
    // 动态添加对象的属性
    var pet_opt = new Object();
    pet_opt.type = 'get';
    pet_opt.url = '/adopts';
    pet_opt.data = {};
    pet_opt.data.pageNum = "1";
    pet_opt.data.pageSize = "9";
    pet_opt.success = function (data) {
        // 渲染分页
        render(data);
        // 渲染内容
        renderDom(data);
    };
    pet_opt.error = function () {
    };
    pet_opt.header = {
        'Content-Type': 'application/json'
    },
  
    // 页面加载第一次发送数据
    ajax(pet_opt);

    // render(obj_pet);
    // renderDom(obj_pet);

    var turnpage_pet;
    // 分页组件的渲染
    function render(data) {
        if (!turnpage_pet) {
            allpage = data.data.pages;
            turnpage_pet = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages,
                wrap:adpot_page,
                changePage: function (page) {
                    pet_opt.data.pageNum = page;
                   ajax(pet_opt);
                }
            });
        }
        else if (allpage != data.data.pages) {
            allpage = data.data.pages;
            turnpage_pet = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages,
                wrap: adpot_page,
                changePage: function (page) {
                    pet_opt.data.pageNum = page;
                    ajax(pet_opt);
                }
            });
        }
        turnpage_pet.init();
    }

    // 数据内容的渲染 
    function renderDom(data) {
        // 容器的内容清空
        adpot_box.innerHTML = "";
        for (let i = 0; i < data.data.list.length; i++) {
            let oDiv = document.createElement('div');
            oDiv.classList.add('pet_box');
            // let url_1='../../images/beijing.jpg';
            let url='../../images/pet.png';
            let str=`
            <div class="pet_img_box"><img src="${data.data.list[i].coverUrl}" class="pet_img"></div>
            <div class="pet_bottom">
                <div class="pet_title"><img src="${url}"><span>领养代替购买</span></div>
                <ul>
                   <li>品种：${data.data.list[i].variety}</li>
                   <li>外貌：${data.data.list[i].appearance}</li>
                   <li>性格：${data.data.list[i].characters}</li>
                   <li>年龄：${data.data.list[i].age}</li>
                   <li>习性：${data.data.list[i].habit}</li>
                </ul>
                <button class="take_care">我要领养</button>
            </div>
            `;
            oDiv.innerHTML=str;
            adpot_box.appendChild(oDiv);
        }
    }

    adpot_box.addEventListener('click',function(e){
        if(e.target.classList.contains('take_care')){
            document.querySelector(".adpot_mask_s").style.display='block';
        }
    })

})