//点击提交
function btnSaveContainImg() {
    var param = '?';
    param += 'userId='+ $("#editForm input[name='userId']").val();
    param += '&userName='+ $("#editForm input[name='userName']").val();
    param += '&email='+ $("#editForm input[name='email']").val();
    param += '&phone='+ $("#editForm input[name='phone']").val();
    param += '&status='+ $("#editForm select[name='status']").val();
    param += '&userType='+ $("#editForm select[name='userType']").val();
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

//注销, 激活
function cancelOrActive(id, newStatus) {
    $.get(modelUrl + 'cancelOrActive?id=' + id + "&newStatus=" + newStatus, function (data) {
        if (data.code == 200) {
            $('#grid').datagrid('reload');
        }
        msgProcess(data);
    });
}

//重置密码
function initPassword(id) {
    $.get(modelUrl + 'initPassword?id=' + id, function (data) {
        if (data.code == 200) {
            $('#grid').datagrid('reload');
        }
        msgProcess(data);
    });
}