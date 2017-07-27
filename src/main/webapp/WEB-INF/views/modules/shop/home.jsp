<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,  user-scalable=no">
<title>三级分销</title>
<link href="${ctxStatic}/shop/lanrenzhijia.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctxStatic}/shop/lanrenzhijia.js"></script>
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
			<c:if test="${status.index < 3}">
			<a href="${shop}/goodsInfo?id=${goods.id}"><img src="${goods.image}"></a>
			</c:if>
		</c:forEach>
		<ul>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<script>
        $(document).ready(function () {
            $("#iframeTwo",window.parent.document)[0].contentWindow.home();
            //window.parent.showSms();
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
			x = (x<3)?x+1:0;
			$(".lunbo img").hide().eq(x).show()
			$("li").css("background-color","#999")
			$("li").eq(x).css("background-color","#f79353")
		},2000)
	</script>
	<div id="container" class="scroller" >
	<div class="loading">下拉刷新数据</div>
	<!-- 产品图 -->
	<div>
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
		<%--<div class="shangpin">
			<a href="xiangqing.html">
				<div style="position: relative;">
					<div><img src="${ctxStatic}/images/产品图/shangpin1.png"></div>
					<div style="position: absolute;bottom: 0;width: 100%;background-color: rgba(0,0,0,0.4);padding: .8rem;box-sizing: border-box;">
						<p style="float: left;">商品名称</p>
						<p style="float: right;">￥45.45</p>
					</div>
				</div>
			</a>
		</div>
		<div class="shangpin">
			<a href="xiangqing.html">
				<div style="position: relative;">
					<div><img src="${ctxStatic}/images/产品图/shangpin1.png"></div>
					<div style="position: absolute;bottom: 0;width: 100%;background-color: rgba(0,0,0,0.4);padding: .8rem;box-sizing: border-box;">
						<p style="float: left;">商品名称</p>
						<p style="float: right;">￥45.45</p>
					</div>
				</div>
			</a>
		</div>
		<div class="shangpin">
			<a href="xiangqing.html">
				<div style="position: relative;">
					<div><img src="../static/images/产品图/shangpin1.png"></div>
					<div style="position: absolute;bottom: 0;width: 100%;background-color: rgba(0,0,0,0.4);padding: .8rem;box-sizing: border-box;">
						<p style="float: left;">商品名称</p>
						<p style="float: right;">￥45.45</p>
					</div>
				</div>
			</a>
		</div>
		<div class="shangpin">
			<a href="xiangqing.html">
				<div style="position: relative;">
					<div><img src="${ctxStatic}/images/产品图/shangpin1.png"></div>
					<div style="position: absolute;bottom: 0;width: 100%;background-color: rgba(0,0,0,0.4);padding: .8rem;box-sizing: border-box;">
						<p style="float: left;">商品名称</p>
						<p style="float: right;">￥45.45</p>
					</div>
				</div>
			</a>
		</div>--%>
	</div>
</div>
<script type="text/javascript">
	var pageNo=1;
	var lockFlag=false;

    function downUpLoad() {
//        if ($(this).scrollTop() + $(this).height() > $(document).height()) {
            if (!lockFlag) {
                lockFlag = true;
                $.get("getGoodsList?pageSize=10&pageNo=" + pageNo, function (data) {
                    if (data.length == 10) lockFlag = false;pageNo++;
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
                    alert("1234");
                })
            }
//        }
    }


    var slide = function (option) {
        var defaults={
            container:'',
            next:function(){}
        }
        var start,
            end,
            length,
            isLock = false,//是否锁定整个操作
            isCanDo = false,//是否移动滑块
            isTouchPad = (/hp-tablet/gi).test(navigator.appVersion),
            hasTouch = 'ontouchstart' in window && !isTouchPad;
        var obj = document.querySelector(option.container);
        var loading=obj.firstElementChild;
        var offset=loading.clientHeight;
        var objparent = obj.parentElement;
		/*操作方法*/
        var fn =
            {
                //移动容器
                translate: function (diff) {
                    obj.style.webkitTransform='translate3d(0,'+diff+'px,0)';
                    obj.style.transform='translate3d(0,'+diff+'px,0)';
                },
                //设置效果时间
                setTransition: function (time) {
                    obj.style.webkitTransition='all '+time+'s';
                    obj.style.transition='all '+time+'s';
                },
                //返回到初始位置
                back: function () {
                    fn.translate(0 - offset);
                    //标识操作完成
                    isLock = false;
                },
                addEvent:function(element,event_name,event_fn){
                    if (element.addEventListener) {
                        element.addEventListener(event_name, event_fn, false);
                    } else if (element.attachEvent) {
                        element.attachEvent('on' + event_name, event_fn);
                    } else {
                        element['on' + event_name] = event_fn;
                    }
                }
            };

        fn.translate(0-offset);
        fn.addEvent(obj,'touchstart',start);
        fn.addEvent(obj,'touchmove',move);
        fn.addEvent(obj,'touchend',end);
        fn.addEvent(obj,'mousedown',start)
        fn.addEvent(obj,'mousemove',move)
        fn.addEvent(obj,'mouseup',end)

        //滑动开始
        function start(e) {
            if (objparent.scrollTop <= 0 && !isLock) {
                var even = typeof event == "undefined" ? e : event;
                //标识操作进行中
                isLock = true;
                isCanDo = true;
                //保存当前鼠标Y坐标
                start = (hasTouch&&even.touches) ? even.touches[0].pageY : even.pageY;
                //消除滑块动画时间
                fn.setTransition(0);
                loading.innerHTML='下拉刷新数据';
            }
            return false;
        }

        //滑动中
        function move(e) {
            if (objparent.scrollTop <= 0 && isCanDo) {
                var even = typeof event == "undefined" ? e : event;
                //保存当前鼠标Y坐标
                end = hasTouch ? even.touches[0].pageY : even.pageY;
                if (start < end) {
                    even.preventDefault();
                    //消除滑块动画时间
                    fn.setTransition(0);
                    //移动滑块
                    if((end-start-offset)/2<=150) {
                        length=(end - start - offset) / 2;
                        fn.translate(length);
                    }
                    else {
                        length+=0.3;
                        fn.translate(length);
                    }
                }
            }
        }
        //滑动结束
        function end(e) {
            if (isCanDo) {
                isCanDo = false;
                //判断滑动距离是否大于等于指定值
                if (end - start >= offset) {
                    //设置滑块回弹时间
                    fn.setTransition(1);
                    //保留提示部分
                    fn.translate(0);
                    //执行回调函数
                    loading.innerHTML='正在刷新数据';
                    if (typeof option.next == "function") {
                        option.next.call(fn, e);
                    }
                } else {
                    //返回初始状态
                    fn.back();
                }
            }
        }
    }
    slide({container:"#container",next: function (e) {
        //松手之后执行逻辑,ajax请求数据，数据返回后隐藏加载中提示
		downUpLoad();
        var that = this;
        setTimeout(function () {
            that.back.call();
        }, 2000);
    }});




</script>
</div>
</body>
</html>
