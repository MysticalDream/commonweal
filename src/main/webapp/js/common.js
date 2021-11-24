function show(ele){
    ele.style.display='block';
}

function hide(ele){
    ele.style.display='none';
}

function $(ele) {
    return document.querySelectorAll(ele);
}

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

function isNumber(val) {
    var reg = /^[0-9]+\.?[0-9]*$/;
    return reg.test(val);
};

function getUrlParamObject(url) {
    const params = url.slice(url.indexOf('?') + 1, url.length);
    const group = params.split('&');
    const data = new Object();
    for (const index in group) {
        const arr = group[index].split('=');
        data[arr[0]] = isNumber(arr[1]) ? parseFloat(arr[1]) : arr[1];
    }
    return data;
};