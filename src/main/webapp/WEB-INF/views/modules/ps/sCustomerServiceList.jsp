<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客服表管理</title>
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
		<li class="active"><a href="${ctx}/ps/sCustomerService/">客服表列表</a></li>
		<shiro:hasPermission name="ps:sCustomerService:edit"><li><a href="${ctx}/ps/sCustomerService/form">客服表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sCustomerService" action="${ctx}/ps/sCustomerService/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>客服名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>客服名称</th>
				<th>状态</th>
				<th>排序</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sCustomerService:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sCustomerService">
			<tr>
				<td><a href="${ctx}/ps/sCustomerService/form?id=${sCustomerService.id}">
					${sCustomerService.name}
				</a></td>
				<td>
				     ${sCustomerService.state}
				</td>
				<td>
				     ${sCustomerService.sort}
				</td>
				<td>
					${sCustomerService.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sCustomerService.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sCustomerService:edit"><td>
    				<a href="${ctx}/ps/sCustomerService/form?id=${sCustomerService.id}">修改</a>
					<a href="${ctx}/ps/sCustomerService/delete?id=${sCustomerService.id}" onclick="return confirmx('确认要删除该客服表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>