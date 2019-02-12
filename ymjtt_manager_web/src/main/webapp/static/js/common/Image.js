/**
 * 适用代码

 <div class="form-group" id="imgProcess" style="position: absolute; top: 25px; left: 410px">
 <div>
 <img src="../../../static/img/uploader.png" style="width: 115px; height: 115px;">
 </div>
 <input type="file" onchange="changeImg(this,event)" name="multipartFiles" multiple style='width: 20px; padding-left: 20px; padding-top: 10px'>
 </div>

 */

var formData;         //上传图片的数据

//图片选中后, 进行回显
function changeImg(target, e) {
    //判断是不是IE浏览器并获取图片大小
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    var fileSize = 0;
    if (isIE && !target.files) {
        var filePath = target.value;
        var fileSystem = new ActiveXObject("Scription.FileSystemObject");
        var file = fileSystem.GetFile(filePath);
        fileSize = file.Size;
    } else {
        // fileSize = target.files[0].size;
    }

    //验证大小
    if (fileSize > 4 * 1024 * 1024) {
        failMsg("Upload Img size must < 4M")
        $(target).val('');
        $(target).next().prop('src', '');
        return;
    }

    formData = new FormData();
    var addHtml = "";
    if (target.files.length == 1) {
        formData.append("multipartFiles", target.files[0]);
        addHtml += '<span ><img src="' + getObjectURL(target.files[0]) + '" style="width: 115px; height: 115px;"></span>';
        $("#imgProcess > div").html(addHtml);
    }else {
        for (var i = 0; i < target.files.length; i++) {
            formData.append("multipartFiles", target.files[i]);
            addHtml += '<span ><img src="' + getObjectURL(target.files[i]) + '" style="width: 132px; height: 132px; padding-top: 10px"></span>&nbsp;&nbsp;';
        }
        $("#imgsProcess > div").html(addHtml);
    }
}

//建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}



