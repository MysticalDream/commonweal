window.addEventListener('load', () => {
    const buttons = document.querySelectorAll('.button-text');
    const output = document.querySelector('.output');
    const fontSize = document.querySelector('#fontSize');
    const emjoy = document.querySelector('.emjoy');
    const emjoys = document.querySelector('.emjoys');
    const submit = document.querySelector('.submit');
    let comments = document.querySelector('#comments');
    const cards = document.querySelector('.cards').children;
    const contain = document.querySelector('.contain');
    const text = document.querySelector('.text');
    const returnb = document.querySelector('.return');
    const back = document.querySelector('.back');
    const sign = document.querySelector('.sign input');
    // 控制点击表情按钮会不会输出表情的
    let flag = false;
    // 控制点击表情按钮 那个表情的盒子出现不出现的
    let flag2 = false;
    let flag3 = true;
    let a;
    let font = 3;
    let that;
    setTimeout(() => {
        for (let i = 0; i < cards.length; i++) {
            cards[i].style.animation = 'unset';
        }
    }, 1000);
    // 获取拼接的参数
    function isNumber(val) {
        var reg = /^[0-9]+\.?[0-9]*$/;
        return reg.test(val);
    };

    function getUrlParamObject(url) {
        const params = url.slice(url.indexOf('?') + 1, url.length);
        const group = params.split('&');
        const data = new Object();
        for (const index in group) {
            const arr = group[index].split('=');
            data[arr[0]] = isNumber(arr[1]) ? parseFloat(arr[1]) : arr[1];
        }
        return data;
    };
    // 调节字体大小的
    // fontSize.addEventListener('click', () => {
    //         document.execCommand(fontSize.dataset.commad, false, 1);
    //     })
    fontSize.addEventListener('change', () => {
            // buttons[2].click();
            // alert(1);
            // console.log(buttons[2]);
            // console.log(parseInt(fontSize.options[fontSize.selectedIndex].text));
            if (parseInt(fontSize.options[fontSize.selectedIndex].text) === 12) {
                font = 1;
            } else if (parseInt(fontSize.options[fontSize.selectedIndex].text) === 18) {
                font = 2;
            } else if (parseInt(fontSize.options[fontSize.selectedIndex].text) === 24) {
                font = 3;
            } else if (parseInt(fontSize.options[fontSize.selectedIndex].text) === 30) {
                font = 4;
            } else if (parseInt(fontSize.options[fontSize.selectedIndex].text) === 36) {
                font = 5;
            } else if (parseInt(fontSize.options[fontSize.selectedIndex].text) === 42) {
                font = 6;
            } else if (parseInt(fontSize.options[fontSize.selectedIndex].text) === 48) {
                font = 7;
            }
            document.execCommand("fontsize", false, font);
        })
        // 点击表情那个按钮的
    emjoy.addEventListener('click', (e) => {
        // a = String.fromCodePoint('😀'.codePointAt());
        // console.log(e.target.nodeName == "LI");
        emjoys.style.display = 'block';

        if (e.target.nodeName == "LI") {
            // a = e.target.innerText;
            flag = true;
            a = String.fromCodePoint(e.target.innerText.codePointAt());
            emjoys.style.display = 'none';
            // console.log(emjoys);
        } else {
            flag = false;
        }
        if (!flag2) {
            emjoys.style.display = 'block';
            flag2 = true;
        } else {
            emjoys.style.display = 'none';
            flag2 = false;
        }
    })


    for (let i = 0; i < buttons.length; i++) {
        // 加粗和斜体不能同时选中 如果flag3为false的话 就说明 那个按钮只点了一次 点完后给它给为true 如果为true说明是再次点击 就要
        buttons[i].addEventListener('click', () => {
            flag3 = true;
            let theEvent = buttons[i].dataset.commad;
            if (buttons[i].classList.contains('choice')) {
                buttons[i].classList.remove('choice');
                flag3 = false;
            }
            if ((i >= 3 && i <= 5) || i == 10) {
                for (let j = 0; j < buttons.length; j++) {
                    if (buttons[j].classList.contains('choice')) {
                        // buttons[j].click();
                        let theEvent2 = buttons[j].dataset.commad;
                        if (theEvent2 === 'xiaolian' && flag) {
                            // output.innerHTML += a;
                            document.execCommand('insertText', false, a);
                            // expression.insertText(a);
                        } else {
                            document.execCommand(theEvent2, false, null);
                        }
                    }
                    buttons[j].classList.remove('choice');
                }
                //真的就进去执行
                if (flag3) {
                    buttons[i].classList.add('choice');
                }
            }
            if (theEvent === 'xiaolian' && flag) {
                // output.innerHTML += a;
                document.execCommand('insertText', false, a);
                // expression.insertText(a);
            } else {
                document.execCommand(theEvent, false, null);
            }
        })
    }


    // ----------------------
    for (let i = 0; i < cards.length; i++) {
        cards[i].addEventListener('click', () => {
                that = cards[i];
                // console.log(i);
                // 点击后其他的循环一次给隐藏掉 就这个是显示的  
                for (let j = 0; j < cards.length; j++) {
                    cards[j].classList.add('nochoice');
                }
                cards[i].classList.remove('nochoice');
                cards[i].classList.add('choice');
                // 点击后让这个大盒子隐藏 显示出编辑页面 
                setTimeout(() => {
                    contain.style.opacity = 0;
                    contain.style.zIndex = 10;
                    text.style.opacity = 1;
                    text.style.zIndex = 11;
                }, 2000);
            })
            // 给所有的卡片添加一个id
        cards[i].dataset.cardId = i;
    }

    submit.addEventListener('click', () => {

        ajax({
            type: 'post',
            url: '/wall',
            data: {
                content: output.innerHTML,
                signature: sign.value,
                cardId: that.dataset.cardId,
                flag: getUrlParamObject(window.location.href)['flag']
            },
            header: {
                'Content-Type': 'application/json'
            },
            success: function(data) {
                if (data.code === 200) {
                    console.log(data);
                    window.location.href = 'cloudWall.html';
                } else {
                    let warn = document.querySelector('.warn');
                    warn.style.display = 'block';
                    let timer_warn = setTimeout(() => {
                        if (timer_warn) {
                            clearTimeout(timer_warn);
                        }
                        warn.style.display = 'none';
                    }, 2000);
                }
            }
        });
        output.innerHTML = '';
        // 点击后返回刚刚我要写的页面
        comments.value = output.innerHTML;

    })
    returnb.addEventListener('click', () => {
        output.innerHTML = '';
        // 让所有的小卡片恢复显示状态 然后要改变一开始被选中的卡片
        for (let j = 0; j < cards.length; j++) {
            // cards[j].style.display = 'block';
            // cards[j].style.opacity = 1;
            cards[j].classList.remove('nochoice');
            cards[j].classList.add('renew');
            if (cards[j].classList.contains('choice')) {
                cards[j].classList.remove('choice');
            }
        }
        contain.style.opacity = 1;
        contain.style.zIndex = 11;
        text.style.opacity = 0;
        text.style.zIndex = 10;

    })
    back.addEventListener('click', () => {
        window.location.href = 'cloudWall.html';
    })
})