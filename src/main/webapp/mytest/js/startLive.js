;
(function (win) {
    //辅助函数
    /**
     * get请求
     * @param opt
     * @returns {Promise}
     */
    function get(opt) {
        return new Promise((resolve, reject) => {
            ajax({
                type: 'get',
                url: opt.url || "",
                data: opt.data || {},
                header: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    resolve(data);
                },
                error: function (data) {
                    reject(data);
                }
            });
        });
    }

    /**
     * post请求
     * @param opt
     */
    function post(opt) {

        return new Promise((resolve, reject) => {
            ajax({
                type: 'post',
                url: opt.url,
                data: opt.data,
                header: {
                    'Content-Type': opt['Content-Type'] || 'application/x-www-form-urlencoded'
                },
                success: function (data) {
                    resolve(data);
                },
                error: function (data) {
                    reject(data);
                }
            });
        });
    }

    /**
     * josn请求
     * @param opt
     * @returns {Promise}
     */
    function postJson(opt) {
        return new Promise((resolve, reject) => {
            ajax({
                type: 'post',
                url: opt.url,
                data: opt.data,
                header: {
                    'Content-Type': 'application/json'
                },
                success: function (data) {
                    resolve(data);
                },
                error: function (data) {
                    reject(data);
                }
            });
        });
    }

//核心
    win.addEventListener("load", function () {
        myCounter({
            tag: $('#counter1'),
            el: $('#title'),
            txtTemplate: '?/20',
            limit: 20
        });
        const liveUrl = "/mytest/p/live3.html";
        const $mainForm = $('#main_form');
        const $save = $('#save');
        const $category = $('#category');
        const listData = {1: "语文", 2: "数学", 3: "英语"};
        const $coverInput = $("#cover_input");
        const $coverForm = $('#cover_form');
        const $coverLabel = $('#cover_label');
        const $cover = $('#cover');
        const $title = $('#title');
        const $into = $('#intro');
        let submitting = false;

        initSelect($category, listData);
        //上传图片
        iframeAjax({
            form: $coverForm,
            callback: function (data) {
                win.console.log(data);
                if (data.data) {
                    $coverLabel.className = "cover";
                    $coverLabel.style.backgroundImage = `url(${data.data})`;
                    $cover.value = data.data;
                }
            },
            error: function (err) {
                win.console.log("错误:", err);
            }
        });
        //获取直播间信息
        get({url: "/live"}).then(data => {
            const data1 = data.data;
            data1.coverUrl && ($coverLabel.className = "cover", $coverLabel.style.backgroundImage = `url(${data1.coverUrl})`, $cover.value = data1.coverUrl);
            data1.categoryId && (data1.categoryId <= win.Object.keys(listData).length) && ($category.selectedIndex = data1.categoryId - 1);
            data1.title && ($title.value = data1.title);
            data1.intro && ($into.value = data1.intro);
        }).catch(e => win.console.log(e));

        $coverInput.addEventListener("change", (e) => {
            if (e.target.files.length === 1) {
                $coverForm.submit();
            }
        });

        /**
         * 提交表单
         */
        $save.addEventListener("click", () => {
            const data = $mainForm.jsondata2;
            if (!data.title || submitting || !data.intro) {
                return;
            }
            submitting = true;

            postJson({'url': "/live", 'data': data}).then(data => {
                submitting = false;
                win.console.log(data);
                redirect(liveUrl);
            }).catch(e => {
                submitting = false;
                win.console.log("post请求错误:", e);
            });

            win.console.log("表单收集的数据:", data);
        });

        /**
         * 初始化列表
         * @param selectDom
         * @param listData
         */
        function initSelect(selectDom, listData = {}) {
            selectDom.innerHTML = "";
            for (let key in listData) {
                if (!listData.hasOwnProperty(key)) {
                    continue;
                }
                const optionElement = win.document.createElement("option");
                optionElement.value = key;
                optionElement.innerText = listData[key];
                selectDom.appendChild(optionElement);
            }
        }
    });
})(window)
;