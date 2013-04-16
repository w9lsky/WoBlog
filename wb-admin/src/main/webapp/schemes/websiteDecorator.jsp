<%--
 * 这是一个siteMesh的模板页面，将会对所有前台页面进行修饰
 * Author: Leo Sun
 * Blog: http://fuxinci.com/
 * Date: 10/23/12
 * Since: 0.1
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath %>">
    <title><sitemesh:write property='title'/></title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.json.js"></script>
    <script type="text/javascript" src="js/decorator/menu.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <sitemesh:write property='head'/>
</head>
<body>
<div id="main_div">
    <div id="top_div">
        <h1>赋新词<%=basePath %></h1>

        <p>一个技术宅的私人领地 欢迎骚扰^^</p>
        <a href="wb_admin/admin">跳转到后台</a>
    </div>
    <div id="menu_div">
        <ul>
            <li></li>
            <li><a href="">首页</a>
            <li><a href="?cat=301">技术相关</a>
                <ul>
                    <li><a href="?cat=440">Java</a></li>
                    <li><a href="?cat=441">JavaScript</a></li>
                </ul>
            </li>
            <li><a href="?cat=418">生活杂谈</a></li>
            <li><a href="">作者</a></li>
        </ul>
    </div>
    <%--<div id="left_div">left</div>--%>
    <div id="center_div"><sitemesh:write property='body'/></div>
    <div id="right_div">
        <div class="nav_div">
            <p>标签</p>
            <div id="nav_tag_div">
                <%--<a href="javascript:void(0)">标签1</a>,--%>
                <%--<a href="javascript:void(0)">标签2</a>--%>
            </div>
        </div>

        <div class="nav_div">
            <p>近期文章</p>
            <div id="nav_post_div">
                <ul>
                    <%--<li><a href="javascript:void(0)">近期文章</a></li>--%>
                    <%--<li><a href="javascript:void(0)">近期文章</a></li>--%>
                </ul>
            </div>
        </div>

        <div class="nav_div">
            <p>近期评论</p>
            <div id="nav_comment_div">
                <%--<ul>--%>
                    <%--<li><a href="javascript:void(0)" title="">近期评论</a></li>--%>
                    <%--<li><a href="javascript:void(0)">近期评论</a></li>--%>
                <%--</ul>--%>
            </div>
        </div>
    </div>
    <div id="foot_div">

    </div>
</div>
<script type="text/javascript" src="js\decorator\websiteDecorator.js"></script>
</body>
</html>