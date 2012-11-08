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
    <input type="hidden" id="post_id" value="${obj.id}">
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
    <div class="comment_list_div">
        <c:if test="${CommentPage.totalElements!=0}">
            <c:forEach var="comment" items="${CommentPage.content}">
                <c:if test="${comment.commentStatus=='APPROVED'}">
                    <p>${comment.authorName}&nbsp;在&nbsp;<fmt:formatDate value="${comment.createDate}"
                                                                           type="date" dateStyle="full"/>&nbsp;说:</p>

                    <div>${comment.content}</div>
                </c:if>
            </c:forEach>
        </c:if>
    </div>
    <div class="comment_div">
        <hr>
        <div class="show_msg"></div>
        <form id="comment_form" method="post">
            <input type="hidden" name="postId" value="${obj.id}">
            <p>姓名：<input type="text" id="name" name="authorName"><span class="required_flag">*</span><span id="name_error" class="error_message"></span></p>
            <p>邮箱：<input type="text" id="mail" name="authorMail"><span class="required_flag">*</span><span id="mail_error" class="error_message"></span></p>
            <p>网址：<input type="text" id="url" name="authorUrl"><span id="url_error" class="error_message"></span></p>
            <span id="content_error" class="error_message"></span>
            <p><textarea id="content" name="content" wrap="virtual"></textarea></p>
            <p>
                <button type="reset" id="reset_comment">重置</button>
                <input type="button" id="submit_comment"value="提交"/>
            </p>
        </form>
    </div>
</div>
<script type="text/javascript" src="js/singlShow.js"></script>
</body>
</html>