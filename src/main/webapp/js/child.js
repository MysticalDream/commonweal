window.addEventListener('load', () => {
    let childLi = document.querySelector('.child-li');
    let childOl = document.querySelector('.child-ol');
    let indexNav = document.querySelector('.index_nav');
    childLi.addEventListener('mouseover', () => {
        console.log(1);
        childOl.style.display = 'block';
    })
    indexNav.addEventListener('mouseleave', () => {
        childOl.style.display = 'none';
    })
})