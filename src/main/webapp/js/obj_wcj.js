
var obj_w = {
    "code": 200, //成功响应码
    "message": "操作成功", //相关信息
    "success": true, //成功响应
    "data": {
        "pageSize": 9, //每页记录数，即每页显示的数量
        "pageNum": 1, //第几页，就是请求的是第几页
        "total": 3, //这个条件下的总记录数
        "size": 3, //当前页的数据数量  (<=pageSize)
        "pages": 1, //总页数

        // 这个list就是数据的条数 就是要渲染的东西
        "list": [
            {
                "itemId": 1, //id
                "itemTitle": "测试项目", //标题
                "itemIntroduction": "为老弱病残群体服务，北京大学爱心社组织志愿活动,常规活动包括关爱儿童，老年人", //项目介绍
                "gmtCreate": "2021-10-16 20:15:58", //创建时间
                "userId": 1, //创建者id
                "coverUrl": '../../images/lunbotu3.jpg', //封面
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
                "coverUrl": '../../images/lunbotu3.jpg', //封面
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
                "coverUrl": '../../images/lunbotu3.jpg', //封面
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

var obj_pet={
    "code": 200, 
     
    "message": "操作成功", 
     
    "success": true, 
     
    "data": {
    "pageSize": 9, 
     
    "pageNum": 1, 
     
    "total": 2, 
     
    "size": 2, 
     
    "pages": 1, 
     
    "list": [
    {
    "adoptId": 1, 
     
    "variety": "拉布拉多", 
     
    "appearance": "黑色", 
     
    "age": 10, 
     
    "characters": "调皮", 
     
    "habit": "喜欢外出", 
     
    "introduction": "这个是一只狗", 
     
    "coverUrl": "/upload/images/adopt/af5df61d7f4d4297c9cc3b58870403.png", 
     
    "gmtCreate": "2021-10-23 14:15:02", 
     
    "gmtModified": "2021-10-23 14:17:53" 
     
    },
    {
    "adoptId": 2, 
     
    "variety": "拉布拉多", 
     
    "appearance": "白色", 
     
    "age": 5, 
     
    "characters": "安静", 
     
    "habit": "喜欢外出", 
     
    "introduction": "这个是一只狗", 
     
    "coverUrl": "/upload/images/adopt/a57f5df61d7f4d4297c9cc3b58870403.png", 
     
    "gmtCreate": "2021-10-23 14:16:51", 
     
    "gmtModified": "2021-10-23 14:17:40" 
     
    }
    ] 
     
    } 
     
    }

var obj_show={
    "code": 200,
    "message": "操作成功",
    "success": true, //成功响应
    "data": {
    "pageSize": 9,
    "pageNum": 1,
    "total": 4,
    "size": 4,
    "pages": 1,
    "list": [
    {
    "id": 4, //成就id
    "coverUrl": "/upload/images/achievements/2.png", //成就封面
    "introduction": "介绍一下这是,项目id为1的成就", //成就介绍
    "type": 2, //对应成就类型代号 1--项目成就 2----团队成就
    "typeId": 1, //对应项目或则团队的id
    "gmtCreate": "2021-10-28 17:04:01", //创建时间
    "gmtModified": "2021-10-28 17:04:01",
    "loveNumber": 0, //点赞数量
    "title": "联合会", //成就标题
    "loved": false 
     
    },
    {
    "id": 3, //成就id
    "coverUrl": "/upload/images/achievements/2.png", //成就封面
    "introduction": "介绍一下这是,项目id为1的成就", //成就介绍
    "type": 1, //对应成就类型代号 1--项目成就 2----团队成就
    "typeId": 3, //对应项目或则团队的id
    "gmtCreate": "2021-10-28 17:03:54", //创建时间
    "gmtModified": "2021-10-28 17:03:54",
    "loveNumber": 0, //点赞数量
    "title": "联合会", //成就标题
    "loved": false 
     
    },
    {
    "id": 2, //成就id
    "coverUrl": "/upload/images/achievements/2.png", //成就封面
    "introduction": "介绍一下这是,项目id为1的成就", //成就介绍
    "type": 1, //对应成就类型代号 1--项目成就 2----团队成就
    "typeId": 2, //对应项目或则团队的id
    "gmtCreate": "2021-10-28 17:03:46", //创建时间
    "gmtModified": "2021-10-28 18:38:16",
    "loveNumber": 1, //点赞数量
    "title": "联合会", //成就标题
    "loved": true 
     
    },
    {
    "id": 1, //成就id
    "coverUrl": "/upload/images/achievements/9f731ec96a474f759c4c9389f11242a5.png", //成就封面
    "introduction": "介绍一下这是,项目id为1的成就", //成就介绍
    "type": 1, //对应成就类型代号 1--项目成就 2----团队成就
    "typeId": 1, //对应项目或则团队的id
    "gmtCreate": "2021-10-22 09:51:08", //创建时间
    "gmtModified": "2021-10-28 18:38:01",
    "loveNumber": 1, //点赞数量
    "title": "联合会", //成就标题
    "loved": true 
     
    }
    ]
    } //返回数据
    }

var obj_expand={
    "code": 200, 
     
    "message": "操作成功", 
     
    "success": true, 
     
    "data": {
    "pageSize": 9, 
     
    "pageNum": 1, 
     
    "total": 1, 
     
    "size": 1, 
     
    "pages": 1, 
     
    "list": [
    {
    "recruitId": 1, 
     
    "introduction": "招募志愿者了，快来呀", 
     
    "begin": "2021-10-20 00:00:00", 
     
    "end": "2021-10-30 00:00:00", 
     
    "location": "广东广州", 
     
    "coverUrl": "/upload/images/recruit/32aecad833c54f4b86bb5fdd16529f40.png", 
     
    "maximumNumberLimit": 100, 
     
    "currentHeadcount": 0, 
     
    "province": "44", 
     
    "city": "01", 
     
    "area": "", 
     
    "title": "支援招募" 
     
    }
    ] 
     
    } 
     
    }