
/* 获取级菜单列表 */
function setMenus(currentMenuNum) {
    for (var i = currentMenuNum + 1; i < 5; i++) {
        $("#dropdown" + i).css("display", "none");
        $("#dropdown" + i + " button").text('--请选择--');
        $("#dropdown" + i + " button").append('<span class="caret"></span>');
    }

    var pid = $("#cidValue").val();
    pid = (pid == '') ? 0 : pid;
    var url = '../../manager/productCat/listByPid' + '?pid=' + pid;
    $.get(url, function (msg) {
        var data = msg.data;
        if (null == data || '' == data) {
            return;
        }
        var addHtml = '';
        for (var i = 0; i < data.length; i++) {
            addHtml += '<li role="presentation">';
            addHtml += '<a role="menuitem" onclick="setMenuName(' + data[i].id + ', ' + currentMenuNum + ')" tabindex="-1" href="#">' + data[i].name + '</a>';
            addHtml += '</li>';
        }
        $("#dropdown" + currentMenuNum + " ul").html(addHtml);
    });
}

/* 设置menu名称显示, 显示下一级菜单Html */
function setMenuName(id, currentMenuNum) {
    var url = '../../manager/productCat/getById?id=' + id;
    $.get(url, function (msg) {
        var data = msg.data;
        $("#dropdown" + currentMenuNum + " button").text(data.productCatName);
        $("#cidValue").val(data.productCatId);
        if(data.parentCat == 1) {
            $("#dropdown" + ++currentMenuNum).css("display", "block");
        }
    });
}
