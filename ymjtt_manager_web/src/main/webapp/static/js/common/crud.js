//提交的方法名称
var method = "list";        //方法名
var listParam = "";          //参数
$(function () {
    //加载表格数据
    $('#grid').datagrid({
        url: modelUrl + method + listParam,
        columns: columns,
        singleSelect: true,
        pagination: true,
        toolbar: [{
            text: '<label><button class="btn btn-default btn-xs" data-toggle="modal" onclick="add()" data-target="#myModal">新建</button></label>',
            handler: function () {
                method = "save";
                clean();
            }
        }]
    });

    //点击查询按钮
    $('#btnSearch').bind('click', function () {
        var formData = $('#searchForm').serializeJSON();
        $('#grid').datagrid('load', formData);
    });


    //点击保存按钮
    $('#btnSave').bind('click', function () {
        var formData = $('#editForm').serializeJSON();
        $.ajax({
            url: modelUrl + method,
            data: formData,
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data.code == 200) {
                    $('#grid').datagrid('reload');
                }
                msgProcess(data);
            }
        });
    });
});


/**
 * 删除
 */
function del(id) {
    $.ajax({
        url: modelUrl + 'remove?id=' + id,
        dataType: 'json',
        type: 'post',
        success: function (data) {
            if (data.code == 200) {
                $('#grid').datagrid('reload');
            }
            msgProcess(data);
        }
    });
}

/**
 * 修改(包含图片时, 不使用)
 */
function edit(id) {
    clean();
    method = "update";
    $.ajax({
        type: "POST",
        url: modelUrl + "get?id=" + id,
        async: false,
        success: function(data){
            if (data.code == 200) {
                $('#editForm').form('load', data.data);
                echoDisplayImage(data.data.image);
            }
        }
    });
}

/* 清空表单 */
function clean() {
    $('#editForm').form('clear');
    $('#editForm input').val('');
    $("#imgProcess > div").html('<img src="../../../static/img/uploader.png" style="width: 115px; height: 115px;">');
    $("#imgsProcess > div").html('<img src="../../../static/img/uploader.png" style="width: 115px; height: 115px;">');
    $("#productInfo > div").html('<div class="dropdown" style="display:inline; width: 140px" id="product" onclick="listProduct()"><button type="button" class="btn dropdown-toggle" id="dropdownNode3" data-toggle="dropdown" style="width: 130px; background-color: white;  border-color: #bbbbbb"><span>--请选择--</span><span class="caret"></span></button><ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu3"></ul></div>');
}

/* 回显图片 */
function echoDisplayImage(image) {
    if (image != '') {
        $("#imgProcess > div").html('<span ><img src="' + image + '" style="width: 115px; height: 115px;"></span>');
    }
}

/**
 *

 <div class="alert alert-success" id="successMsg" style="display: none; position: absolute; top: 70px; right: 40px; width: 300px"></div>
 <div class="alert alert-danger" id="failMsg" style="display: none; position: absolute; top: 70px; right: 40px; width: 300px"></div>

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





