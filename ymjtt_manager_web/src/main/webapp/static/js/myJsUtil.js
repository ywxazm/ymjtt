//时间转换
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

//格式化金额的小数点
function submoney(value){
    var b = false;
    if (value == null || value == "") return "0";
    value = value.toString();
    if(value.indexOf('-') != -1){
        b= true;
        value = value.substring(1,value.length);
    }
    if (/^\-?[0-9]+(.[0-9]+)?$/.test(value)){
        value = value.toString().replace(/^(\d*)$/, "$1.");
        value = (value + "00").replace(/(\d*\.\d\d)\d*/, "$1");
        value = value.replace(".", ",");
        var re = /(\d)(\d{3},)/;
        while (re.test(value))
            value = value.replace(re, "$1,$2");
        value = value.replace(/,(\d\d)$/, ".$1");
    }
    if(b){
        value = "-"+value;
    }
    return value;
}