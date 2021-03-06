<%--
  Created by IntelliJ IDEA.
  User: ywx_azm
  Date: 2019/2/12
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>商品详情</title>

    <link rel="shortcut icon" href="./static/favicon.ico">
    <link rel="stylesheet" href="./static/themes/style.css">
    <link rel="stylesheet" href="./static/css/ymjttForEasyUi.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="./static/plugins/jquery.min.js"></script>
    <script type="text/javascript" src="./static/plugins/bootstrap.min.js"></script>
    <script type="text/javascript" src="./static/plugins/jquery.easyui.min.js"></script>

</head>

<body>

<div class="container">
    <!--  注册 登录 订单等 -->
    <div class="row clearfix abcdefg" style="height: 40px">
        <div class="col-md-12 column">
            <div class="col-md-3 column abcdefg" style="height: 40px" id="topList1"></div>
            <div class="col-md-2 column abcdefg" style="height: 40px" id="topList2"></div>
            <div class="col-md-7 column abcdefg" style="height: 40px; float: right; right: -100px; top: 7px" id="topList3"></div>
        </div>
    </div>

    <!-- 公司标识 搜索 -->
    <div class="row clearfix">
        <!-- 公司标识 -->
        <div class="col-md-2 column">
            <img src="" style="width: 180px; height: 70px" id="logo">
        </div>
        <!-- 搜索 -->
        <div class="col-md-10 column">
            <div class="row clearfix abcdefg" style="height: 110px">
                <div class="col-md-12 column" style="padding-left: 200px; padding-top: 20px">
                    <div class="input-group" style="display: inline">
                        <input type="text" class="form-control" name="criteria" placeholder="鲜榨果汁" style=" height: 30px; width: 400px">
                    </div>
                    <label style="display: inline">
                        <button type="button" class="btn btn-default btn-sm">
                            <a href="htmlPage/portal/search" class="abcdefg">搜索</a>
                        </button>
                    </label>
                </div>
            </div>
        </div>
    </div>

    <!-- 全部商品分类 导航栏 -->
    <div class="row clearfix">
        <!-- 全部商品分类 -->
        <div class="col-md-2 column">
            <div class="row clearfix abcdefg" style="height: 50px">
                <div class="col-md-12 column" >
                    <span class="myfont">全部商品分类</span>
                    <ul class="nav nav-pills"></ul>
                </div>
            </div>
        </div>
        <!-- 导航栏 -->
        <div class="col-md-10 column">
            <div class="row clearfix abcdefg" style="height: 50px">
                <div class="col-md-12 column" id="tabList">
                    <ul class="nav nav-pills"></ul>
                </div>
            </div>
        </div>
    </div>

    <!-- 搜索条件 -->
    <div class="row clearfix">
        <div class="col-md-12 column">
        </div>
    </div>

    <!-- 商品精选 商品列表 -->
    <div class="row clearfix">
        <!-- 商品精选 -->
        <div class="col-md-2 column">
        </div>

        <!-- 商品列表 -->
        <div class="col-md-10 column" id="productList">
        </div>
    </div>
</div>

</body>

</html>
