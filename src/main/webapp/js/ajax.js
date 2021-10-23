function ajax(opt){
    // 由于ajax函数在调用时传入的参数太多，因此我们可以在封装的函数里面设置默认值
    var defaults={
       type:'get',
       url:'',
       data:{},
       header:{
           'Content-Type':'application/x-www-form-urlencoded'
       },
       success:function(){
       },
       error:function(){}
    }
    // 这里是用opt覆盖default的值，opt是用户传入的参数，利用浅拷贝的方法
    Object.assign(defaults,opt);
    console.log(opt);
    var xhr=new XMLHttpRequest();
    var params='';
    for(var attr in defaults.data){
       params+=attr+'='+defaults.data[attr]+'&'; 
    }
    params=params.substr(0,params.length-1);
    if(defaults.type=='get'){
       defaults.url=defaults.url+'?'+params;
    }
  xhr.open(defaults.type,defaults.url);
  if(defaults.type=='post'){
       var contentType=defaults.header['Content-Type'];
       xhr.setRequestHeader('Content-Type',contentType);
       if(contentType=='application/json'){
           xhr.send(JSON.stringify(defaults.data));
       }else{
           xhr.send(params);
       }	
  }else{
       xhr.send();
  }
  xhr.onload=function(){
       // 获取响应头中的数据
       var contentType=xhr.getResponseHeader('Content-Type');
       //  服务器端返回的数据
       var responseText=xhr.responseText;
       if(contentType.includes('application/json')){
        //    已是json对象就不用处理，不是才需要转化
        //    responseText=JSON.parse(responseText);
        //    console.log(responseText);
       }
       if(xhr.status==200){
           opt.success(responseText);
       }else{
           opt.error(responseText);
       }
      }
}

//  登录案例
// var opt;
// sign_in.addEventListener('click',function(){
//      opt={
//         type:'get',
//         url:'https://mock.apipost.cn/app/mock/project/adb58a24-e8c9-40fc-9b10-2d8da179d593//get',
//         data:{
//             username:input_test.value,
//             password:input_testa.value
//         },
//         header:{
//             'Content-Type':'application/x-www-form-urlencoded'
//         },
//         success:function(data){
//             console.log(data);
//         },
//         error:function(){}
// }
// ajax(opt);

// 获取省市
// window.addEventListener('load',function(){
//     function $(ele) {
//         return document.querySelectorAll(ele);
//     }
    
//     let sele_wrapper=$('.sele_wrapper')[0];
//     let province_d=$('.province_d')[0];
//     // let provin_inner=$('.provin_inner')[0];
//     let theme_d=$('.theme_d')[0];
//     let city_d=$('.city_d')[0];
//     let people_d=$('.people_d')[0];
//     let city;
//     let obj_d;
//     var move_span;
//     var city_div;
//     city={
//         type:'get',
//         url:'./js/data.json',
//         data:{
          
//         },
//         success:function(data){
//             obj_d=JSON.parse(data);
//             for(let i=0;i<obj_d.length;i++){
//                 // 省部分
//                 move_span=document.createElement('span');
//                 move_span.innerText=obj_d[i].name;
//                 move_span.classList.add('move_span');
//                 province_d.appendChild(move_span);
//                 // 市部分
//                 city_div=document.createElement('div');
//                 city_div.classList.add('hide_city');
//                 let all_span=document.createElement('span');
//                 all_span.innerText='全部';
//                 city_div.appendChild(all_span);
//                 sele_wrapper.insertBefore(city_div,theme_d);
//                 for(let j=0;j<obj_d[i].children.length;j++){
//                     let city_span=document.createElement('span');
//                     city_div.appendChild(city_span);
//                     city_span.innerText=obj_d[i].children[j].name;
//                 }
//             }
//         },
//         error:function(){}
//     }
//     ajax(city);   
// })
