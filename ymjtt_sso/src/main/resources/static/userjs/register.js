var salt = "ywx_aymj";
var url = "./../register";

//用户输入校验
$(function () {
    console.log("111111");
    //用户名验证
    $('#id_userNick').bind("blur", function () {
        console.log("222222");
        var userNick = $('#id_userNick').val().trim();
        var msg = "";
        if (null == userNick) {
            msg = "用户名不可为空";
        }else if (userNick.length < 4) {
            msg ="用户名不可少于4位";
        }else {
            $.get(url + "/isExist/" + userNick, function (data) {
                msg = data.msg;
                console.log(data.msg);
            });
        }

    });

    //离焦检查2次输入密码是否一致
    $('#id_pwdAgain').bind("blur", function () {
        console.log("33333");
        var pwd = $('#id_pwd').val().trim();
        var pwdAgain = $('#id_pwdAgain').val().trim();
        var msg = "";
        if (null == pwd) {
            msg = "密码不可以为空";
        }
        if (pwd.length < 8) {
            msg = "密码不可少于8位数";
        }
        if (pwd != pwdAgain) {
            msg = "两次输入密码不一致";
        }
        console.log(msg);
    });
});

//密码加盐后md5
function slatMD5(pwd) {
    hex_md5(pwd.trim());
    
}