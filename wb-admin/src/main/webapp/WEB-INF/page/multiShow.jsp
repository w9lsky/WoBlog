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
    <div>
        <p><strong>${post.title}</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${post.modifiedDate}</p>

        <div style="line-height:10px;padding: 20px;text-align: left;height: 200px;overflow: hidden;">
                ${post.content}
        </div>

        <c:forEach var="postTerms" items="${post.postTerms}">
            <c:set var="term" value="${postTerms.term}"/>
            <c:if test="${term.taxonomy=='TAG'}">
                <label><a href="">${term.name}</a></label>
            </c:if>
        </c:forEach>

        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>

        <a href="?post=${post.id}">详细</a>
        <label>评论${post.commentCount}</label>
    </div>
    <hr style="width:100%;">
</c:forEach>
<c:forEach var="index" begin="1" end="${PostTermPage.totalPages}">
    <label><a href="?${RequestUrl}&pageIndex=${index}">${index}</a></label>
</c:forEach>
</body>
</html>