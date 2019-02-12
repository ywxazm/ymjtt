<%--
  Created by IntelliJ IDEA.
  User: ywx_azm
  Date: 2018/12/16
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公共异常处理</title>

    <link rel="shortcut icon" href="../../static/favicon.ico">
    <link rel="stylesheet" href="../../static/themes/style.css">
    <link rel="stylesheet" href="../../static/themes/icon.css">
    <link rel="stylesheet" href="../../static/themes/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="../../static/plugins/jquery.min.js"></script>
    <script type="text/javascript" src="../../static/plugins/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

</head>

<body class="gray-bg">

<div class="middle-box text-center animated fadeInDown">
    <h1>${code}</h1>
    <h3 class="font-bold">服务器内部错误</h3>

    <div class="error-desc">
        ${msg}
        <br/>您可以返回主页看看
        <br/><a href="../../" class="btn btn-primary m-t">主页</a>
    </div>
</div>

</body>

</html>
