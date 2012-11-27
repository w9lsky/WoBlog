<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>撰写新文章</title>
</head>
<body>
<div class="title">
    <h2>撰写新文章</h2>
    <form:form action="${admin_url}/savePost/" modelAttribute="post" method="post">
        <div class="post">
        <div class=col_left>
            <p>
                <form:hidden path="id"/>
                <c:if test="${createDate!=null}">
                <form:hidden path="createDate"/>
                </c:if>
                <form:hidden path="postType"/>
                <form:hidden path="commentCount"/>
                <form:input path="title" cssClass="post_title"/>
                <span style="color:red;"><form:errors path="title"/></span>
            </p>

            <p>
                <form:hidden path="excerpt"/>
                <form:textarea path="content" cssClass="post_content"/>
                <span style="color:red;"><form:errors path="content"/></span>
            </p>
        </div>
        <div class="col_right">
            <div class="postbox">
                <div class="handlediv" title="点击以切换"><br></div>
                <p class="handlep">发布</p>
                <div class="inside">
                    <p>
                        <input type="button" id="save" value="保存草稿">
                        <form:button path="preview" id="preview">预&nbsp;&nbsp;&nbsp;&nbsp;览</form:button>
                    </p>

                    <button type="submit">发&nbsp;&nbsp;&nbsp;&nbsp;布</button>
                </div>
            </div>
            <div id="category_div" class="postbox">
                <div class="handlediv" title="点击以切换"><br></div>
                <p class="handlep">分类目录</p>
                <div id="category_inside" class="inside">
                </div>
            </div>

            <div id="tag" class="postbox">
                <div class="handlediv" title="点击以切换"><br></div>
                <p  class="handlep">标签</p>
                <div id="tag_inside" class="inside">
                    <p><input type="text" id="tag_text"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="add_tag_btn" value="添加"/></p>
                    <p>&nbsp;&nbsp;多个标签请用英文逗号（,）分开</p>
                    <div id="tag_div">

                        <c:if test="${post.postTerms!=null}">
                            <c:forEach var="postTerm" items="${post.postTerms}" varStatus="status">
                                <c:set var="term" value="${postTerm.term}"/>
                                <c:choose>
                                    <c:when test="${term.taxonomy=='CATEGORY'}">
                                        <input type="hidden" name="temp_category_id" value="${term.id}"/>
                                    </c:when>
                                    <c:when test="${term.taxonomy=='TAG'}">
                                        <script type="text/javascript">
                                            $(document).ready(function() {
                                                linkTagHandler(${term.id}, '${term.name}');
                                            });
                                        </script>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </c:if>

                    </div>
                    <p><a href="javascript:void(0)" id="tag_cloud_link" title="从常用标签中选择">&nbsp;&nbsp;从常用标签中选择</a></p>
                    <div id="tag_cloud_div"></div>
                </div>
            </div>
        </div>
            </div>
    </form:form>
</div>
<script type="text/javascript" src="js/editPost.js"></script>
<script type="text/javascript" src="js/kindeditor/kindeditor.js"></script>
<link rel="stylesheet" href="css/admin/editPost.css">
<link rel="stylesheet" href="css/admin/wbox.css">
</body>
</html>