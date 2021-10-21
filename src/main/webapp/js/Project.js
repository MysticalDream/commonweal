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


        window.addEventListener('load',function(){
            function $(ele) {
                return document.querySelectorAll(ele);
            }
            
            let sele_wrapper=$('.sele_wrapper')[0];
            let province_d=$('.province_d')[0];
            // let people_d=$('.people_d')[0];
            let theme_d=$('.theme_d')[0];
            let city;
            let obj_d;
            let move_span;
            let city_div;
            let arr_1=new Array();
            let arr_2=new Array();
            city={
                type:'get',
                url:'/js/data.json',
                data:{
                  
                },
                success:function(data){
                    // if(data.success)

                    obj_d=JSON.parse(data);
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
            let the_span=$('.item_d');
            let peo_span=$('.peo_d');
            for(let i=0;i<the_span.length;i++){
                the_span[i].addEventListener('click',function(){
                    for(let j=0;j<the_span.length;j++){
                        the_span[j].classList.remove('red_bg');
                    }
                    this.classList.add('red_bg');
                })
            }
           
            for(let i=0;i<peo_span.length;i++){
                peo_span[i].index=i;
                peo_span[i].addEventListener('click',function(){
                    for(let j=0;j<peo_span.length;j++){
                        peo_span[j].classList.remove('red_bg');
                    }
                    this.classList.add('red_bg');
                })
            }

            // 传送参数的获取
            //  province:11,
            // city:"01",
            // area:"01",
            // "itemCategory": "社区服务",
            // "numberScope": "1",
            // "pageSize": "9",
            // "pageNum": "1"
            var seek_opt=[];
            var province;
            var itemCategory;
            var numberScope;
            var city_d;
            var area;
            sele_wrapper.addEventListener('click',function(e){
                if(e.target==sele_wrapper){
                    return;
                }
                if(e.target.classList.contains('move_span')){
                    province=e.target.province;
                    console.log(province);
                }
                if(e.target.classList.contains('red_bg_d')){
                    city_d=e.target.city;
                    e.target.area?data.area=e.target.area:null;
                    console.log(city_d);
                    console.log(area);
                }
                if(e.target.classList.contains('item_d')){
                    itemCategory=e.target.innerText;
                    console.log(itemCategory);
                }
                if(e.target.classList.contains('peo_d')){
                    numberScope=e.target.index;
                    console.log(numberScope);
                }
                seek_opt={
                    type:'GET',
                    url:'/js/data.json',
                    data:{
                        province:province,
                        city:city_d,
                        itemCategory:itemCategory,
                        numberScope: numberScope,
                        pageSize: "9",
                        pageNum: "1"
                        },
                   
                    // header:{
                    //     'Content-Type':'application/x-www-form-urlencoded'
                    //     // 'Content-Type':'application/json'
                    // },
                    success:function(data){
                        var p=JSON.parse(data);
                        console.log(p);
                    },
                    error:function(){}
                    }
                    console.log(seek_opt);
                    ajax(seek_opt);

            })    
        })