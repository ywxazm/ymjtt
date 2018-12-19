/* 上传N张图片 */
function updateImg() {
    /* 上传图片数量校验 */
    var img_files = document.getElementById("uploaderImgs");
    if(img_files.files.length > 3) {
        failMsg02("the imgs count must <= 3 !");
        return;
    }

    /* 删除服务器上数据 */
    cleanImg();

    /* 选择及上传图片 */
    var formData = new FormData();
    for(var i = 0; i < img_files.files.length; i++) {
        formData.append("multipartFiles", img_files.files[i]);
    }
    $.ajax({
        type: "post",
        url: "../../manager/fileupload/upload",
        data: formData,
        processData : false,
        contentType : false,
        success: function(data){
            $("#image").val(data.data);
            showImgs();
            if(200 == data.code) {
                successMsg02(data.msg);
            }else {
                failMsg02(data.msg);
            }
        }
    });
}

/* 删除N张图片 */
function delImg(imgId) {
    var path = $("#" + imgId).attr("src");
    /* 执行删除 */
    $.ajax({
        type: "get",
        url: "../../manager/fileupload/remove?filePath=" + path,
        success: function(data){
            if(200 == data.code) {
                var failPath = data.data.FailRemove;
                if(null == failPath || '' == failPath) {
                    var paths = $("#image").val().replace(path, "");
                    $("#image").val(paths);
                    successMsg02("成功删除图片");
                }else{
                    failMsg02("删除图片失败");
                }
                showImgs();
            }else {
                failMsg02(data.msg);
            }
        }
    });
}

/* 前端实现图片回显 */
/*function changImg(event) {
    for (var i = 0; i < event.target.files.length; i++) {
        var file = event.target.files.item(i);
        if (!(/^image\/.*$/i.test(file.type))) {
            continue; //不是图片 就跳出这一次循环
        }

        var freader = new FileReader();
        freader.readAsDataURL(file);
        freader.onload = function(event) {
            $(".input-group :file").css('background-image','url('+ event.target.result +')');
        }
    }
}*/

/* 成功提醒 */
function successMsg02(msg) {
    $("#alertSuccess02").text(msg);
    $("#alertSuccess02").css("display", "block");
    setTimeout(function () {
        $("#alertSuccess02").css("display", "none");
    }, 3000);
}
/* 失败提醒 */
function failMsg02(msg) {
    $("#alertFail02").text(msg);
    $("#alertFail02").css("display", "block");
    setTimeout(function () {
        $("#alertFail02").css("display", "none");
    }, 3000);
}

/* 回显图片 */
function showImgs() {
    for(var i = 0; i < 3; i++) {
        $("#img0" + i).css("display", "none");
    }

    var paths = $("#image").val();
    var pathArray = paths.split(",");
    for(var i = 0; i < pathArray.length; i++) {
        if('' != pathArray[i]) {
            $("#img0" + i).css("display", "inline");
            $("#img0" + i).attr("src", pathArray[i]);
        }
    }
}

/* 清空图片 */
function cleanImg() {
    $("#img00").css("display", "none");
    $("#img01").css("display", "none");
    $("#img02").css("display", "none");

    var paths = $("#image").val();
    if(null == paths || '' == paths) {
        return;
    }

    /* 执行删除 */
    $.ajax({
        type: "get",
        url: "../../manager/fileupload/remove?filePath=" + paths,
        success: function(data){
            if(200 == data.code) {
                var failPath = data.data.FailRemove;
                $("#image").val(failPath);
                showImgs();
            }
        }
    });
}
