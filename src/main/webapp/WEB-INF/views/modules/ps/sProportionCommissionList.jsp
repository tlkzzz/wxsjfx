<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提成比例表管理</title>
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
		<li class="active"><a href="${ctx}/ps/sProportionCommission/">提成比例表列表</a></li>
		<shiro:hasPermission name="ps:sProportionCommission:edit"><li><a href="${ctx}/ps/sProportionCommission/form">提成比例表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sProportionCommission" action="${ctx}/ps/sProportionCommission/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>提成比例：</label>
				<form:input path="commission" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>提成比例</th>
				<th>排序</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sProportionCommission:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sProportionCommission">
			<tr>
				<td>
					<fmt:formatNumber value="${sProportionCommission.commission}" pattern="#.####"/>%
				</td>
				<td>
					${sProportionCommission.sort}
				</td>
				<td>
					${sProportionCommission.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sProportionCommission.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sProportionCommission:edit"><td>
    				<a href="${ctx}/ps/sProportionCommission/form?id=${sProportionCommission.id}">修改</a>
					<a href="${ctx}/ps/sProportionCommission/delete?id=${sProportionCommission.id}" onclick="return confirmx('确认要删除该提成比例表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>