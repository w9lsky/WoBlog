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
    <p>标签：
        <c:forEach var="pt" items="${obj.postTerms}">
                <c:if test="${pt.term.taxonomy=='TAG'}">
                    <c:out value="${pt.term.name}"/>
                </c:if>
        </c:forEach>
    </p>
    <div style="padding: 20px;text-align: left;">
        ${obj.content}
            <p>
                <c:if test="${PreviousPost!=null}">
                    <a href="?post=${PreviousPost.id}">上一篇:${PreviousPost.title}</a>
                </c:if>
                <c:if test="${PreviousPost==null}">
                    上一篇:没有了
                </c:if>
                <c:if test="${NextPost!=null}">
                    <a href="?post=${NextPost.id}">下一篇:${NextPost.title}</a>
                </c:if>
                <c:if test="${NextPost==null}">
                    下一篇:没有了
                </c:if>
            </p>
    </div>
</body>
</html>