<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<title>提现明细</title>
<script type="text/javascript" src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
</head>

<body style="margin: 0;padding: 0;">
<div style="width: 100%;margin: 0;">
	<div style="width: 100%;height: 30%;background-color: #84bd00;color: #fff;padding: 10% 2%;">
		<p style="font-size: 3em;margin: 0 2% 2%;">余额账户（元）</p>
		<p style="font-size: 10em;margin: 0 2% 0;">￥${(empty user.member.balance)?"0":user.member.balance}</p>
	</div>
	<div style="width: 100%;">
		<button id="dian" <c:if test="${(empty user.member.balance)||(user.member.balance eq '0')}">disabled="disabled"</c:if> style="width: 104%;padding: 4% 2%;padding-left: 4%;margin: 0; background-color: #D3D3D3;color: #fff;font-size: 3em;margin: 0;padding-left: 2%">提现</button>
	</div>
	<ul style="width: 100%;margin: 0;">
		<c:forEach items="${withDrawList}" var="draw">
		<li style="list-style: none;padding: 4% 0;border-bottom: 1px solid #ccc;box-sizing: border-box;">
			<div style="width: 10%;float: left;"><img src="${ctxStatic}/images/tixian.png" style="width: 100%;"></div>
			<div style="width: 90%; padding-left: 13%;box-sizing: border-box;margin-top: 2%;">
				<p style="font-size: 3em;margin: 0 0 1%;float: left;">
					<span>提现金额</span>
					<span>￥${draw.money}</span>
				</p>
				<p style="font-size: 2em;margin: 0;float: right;">
					<span><fmt:formatDate value="${draw.updateDate}" pattern="hh-MM-dd HH:mm:ss"/></span>
				</p>
				<div style="clear: both;"></div>
			</div>
			<div style="clear: both;"></div>
		</li>
		</c:forEach>
	</ul>
	<div id="none" style="position: fixed;top: 0%;left: 0;width: 100%;height: 100%;background-color: rgba(255,255,255,1.00);border: 1px solid red;display: none;">
	<div style="position: fixed;top: 0%;left: 0;width: 100%;background-color: rgba(255,255,255,1);padding: 10% 0;padding-left: 4%;">
		<div style="border: 1px solid #ccc; width: 92%;padding: 0 4%;box-sizing: border-box;">
			<p style="font-size: 4em;">提现金额</p>
			<div style="width: 100%;">
				<span style="width: 40%;font-size: 4em;">￥</span><input id="money" type="text" style="width: 70%;font-size: 5em;">
			</div>
			<p style="font-size: 3em;">当前余额${(empty user.member.balance)?"0":user.member.balance}元</p>
		</div>
		<p style="width: 92%;padding: 2% 0;text-align: center;color: #CCCACA;font-size: 2.4em;">24小时内到账</p>
		<button style="width: 92%;padding: 2% 0;background-color: #35B040;text-align: center;color: #fff;font-size: 4em;" onclick="whitDraw()">提现</button>
	</div>
	</div>
</div>
<script type="text/javascript">
	var balance = ${(empty user.member.balance)?"0":user.member.balance};
	$("#dian").click(function(){
		$("#none").show()
	})
	function whitDraw() {
	    var money = $("#money").val();
	    var reg = /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/;
		if(money==""){window.parent.Message("请填写金额!");return;}
		if(!reg.test(money)){window.parent.Message("请填写正确金额!");return;}
		if(money>balance){window.parent.Message("提现金额大于账户余额!");return;}
		$.post("${shop}/withDrawApply",{money:money},function (data) {
			if(data&&data=="true"){
				window.location.reload();
			}else {
				window.parent.Message("申请提现失败,请稍后重试!");
			}
        });
    }
</script>
</body>
</html>
