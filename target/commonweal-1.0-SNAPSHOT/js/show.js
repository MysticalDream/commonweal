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

    //  分页组件容器
    var show_page=$('.show_page')[0]; 
    // 数据内容容器
    var show_box=$('.show_box')[0];

     // 从这里开始
    // 动态添加对象的属性
    var show_opt = new Object();
    show_opt.type = 'get';
    show_opt.url = '/achievements/conditions';
    show_opt.data = {};
    show_opt.success = function (data) {
        // 渲染分页
        render(data);
        // 渲染内容
        renderDom(data);
    };
    show_opt.error = function () {
    };
    show_opt.header = {
        'Content-Type': 'application/json'
    },
    show_opt.data.pageNum = "1";
    show_opt.data.pageSize = "5";

    // 页面加载第一次发送数据
    ajax(show_opt);

    // 找个示例对象来处理
    // render(obj_show);
    // renderDom(obj_show);

    var turnpage_show;
    // 分页组件的渲染
    function render(data) {
        if (!turnpage_show) {
            allpage = data.data.pages;
            turnpage_show = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages,
                wrap:show_page,
                changePage: function (page) {
                    show_opt.data.pageNum = page;
                    console.log(show_opt.success);
                   ajax(show_opt);
                }
            });
        }
        else if (allpage != data.data.pages) {
            allpage = data.data.pages;
            turnpage_show = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages,
                wrap: show_page,
                changePage: function (page) {
                    show_opt.data.pageNum = page;
                    ajax(show_opt);
                }
            });
        }
        turnpage_show.init();
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

    // 数据内容的渲染 
    function renderDom(data) {
        // 容器的内容清空
        show_box.innerHTML = "";
        for (let i = 0; i < data.data.list.length; i++) {
            let oDiv = document.createElement('div');
            oDiv.classList.add('show_item');
            oDiv.id=data.data.list[i].id;
            // let url_1='../../images/beijing.jpg';
            // let url_small='../../images/join.png';
            let url_left='../../images/left_tri.png';
            let url_ri='../../images/right_tri.png';
            // 注意点赞的图要放在like的背景图 这样点击了才可以换背景图（灰色like和红色like）
            let str1=`
            <div class="left_show_img">
                <img src=${data.data.list[i].coverUrl}>
                <div class="triangle">
                    <img src="${url_left}">
                </div>
            </div>
            <div class="right_show_text">
                <div class="show_title">
                    <span>${data.data.list[i].name}</span>
                    <span class="red_d"></span>
                    <span class="blue_d"></span>
                </div>
                <p>${data.data.list[i].introduction}</p>
                <div class="decoration_red"></div>
                <div class="bottom_text">
                    <span>${new Date(data.data.list[i].gmtCreate).Format('yyyy年MM月dd日')}</span>
                    <div class="like">
                        <span>${data.data.list[i].loveNumber}</span>
                    <div>
                </div>
            </div>
            `;
            let str2=`
            <div class="left_show_text">
                <div class="show_title_l">
                    <span class="red_d"></span>
                    <span class="blue_d"></span>
                    <span>${data.data.list[i].title}</span>
                </div>
                <p>${data.data.list[i].introduction}</p>
                <div class="decoration_red red_l"></div>
                <div class="bottom_text">
                    <div class="like like_l">
                        <span>${data.data.list[i].loveNumber}</span>
                    </div>
                    <span>${new Date(data.data.list[i].gmtCreate).Format('yyyy年MM月dd日')}</span>
                </div>
            </div>
            <div class="right_show_img">
                <img src=${data.data.list[i].coverUrl}>
                <div class="triangle tria_r">
                    <img src="${url_ri}">
                </div>
            </div>
            `;
            i%2==0? oDiv.innerHTML=str1:oDiv.innerHTML=str2;
            show_box.appendChild(oDiv);
            // 点赞
            oDiv.addEventListener('click',(e)=>{
                if(e.target.classList.contains('like')){
                    // ajax({
                    //     type:'put',
                    //     url:'/thumb',
                    //     data:{
                    //         achievementId:oDiv.id,
                    //     },
                    //     header:{
                    //         'Content-Type':'application/json'
                    //     },
                    //     success:function(data){
                    //         if(data.success){
                                e.target.style.backgroundImage='../../images/like_red.png';
                                e.target.classList.add('liked');
                                e.target.children[0].innerText=e.target.children[0].innerText+1;
                    //         }
                    //     },
                    //     error:function(){

                    //     }
                    // })
                }
                if(e.target.classList.contains('liked')){
                    e.target.style.backgroundImage='../../images/like_grey.png';
                    e.target.classList.remove('liked');
                    e.target.children[0].innerText=e.target.children[0].innerText-1;
                    if(e.target.children[0].innerText==0){
                        e.target.children[0].innerText=0;
                    }
                }
            })
        }
    }


})