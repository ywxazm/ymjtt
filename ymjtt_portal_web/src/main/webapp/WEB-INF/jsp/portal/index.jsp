<%--
  Created by IntelliJ IDEA.
  User: ywx_azm
  Date: 2019/2/14
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>YMJTT</title>

    <link rel="shortcut icon" href="static/favicon.ico">
    <link rel="stylesheet" href="static/themes/style.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="static/plugins/jquery.min.js"></script>
    <script type="text/javascript" src="static/plugins/bootstrap.min.js"></script>
    <script src="http://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="static/js/portal/index.js"></script>

</head>

<body>

<%-- 顶端导航 --%>
<jsp:include page="../../jsp/common/header.jsp" />

<%-- 中间部分 --%>
<div class="container">
    <div class="row clearfix">
        <%-- 商品类别 --%>
        <div class="col-md-2 column" id="productCatList">
        </div>
        <%-- 中间轮播图 --%>
        <div class="col-md-6 column" style="padding-right: 5px" id="centerImg">
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators"></ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner"></div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <%-- 右侧小轮播图 --%>
        <div class="col-md-2 column" style="padding-left: 0; padding-right: 10px" id="rightImg"></div>
        <%-- 公告 --%>
        <div class="col-md-2 column" style="padding-left: 0; padding-right: 0; width: 180px; text-align: center">
            <%-- 用户登录部分 --%>
            <div>
                <img src="static/img/no_login.jpg" style="border-radius: 50px; width: 60px; height: 60px">
                <p style="font-size: 12px; text-align: center">Hi~欢迎来到京东！</p>
                <div>
                    <a href="#" style="color: #9d9d9d">登录</a>
                    <a href="#" style="color: #9d9d9d">注册</a>
                </div>
            </div>
            <%-- 京东快报 --%>
            <div id="jdNews" style="padding-top: 10px; text-align: left">
                <hr style="margin-bottom: 0"/>
                <p style="padding-top: 5px">
                    <span>京东快报</span>
                    <span style="padding-left: 30px"><a href="#">查看更多>></a></span>
                </p>
            </div>
            <%-- 快捷链接 --%>
            <div id="kjLink" style="text-align: left;">
                <hr style="margin-bottom: 0"/>
                <p style="padding-top: 5px">
                    <span>快捷链接</span>
                </p>
            </div>
        </div>
    </div>
</div>

<%-- 内容部分 --%>



<%-- 底部 --%>
<hr/>
<jsp:include page="../../jsp/common/footer.jsp" />

</body>

</html>
