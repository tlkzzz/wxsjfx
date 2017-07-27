<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<title>产品详情页</title>
<script src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
<style>
	/* 轮播区域 */
	.lunbo{
		position: relative;
		width: 100%;
		margin-bottom: .4em;
		z-index: 999;
	}
	.lunbo img{
		width: 100%;
		display: block;
	}
	.lunbo ul{
		position: absolute;
		left: 50%;
		bottom: 0;
		margin-left: -70px;
	}
	.lunbo li{
		float: left;
		list-style: none;
		width: 10px;
		height: 10px;
		border-radius: 10px;
		background-color: #000;
		margin-right: 10px;
	}
</style>
</head>

<body style="margin: 0;">
<div style="width: 100%;padding-top: 1rem;">
	<!-- 轮播区域 -->
	<div class="lunbo">
		<c:forEach items="${fn:split(goods.images, '|')}" var="url">
			<a href="#"><img src="${url}"></a>
		</c:forEach>
		<ul>
			<c:forEach items="${fn:split(goods.images, '|')}" var="url">
			<li></li>
			</c:forEach>
		</ul>
	</div>
	<script>
		var x = 0
		$(".lunbo img").hide().eq(0).show();
		$("li").css("background-color","#999");
		$("li").eq(0).css("background-color","#f79353");
		setInterval(function(){
			if(x<($(".lunbo img").length-1)){
				x=x+1
			}else{
				x=0
			}
			$(".lunbo img").hide().eq(x).show()
			$("li").css("background-color","#999")
			$("li").eq(x).css("background-color","#f79353")
		},2000)
	</script>
  <div style="width: 100%;padding: .8rem;box-sizing: border-box;">
		<div style="width: 100%;">
			<div style="width: 60%;float: left;">
				<p style="font-size: 3.4em;color: #333;margin: .4rem 0;">${goods.name}</p>
			</div>
			<div style="width: 40%;float: right;">
				<p style="font-size: 3em;color: #f79353;float: right; margin: 0;">￥${goods.price}元</p>
			</div>
			<div style="clear: both;"></div>
		</div>
		<div style="width: 100%;position: relative;">
			<c:if test="${not empty goods.gener}">
			<c:forEach items="${goods.gener.specClassList}" var="specClass">
			<p style="margin: 1rem 1rem 1rem 0;">
				<span style="color: #666;font-size: 2.2em;">${specClass.name}:</span>
				<c:forEach items="${specClass.sSpecList}" var="spec">
				<span style="font-size: 2.2rem;">${spec.name}</span>
				</c:forEach>
			</p>
			</c:forEach>
			</c:if>
			<p style="font-size:2em;color: #999;margin: 0;float: right;position: absolute;bottom: .2em;right: 0;"><s>市场价:￥${goods.marketPrice}元</s></p>
			<p style="clear: both;"></p>
		</div>
	</div>
	<div style="width: 100%;font-size: 2em;">
		${goods.goodsDesc}
	</div>
	<div>
		<p id="xian" style="text-align: center;color: #999;display: none;font-size: 3em;">查看评论 | COMMENT</p>
		<p id="yin" style="text-align: center;color: #999;font-size: 3em;">隐藏评论 | COMMENT</p>
		<div id="pinglun" style="width: 75%;margin: 0 auto;height: 100px;border: 1px solid #ccc;overflow: auto;color: #999;padding: .4rem .8rem;">
			<c:forEach items="${commentList}" var="comment">
				<p style="font-size: 2rem;margin:.4rem 0;">ID:${comment.createBy.name}  <fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				<p style="font-size: 2rem;margin: .4rem 0;margin-bottom: .8rem;">${comment.comment}</p>
			</c:forEach>
		</div>
	</div>
	<div id="anniu" style="width: 100%;margin-top: 1.8rem;margin-bottom: 20%;">
		<a href="${shop}/shoplist?id=${goods.id}" onClick="changeImage()"><input id="an" type="button" value="立 即 购 买" style="width: 100%;background-color: #84bd00;color: #fff;font-size: 4em;border: 0;padding: 2rem 0;"></a>
	</div>
	<script>
		$(document).ready(function () {
            $("#iframeTwo",window.parent.document)[0].contentWindow.home();
        });
		$("#yin").click(function(){
			$("#xian").show()
			$("#yin,#pinglun").hide()
		})
		$("#xian").click(function(){
			$("#xian").hide()
			$("#yin,#pinglun").show()
		})
		$("#anniu").click(function(){
			$("#an").css("background-color","#f79353")
		})
	</script>
</div>
</body>
</html>
