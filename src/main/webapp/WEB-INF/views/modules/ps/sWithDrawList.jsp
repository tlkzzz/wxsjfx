<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现记录管理</title>
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
		<li class="active"><a href="${ctx}/ps/sWithDraw/">提现记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sWithDraw" action="${ctx}/ps/sWithDraw/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员名称：</label>
				<form:input path="createBy.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>提现会员</th>
				<th>提现金额</th>
				<th>提现时间</th>
				<th>备注</th>
				<shiro:hasPermission name="ps:sWithDraw:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sWithDraw">
			<tr>
				<td>
					${sWithDraw.createBy.name}
				</td>
				<td>
					${sWithDraw.money}
				</td>
				<td>
					<fmt:formatDate value="${sWithDraw.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sWithDraw.remarks}
				</td>
				<shiro:hasPermission name="ps:sWithDraw:edit"><td>
    				<%--<a href="${ctx}/ps/sWithDraw/form?id=${sWithDraw.id}">修改</a>--%>
					<%--<a href="${ctx}/ps/sWithDraw/delete?id=${sWithDraw.id}" onclick="return confirmx('确认要删除该提现记录吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>