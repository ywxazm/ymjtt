/*footerFirst*/
var footerFirstId = 106;
/*footerSecond*/
var footerSecond = "109,110,111,112,113,114";
/*footerThree*/
var footerThree = 108;
/*footerFour*/
var footerFour = 115;

/*内容管理url*/
var contentUrl = 'portal/content/';

$(function () {
    /*footerFirst*/
    $.get(contentUrl + "listContent?catId=" + footerFirstId, function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<b style="padding-left: 50px; font-size: 20px; padding-top: 20px;">'+ data[i].contentName +'</b>';
        }
        $("#oneFooter").html(addHtml);
    });

    /*footerSecond*/
    $.get(contentUrl + "listContents?catIds=" + footerSecond, function (data) {
        var addHtml = '';
        for (var i = 0; i < data.length; i++) {
            addHtml += '<div class="col-md-2 column" style="padding-top: 1px">';
            addHtml += '<ul>';
            for (var j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    addHtml += '<li style="list-style-type:none; padding-bottom: 10px"><a href="#"><b style="font-size: 14px;color: #5E5E5E">' + data[i][j].contentName + '</b></a></li>';
                }else if(!(i == data.length - 1 && j == data[i].length - 1)){
                    addHtml += '<li style="list-style-type:none; padding-bottom: 5px"><a href="#" style="color: #5E5E5E">' + data[i][j].contentName + '</a></li>';
                }

                if (i == data.length - 1 && j == data[i].length - 1) {
                    addHtml += '<li style="width: 200px; list-style-type:none; padding-bottom: 5px"><a href="#" style="color: #5E5E5E">' + data[i][j].contentName + '</a></li>';
                    addHtml += '<a style="color: #5E5E5E; padding-left: 50px">查看详情>></a>';
                }
            }
            addHtml += '</ul>';
            addHtml += '</div>';
        }
        $("#twoFooter").html(addHtml);
    });

    /*footerThree*/
    $.get(contentUrl + "listContent?catId=" + footerThree, function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<a href="#"><span style="color: #5E5E5E"> '+ data[i].contentName +' </span> | </a>';
        }
        $("#threeFooter").html(addHtml);
    });

    /*footerFour*/
    $.get(contentUrl + "listContent?catId=" + footerFour, function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<a href="#"><span style="color: #5E5E5E"> '+ data[i].contentName +' </span> | </a>';
        }
        $("#fourFooter").html(addHtml);
    });
});