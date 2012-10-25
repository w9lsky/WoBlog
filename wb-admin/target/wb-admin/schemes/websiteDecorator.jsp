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
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath %>">
    <title><sitemesh:write property='title'/></title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.json.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <sitemesh:write property='head'/>
</head>
<body>
<div id="main_div">
    <div id="top_div">
         <a
			href="wb_admin/admin">跳转到后台</a>
    </div>
    <div id="left_div">left</div>
    <div id="center_div"><sitemesh:write property='body'/></div>
    <div id="right_div">right</div>
    <div id="foot_div">foot</div>
</div>
</body>
</html>