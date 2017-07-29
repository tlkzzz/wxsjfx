<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<title>支付成功页面</title>
<style>
	p{
		margin: 0;
	}
</style>
</head>

<body style="margin: 0;">
<div>
	<div style="width: 100%;text-align: center;margin-top: 3rem;">
		<p><img src="${ctxStatic}/images/zhifuwancheng.png" style="width: 40%;"></p>
		<p style="font-size: 3rem;font-weight: bold;margin: 1rem 0;">支付成功！</p>
	</div>
	<div style="width: 100%;margin-top: 2rem;">
		<div style="width: 80%;margin: 0 auto;font-size: 2rem;">
			<p style="margin-bottom: 1rem;">
				<span style="font-weight: bold;">商品名称：</span>
				<span><c:forEach items="${orderList}" var="order" varStatus="sta">${order.goods.name}${(sta.last)?"":","}</c:forEach></span>
			</p>
			<p style="margin-bottom: 1rem;">
				<span style="font-weight: bold;">订单时间：</span>
				<span><fmt:formatDate value="${receipt.receiptDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
			<p style="margin-bottom: 1rem;"><span style="font-weight: bold;">订单号码：</span><span>ABCDABCDABCDABCDABCD</span></p>
			<p style="margin-bottom: 1rem;"><span style="font-weight: bold;">支付金额：</span><span>￥${receipt.revenueMoney}</span></p>
		</div>
	</div>
	<div style="width: 100%;margin-top: 2rem;">
		<p style="width: 80%;margin: 0 auto 2rem;">
			<a href="${shop}">
			<input type="button" value="返回首页" style="width: 100%;background-color: #86bd00;border: 0;color: #fff;border-radius: 4px;padding: 2rem;font-size: 4rem;">
			</a>
		</p>
	</div>
</div>
</body>
</html>
