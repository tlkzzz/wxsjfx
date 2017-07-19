<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/ps/sOrder/">订单列表</a></li>
		<!--<shiro:hasPermission name="ps:sOrder:edit"><li><a href="${ctx}/ps/sOrder/form">订单添加</a></li></shiro:hasPermission> -->
	</ul>
	<form:form id="searchForm" modelAttribute="sOrder" action="${ctx}/ps/sOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>商品</th>
				<th>规格</th>
				<th>数量</th>
				<th>价格</th>
				<th>商品成本价</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sOrder">
			<tr>
				<td>
				    ${sOrder.orderNo}
				</td>
				<td>
					${sOrder.goodsId}
				</td>
				<td>
					${sOrder.specIds}
				</td>
				<td>
					${sOrder.num}
				</td>
				<td>
					<fmt:formatNumber value="${sOrder.price}" pattern="#.####"/>
				</td>
				<td>
					<fmt:formatNumber value="${sOrder.costPrice}" pattern="#.####"/>
				</td>
				<td>
					${sOrder.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sOrder:edit"><td>
    				<a href="${ctx}/ps/sOrder/form?id=${sOrder.id}">修改</a>
					<a href="${ctx}/ps/sOrder/delete?id=${sOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>