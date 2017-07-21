<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
		<li><a href="${ctx}/ps/sGoods/">商品列表</a></li>
		<li class="active"><a href="${ctx}/ps/sGoods/form?id=${sGoods.id}">商品<shiro:hasPermission name="ps:sGoods:edit">${not empty sGoods.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ps:sGoods:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sGoods" action="${ctx}/ps/sGoods/${empty sGoods.gener?'form':'save'}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<c:if test="${not empty sGoods.gener}"><form:hidden path="gClass.id"/></c:if>
		<sys:message content="${message}"/>
		<c:if test="${empty sGoods.gener}">
		<div class="control-group">
			<label class="control-label">商品分类：</label>
			<div class="controls">
				<sys:treeselect id="gClass" name="gClass.id" value="${sGoods.GClass.id}" labelName="gClass.name" labelValue="${sGoods.GClass.name}"
								title="商品分类" url="/ps/sGoodsClass/treeData" extId="0" cssClass="" allowClear="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">商品类型：</label>
			<div class="controls">
				<form:select path="gener.id" cssClass="required">
					<form:option value="" label="请选择"/>
					<form:options items="${generList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>--%>
		</c:if>
		<c:if test="${not empty sGoods.gener}">
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品副标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品价格：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" number="true" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场价：</label>
			<div class="controls">
				<form:input path="marketPrice" htmlEscape="false" number="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成本价：</label>
			<div class="controls">
				<form:input path="costPrice" htmlEscape="false" number="true" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:forEach items="${sGoods.gener.specClassList}" var="specClass">
		<div class="control-group">
			<label class="control-label">${specClass.name}：</label>
			<div class="controls">
				<form:checkboxes items="${specClass.specList}" path="specIds" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</div>
		</div>
		</c:forEach>
		<div class="control-group">
			<label class="control-label">商品库存：</label>
			<div class="controls">
				<form:input path="goodsStock" htmlEscape="false" digits="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品货号：</label>
			<div class="controls">
				<form:input path="itemNum" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品图片多图：</label>
			<div class="controls">
				<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="images" type="images" uploadPath="/photo" selectMultiple="true" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品品牌：</label>
			<div class="controls">
				<form:input path="bandsId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:input path="supplierId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品详细信息：</label>
			<div class="controls">
				<form:textarea id="goodsDesc" htmlEscape="true" path="goodsDesc" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="goodsDesc" uploadPath="/goods/image" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品发布：</label>
			<div class="controls">
				<form:checkboxes path="publish" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品推荐：</label>
			<div class="controls">
				<form:input path="recommend" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> 关键字之间用","隔开</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" digits="true" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge "/>
			</div>
		</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="ps:sGoods:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="${empty sGoods.gener?'下一步':'保 存'}"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>