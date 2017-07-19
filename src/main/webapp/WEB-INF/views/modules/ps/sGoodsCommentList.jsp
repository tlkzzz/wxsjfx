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
        function addReply(id,comment) {
			if(id=="")return;
			$("#xian").css("display","block");
			$("#commentId").val(id);
			$("#reply").val("");
			$("#reply").attr("placeholder","评论："+comment);
        }
        function commitReply() {
            var id = $("#commentId").val();
            var reply = $("#reply").val();
            if(id==""||reply=="")return;
            var url = "${ctx}/ps/sGoodsComment/addReply?id="+id+"&reply="+reply;
            window.location.href = url;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ps/sGoodsComment/">商品评论列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sGoodsComment" action="${ctx}/ps/sGoodsComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品：</label>
				<form:select path="goods.id" >
					<form:option value="" label="请选择"/>
					<form:options items="${goodsList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>订单编号：</label>
				<form:input path="order.orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品</th>
				<th>订单编号</th>
				<th>评论</th>
				<th>备注</th>
				<th>评论时间</th>
				<shiro:hasPermission name="ps:sGoodsComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sGoodsComment">
			<tr>
				<td><a href="${ctx}/ps/sGoodsComment/form?id=${sGoodsComment.id}">
					${sGoodsComment.goods.name}
				</a></td>
				<td>
					${sGoodsComment.order.orderNo}
				</td>
				<td>
					<span>${sGoodsComment.createBy.name}：${sGoodsComment.comment}</span>
					<c:if test="${not empty sGoodsComment.reply}">
						</br><span>${sGoodsComment.updateBy.name}回复：${sGoodsComment.reply}</span>
					</c:if>
				</td>
				<td>
					${sGoodsComment.remarks}
				</td>
				<td>
					<fmt:formatDate value="${sGoodsComment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ps:sGoodsComment:edit"><td>
					<a href="javascript:void(0)" onclick="addReply('${sGoodsComment.id}','${sGoodsComment.comment}')" >回复</a>
					<a href="${ctx}/ps/sGoodsComment/delete?id=${sGoodsComment.id}" onclick="return confirmx('确认要删除该商品评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div style="position: relative;width: 100%;height: 100%;">
		<div id="xian" style="position: fixed; top: 0; width: 100%;height: 100%;background-color: rgba(0,0,0,0.4);display: none;">
			<div style="position: absolute;left: 25%;top: 25%;background-color: #fff;width: 40%;height: 60%;">
				<div style="width: 80%;margin: 2rem auto 1rem;">
					<input type="hidden" id="commentId">
					<textarea id="reply" style="width: 100%;height: 200px; margin: 0;border: 0;padding: .4rem;box-sizing: border-box;font-size: 0.8em;line-height: 18px;border: 1px solid #ccc;" placeholder=""></textarea>
				</div>
				<div style="width: 80%;margin: 0 auto;"><input type="button" onclick="commitReply()" value="提交" style="width: 100%;padding: .6rem 0;"></div>
			</div>
		</div>
	</div>

</body>
</html>