<%--
  Created by IntelliJ IDEA.
  User: ywx_azm
  Date: 2019/2/14
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="static/themes/style.css">
<script src="static/js/common/header.js"></script>

<%-- 顶端导航 --%>
<div class="container" style="width: 1200px">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="row clearfix" >
                <%-- 第一行 --%>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <%-- 公司标识 --%>
                        <div class="col-md-2 column" style="padding-left: 0" id="firstHeaderCompanyImg">
                            <img src="" style="width: 190px; height: 180px;">
                        </div>
                        <div class="col-md-10 column">
                            <%-- 第一行 --%>
                            <div class="row clearfix">
                                <div class="col-md-12 column">
                                    <%-- 定位 --%>
                                    <div class="col-md-2 column" id="firstHeaderLocation" style="padding-top: 7px">
                                        <p style="font-size: 14px;"><span class="glyphicon glyphicon-map-marker"></span> 深圳</p>
                                    </div>
                                    <%-- 登录信息等 --%>
                                    <div class="col-md-10 column" id="firstHeaderLoginInfo" style="padding-top: 7px; padding-right: 20px">
                                    </div>
                                </div>
                            </div>
                            <%-- 第二行 --%>
                            <div class="row clearfix" style="padding-left: 100px; padding-top: 40px">
                                <div class="col-md-12 column">
                                    <%-- 搜索栏 --%>
                                    <div class="col-md-7 column">
                                        <div class="input-group" style="display: inline">
                                            <input type="text" class="form-control" name="criteria" placeholder="鲜榨果汁" style=" height: 30px; width: 400px">
                                        </div>
                                        <label style="padding-left: 10px;display: inline">
                                            <button type="button" class="btn btn-default btn-sm" style="background-color: white; height: 30px; ">
                                                <a href="htmlPage/portal/search" style="color: #9d9d9d;">搜索</a>
                                            </button>
                                        </label>
                                    </div>
                                    <%-- 购物车 --%>
                                    <div class="col-md-5 column">
                                        <label style="padding-left: 10px">
                                            <button type="button" class="btn btn-default btn-sm" style="background-color: white; height: 30px; width: 160px">
                                                <a href="" style="color: #9d9d9d" ><span class="glyphicon glyphicon-shopping-cart"/><span style="padding-left: 4px">查看购物车</span></a>
                                            </button>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <%-- 第三行 --%>
                            <div class="row clearfix" style="padding-top: 25px">
                                <%-- 选项卡 --%>
                                    <div class="col-md-12 column" id="tabList">
                                        <ul class="nav nav-pills"></ul>
                                    </div>
                            </div>

                        </div>
                    </div>
                </div>
                <hr/>
            </div>
        </div>
    </div>
</div>