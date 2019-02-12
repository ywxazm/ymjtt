//加载商品
$.get("../../../manager/product/listNoPage", function (data) {
    if (data.code == 200) {
        var productArray = data.data;
        var addHtml = '';
        for (var i = 0; i < productArray.length; i++) {
            addHtml += '<tr onclick="listAttr('+ productArray[i].productId +')">';
            addHtml += '<td>'+ productArray[i].barcode +'</td>';
            addHtml += '<td>'+ productArray[i].productName +'</td>';
            addHtml += '</tr>';
        }
        $("#product tbody").append(addHtml);
    }
});

//加载商品规格和类别规格
function listAttr(productId) {
    $("#productAttrValue tbody").html("");
    $.get("../../../manager/productAttr/listAttrByProductId?productId=" + productId, function (data) {
            var addHtml = '';
            for (var i = 0; i < data.length; i++) {
                addHtml += '<tr onclick="listAttrValue('+ data[i].productAttrId +')">';
                if (data[i].attrType == 1) {
                    addHtml += '<td style="text-align: center">商品</td>';
                }else if (data[i].attrType == 2) {
                    addHtml += '<td style="text-align: center">类别</td>';
                }
                addHtml += '<td style="text-align: center">'+ data[i].productAttrName +'</td>';
                addHtml += '</tr>';
            }
            $("#productAttr").css("display", "block");
            $("#productAttrValue").css("display", "none");
            $("#productAttr tbody").html(addHtml);
    });
}

//获取属性值
function listAttrValue(productAttrId) {
    $("#attrValueForm input").val('');
    $("#attrValueForm select").val('');

    $('#attrValueForm input[name="productAttrId"]').val(productAttrId);
    $.get("../../../manager/productAttrValue/listNoPage?productAttrId=" + productAttrId, function (data) {
        if (data.code == 200) {
            var addHtml = '';
            var productAttrValueArray = data.data;
            for (var i = 0; i < productAttrValueArray.length; i++) {
                addHtml += '<tr>';
                addHtml += '<td style="text-align: center">'+ productAttrValueArray[i].productAttrValueV +'</td>';
                if (productAttrValueArray[i].valueType == 1) {
                    addHtml += '<td style="text-align: center">累乘</td>';
                }else if (productAttrValueArray[i].valueType == 2) {
                    addHtml += '<td style="text-align: center">累加</td>';
                }
                addHtml += '<td style="text-align: center">'+ productAttrValueArray[i].attrParam +'</td>';
                addHtml += '<td style="text-align: center" onclick="removeValue('+ productAttrValueArray[i].productAttrValueId +')"><p><span class="glyphicon glyphicon-remove"></span></p></td>';
                addHtml += '</tr>';
            }
            $("#productAttrValue").css("display", "block");
            $("#productAttrValue tbody").html(addHtml);
        }
    });
}

//删除规格值
function removeValue(productAttrValueId) {
    $.get("../../../manager/productAttrValue/remove?id=" + productAttrValueId, function (data) {
        if (data.code == 200) {
            var productAttrId = $('#attrValueForm input[name="productAttrId"]').val();
            listAttrValue(productAttrId);
        }
        msgProcess(data);
    });
}

//保存
function saveProductAttrValue() {
    var formData = $('#attrValueForm').serializeJSON();
    $.ajax({
        url: "../../../manager/productAttrValue/save",
        data: formData,
        dataType: 'json',
        type: 'post',
        success: function (data) {
            if (data.code == 200) {
                var productAttrId = $('#attrValueForm input[name="productAttrId"]').val();
                listAttrValue(productAttrId);
            }
            msgProcess(data);
        }
    });
}















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
