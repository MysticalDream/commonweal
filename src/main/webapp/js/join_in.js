window.addEventListener('load', () => {
    let first = document.querySelector('.first');
    let second = document.querySelector('.second');
    let wait = document.querySelector('.wait');
    // 获取项目id
    first.addEventListener('click', () => {
            wait.style.display = 'block';
            let timer = setTimeout(() => {
                clearTimeout(timer)
                wait.style.display = 'none';
            }, 2000);
        })
        // let itemId = getCookie("itemId");
    second.addEventListener('click', () => {
        // 给个标记 然后跳转
        window.location.href = 'join_in_box.html';
        // 要获取团队人数
        // targetid!=2
    })

})