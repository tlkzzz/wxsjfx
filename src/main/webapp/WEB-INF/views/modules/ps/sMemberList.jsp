<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
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
		<li class="active"><a href="${ctx}/ps/sMember/">会员列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sMember" action="${ctx}/ps/sMember/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>登录名称：</label>
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>手机：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员名称</th>
				<th>登录名称</th>
				<th>头像</th>
				<th>邮箱</th>
				<th>电话</th>
				<th>手机</th>
				<th>最后登录IP</th>
				<th>最后登录时间</th>
				<th>是否允许登录</th>
				<th>备注</th>
				<th>更新时间</th>
				<shiro:hasPermission name="ps:sMember:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sMember">
			<tr>
				<td><a href="${ctx}/ps/sMember/form?id=${sMember.id}">
					${sMember.name}
				</a></td>
				<td>
					${sMember.loginName}
				</td>
				<td>
					<c:if test="${not empty sMember.photo}"><img src="${sMember.photo}" style="height:50px;width:50px"/></c:if>
				</td>
				<td>
					${sMember.email}
				</td>
				<td>
					${sMember.tel}
				</td>
				<td>
					${sMember.mobile}
				</td>
				<td>
					${sMember.loginIp}
				</td>
				<td>
					<fmt:formatDate value="${sMember.loginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(sMember.loginFlag, "yes_no", "")}
				</td>
				<td>
					${sMember.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sMember.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sMember:edit"><td>
    				<a href="${ctx}/ps/sMember/form?id=${sMember.id}">修改</a>
					<a href="${ctx}/ps/sMember/delete?id=${sMember.id}" onclick="return confirmx('确认要删除该会员吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>