//点击提交
function btnSaveContainImg() {
    var param = '?';
    param += 'contentId='+ $("#editForm input[name='contentId']").val();
    param += '&contentCatId='+ $("#editForm input[name='parentId']").val();
    param += '&contentName='+ $("#editForm input[name='contentName']").val();
    param += '&sortOrder='+ $("#editForm input[name='sortOrder']").val();
    param += '&contentDesc='+ $("#editForm input[name='contentDesc']").val();
    param += '&url='+ $("#editForm input[name='url']").val();
    $.ajax({
        type: "POST",
        url: modelUrl + method + param,
        data: formData,
        processData : false,
        contentType : false,
        success: function(data){
            if (data.code == 200) {
                $('#grid').datagrid('reload');
            }
            msgProcess(data);
        }
    });
}

//回显数据
function loadNodeData(contentId) {
    clean();
    method = "update";
    $.get(modelUrl +  'getContainParentDo?contentId=' + contentId, function (data) {
        if (data.code == 200) {
            var node = data.data;
            $("#myModal input[name='contentId']").val(node.nodeId);
            $("#myModal input[name='contentName']").val(node.nodeName);
            $("#myModal input[name='sortOrder']").val(node.paramMap.sortOrder);
            $("#myModal input[name='contentDesc']").val(node.paramMap.contentDesc);
            $("#myModal input[name='url']").val(node.paramMap.url);
            echoDisplayDownPull(node.upLevelNodeList);
            echoDisplayImage(node.paramMap.image);
        }
    });
}
