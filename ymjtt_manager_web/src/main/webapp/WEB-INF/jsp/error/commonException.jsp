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

    <link href="../../static/favicon.ico" rel="shortcut icon">
    <link href="../../static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="../../static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="../../static/css/animate.css" rel="stylesheet">
    <link href="../../static/css/style.css?v=4.1.0" rel="stylesheet">

    <script src="../../static/js/jquery.min.js?v=2.1.4"></script>
    <script src="../../static/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

</head>

<body class="gray-bg">

<div class="middle-box text-center animated fadeInDown">
    <h1>${code}</h1>
    <h3 class="font-bold">服务器内部错误</h3>

    <div class="error-desc">
        ${message}
        <br/>您可以返回主页看看
        <br/><a href="../../" class="btn btn-primary m-t">主页</a>
    </div>
</div>

</body>

</html>
