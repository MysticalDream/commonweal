
var obj_w = {
    "code": 200, //成功响应码
    "message": "操作成功", //相关信息
    "success": true, //成功响应
    "data": {
        "pageSize": 9, //每页记录数，即每页显示的数量
        "pageNum": 1, //第几页，就是请求的是第几页
        "total": 3, //这个条件下的总记录数
        "size": 3, //当前页的数据数量<=pageSize
        "pages": 1, //总页数

        // 这个list就是数据的条数，就是要渲染的东西
        // div.innerText=obj.list[0].itemIntroduction等等
        "list": [
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "为老弱病残群体服务，北京大学爱心社组织志愿活动,常规活动包括关爱儿童，老年人", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "项目介绍", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/1.png", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "项目介绍", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/1.png", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "项目介绍", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/1.png", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "项目介绍", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/1.png", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "项目介绍", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/1.png", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "项目介绍", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/1.png", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "项目介绍", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/1.png", //封面
                "duration": "项目预计持续时间", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 4,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 2, //id
                "itemTitle": "加油2", //标题
                "itemIntroduction": "一起加油2", //项目介绍
                "gmtCreate": "2021-10-16 20:32:47", //创建时间
                "userId": 1, //创建者id
                "coverUrl": "/upload/images/avatars/item/be420a23a1854c5992e8e54687082d0c.jpg", //封面
                "duration": "1个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "area": "",

                "status": 1 //现在先忽略该字段
            },
            {
                "itemId": 3, //id
                "itemTitle": "爱心", //标题
                "itemIntroduction": "爱心之旅", //项目介绍
                "gmtCreate": "2021-10-19 21:17:15", //创建时间
                "userId": 2, //创建者id
                "coverUrl": "/upload/images/avatars/item/udiwdi.png", //封面
                "duration": "2个月", //预计持续时间
                "itemCategory": "社区服务", //分类
                "maximumNumberLimit": 10, //最大人数
                "currentHeadcount": 2,

                "province": "44",
                "city": "01",
                "status": 1 //现在先忽略该字段
            }
        ] //获取到的数据
    } //返回数据
};
// console.log(obj);

// InputEvent.value.trim();


// addEventListener("click",()=>{
//    var fileInput= document.createElement("input");
//    fileInput.setAttribute();
