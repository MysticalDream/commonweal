window.addEventListener('load', () => {
    let first = document.querySelector('.mask_join>.first');
    let second = document.querySelector('.mask_join>.second');
    let wait = document.querySelector('.wait');
    let mask_join = document.querySelector('.mask_join');
    let mask_join_in = document.querySelector('.mask_join_in');
    let icon_fork = document.querySelector('.icon-fork');
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
        // window.location.href = 'join_in_box.html';
        mask_join.style.display = 'none';
        setTimeout(() => {
                mask_join_in.style.display = 'block';
                mask_join_in.classList.add('drop_down');
            }, 200)
            // 要获取团队人数
            // targetid!=2
    })
    icon_fork.addEventListener('click', () => {
        mask_join.style.display = 'none';
    })
})