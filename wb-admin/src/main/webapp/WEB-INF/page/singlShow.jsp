<%--
 * The description about this file.       
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 10/23/12
 * Since: 0.1
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="admin/include.jsp" %>
<html>
<head>
    <title>Simple jsp page</title>
</head>
<body>
    <c:set var="obj" value="${Post}"/>
    <p><strong>${obj.title}</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${obj.modifiedDate}</p>

    <div style="padding: 20px;text-align: left;">
        ${obj.content}
    </div>
</body>
</html>