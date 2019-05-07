var salt = "ywx_aymj";
var url = "./../check";

//用户输入校验
$(function () {
    //用户名验证
    $('#id_userNick').bind("change", function () {
        $("#id_loginInfo").empty();
        var userNick = $('#id_userNick').val().trim();
        var msg = "";
        if (userNick.length == 0) {
            msg = "用户名不可为空";
        }else if (userNick.length < 4) {
            msg ="用户名不可少于4位";
        }else {
            $.get(url + "/isExist/" + userNick, function (data) {
                if (data.code != 500101) {
                    $("#id_loginInfo").html("用户名不存在");
                }
            });
        }
        $("#id_loginInfo").html(msg);
    });

});
