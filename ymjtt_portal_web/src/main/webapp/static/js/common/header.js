/*公司图标*/
var companyImgId = 34;
/* 登录信息等 */
var loginInfoId = 100;
/* 选项卡 */
var optionId = 99;

/*内容管理url*/
var contentUrl = 'portal/content/';

$(function () {
    /* 公司图标 */
    $.get(contentUrl + "listContent?catId=" + companyImgId, function (data) {
        $("#firstHeaderCompanyImg > img").attr("src", data[0].image);
    });

    /* 定位地址 */

    /* 登录信息等 */
    $.get(contentUrl + "listContent?catId=" + loginInfoId, function (data) {
        var addHtml = '';
        addHtml += '<span style="font-size:14px">您好,请登录&nbsp;&nbsp;</span>';
        addHtml += '<span><a href="#" style="font-size:14px; color: red">免费注册</a></span>';
        addHtml += '<span style="padding-right: 10px; padding-left: 10px">|</span>';
        for (var i = 0; i < data.length; i++) {
            addHtml += '<a href="'+ data[i].url +'">';
            addHtml += '<span style="font-size:14px; color: #5E5E5E;">'+ data[i].contentName +'</span>';
            if (i != data.length - 1) {
                addHtml += '<span style="padding-right: 10px; padding-left: 10px">|</span>';
            }
            addHtml += '</a>';
        }
        $("#firstHeaderLoginInfo").html(addHtml);
    });

    /* 选项卡 */
    $.get(contentUrl + "listContent?catId=" + optionId, function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<li><a href="#" style="color: #5E5E5E; font-size: 14px">'+ data[i].contentName +'</a></li>';
        }
        $("#tabList > ul").append(addHtml);
    });
});