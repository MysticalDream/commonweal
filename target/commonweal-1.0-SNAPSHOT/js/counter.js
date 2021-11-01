//计数器
(function(global, factory) { 
    typeof exports === "object" && typeof module !== "undefined"   ? (module.exports = factory())   : typeof define === "function" && define.amd    ? define(factory)    : ((global = global || self), (global.myCounter = factory()));
})(this, function() {  //辅助函数

     
    function $(matcher) {   return document.querySelectorAll(matcher);  }

     
    function isElementNode(node) {   return node.nodeType === 1;  } 
    /**
     * 防抖函数 执行最后一次
     * @param {*} func 业务代码
     * @param {*} delay 延时
     * @returns
     */
     
    function antiShake(func, delay) {  
        var t = null;  
        return function() {   
            if (t !== null) {     clearTimeout(t);    }   
            t = setTimeout(() => {     func.call(this);    }, delay);  
        }; 
    } 
    /**
     * 节流函数 减少执行次数
     * @param {*} func 业务代码
     * @param {*} delay 延时
     */
     
    function throttle(func, delay) {  
        var flag = true;  
        return function() {   
            if (flag) {    
                setTimeout(() => {     
                    func.call(this);     
                    flag = true;    
                }, delay);   
            }   
            flag = false;  
        }; 
    }  //核心函数
     
    function myCounter(opt) {  
        var tag = isElementNode(opt.tag) ? opt.tag : $(opt.tag)[0];  
        var listenObj = isElementNode(opt.el) ? opt.el : $(opt.el)[0];  
        var txtTemplate = opt.txtTemplate || "?";  
        tag.innerText = txtTemplate.replace(/[?？]/g, listenObj.value.length);  
        listenObj.addEventListener(   "input",    throttle(function() {     tag.innerText = txtTemplate.replace(/[?？]/g, listenObj.value.length);    }, 200)  ); 
    } 
    return myCounter;
});  
// new myCounter({    el: '#blog_content'   ,    tag: '#count_tag',    txtTemplate: '共?字'   });
new myCounter({
    el: '#first_text',
    tag: '#first_show',
    txtTemplate: '?/10'
});
new myCounter({
    el: '#fifth_text',
    tag: '#fifth_show',
    txtTemplate: '?/200'
});