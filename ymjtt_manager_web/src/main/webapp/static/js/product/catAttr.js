//点击提交
function btnSaveContainImg() {
    var param = '?';
    param += 'productAttrId='+ $("#editForm input[name='productAttrId']").val();
    param += '&productAttrName='+ $("#editForm input[name='productAttrName']").val();
    param += '&sortOrder='+ $("#editForm input[name='sortOrder']").val();
    param += '&attrType=2';
    param += '&belongId='+ $("#editForm input[name='parentId']").val();

    $.ajax({
        type: "get",
        url: modelUrl + method + param,
        success: function(data){
            if (data.code == 200) {
                $('#grid').datagrid('reload');
            }
            msgProcess(data);
        }
    });
}

//回显数据
function loadData(productAttrId) {
    clean();
    method = "update";
    $.get(modelUrl +  'getContainParentDo?id=' + productAttrId, function (data) {
        if (data.code == 200) {
            var node = data.data;
            $("#editForm input[name='productAttrId']").val(node.nodeId);
            $("#editForm input[name='productAttrName']").val(node.nodeName);
            $("#editForm input[name='sortOrder']").val(node.paramMap.sortOrder);
            echoDisplayDownPull(node.upLevelNodeList);
        }
    });
}