<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,  user-scalable=no">
<title>三级分销</title>
<link href="${ctxStatic}/shop/lanrenzhijia.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/shop/dropload.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxStatic}/shop/lanrenzhijia.js"></script>
<script type="text/javascript" src="${ctxStatic}/shop/dropload.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/shop/zepto.min.js"></script>
<style>
	body{
		margin: 0;
		background-color: #E3E1E1;
	}
	img{
		display: block;
	}
	p{
		margin: 0;
	}
	a{
		text-decoration: none;
		color: #fff;
	}
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
	/* 产品图 */
	.shangpin{
		width: 100%;margin-bottom: .4em;
	}
	.shangpin img{
		width: 100%;
	}
	
	.scroller .loading{height: 60px;line-height: 60px;text-align: center;width: 100%;background-color: #f1f1f1;}
    .scroller{-webkit-overflow-scrolling:touch;}
</style>
</head>

<body>
<div>
	<!-- 轮播区域 -->
	<div class="lunbo">
		<c:forEach items="${page.list}" var="goods" varStatus="status">
			<c:if test="${status.index < 4}">
			<a href="${shop}/goodsInfo?id=${goods.id}"><img src="${goods.image}"></a>
			</c:if>
		</c:forEach>
		<ul>
			<c:forEach items="${page.list}" var="goods" varStatus="status">
				<c:if test="${status.index < 4}">
					<li></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<script>
        $(document).ready(function () {
            $("#iframeTwo",window.parent.document)[0].contentWindow.home();
            window.parent.showSms(false);
        });
        window.onload = function (){
            var oLazy = document.getElementById("container");
            new LazyLoad(oLazy);
        };

		var x = 0
		$(".lunbo img").hide().eq(0).show()
		$("li").css("background-color","#999")
		$("li").eq(0).css("background-color","#f79353")
		setInterval(function(){
			x = (x<($(".lunbo img").length-1))?x+1:0;
			$(".lunbo img").hide().eq(x).show()
			$("li").css("background-color","#999")
			$("li").eq(x).css("background-color","#f79353")
		},2000)
	</script>
	<div id="container" class="scroller" >
	<!-- 产品图 -->
	<c:forEach items="${page.list}" var="goods">
	<div class="shangpin">
		<a href="${shop}/goodsInfo?id=${goods.id}">
			<div style="position: relative;">
				<div><img src="${goods.image}"></div>
				<div style="position: absolute;bottom: 0;width: 100%;background-color: rgba(0,0,0,0.4);padding: 2rem;box-sizing: border-box;">
					<p style="float: left;font-size: 3em;">${goods.name}</p>
					<p style="float: right;font-size: 3em;">￥${empty goods.price?"0":goods.price}</p>
				</div>
			</div>
		</a>
	</div>
	</c:forEach>
</div>
<script type="text/javascript">
	var pageNo=2;
	var lockFlag=${(fn:length(page.list)==10)?false:true};

    function downUpLoad(me) {
        if (!lockFlag) {
            lockFlag = true;
            $.get("getGoodsList?pageSize=10&pageNo=" + pageNo, function (data) {
                if (data.length == 10){
                    lockFlag = false;pageNo++;
                }else {
                    me.lock();me.noData();
				}
                $.each(data,function (indx,goods) {
                    var price = (goods.price==null)?"0":goods.price;
                    var text = '<div class="shangpin"><a href="?id='+goods.id+'">'+
                        '<div style="position: relative;"><div><img src="'+goods.image+'"></div>'+
                        '<div style="position: absolute;bottom: 0;width: 100%;background-color: rgba(0,0,0,0.4);padding: 2rem;box-sizing: border-box;">'+
                        '<p style="float: left;font-size: 3em;">'+goods.name+'</p>'+
                        '<p style="float: right;font-size: 3em;">￥'+price+'</p>'+
                        '</div></div></a></div>';
                    $("#container").append(text);
                })
                me.resetload();
            })
        }else {
            me.lock();
            me.noData();
            me.resetload();
		}
    }
    $('#container').dropload({
        scrollArea : window,
        loadDownFn : function(me){
            downUpLoad(me);
        }
    });





</script>
</div>
</body>
</html>
