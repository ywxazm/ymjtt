$(function () {
    //顶端导航
    $.get('../../portal/listProcess/' + 'listContent?catId=100', function (data) {
        var addHtml = '';
        for (var i = 0; i < data.length; i++) {
            addHtml += '<span style="padding-right: 15px;"><a href="#" style="color: #a7b1c2">' + data[i].contentName + '</a></span>';
        }
        $("#topList3").append(addHtml);
    });

    //公司标识图
    $.get('../../portal/listProcess/' + 'listContent?catId=103', function (data) {
        $("#logo").attr("src", data[0].image);
    });

    //选项卡
    $.get('../../portal/listProcess/' +  'listContent?catId=99', function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<li><a href="#">'+ data[i].contentName +'</a></li>';
        }
        $("#tabList > ul").append(addHtml);
    });

    //加载商品
    $.get("../../portal/product/" + "list", function (data) {
        var p = data.rows;
        var addHtml = "";
        for (var i = 0; i < p.length; i++) {
            addHtml += '<div style="width: 200px; height: 300px; float: left; padding-left: 10px">';
            addHtml += '<div><img src="' + p[i].image +'" style="width: 190px; height: 190px"></div>';
            addHtml += '<div style="padding-left: 60px">'+ "¥" +p[i].basePrice + ".00" +'</div>';
            addHtml += '<div>'+ p[i].productName +'</div>';
            addHtml += '</div>';
        }
        $("#productList").append(addHtml);
    });
});