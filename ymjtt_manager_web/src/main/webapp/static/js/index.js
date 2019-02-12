$(function () {
    getMenus(1, 1);

    $("#closeCurrentTabs").click(function () {
        closeTab($("#currentTabs").val());
    });

    $("#allTabs").click(function () {
        var baseHtml = '';
        baseHtml += '<li class="active" id="firstPage">';
        baseHtml += '<a href="#panel-firstPage" data-toggle="tab">首页</a>';
        baseHtml += '</li>';
        $("#tabs .nav").html(baseHtml);
        baseHtml = '';
        baseHtml += '<div class="tab-pane active" id="panel-firstPage">';
        baseHtml += '<iframe src="htmlPage/manager/menu/welcome" frameborder="0" scrolling="no" width="100%" height="100%"/>';
        baseHtml += '</div>';
        $("#tabs .tab-content").html(baseHtml);
    });

});


function getMenus(pid, level) {
    $("#iconType" + pid).toggle();
    $("#" + pid + "> div").toggle();
    if('none' == $("#" + pid + "> div").css("display")) {
        return;
    }
    if(undefined != $("#" + pid + "> div").html()) {
        $("#" + pid + "> div").remove();
    }

    var url = 'manager/menu/listByParentId?parentId=' + pid;
    $.get(url, function (data) {
        var menus = data.data;
        if(null == menus) {
            return;
        }
        for(var i = 0; i < menus.length; i++) {
            var id = menus[i].nodeId;
            var addHtml = '';
            addHtml += level == 1 ? '<div class="panel panel-default">' : '';
            addHtml += '<div class="panel-heading" id="'+ id +'">&nbsp;&nbsp;';
            addHtml += '<span class="'+ menus[i].nodeIcon +'">&nbsp;</span>';
            if(menus[i].ifNextNode) {
                addHtml += '<span class="panel-title" onclick="getMenus(' + id + ', ' + (level + 1) + ')">' + menus[i].nodeName + '</span>';
                addHtml += '<span id="iconType' + id + '" class="glyphicon glyphicon-chevron-down" style="float: right;"></span>';
            }else {
                addHtml += '<span class="panel-title" onclick="showMainPage('+ id +')">' + menus[i].nodeName + '</span>';
                addHtml += '<input type="hidden" value="'+ menus[i].paramMap.url +'">';
            }
            addHtml += '</div>';
            addHtml += level == 1 ? '</div>' : '';
            $("#" + pid).append(addHtml);
        }
    });
}

function showMainPage(id) {
    $("#currentTabs").val(id);
    var url = $("#" + id + " :input").val();
    var menuName = $("#" + id + " .panel-title").text();

    $("#tabs > ul > li").removeClass("active");
    $("#tabs .tab-content div").removeClass("active");

    if($("#panel-" + id).html() != null) {
        $("#li" + id).addClass("active");
        $("#panel-" + id).addClass("active");
        return;
    }

    var addHtml = '';
    addHtml += '<li class="active" onclick="chageTab('+ id +')" id="'+ ("li" + id)+'">';
    addHtml += '<a href="#panel-'+ id +'" data-toggle="tab">'+ menuName +'&nbsp;&nbsp;<span class="glyphicon glyphicon-remove" onclick="closeTab('+ id +')"></span></a>';
    addHtml += '</li>';
    $("#tabs > ul").append(addHtml);

    addHtml = '';
    addHtml += '<div class="tab-pane active" id="panel-'+ id +'">';
    addHtml += '<iframe src="'+ url +'" frameborder="0" scrolling="no" width="100%" height="900px"/>';
    addHtml += '</div>';
    $("#tabs .tab-content").append(addHtml);
}

function closeTab(id) {
    $("#currentTabs").val(id);
    $("#li" + id).remove();
    $("#panel-" + id).remove();
    $("#tabs > ul > li").removeClass("active");
    $("#tabs  .tab-content div").removeClass("active");
    $("#firstPage").addClass("active");
    $("#panel-firstPage").addClass("active");
}

function chageTab(id) {
    $("#currentTabs").val(id);
}




