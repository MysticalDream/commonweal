window.addEventListener('load', () => {
    let avator_d = document.querySelector('.avator_d');
    if (getCookie("userId") != "undefined") {
        let avator_circle = document.createElement('div');
        avator_circle.classList.add('avator_circle');
        let avator_img = document.createElement('img');
        avator_img.style.width = '68px';
        avator_img.style.height = '68px';
        avator_img.style.borderRadius = '50%';
        avator_circle.appendChild(avator_img);
        let avator_name = document.createElement('div');
        avator_name.classList.add('avator_name');
        let avator_p = document.createElement('p');
        avator_p.innerText = getCookie("username");
        let div_area = document.createElement('div');
        let left_area = document.createElement('span');
        left_area.classList.add('left_area')
        left_area.innerText = '所在地';
        let l_span = document.createElement('span');
        l_span.innerText = getCookie("location");
        div_area.appendChild(left_area);
        div_area.appendChild(l_span);
        avator_name.appendChild(avator_p);
        avator_name.appendChild(div_area);
        avator_d.appendChild(avator_circle);
        avator_d.appendChild(avator_name);
    } else {
        avator_d.innerHTML = '<div class="avator_circle"></div><div class="avator_name"><p>Quester</p><div><span class="left_area">所在地</span><span>地区</span></div></div>'
    }
})