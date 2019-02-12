//点击提交
function btnSaveContainImg() {
    var param = '?';
    param += 'productAttrId='+ $("#editForm input[name='productAttrId']").val();
    param += '&productAttrName='+ $("#editForm input[name='productAttrName']").val();
    param += '&sortOrder='+ $("#editForm input[name='sortOrder']").val();
    param += '&attrType=1';
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
            var startNode = node.upLevelNodeList.splice(0, 4);
            var product = startNode[3];
            echoDisplayDownPull(startNode);
            $("#productInfo > div").html('<span>'+ product.nodeName +'</span>');
            $("#productInfo > div").html('<div class="dropdown" style="display:inline; width: 140px" id="product" onclick="listProduct()"><button type="button" class="btn dropdown-toggle" id="dropdownNode3" data-toggle="dropdown" style="width: 130px; background-color: white;  border-color: #bbbbbb"><span>'+ product.nodeName +'</span><span class="caret"></span></button><ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu3"></ul></div>');
        }
    });
}

//列出所有商品
function listProduct() {
    if ($("#productInfo > div").html().length > 500) {
        return;
    }
    var parentId = $("#editForm input[name='parentId']").val();
    if (parentId == '') {
        return;
    }
    $.get("../../../manager/product/listNoPage?cid=" + parentId, function (data) {
        var addHtml = '';
        var array = data.data;
        for (var i = 0; i < array.length; i++) {
            addHtml += '<li role="presentation">';
            addHtml += '<a role="menuitem" tabindex="-1" href="#" id="'+ "nodeId_" + array[i].nodeId +'" onclick="changeProduct('+ array[i].productId +')">'+ array[i].productName +'</a>';
            addHtml += '</li>';
        }
        $("#product > ul").html("");
        $("#product > ul").append(addHtml);
    });
}

//选中商品
function changeProduct(productId) {
    $("#editForm input[name='parentId']").val(productId);
    $.get("../../../manager/product/get?id=" + productId, function (data) {
        $("#product > button > span").first().text(data.data.productName);
    });
}
