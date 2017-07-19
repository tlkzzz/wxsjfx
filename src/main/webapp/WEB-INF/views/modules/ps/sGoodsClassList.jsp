<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品分类管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ps/sGoodsClass/">商品分类管理列表</a></li>
		<shiro:hasPermission name="ps:sGoodsClass:edit"><li><a href="${ctx}/ps/sGoodsClass/form">商品分类管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sGoodsClass" action="${ctx}/ps/sGoodsClass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sGoodsClass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sGoodsClass">
			<tr>
				<td><a href="${ctx}/ps/sGoodsClass/form?id=${sGoodsClass.id}">
					${sGoodsClass.name}
				</a></td>
				<td>
					${sGoodsClass.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sGoodsClass.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sGoodsClass:edit"><td>
    				<a href="${ctx}/ps/sGoodsClass/form?id=${sGoodsClass.id}">修改</a>
					<a href="${ctx}/ps/sGoodsClass/delete?id=${sGoodsClass.id}" onclick="return confirmx('确认要删除该商品分类管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>