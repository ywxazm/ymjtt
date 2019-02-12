$(function () {
    //顶端导航
    $.get('portal/listProcess/' +  'listContent?catId=100', function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<span style="padding-right: 15px;"><a href="#" style="color: #a7b1c2">'+ data[i].contentName +'</a></span>';
        }
        $("#topList3").append(addHtml);
    });

    //左侧商品类别
    $.get('portal/listProcess/' +  'listProductCat?pid=1', function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<li id="'+ data[i].productCatId +'"><a style="padding: 2px 15px">'+ data[i].productCatName +'</a></li>';
        }
        $("#productCatList > ul").append(addHtml);
    });

    //选项卡
    $.get('portal/listProcess/' +  'listContent?catId=99', function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<li><a href="#">'+ data[i].contentName +'</a></li>';
        }
        $("#tabList > ul").append(addHtml);
    });

    //中间轮播图
    $.get('portal/listProcess/' +  'listContent?catId=30', function (data) {
        var olHtml = '';
        var divHtml = '';
        for(var i = 0; i < data.length; i++) {
            olHtml += '<li data-target="#myCarousel" data-slide-to="'+ i +'"></li>';
            if(i == 0) {
                divHtml += '<div class="item active">';
            }else {
                divHtml += '<div class="item">';
            }
            divHtml += '<a href="'+ data[i].url +'"><img src="'+ data[i].image +'" alt="'+ data[i].contentDesc +'" style="width: 650px; height: 480px;"></a>';
            divHtml += '</div>';
        }
        $("#centerImg > div > ol").append(olHtml);
        $("#centerImg > div > div").append(divHtml);
    });

    //右侧小图
    $.get('portal/listProcess/' +  'listContent?catId=33', function (data) {
        var addHtml = '';
        for(var i = 0; i < data.length; i++) {
            addHtml += '<a href="'+ data[i].url +'"><img src="'+ data[i].image +'" style="width: 150px; height: 160px"></a>';
        }
        $("#rightImg").append(addHtml);
    });
});
