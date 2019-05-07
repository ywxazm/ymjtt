/* 商品类别顶层目录 */
var topProductCat = 1;
/* 中间大图 */
var bigImg = 30;
/* 右侧小图 */
var smallImg = 33;
/* 京东快报 */
var jdnews = 116;
/* 快捷链接 */
var kjLink = 117;

/* 商品类别Url */
var productCatUrl = 'portal/indexControl/';
/* 内容Url */
var contentUrl = 'portal/content/';

$(function () {
    /* 商品类别 */
    $.get(productCatUrl + "listProductCat?pid=" + topProductCat, function (data) {
        var addHtml = '';
        for (var i = 0; i < data.length; i++) {
            addHtml += '<div style="padding-top: 12px;"><a href="#" style="color: #5E5E5E; font-size: 16px">'+ data[i].productCatName +'</a></div>';
        }
        $("#productCatList").html(addHtml);
    });

    //中间轮播图
    $.get(contentUrl +  'listContent?catId=' + bigImg, function (data) {
        var olHtml = '';
        var divHtml = '';
        for(var i = 0; i < data.length; i++) {
            olHtml += '<li data-target="#myCarousel" data-slide-to="'+ i +'"></li>';
            if(i == 0) {
                divHtml += '<div class="item active">';
            }else {
                divHtml += '<div class="item">';
            }
            divHtml += '<a href="'+ data[i].url +'"><img src="'+ data[i].image +'" alt="'+ data[i].contentDesc +'" style="width: 750px; height: 510px;"></a>';
            divHtml += '</div>';
        }
        $("#centerImg > div > ol").append(olHtml);
        $("#centerImg > div > div").append(divHtml);
    });

    //右侧小图
    $.get(contentUrl +  'listContent?catId=' + smallImg, function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<a href="'+ data[i].url +'"><img src="'+ data[i].image +'" style="width: 160px; height: 172px; padding-bottom: 5px"></a>';
        }
        $("#rightImg").append(addHtml);
    });

    //京东快报
    $.get(contentUrl +  'listContent?catId=' + jdnews, function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            var contentName = data[i].contentName;
            if (contentName.length < 15) {
                addHtml += '<p><a href="#" style="color: #5E5E5E">'+ data[i].contentName +'</a></p>';
            }else {
                contentName = contentName.substring(0, 12);
                addHtml += '<p><a href="#" style="color: #5E5E5E">'+ contentName +'...</a></p>';
            }
        }
        $("#jdNews").append(addHtml);
    });

    //快捷链接
    $.get(contentUrl +  'listContent?catId=' + kjLink, function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            if (i%4 != 0) {
                addHtml += '<div style="width: 10px; display: inline; padding-left:20px; padding-top: 25px; text-align: center">';
            }else {
                addHtml += '<div style="width: 10px; display: inline; padding-top: 25px; text-align: center">';
            }
            addHtml += '<a href="#" style="color: #5E5E5E" class="'+ data[i].url +'"><p style="font-size: 4px">'+ data[i].contentName +'</p></a>';
            addHtml += '</div>';
        }
        $("#kjLink").append(addHtml);
    });


});