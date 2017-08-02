<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员关系管理</title>
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
		<li class="active"><a href="${ctx}/ps/sMemberRelation/">会员关系列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sMemberRelation" action="${ctx}/ps/sMemberRelation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>老会员名称：</label>
				<form:input path="oldMember.name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>新会员名称：</label>
				<form:input path="newMember.name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>老会员</th>
				<th>新会员</th>
				<th>建立时间</th>
				<th>排序</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sMemberRelation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sMemberRelation">
			<tr>
				<td>
					${sMemberRelation.oldMember.name}
				</td>
				<td>
					${sMemberRelation.newMember.name}
				</td>
				<td>
					<fmt:formatDate value="${sMemberRelation.buildDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sMemberRelation.sort}
				</td>
				<td>
					${sMemberRelation.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sMemberRelation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sMemberRelation:edit"><td>
					<a href="${ctx}/ps/sMemberRelation/delete?id=${sMemberRelation.id}" onclick="return confirmx('确认要删除该会员关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>