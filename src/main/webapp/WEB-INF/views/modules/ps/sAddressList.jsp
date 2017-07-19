<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>送货地址管理</title>
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
		<li class="active"><a href="${ctx}/ps/sAddress/">送货地址列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sAddress" action="${ctx}/ps/sAddress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员：</label>
				<form:input path="member.name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>区域ID：</label>
				<sys:treeselect id="area" name="area.id" value="${sAddress.area.id}" labelName="area.name" labelValue="${sAddress.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员</th>
				<th>区域</th>
				<th>详细地址</th>
				<th>默认地址</th>
				<th>排序</th>
				<th>备注</th>
				<th>添加时间</th>
				<shiro:hasPermission name="ps:sAddress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sAddress">
			<tr>
				<td><a href="${ctx}/ps/sAddress/form?id=${sAddress.id}">
					${sAddress.member.id}
				</a></td>
				<td>
					${sAddress.area.name}
				</td>
				<td>
					${sAddress.address}
				</td>
				<td>
					${fns:getDictLabel(sAddress.isDefault, "yes_no", "否")}
				</td>
				<td>
					${sAddress.sort}
				</td>
				<td>
					${sAddress.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sAddress.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sAddress:edit"><td>
    				<a href="${ctx}/ps/sAddress/form?id=${sAddress.id}">修改</a>
					<a href="${ctx}/ps/sAddress/delete?id=${sAddress.id}" onclick="return confirmx('确认要删除该送货地址吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>