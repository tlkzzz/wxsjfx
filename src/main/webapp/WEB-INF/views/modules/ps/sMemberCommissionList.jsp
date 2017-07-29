<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员提成管理</title>
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
		<li class="active"><a href="${ctx}/ps/sMemberCommission/">会员提成列表</a></li>
		<!--<shiro:hasPermission name="ps:sMemberCommission:edit"><li><a href="${ctx}/ps/sMemberCommission/form">会员提成添加</a></li></shiro:hasPermission> -->
	</ul>
	<form:form id="searchForm" modelAttribute="sMemberCommission" action="${ctx}/ps/sMemberCommission/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>老会员：</label>
				<form:input path="oldMemberId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>提成会员：</label>
				<form:input path="newMemberId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>老会员</th>
				<th>提成会员</th>
				<th>订单</th>
				<th>提成金额</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sMemberCommission:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sMemberCommission">
			<tr>
				<td>
					${sMemberCommission.oldMemberId}
				</td>
				<td>
					${sMemberCommission.newMemberId}
				</td>
				<td>
					${sMemberCommission.receipt.revenueMoney}
				</td>
				<td>
					<fmt:formatNumber value="${sMemberCommission.total}" pattern="#.####"/>
				</td>
				<td>
					${sMemberCommission.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sMemberCommission.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sMemberCommission:edit"><td>
    				<a href="${ctx}/ps/sMemberCommission/form?id=${sMemberCommission.id}">修改</a>
					<a href="${ctx}/ps/sMemberCommission/delete?id=${sMemberCommission.id}" onclick="return confirmx('确认要删除该会员提成吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>