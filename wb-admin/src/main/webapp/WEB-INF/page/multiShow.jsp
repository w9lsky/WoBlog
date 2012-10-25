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
<head><title>Simple jsp page</title></head>
<body>
<c:forEach var="obj" items="${PostTermPage.content}">
    <c:set var="post" value="${obj.post}"/>
    <c:set var="term" value="${obj.term}"/>

    <div>
        <p><strong>${post.title}</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${post.modifiedDate}</p>

        <div style="padding: 20px;text-align: left;">
                ${post.content}
        </div>

    </div>
    <hr style="width:100%;">
</c:forEach>

</body>
</html>