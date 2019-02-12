var method = '';

$(function () {
    openMenu(topNodeParentId);

    //点击新增按钮
    $("#btnAdd").on("click", function () {
        $("#editForm input").val("");
        method = "save";
    });

    //点击提交按钮
    $("#btnSave").on("click", function () {
        var formData = $('#editForm').serializeJSON();
        $.post(modelUrl + method, formData, function (data) {
            if (data.code == 200) {
                location.reload();
                successMsg(data.msg);
                return;
            }
            failMsg(data.msg);
        });
    });

});

//展开菜单
function openMenu(nodeId) {
    $("#" + nodeId + " span[class='glyphicon glyphicon-plus']").attr("class", "glyphicon glyphicon-minus");
    if ($("#" + nodeId + " div").html() != undefined) {
        packUpMenu(nodeId);
        return;
    }
    $.ajax({
        type: "GET",
        async: false,
        url: modelUrl +  'listByParentId?parentId=' + nodeId,
        success: function(data){
            if (200 == data.code) {
                var array = data.data;
                var addHtml = '';
                for (var i = 0; i < array.length; i++) {
                    addHtml += '<div class="dd-handle" style="margin:2px; padding:5px 20px" id="'+ array[i].nodeId +'">';
                    if (array[i].ifNextNode == true) {
                        addHtml += '<span onclick="openMenu('+ array[i].nodeId +')">';
                        addHtml += '<span class="glyphicon glyphicon-plus">&nbsp;</span>';
                    }else {
                        addHtml += '<span>';
                        addHtml += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                    }
                    addHtml += '<span class="label label-default"><i class="'+ array[i].nodeIcon +'"></i></span>&nbsp;&nbsp;' + array[i].nodeName;
                    addHtml += '</span>';
                    addHtml += '<label style="position: absolute; right: 100px;">';
                    addHtml += '<button class="btn btn-default btn-xs" data-toggle="modal" onclick="loadNodeData('+ array[i].nodeId +')" data-target="#myModal" >修改</button>';
                    addHtml += '</label>';
                    addHtml += '<label style="position: absolute; right: 55px;">';
                    addHtml += '<button class="btn btn-default btn-xs" onclick="remove('+ array[i].nodeId +')">删除</button>';
                    addHtml += '</label>';
                    addHtml += '</div>';
                }
                $("#" + nodeId).append(addHtml);
            }
        }
    });
}

//收缩菜单
function packUpMenu(nodeId) {
    $("#" + nodeId + " div").remove();
    $("#" + nodeId + " span[class='glyphicon glyphicon-minus']").attr("class", "glyphicon glyphicon-plus");
}

//删除
function remove(menuId) {
    $.get(modelUrl +  'remove?id=' + menuId, function (data) {
        if (data.code == 200) {
            location.reload();
            successMsg(data.msg);
            return;
        }
        failMsg(data.msg);
    });
}



/**
 *

 <div class="alert alert-success" id="successMsg" style="display: none; position: absolute; top: 70px; right: 40px; width: 300px"></div>
 <div class="alert alert-danger" id="failMsg" style="display: none; position: absolute; top: 70px; right: 40px; width: 300px"></div>

 * @author  ywx
 * @date    2019/1/29 16:11
 */

/* 消息提醒 */
function msgProcess(msg) {
    if(200 == msg.code) {
        successMsg(msg.msg);
    }else {
        failMsg(msg.msg);
    }
}
function successMsg(msg) {
    $("#successMsg").text(msg);
    $("#successMsg").css("display", "block");
    setTimeout(function () {
        $("#successMsg").css("display", "none");
    }, 5000);
}
function failMsg(msg) {
    $("#failMsg").text(msg);
    $("#failMsg").css("display", "block");
    setTimeout(function () {
        $("#failMsg").css("display", "none");
    }, 5000);
}


