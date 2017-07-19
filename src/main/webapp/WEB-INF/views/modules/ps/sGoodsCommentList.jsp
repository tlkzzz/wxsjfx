<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品评论管理</title>
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
		<li class="active"><a href="${ctx}/ps/sGoodsComment/">商品评论列表</a></li>
		<shiro:hasPermission name="ps:sGoodsComment:edit"><li><a href="${ctx}/ps/sGoodsComment/form">商品评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sGoodsComment" action="${ctx}/ps/sGoodsComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品ID：</label>
				<form:input path="goodsId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>订单ID：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品ID</th>
				<th>订单ID</th>
				<th>评论</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sGoodsComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sGoodsComment">
			<tr>
				<td><a href="${ctx}/ps/sGoodsComment/form?id=${sGoodsComment.id}">
					${sGoodsComment.goodsId}
				</a></td>
				<td>
					${sGoodsComment.orderId}
				</td>
				<td>
					${sGoodsComment.comment}
				</td>
				<td>
					${sGoodsComment.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sGoodsComment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sGoodsComment:edit"><td>
    				<a href="${ctx}/ps/sGoodsComment/form?id=${sGoodsComment.id}">修改</a>
					<a href="${ctx}/ps/sGoodsComment/delete?id=${sGoodsComment.id}" onclick="return confirmx('确认要删除该商品评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>