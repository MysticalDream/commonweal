window.addEventListener('load', () => {
    const childteach = document.querySelector('.childteach');
    const childhelp = document.querySelector('.childhelp');
    const childwall = document.querySelector('.childwall');
    const child_s = document.querySelector('.child_s');
    const child_all = document.querySelector('.child_all');
    child_s.addEventListener('mouseover', () => {
        child_all.style.display = 'block'
    })
    child_s.addEventListener('mouseleave', () => {
        child_all.style.display = 'none'
    })
    childteach.addEventListener('click', () => {
        window.location.href = '/pages/lovekid.html';
    })
    childhelp.addEventListener('click', () => {
        window.location.href = '/pages/cloudHelp.html';
    })
    childwall.addEventListener('click', () => {
        window.location.href = '/pages/cloudWall.html';
    })
})