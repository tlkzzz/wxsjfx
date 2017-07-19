<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收款表管理</title>
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
		<li class="active"><a href="${ctx}/ps/sReceipt/">收款表列表</a></li>
		<shiro:hasPermission name="ps:sReceipt:edit"><li><a href="${ctx}/ps/sReceipt/form">收款表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sReceipt" action="${ctx}/ps/sReceipt/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单：</label>
				<form:input path="orderId.orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr><th>订单</th>
				<th>应收金额</th>
				<th>实收金额</th>
				<th>收款时间</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sReceipt:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sReceipt">
			<tr>
				<td>
					${sReceipt.sOrder.orderId}
				</td>
				<td>
					<fmt:formatNumber value="${sMemberCommission.receivableMoney}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${sMemberCommission.revenueMoney}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatDate value="${sReceipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sReceipt.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sReceipt.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sReceipt:edit"><td>
    				<a href="${ctx}/ps/sReceipt/form?id=${sReceipt.id}">修改</a>
					<a href="${ctx}/ps/sReceipt/delete?id=${sReceipt.id}" onclick="return confirmx('确认要删除该收款表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>