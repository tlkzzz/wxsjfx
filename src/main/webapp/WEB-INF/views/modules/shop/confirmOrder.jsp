<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<title>订单确认页面</title>
<style>
	/* 公共部分 */
	body,p{
		margin: 0;
	}
	img{
		width: 20%;
		display: inline-block;
	}
	.clearfix{
		clear: both;
	}
	/* 第一栏地址 */
	.add_box{
		width: 100%;
		vertical-align: middle;
	}
	.icon{
		width: 8%;
		float: left;
		vertical-align: middle;
		margin: 2rem 1rem;
	}
	.icon img{
		width: 100%;
	}
	.box_add{
		float: left;
		margin: .8rem 0;
	}
	.name{
		font-size: 1em;
		color: #000;
		margin-top: .8rem;
	}
	.add{
		font-size: 0.6em;
		color: #999;
	}
	.tel{
		font-size: 1em;
		color: #000;
		margin: .8rem 0;
		margin-left: 4rem;
		vertical-align: middle;
	}
	/* 选择地址 隐藏 */
	.box{
		width: 100%;
		height: 200px;
		background-color: #999;
		overflow: auto;
		display: none;
	}
	.choose{
		width: 100%;
		padding: 1rem 0;
		border-bottom: 1px solid #fff;
	}
	.text{
		float: left;
		width: 76%;
		margin: 0 1rem;
	}
	.text p{
		font-size: 0.8em;
		color: #fff;
	}
	.img img{
		width: 8%;
	}
	.jia{
		width: 100%;
		text-align: center;
		padding: .8rem 0;
		font-size: .8em;
		color: #fff;
	}
	.jia img{
		width:8%;
		vertical-align: middle
	}
	/* 分割线 */
	.fenge{
		width: 100%;
		height: 4px;
		background-image: url(${ctxStatic}/images/bg_fenge.png);
		background-size: 10%;
	}
	/* 商品信息 */
	.dingdan{
		width: 100%;
		border-bottom: 1px solid #ccc;
	}
	.left{
		float: left;
		font-size: 0.8em;
		padding: .8rem;
		color: #999;
	}
	.right{
		float: right;
		font-size: 0.8em;
		color: #006a92;
		padding: .8rem;
	}
	.er{
		width: 100%;
		background-color: #f2f1ef;
		margin-bottom: .4rem;
	}
	.tutu{
		width: 30%;
		float: left;
		margin-right: .4rem;
	}
	.tutu img{
		width: 100%;
		display: block;
	}
	.shangpin{
		font-size: 1em;
		color: #000;
		padding-top: 1rem;
	}
	.dis_box{
		width: 100%;
		font-size: .9em;
		padding: .8rem 0;
		border-bottom: 1px solid #ccc;
	}
	.dis{
		float: left;
		margin-left: 1rem;
	}
	.dis_ch,.dis_img{
		float: right;
	}
	.dis_ch{
		margin-right: 0.4rem;
	}
	.dis_img{
		width: 5%;
		margin-right: 0.4rem;
		margin-left: 0.4rem;
	}
	.dis_img img{
		width: 100%;
		vertical-align: middle;
	}
</style>
<script src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
<script type="text/javascript">
	function chooseAddress(id) {
		$("#jhr").text($("#jhr_"+id).text());
		$("#tel").text($("#tel_"+id).text());
		$("#info").text($("#info_"+id).text());
		$("#addressId").val(id);
    }
</script>
</head>

