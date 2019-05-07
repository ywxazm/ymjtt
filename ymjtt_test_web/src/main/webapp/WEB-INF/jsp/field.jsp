<%--
  Created by IntelliJ IDEA.
  User: ywx_azm
  Date: 2019/2/20
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="GB2312" %>
<html>
<head>
    <title>JSP Field Object Test</title>
</head>
<body>
    <%-- Ö±½ÓÈ¡ --%>
    ${a}
    ${b}
    ${userDo.uid}
    ${userDo.uname}
    ${userDo.pwd}

    <hr/>
    <%-- Request --%>
    ${requestScope.a}
    ${requestScope.b}
    ${requestScope.userDo.uid}
    ${requestScope.userDo.uname}
    ${requestScope.userDo.pwd}
    <hr/>
</body>
</html>
