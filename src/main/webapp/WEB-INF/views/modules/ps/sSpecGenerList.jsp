<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格类型中间表管理</title>
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
		<li class="active"><a href="${ctx}/ps/sSpecGener/">规格类型中间表列表</a></li>
		<shiro:hasPermission name="ps:sSpecGener:edit"><li><a href="${ctx}/ps/sSpecGener/form">规格类型中间表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sSpecGener" action="${ctx}/ps/sSpecGener/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型ID：</label>
				<form:input path="generId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>规格分类ID：</label>
				<form:input path="specId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型ID</th>
				<th>规格分类ID</th>
				<shiro:hasPermission name="ps:sSpecGener:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sSpecGener">
			<tr>
				<td><a href="${ctx}/ps/sSpecGener/form?id=${sSpecGener.id}">
					${sSpecGener.generId}
				</a></td>
				<td>
					${sSpecGener.specId}
				</td>
				<shiro:hasPermission name="ps:sSpecGener:edit"><td>
    				<a href="${ctx}/ps/sSpecGener/form?id=${sSpecGener.id}">修改</a>
					<a href="${ctx}/ps/sSpecGener/delete?id=${sSpecGener.id}" onclick="return confirmx('确认要删除该规格类型中间表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>