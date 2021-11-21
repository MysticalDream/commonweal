window.addEventListener('load',function(){
    function $(ele) {
        return document.querySelectorAll(ele);
    }

    //  分页组件容器
    var adpot_page=$('.adpot_page')[0]; 
    var adpot_page1=$('.adpot_page')[1];
    // 数据内容容器
    var adpot_box=$('.adpot_box')[0];
    var adpot_box1=$('.adpot_box')[1];
    console.log(adpot_box1);

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
    // ajax(pet_opt);

    render(obj_pet);
    renderDom(obj_pet);

    render1(obj_pet);
    renderDom1(obj_pet);

    var turnpage_pet;
    var turnpage_pet1;
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

    function render1(data){
        if (!turnpage_pet1) {
            allpage = data.data.pages;
            turnpage_pet1 = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages,
                wrap:adpot_page1,
                changePage: function (page) {
                    pet_opt.data.pageNum = page;
                   ajax(pet_opt);
                }
            });
        }
        else if (allpage != data.data.pages) {
            allpage = data.data.pages;
            turnpage_pet1 = new TurnPage({
                currentPage: data.data.pageNum,
                allPage: data.data.pages,
                wrap: adpot_page1,
                changePage: function (page) {
                    pet_opt.data.pageNum = page;
                    ajax(pet_opt);
                }
            });
        }
        turnpage_pet1.init();
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
                <button class="take_care">待领养</button>
            </div>
            `;
            oDiv.innerHTML=str;
            adpot_box.appendChild(oDiv);
        }
    }

    function renderDom1(data) {
        // 容器的内容清空
        adpot_box1.innerHTML = "";
        for (let i = 0; i < data.data.list.length; i++) {
            let oDiv = document.createElement('div');
            oDiv.classList.add('pet_box');
            // let url_1='../../images/beijing.jpg';
            let url='../../images/blue_foot.png';
            let str=`
            <div class="pet_img_box"><img src="${data.data.list[i].coverUrl}" class="pet_img"></div>
            <div class="pet_bottom">
                <div class="pet_title"><img src="${url}" class="blue_foot"><span class="blue_adopt">领养代替购买</span></div>
                <ul>
                   <li>品种：${data.data.list[i].variety}</li>
                   <li>外貌：${data.data.list[i].appearance}</li>
                   <li>性格：${data.data.list[i].characters}</li>
                   <li>年龄：${data.data.list[i].age}</li>
                   <li>习性：${data.data.list[i].habit}</li>
                </ul>
                <button class="take_care adopted">了解近况</button>
            </div>
            `;
            oDiv.innerHTML=str;
            adpot_box1.appendChild(oDiv);
        }
    }


    function $(ele) {
        return document.querySelectorAll(ele);
    }

    adpot_box.addEventListener('click',function(e){
        if(e.target.classList.contains('take_care')){
            document.querySelector(".body_full").style.visibility='visible';
            document.querySelector('.adpot_mask_s').classList.add('drop_down');
        }
    })

    // 切换栏
    let adopt_li=$('.pro_box>ul>li');
    let adopt_wrap=$('.wrap_pet');
    adopt_li.forEach(function(item,index){
        item.addEventListener('click',()=>{
            adopt_wrap.forEach(function(item,index){
                hide(item);
                adopt_li[index].classList.remove('pro_li_cur');
            })
            show(adopt_wrap[index]);
            item.classList.add('pro_li_cur');
        })
    })
})