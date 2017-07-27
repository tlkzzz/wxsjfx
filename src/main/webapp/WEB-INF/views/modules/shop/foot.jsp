<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta charset="utf-8">
<title>底部</title>
<script type="text/javascript" src="../static/shop/jquery-1.9.1.js"></script>
<style>
	body{
		padding: 0;
		margin: 0;
	}
	p{
		margin: 3% 0 2%;
		font-size: 2em;
	}
	a{
		text-decoration: none;
		color: #333;
	}
	/* 底部操作栏 */
	.bottom{
		width: 100%;
		position: fixed;
		bottom: 0;
		background-color: #fff;
		padding: 6% 0 1.8%;
	}
	.bottom_box{
		border-top: 1px solid #ccc;
		width: 100%;
		background-color: #fff;
		font-family: "微软雅黑";
		color: #333;
	}
	.home,.shopping,.personal_center{
		float: left;
		width: 33.3333%;
		text-align: center;
	}
	#text{
		color: #84bd00;
	}
	.home img,.shopping img,.personal_center img{
		width: 18%;
	}
</style>
</head>

<body>
<!-- 底部操作栏 -->
	<div class="bottom">
		<div class="bottom_box">
			<a href="home" target="jj">
				<div class="home color">
					<p><img src="../static/images/btn-dibushouye.png"></p>
					<p id="text">首页</p>
				</div>
				<div class="clearfix"></div>
			</a>
			<a href="shoplist" target="jj">
				<div class="shopping color">
					<p><img src="../static/images/btn-dibugouwuche-h.png"></p>
					<p>购物车</p>
				</div>
				<div class="clearfix"></div>
			</a>
			<a href="PersonalCenter" target="jj">
				<div class="personal_center color">
					<p><img src="../static/images/btn-dibugerenzhongxin-h.png"></p>
					<p>个人中心</p>
				</div>
				<div class="clearfix"></div>
			</a>
			<div class="clearfix"></div>
		</div>
	</div>
	<script>
		$(".shopping").click(function(){
			shopping();
		})
		$(".home").click(function(){
			home();
		})
		$(".personal_center").click(function(){
			personal_center();
		})
		function home(){
			$(".shopping img").attr("src","../static/images/btn-dibugouwuche-h.png")
			$(".shopping,.personal_center").css("color","#333")
			$(".home img").attr("src","../static/images/btn-dibushouye.png")
			$("#text").css("color","#84bd00")
			$(".personal_center img").attr("src","../static/images/btn-dibugerenzhongxin-h.png")
		}
		function shopping(){			
			$(".shopping img").attr("src","../static/images/btn-dibugouwuche.png")
			$(".shopping").css("color","#84bd00")
			$(".home img").attr("src","../static/images/btn-dibushouye-h.png")
			$("#text,.personal_center").css("color","#333")
			$(".personal_center img").attr("src","../static/images/btn-dibugerenzhongxin-h.png")
		}
		function personal_center(){			
			$(".shopping img").attr("src","../static/images/btn-dibugouwuche-h.png")
			$(".shopping,#text").css("color","#333")
			$(".home img").attr("src","../static/images/btn-dibushouye-h.png")
			$(".personal_center").css("color","#84bd00")
			$(".personal_center img").attr("src","../static/images/btn-dibugerenzhongxin.png")
		}
	</script>
</body>
</html>
