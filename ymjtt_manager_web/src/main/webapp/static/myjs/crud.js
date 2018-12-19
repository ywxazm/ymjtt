
/* 区别更新或添加 */
function updateOrAdd() {
    if("add" == $("#submitForm").val()) {
        add();
    }else if("update" == $("#submitForm").val()) {
        update();
    }
}

/* 更新 */
function update() {
    var mapping = "/update";
    var formData = $('form').serializeJSON();
    $.ajax({
        type: 'post',
        url:  commonUrl + mapping,
        data: formData,
        dataType: 'json',
        success:function(data){
            if(200 == data.code) {
                successMsg01(data.msg);
                showData(1);
            }else {
                failMsg01(data.msg);
            }
        }
    });
}

/* 删除 */
function del(id) {
    var mapping = "/remove";
    $.get(commonUrl + mapping + "?id=" + id, function (data) {
        if ('200' == data.code) {
            successMsg01(data.msg);
            showData(1);
        } else {
            failMsg01(data.msg);
        }
    });
}

/* 添加 */
function add() {
    var mapping = "/add";
    var formData = $('form').serializeJSON();
    $.ajax({
        type: 'post',
        url:  commonUrl + mapping,
        data: formData,
        dataType: 'json',
        success:function(data){
            if(200 == data.code) {
                successMsg01(data.msg);
                showData(1);
            }else {
                failMsg01(data.msg);
            }
        }
    });
}


/* 成功提醒 */
function successMsg01(msg) {
    $("#alertSuccess01").text(msg);
    $("#alertSuccess01").css("display", "block");
    setTimeout(function () {
        $("#alertSuccess01").css("display", "none");
    }, 3000);
}
/* 失败提醒 */
function failMsg01(msg) {
    $("#alertFail01").text(msg);
    $("#alertFail01").css("display", "block");
    setTimeout(function () {
        $("#alertFail01").css("display", "none");
    }, 3000);
}