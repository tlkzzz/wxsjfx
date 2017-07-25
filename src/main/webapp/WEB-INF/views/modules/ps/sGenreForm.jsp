<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>类型管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ps/sGenre/">类型管理列表</a></li>
		<li class="active"><a href="${ctx}/ps/sGenre/form?id=${sGenre.id}">类型管理<shiro:hasPermission name="ps:sGenre:edit">${not empty sGenre.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ps:sGenre:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sGenre" action="${ctx}/ps/sGenre/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格分类：</label>
			<div class="controls">
				<c:forEach items="${sSpecClassList}" var="spec">
				<span>
					<input name="specClass" type="checkbox" value="${spec.id}"
						   <c:if test="${fn:contains(sGenre.specClass, spec.id)}">checked="checked"</c:if>/>
					<label>${spec.name}</label>
				</span>
				</c:forEach>
				<%--<form:checkboxes path="specClass" items="${sSpecClassList}" itemLabel="name" itemValue="id" htmlEscape="false"/>--%>
				<%--<form:input path="sort" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ps:sGenre:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>