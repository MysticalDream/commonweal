function ajax(opt) {
    // 由于ajax函数在调用时传入的参数太多，因此我们可以在封装的函数里面设置默认值
    var defaults = {
        type: 'get',
        url: '',
        data: {},
        header: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        success: function (data) {
        },
        error: function () {
        }
    }
    // 这里是用opt覆盖default的值，opt是用户传入的参数，利用浅拷贝的方法
    Object.assign(defaults, opt);
    const xhr = new XMLHttpRequest();
    var params = '';
    for (var attr in defaults.data) {
        params += attr + '=' + defaults.data[attr] + '&';
    }
    params = params.substr(0, params.length - 1);
    if (defaults.type != 'post') {
        defaults.url = defaults.url + '?' + params;
    }
    xhr.open(defaults.type, defaults.url);
    if (defaults.type == 'post') {
        const contentType = defaults.header['Content-Type'];
        xhr.setRequestHeader('Content-Type', contentType);
        if (contentType == 'application/json') {
            xhr.send(JSON.stringify(defaults.data));
        } else {
            xhr.send(params);
        }
    } else {
        xhr.send();
    }
    xhr.onload = function () {
        // 获取响应头中的数据
        const contentType = xhr.getResponseHeader('Content-Type');
        //  服务器端返回的数据
        var responseText = xhr.responseText;
        if (contentType.includes('application/json')) {
            //已是json对象就不用处理，不是才需要转化
            responseText = JSON.parse(responseText);
            // console.log(responseText);
        }
        if (xhr.status == 200) {
            opt.success(responseText);
        } else {
            opt.error(responseText);
        }
    }
}
