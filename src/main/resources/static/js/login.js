//隐藏错误提示
$('.icons').hide();
$('.modal_icons').hide();

//登录表单验证
var phone = $("#phone");
var phone_null = $("#phone_null");
var phone_error = $("#phone_error");
var password = $("#password");
var password_null = $("#password_null");
var login_error = $('.login_error');


//电话输入框失去焦点
phone.blur(function () {
    var phone_len = phone.val().length;
    if (phone_len === 0) {
        phone_null.show();
    } else {
        var pattren = /^1[345789]\d{9}$/;
        var phone_value = phone.val();
        if (!pattren.test(phone_value)) {
            phone_error.show();
        }
    }
});
//电话输入框获得焦点
phone.focus(function () {
    phone_null.hide();
    phone_error.hide();
    login_error.css("visibility", "hidden");
});

//密码输入框获得焦点 ，失去焦点没有操作
password.focus(function () {
    password_null.hide();
    login_error.css("visibility", "hidden");
});


//登录表单提交验证
var loginFormSubmit = $("#loginFormSubmit");
loginFormSubmit.click(function () {
    var password_len = password.val().length;
    var phone_len = phone.val().length;
    var phone_value = phone.val();
    var pattren1 = /^1[345789]\d{9}$/;
    if (password_len !== 0 && phone_len !== 0 && pattren1.test(phone_value)) {
        return true;
    } else {
        if (phone_len === 0) {
            phone_null.show();
        }
        if (!pattren1.test(phone_value) && phone_len !== 0) {
            phone_error.show();
        }
        if (password_len === 0) {
            password_null.show();
        }
        return false;
    }
});




