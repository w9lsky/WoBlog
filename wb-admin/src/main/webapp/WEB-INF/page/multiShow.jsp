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
<c:forEach var="post" items="${PostPage.content}">
    <div class="post_list_div">
        <c:if test="${TermName!=null}">
            <p style="text-align:left;">分类归档：${TermName}</p>
        </c:if>
        <h2 class="title_h2"><a href="?post=${post.id}">${post.title}</a></h2>

        <p class="create_data_p">发表于<a href="javascript:(0)"><fmt:formatDate value="${post.createDate}" type="date"
                                                                             dateStyle="full"/></a></p>

        <div class="excerpt_div">
                ${post.excerpt}
                    <p><a href="?post=${post.id}" class="more_a">阅读全文>></a></p>
        </div>

        <div class="post_foot_div">
            <span class="tag_span"> 标签为
            <c:forEach var="postTerms" items="${post.postTerms}">
                <c:set var="term" value="${postTerms.term}"/>
                <c:if test="${term.taxonomy=='TAG'}">
                    <label><a href="?tag=${term.id}">${term.name}</a></label>
                </c:if>
            </c:forEach>

            </span>
            <label class="comment_count_lbl">评论${post.commentCount}</label>
        </div>
    </div>
    <hr class="excerpt_hr">
</c:forEach>
<div class="page_div">
    <c:forEach var="index" begin="1" end="${PostPage.totalPages}">
        <c:if test="${PostPage.number+1==index}">
            <label style="border: 1px solid #FF0000;">
        </c:if>
        <c:if test="${PostPage.number+1!=index}">
            <label>
        </c:if>
        <c:if test="${RequestUrl==null}">
            <a style="text-decoration:blink;" href="?pageIndex=${index}">${index}</a>
            </label>
        </c:if>
        <c:if test="${RequestUrl!=null}">
            <label>
            <a style="text-decoration:blink;" href="?${RequestUrl}&pageIndex=${index}">${index}</a>
            </label>
        </c:if>
    </c:forEach>
</div>
</body>
</html>