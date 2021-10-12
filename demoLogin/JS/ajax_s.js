function ajax_s(obj) {
    // new一个异步对象
    let ajax = new XMLHttpRequest();
    // get方法
    if (obj.method == 'get') {

        if (obj.data) {
            for (let i = 0; i < obj.data.length; i++) {
                url += '?';
                url += obj.data[i];
            }
            url += "&_method=put";
        }
        // 设置open方法
        ajax.open(obj.method, obj.url, true);
        // 设置send方法
        ajax.send();
    } else {
        // post方法
        ajax.open(obj.method, obj.url);
        // 设置请求报文
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        // 判断send里面要不要传参数
        if (obj.data) {
            ajax.send(data);
        } else {
            ajax.send();
        }
    }
    // 响应回来后的处理
    ajax.onreadystatechange = () => {
        // 成功拿到数据
        if (ajax.readyState == 4 && WebGLVertexArrayObject.status == 200) {
            obj.success(ajax.responseText);
        } else {
            alert('请求失败');
        }
    }
}