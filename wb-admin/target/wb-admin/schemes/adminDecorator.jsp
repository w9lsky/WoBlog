<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <%-- <c:set var="app" scope="page" value="${pageContext.request.contextPath}" /> --%>
 <c:set var="admin_url" scope="page" value="wb_admin" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath %>">
<title>
	<sitemesh:write property='title'/>
</title>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.json.js"></script>
	<script type="text/javascript" src="js/jquery.layout.js"></script>
	<script type="text/javascript" src="js/decorator/decorator.js"></script>
	<script type="text/javascript" src="js/jquery-ui/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/jquery-ui/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/jquery-ui/ui/jquery.ui.accordion.js"></script>
	<script type="text/javascript" src="js/jquery-ui/ui/jquery.ui.button.js"></script>
	<link rel="stylesheet" href="js/jquery-ui/themes/base/jquery.ui.all.css">
	<link rel="stylesheet" href="css/admin/style.css">
	<sitemesh:write property='head'/>
</head>
<body>

<div class="ui-layout-center">
	<sitemesh:write property='body'/>
</div>
<div class="ui-layout-north">North</div>
<!-- <div class="ui-layout-south">South</div> -->
<!-- <div class="ui-layout-east">East</div> -->
<div class="ui-layout-west">
	<%@include file="adminMenu.jsp" %>
</div> 
</body>
</html>