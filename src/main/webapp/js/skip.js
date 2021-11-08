window.addEventListener('load', () => {

    // 志愿项目
    let volunteer = document.querySelector('.volunteer_more');
    // 风采展示
    let show = document.querySelector('.show_more');
    // 志愿者招募
    let recruitment = document.querySelector('.recruitment_more');
    // 助农模块
    let agriculture = document.querySelector('.agriculture_more');
    // 领养动物
    let adopt = document.querySelector('.adopt_more');
    // 关爱儿童
    let children = document.querySelector('.children_more');
    volunteer.addEventListener('click', () => {
        window.location.href = 'pages/common/newPro.html';
    })
    recruitment.addEventListener('click', () => {
        window.location.href = 'pages/common/expand.html';
    })
    show.addEventListener('click', () => {
        window.location.href = 'pages/common/show.html';
    })
    agriculture.addEventListener('click', () => {
        window.location.href = 'pages/common/agriculture.html';
    })
    adopt.addEventListener('click', () => {
        window.location.href = 'pages/common/adpot.html';
    })
})