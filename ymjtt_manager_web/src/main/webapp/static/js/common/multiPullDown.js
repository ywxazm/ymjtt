/* 多级目录回显 */
/* html要求
 *
  *                 <div class="form-group" id="multiPullDownDiv">
                        <label>父菜单名称</label>
                        <input type="hidden" class="form-control" name="parentId">
                        <div></div>
                    </div>

        var pullDownUrl = '../../../manager/contentCat/';
        var topNodeParentId = 0;
  *
  * */

$(function () {
    //点击新增按钮
    $("#btnAdd").on("click", function () {
        add();
    });
});

//数据回显之多级下拉框
function echoDisplayDownPull(array) {
    $("#multiPullDownDiv input[name='parentId']").val(array[array.length - 1].lastNodeId);
    //最顶层菜单, 不显示菜单列表
    if (array.length == 1) {
        $("#multiPullDownDiv > label").css("display", "none");
        $("#multiPullDownDiv > div").css("display", "none");
        return;
    }
    $("#multiPullDownDiv > label").css("display", "block");
    $("#multiPullDownDiv > div").css("display", "block");

    var addHtml= '';
    for (var i = 0; i < array.length - 1; i++) {
        addHtml += '<div class="dropdown" style="display:inline; width: 140px" id="'+ "parentId_" + array[i].lastNodeId +'" onclick="loadPullDownList('+ array[i].lastNodeId +')">';
        addHtml += '<button type="button" class="btn dropdown-toggle" id="dropdownNode'+ i +'" data-toggle="dropdown" style="width: 130px; background-color: white;  border-color: #bbbbbb">';
        addHtml += '<span>'+ array[i].nodeName +'</span>';
        addHtml += '<span class="caret"></span>';
        addHtml += '</button>';

        addHtml += '<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu'+ i +'" id="'+ "list_" + array[i].lastNodeId +'">';
        addHtml += '</ul>';
        addHtml += '</div>';
    }
    $("#multiPullDownDiv > div").html("");
    $("#multiPullDownDiv > div").append(addHtml);

}

//加载下拉列表
function loadPullDownList(parentNodeId) {
    //下拉列表已构成, 则不再重复加载
    if ($("#list_" + parentNodeId).html().length > 50) {
        return;
    }

    $.get(pullDownUrl +  'listByParentId?parentId=' + parentNodeId, function (data) {
        if (data.code == 200) {
            if (data.data.length == 0) {
                $("#parentId_" + parentNodeId).remove();
                return;
            }
            var addHtml = '';
            var array = data.data;
            for (var i = 0; i < array.length; i++) {
                addHtml += '<li role="presentation">';
                addHtml += '<a role="menuitem" tabindex="-1" href="#" id="'+ "nodeId_" + array[i].nodeId +'" onclick="changeNode('+ array[i].lastNodeId +', '+ array[i].nodeId +')">'+ array[i].nodeName +'</a>';
                addHtml += '</li>';
            }
            $("#list_" + parentNodeId).html("");
            $("#list_" + parentNodeId).append(addHtml);
        }
    });
}

//选择菜单
function changeNode(lastNodeId, currNodeId) {
    $("#parentId_" + lastNodeId + " > button").html('<span>'+ $("#nodeId_" + currNodeId).text() +'</span><span class="caret"></span>');
    $("#multiPullDownDiv input[name='parentId']").val(currNodeId);

    $("#parentId_" + lastNodeId).nextAll().remove();    //移除此div后面的所有div
    addNullNode(lastNodeId, currNodeId);

}

//添加下一级目录的选择
function addNullNode(lastNodeId, currNodeId) {
    var addHtml = '';
    addHtml += '<div class="dropdown" style="display:inline; width: 140px" id="'+ "parentId_" + currNodeId +'" onclick="loadPullDownList('+ currNodeId +')">';
    addHtml += '<button type="button" class="btn dropdown-toggle" id="dropdownNode'+ currNodeId +'" data-toggle="dropdown" style="width: 130px; background-color: white;  border-color: #bbbbbb">';
    addHtml += '<span>--请选择--</span>';
    addHtml += '<span class="caret"></span>';
    addHtml += '</button>';

    addHtml += '<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownNode'+ currNodeId +'" id="'+ "list_" + currNodeId +'">';
    addHtml += '</ul>';
    addHtml += '</div>';
    $("#parentId_" + lastNodeId).after(addHtml);
}


//点击新增
function add() {
    $("#multiPullDownDiv > label").css("display", "block");
    $("#multiPullDownDiv > div").css("display", "block");

    var addHtml = '';
    addHtml += '<div class="dropdown" style="display:inline; width: 140px" id="'+ "parentId_" + topNodeParentId +'" onclick="loadPullDownList('+ topNodeParentId +')">';
    addHtml += '<button type="button" class="btn dropdown-toggle" id="dropdownNode'+ topNodeParentId +'" data-toggle="dropdown" style="width: 130px; background-color: white;  border-color: #bbbbbb">';
    addHtml += '<span>--请选择--</span>';
    addHtml += '<span class="caret"></span>';
    addHtml += '</button>';

    addHtml += '<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu'+ topNodeParentId +'" id="'+ "list_" + topNodeParentId +'">';
    addHtml += '</ul>';
    addHtml += '</div>';

    $("#multiPullDownDiv > div").html("");
    $("#multiPullDownDiv > div").append(addHtml);
}

