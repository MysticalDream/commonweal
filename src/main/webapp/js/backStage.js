window.addEventListener('load',()=>{
    let lis=$('.hash_click>ul>li');
    for(let i=0;i<lis.length;i++){
        lis[i].addEventListener('click',()=>{
            for(let j=0;j<lis.length;j++){
                lis[j].classList.remove('cur_li_d');
            }
            lis[i].classList.add('cur_li_d');
        })
    }
})