<body>
<div>
	<!-- 第一栏地址 -->
	<div class="add_box">
		<div class="icon">
			<img src="${ctxStatic}/images/add.png">
		</div>
		<div class="box_add">
			<c:if test="${fn:length(addressList)>0}">
			<p style="margin: .8rem 0 .4rem;">
				<span class="name" id="jsr">${addressList[0].shr}</span>
				<span class="tel" id="tel">${addressList[0].tel}</span>
			</p>
			<div class="clearfix"></div>
			<p class="add" id="info">${addressList[0].area.parent.parent.name}，
					${addressList[0].area.parent.name}，
					${addressList[0].area.name}，${addressList[0].address}</p>
			</c:if>
			<c:if test="${empty addressList}">
				<p style="margin: .8rem 0 .4rem;">
					<span class="name">还没有送货地址，点击新增吧！</span>
				</p>
			</c:if>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- 选择地址 隐藏 -->
	<div class="box">
		<c:forEach items="${addressList}" var="address">
		<div class="choose">
			<div class="text" onclick="chooseAddress('${address.id}')"><p>
				<span id="shr_${address.id}">${address.shr}</span>,
				<span id="tel_${address.id}">${address.tel}</span>,
				<span id="info_${address.id}">${address.area.parent.parent.name}，${address.area.parent.name}，${address.area.name}，${address.address}</span></p></div>
			<div class="img"><a href="${shop}/xgshList?data=${address.id}" style="text-decoration: none;" onClick="change"><img src="${ctxStatic}/images/bianji.png"></a></div>
		</div>
		</c:forEach>
		<a href="${shop}/listss" style="text-decoration: none;" onClick="change">
		<div class="jia">
			<span>新增</span> <span><img src="${ctxStatic}/images/jia.png"></span> <span>地址</span>
		</div>
		</a>
	</div>
	<!-- 分割线 -->
	<div class="fenge"></div>
	<!-- 商品信息 -->
	<div>
		<div class="dingdan">
			<p class="left">订单商品</p>
			<p class="right"><a href="${shop}/shoplist" style="text-decoration: none;color: #006a92;">订单修改</a></p>
			<p class="clearfix"></p>
		</div>
		<c:forEach items="${orderList}" var="sShop">
		<div class="er">
			<div class="tutu"><img src="${sShop.goods.image}"></div>
			<div class="shangpin">
				<p>${sShop.goods.name}</p>
				<c:forEach items="${sShop.goods.gener.specClassList}" var="specClass">
				<p style="font-size: 0.8em;margin-top: 0.4rem;color: #999;">${specClass.name}:<c:forEach items="${specClass.sSpecList}" var="spec">${spec.name}&nbsp;</c:forEach></p>
				</c:forEach>
				<p style="float: left;font-size: 0.8em;color: #f79323; margin-top:1rem;">￥${sShop.price}</p>
				<p style="float: right;font-size: 0.8em;margin-top: 1rem;margin-right: 1rem;">*${sShop.num}</p>
				<p class="clearfix"></p>
			</div>
		</div>
		</c:forEach>
		<div class="dis_box">
			<p class="dis">微信支付</p>
			<p class="clearfix"></p>
		</div>
		<div class="dis_box">
			<p class="dis">配送方式</p>
			<p class="dis_img"><img src="${ctxStatic}/images/btn-fanhui-hui.png"></p>
			<p class="dis_ch">快递 免邮</p>
			<p class="clearfix"></p>
		</div>
		<div class="dis_box">
			<p class="dis">运费金额</p>
			<p class="dis_ch" style="margin-right: .8rem; color: #f79323;">￥0.00</p>
			<p class="clearfix"></p>
		</div>
		<div class="dis_box">
			<p class="dis">应付总额</p>
			<p class="dis_ch" style="margin-right: .8rem; color: #f79323;">￥${(empty orderTotal)?0:orderTotal}</p>
			<p class="clearfix"></p>
		</div>
		<div style="padding-top: .4rem;padding-bottom: .2rem; border-bottom: 1px solid #ccc;">
			<textarea style="width: 100%;height: 70px;margin: 0;border: 0;padding: .4rem;box-sizing: border-box;font-size: 0.8em;line-height: 18px;" placeholder="备注：公司指定快递，少数偏远地区以及村、组快件需自提，快递物流时效为一个月，请在发货后7天内及时查询物流信息，新旧包装随机发货，不支持制定包装。"></textarea>
		</div>
	</div>
	<!-- 支付按钮 -->
	<div style="width: 80%; margin: 2rem auto !important;border-radius: 8px;">
		<input type="hidden" id="addressId" value="${(empty addressList)?"":addressList[0].id}">
		<input type="button" value="立即支付" onclick="submitForm()" style="width: 100%;border: 0;background-color: #84bd00;padding: 1rem 2rem;border-radius: 8px;color: #fff;font-size: 1.2em;">
	</div>
	<script>
		$(".add_box").click(function(){
			$(".box").toggle();
		});
		function change(){
			$("#iframeTwo",window.parent.document)[0].contentWindow.personal_center();
		}
		function submitForm() {
			var addressId = $("#addressId").val();
			if(addressId==""){
			    window.parent.Message("请选择送货地址!");
			    return;
			}
			window.location.href = "${shop}/paymentOrder?addressId="+addressId;
        }
	</script>
</div>
</body>
</html>
