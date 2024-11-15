<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <% String path=request.getContextPath(); String basePath=request.getScheme() + "://" + request.getServerName() + ":"
        + request.getServerPort() + path + "/" ; %>
        <!doctype html>
        <html lang="en" class="fullscreen-bg">

        <head>
            <base href="<%=basePath%>">
            <title>登录</title>
            <meta charset="utf-8">
            <link rel="stylesheet" href="./assets/css/login.css">

        </head>
        <% String message=(String) request.getAttribute("message"); %>
            <% if (message !=null) { %>
                <div id="error-message"
                    style="color: #fff; background-color: #f44336; padding: 10px; border-radius: 5px;text-align: center; font-weight: bold;">
                    <i class="fas fa-exclamation-circle" style="margin-right: 5px;"></i>
                    <%= message %>
                </div>
                <% } %>
                    <section>
                        <div class="box">
                            <div class="cross" style="--i:0;"></div>
                            <div class="cross" style="--i:1;"></div>
                            <div class="cross" style="--i:2;"></div>
                            <div class="cross" style="--i:3;"></div>
                            <div class="cross" style="--i:4;"></div>
                            <div class="cross" style="--i:5;"></div>


                            <div class="container">
                                <div class="form">
                                    <h2>协会管理系统</h2>
                                    <form id="loginForm" action="/studentSociety/student/login" method="post">
                                        <div class="inputBx">
                                            <input type="text" id="login" name="account" required="required">
                                            <span>账号</span>
                                            <i class="fas fa-user-circle"></i>
                                
                                        </div>
                                        <span id="accountError" style="color: #f44336; font-size: 12px; display: block; margin-top: 5px;"></span>

                                        <div class="inputBx password">
                                            <input id="password-input" type="password" name="password"
                                                required="required">
                                            <span>密码</span>
                                            <a href="#" class="password-control"
                                                onclick="return show_hide_password(this);"></a>
                                            <i class="fas fa-key"></i>
                                            <span id="passwordError" style="color: #f44336; font-size: 12px; display: block; margin-top: 5px;"></span>

                                        </div>
                                        <label class="remember">
                                            <input type="checkbox"> 记住我
                                        </label>
                                        <div class="inputBx">
                                            <input type="submit" value="Log in" disabled>
                                        </div>
                                    </form>
                                    <p>忘记密码？ <a href="#">点这里</a></p>
                                    <p>还没有账号 <a href="#">注册</a></p>
                                </div>
                            </div>
                        </div>

                    </section>
<script>
 document.addEventListener('DOMContentLoaded', function () {
    const loginInput = document.getElementById('login');
    const passwordInput = document.getElementById('password-input');
    const submitButton = document.querySelector('input[type="submit"]');
    const accountError = document.getElementById('accountError');
    const passwordError = document.getElementById('passwordError');

    // 清空错误提示的函数
    function clearErrorMessages() {
        if (accountError) accountError.innerText = '';
        if (passwordError) passwordError.innerText = '';
    }

    // 验证输入框内容
    function validateInputs() {
        let isValid = true;

        if (loginInput.value.trim() === '') {
            accountError.innerText = '账号不能为空';
            isValid = false;
        } else {
            accountError.innerText = '';
        }

        if (passwordInput.value.trim() === '') {
            passwordError.innerText = '密码不能为空';
            isValid = false;
        } else {
            passwordError.innerText = '';
        }

        return isValid;
    }

    // 启用/禁用提交按钮
    function toggleSubmitButton() {
        submitButton.disabled = !(loginInput.value && passwordInput.value);
    }

    // 初始化页面时，清空错误提示并检查按钮状态
    clearErrorMessages();
    toggleSubmitButton();

    // 监听输入事件，以动态调整提交按钮状态
    loginInput.addEventListener('input', toggleSubmitButton);
    passwordInput.addEventListener('input', toggleSubmitButton);

    // 监听提交按钮点击事件，验证输入内容
    submitButton.addEventListener('click', function (event) {
        if (!validateInputs()) {
            event.preventDefault();
        }
    });

    // 错误提示信息自动隐藏
    const errorMessage = document.getElementById('error-message');
    if (errorMessage) {
        setTimeout(() => {
            errorMessage.style.display = 'none';
        }, 1500);
    }
});

// 切换密码显示/隐藏
function show_hide_password(target) {
    const input = document.getElementById('password-input');
    if (input.getAttribute('type') === 'password') {
        target.classList.add('view');
        input.setAttribute('type', 'text');
    } else {
        target.classList.remove('view');
        input.setAttribute('type', 'password');
    }
    return false;
}


</script>
                    <style>
                        .cross {
                            position: absolute;
                            background: rgba(255, 255, 255, 0.1);
                            backdrop-filter: blur(5px);
                            box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
                            border: 1px solid rgba(255, 255, 255, 0.15);
                            border-radius: 15px;
                            animation: cross 10s linear infinite;
                            animation-delay: calc(-1s * var(--i)) !important;

                        }

                        @keyframes cross {

                            0%,
                            100% {
                                transform: translateY(-40px);
                            }

                            50% {
                                transform: translateY(30px);
                            }
                        }

                        .cross:nth-child(1) {
                            width: 100px;
                            height: 100px;
                            top: -15px;
                            right: -45px;
                        }

                        .cross:nth-child(2) {
                            width: 150px;
                            height: 150px;
                            top: 105px;
                            left: -125px;
                            z-index: 2;
                        }

                        .cross:nth-child(3) {
                            width: 60px;
                            height: 60px;
                            bottom: 85px;
                            right: -45px;
                            z-index: 2;
                        }

                        .cross:nth-child(4) {
                            width: 50px;
                            height: 50px;
                            bottom: 35px;
                            left: -95px;
                        }

                        .cross:nth-child(5) {
                            width: 50px;
                            height: 50px;
                            top: -15px;
                            left: -25px;
                        }

                        .cross:nth-child(6) {
                            width: 85px;
                            height: 85px;
                            top: 165px;
                            right: -155px;
                            z-index: 2;
                        }
                    </style>