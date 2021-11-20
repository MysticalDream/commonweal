window.addEventListener('load', () => {
    const buttons = document.querySelectorAll('.button-text');
    const output = document.querySelector('.output');
    const fontSize = document.querySelector('#fontSize');
    const emjoy = document.querySelector('.emjoy');
    const emjoys = document.querySelector('.emjoys');
    // 控制点击表情按钮会不会输出表情的
    let flag = false;
    // 控制点击表情按钮 那个表情的盒子出现不出现的
    let flag2 = false;
    let flag3 = false;
    let a;
    let font = 3;
    // 调节字体大小的
    // fontSize.addEventListener('click', () => {
    //         document.execCommand(fontSize.dataset.commad, false, 1);
    //     })
    fontSize.addEventListener('change', () => {
            // buttons[2].click();
            // alert(1);
            // console.log(buttons[2]);
            console.log(parseInt(fontSize.options[fontSize.selectedIndex].text));
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

    // console.log(String.fromCodePoint('😀'.codePointAt()));
    buttons.forEach((button, index) => {
        button.addEventListener('click', () => {
            let theEvent = button.dataset.commad;
            // 点击后给个背景颜色
            if ((index >= 3 && index <= 5) || index == 10) {
                if (!flag3) {
                    button.style.backgroundColor = 'rgb(205, 235, 246)';
                    flag3 = true;
                } else {
                    button.style.backgroundColor = 'rgb(247, 247, 247)';
                    flag3 = false;
                }
            }
            if (theEvent === 'xiaolian' && flag) {
                output.innerHTML += a;
            } else {
                if (flag3) {
                    console.log(1);
                    document.execCommand(theEvent, false, null);
                }
            }
        })
    })
})