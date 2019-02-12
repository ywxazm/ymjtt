//点击提交
function btnSaveContainImg() {
    var param = '?';
    param += 'productId='+ $("#editForm input[name='productId']").val();
    param += '&productName='+ $("#editForm input[name='productName']").val();
    param += '&sellPoint='+ $("#editForm input[name='sellPoint']").val();
    param += '&basePrice='+ $("#editForm input[name='basePrice']").val();
    param += '&productStatus='+ $("#editForm select[name='productStatus']").val();
    param += '&cid='+ $("#editForm input[name='parentId']").val();

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
function loadNodeData(productId) {
    clean();
    method = "update";
    $.get(modelUrl +  'getContainParentDo?id=' + productId, function (data) {
        if (data.code == 200) {
            var node = data.data;
            $("#editForm input[name='productId']").val(node.nodeId);
            $("#editForm input[name='productName']").val(node.nodeName);
            $("#editForm input[name='sellPoint']").val(node.paramMap.sellPoint);
            $("#editForm input[name='basePrice']").val(node.paramMap.basePrice);
            $("#editForm input[name='barcode']").val(node.paramMap.barcode);
            $("#editForm select[name='productStatus']").val(node.paramMap.productStatus);
            echoDisplayDownPull(node.upLevelNodeList);
            echoDisplayImage(node.paramMap.image);
        }
});
}

//商品详情部分
function loadDetailData(productId) {
    clean();
    $("#productDetailForm input[name='productId']").val(productId);
    $.get("../../../manager/productDetail/listNoPage?productId=" + productId, function (data) {
        if (data != null && data.length != 0) {
            var addHtml = "";
            for (var i = 0; i < data.length; i++) {
                addHtml += '<span onclick="delImage('+ data[i].productDetailId +')"><img src="' + data[i].image + '" style="width: 132px; height: 132px; padding-top: 10px"></span>&nbsp;&nbsp;';
            }
            $("#imgsProcess > div").html(addHtml);
        }
    });
}

//商品详情提交
function btvSaveDetail() {
    var param = '?';
    param += 'productId='+ $("#productDetailForm input[name='productId']").val();

    $.ajax({
        type: "POST",
        url: "../../../manager/productDetail/save" + param,
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

//删除图片
function delImage(productDetailId) {
    $.get("../../../manager/productDetail/remove?id=" + productDetailId, function (data) {
        if (data.code == 200) {
            var productId = $("#productDetailForm input[name='productId']").val();
            loadDetailData(productId);
        }
        msgProcess(data);
    });
}