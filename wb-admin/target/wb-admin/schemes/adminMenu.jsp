<%@ page contentType="text/html; charset=UTF-8"%>
<div id="accordion">
<h3>
		<a href="#">仪表盘</a>
	</h3>
	<div>
			<p><a href="#">首页</a></p>
			<p><a href="#">更新</a></p>			
	</div>
	<h3>
		<a href="#">文章</a>
	</h3>
	<div>
			<p><a href="${admin_url}/listPost">所有文章</a></p>
			<p><a href="${admin_url}/savePost">写文章</a></p>
            <%--<p><a href="${admin_url}/category">Ajax分类目录</a></p>--%>
			<p><a href="${admin_url}/term?taxonomy=category">分类目录</a></p>
            <p><a href="${admin_url}/term?taxonomy=tag">标签</a></p>
	</div>
	<h3>
		<a href="#">页面</a>
	</h3>
	<div>
			<p><a href="#">所有页面</a></p>
			<p><a href="#">新建页面</a></p>
	</div>
	<h3>
		<a href="#">外观</a>
	</h3>
	<div>
			<p><a href="#">主题</a></p>
			<p><a href="#">菜单</a></p>
			<p><a href="#">背景</a></p>
	</div>
	<h3>
		<a href="#">设置</a>
	</h3>
	<div>
			<p><a href="#">主题</a></p>
			<p><a href="#">菜单</a></p>
			<p><a href="#">背景</a></p>
	</div>
</div>