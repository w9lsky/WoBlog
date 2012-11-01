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
<div class="post_div">
    <c:set var="obj" value="${Post}"/>
    <h1>${obj.title}</h1>

    <p class="tag_p">
        <c:forEach var="pt" items="${obj.postTerms}">
            <c:if test="${pt.term.taxonomy=='TAG'}">
                <a href="?tag=${pt.term.id}">${pt.term.name}</a>
            </c:if>
        </c:forEach>
    </p>

    <div class="content_div">
        ${obj.content}
    </div>
    <div class="page_div">
        <div class="pre_post_div">上一篇:
        <c:if test="${PreviousPost!=null}">
            <a href="?post=${PreviousPost.id}">${PreviousPost.title}</a>
        </c:if>
        <c:if test="${PreviousPost==null}">
            没有了
        </c:if>
        </div>
        <div class="next_post_div">下一篇:
        <c:if test="${NextPost!=null}">
            <a href="?post=${NextPost.id}">${NextPost.title}</a>
        </c:if>
        <c:if test="${NextPost==null}">
            没有了
        </c:if>
        </div>
    </div>

    <div class="foot_div">
        <span><fmt:formatDate value="${obj.createDate}" type="date" dateStyle="full"/></span> |
        <span>评论:${obj.commentCount}</span>|
        <span>分类:<c:forEach var="pt" items="${obj.postTerms}">
            <c:if test="${pt.term.taxonomy=='CATEGORY'}">
                <a href="?/category=${pt.term.id}">${pt.term.name}</a>
            </c:if>
        </c:forEach>
        </span>
    </div>
</div>
</body>
</html>