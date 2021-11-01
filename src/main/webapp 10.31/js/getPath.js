function getPath(obj) {
    if (obj) {
        if (window.navigator.userAgent.indexOf("MSIE") >= 1) { //如果是ie浏览器
            obj.select();
            return obj.value;
        } else if (window.navigator.userAgent.indexOf("Firefox") >= 1) { //如果是火狐浏览器
            if (obj.files) {
                return window.URL.createObjectURL(obj.files[0]);
            }
            return obj.value;
        }
        return obj.value;
    }
}