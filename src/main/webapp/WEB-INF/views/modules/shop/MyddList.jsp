<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>我的订单</title>
    <script src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
    <style>
        body,p{
            margin: 0;
        }
        body{
            background-color: #F0F0F0;
            width: 100%;
            height: 100%;
        }
        .box{
            width: 100%;
            box-shadow: 0 0 20px #666;
            background-color: #fff;
        }
        .text{
            width: 20%;
            float: left;
            text-align: center;
            font-size:2.4rem;
            color: #999;
            padding: 2rem 0;
        }
        .huakuai{
            width: 20%;
            height: 3px;
            font-size: 0px;/*ie6*/
            position: absolute;
            top: 6.3rem;
            left: 0px;
            background-color:  #84bd00;
        }

        .dingdan_box{
            width: 100%;
            margin-top: 1rem;
            padding: 2rem;
            background-color: #fff;
            border-top: 1px solid #ccc;
            border-bottom: 1px solid #ccc;
            box-sizing: border-box;
            margin-bottom: 1rem;
        }
        .text_left{
            text-align: left;
            font-size: 2rem;
            font-weight: bold;
            float: left;
        }
        .text_right{
            text-align: right;
            font-size: 2rem;
            color: #84bd00;
        }
        .time{
            color: #999;
            font-size: 2rem;
            margin-top: 1rem;
        }
        .products{
            width: 100%;
            margin: 1rem 0;
        }
        .images{
            width: 20%;
            height: 200px;
            float: left;
            overflow: hidden;
        }
        .images img{
            width: 100%;
            display: block;
            float: left;
            margin-right: 1rem;
        }
        .name{
            width: 80%;
            padding: 1rem 0;
            margin-left: 22%;
        }
        .products_name{
            text-align: left;
            font-size: 2.4rem;
            font-weight: bold;
        }
        .price{
            font-size: 3rem;
            color: #f79323;
            margin-top: 1rem;
        }
        .button_group{
            text-align: right;
        }
        .button1{
            border: 1px solid #999;
            background-color: transparent;
            border-radius: 4px;
            padding: 1rem 1rem;
            color: #999;
            font-size: 2rem;
            margin-right: .4em;
        }
        .button2{
            border: 0;
            background-color: #86bd00;
            border-radius: 4px;
            padding: 1rem 2rem;
            color: #fff;
            font-size: 2rem;
            -webkit-appearance:none;
        }
        .button3{
            border: 0;
            background-color: #86bd00;
            border-radius: 4px;
            padding: .2rem .8rem;
            color: #fff;
            font-size: 2em;
        }
        .hide{
            position: fixed;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.4)
        }
        .bg{
            width: 70%;
            position: absolute;
            left: 15%;
            top: 30%;
            background-color: #fff;
        }
        .queding{
            text-align: center;
            margin-top: 25%;
            font-size: 3.4em;
        }
        .anniu{
            width: 100%;
            margin-top: 20%;
            text-align: center;
            margin-bottom: 10%;
        }
        .anniu1,.anniu2{
            width: 45%;
            padding: 2rem 0;
            border: 0;
            color: #fff;
            font-size: 2em;
            border-radius: 4px;
        }
        .anniu1{
            background-color: #999;
            -webkit-appearance:none;
        }
        .anniu2{
            background-color: #86bd00;
            -webkit-appearance:none;
        }
    </style>
</head>

<body>
<div style="width: 100%;">
    <div class="box">
        <div class="text" onclick="qb();" style="color: #84bd00;"><p>全部</p></div>
        <div class="text" onclick="ff();"><p>待付款</p></div>
        <div class="text" onclick="fa();"><p>待发货</p></div>
        <div class="text" onclick="sh();"><p>待收货</p></div>
        <div class="text" onclick="pin();"><p>待评价</p></div>
        <div class="huakuai"></div>
        <div style="clear: both;"></div>
    </div>
    <div id="ddiv">
<c:forEach items="${sorderList}" var="sorder">
    <div class="dingdan_box">
        <p style="width: 100%;"><p class="text_left">订单编号：${sorder.orderNo}</p><p class="text_right">
        <c:if test="${sorder.ddbs=='1'}">
        待付款
        </c:if>
        <c:if test="${sorder.ddbs=='2'}">
            待发货
        </c:if>
        <c:if test="${sorder.ddbs=='3'}">
            待收货
        </c:if>
        <c:if test="${sorder.ddbs=='4'}">
            待评价
        </c:if>
        </p></p>
        <p>下单时间：<fmt:formatDate value="${sorder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
        <%--<p class="time" pattern="yyyy-MM-dd HH:mm:ss">${sorder.createDate}</p>--%>
        <div class="products">
            <div class="images"><img src="${ctxStatic}/images/btn-dibushouye.png" style="width: 100%;"></div>
            <div class="name">
                <p class="products_name">${sorder.goods.name}</p>
                <p class="time">规格:${sorder.specIds}包</p>
                <p class="price">￥${sorder.price}</p>
            </div>
            <div style="clear: both;"></div>
            <div class="button_group">
                <c:if test="${sorder.ddbs=='1'}">
                    <span>
					<input type="button" value="取消订单" class="button1">
					<input type="button" value="去付款" class="button2">
				</span>
                </c:if>
                <c:if test="${sorder.ddbs=='2'}">
                    <span>
					<input type="button" value="确认收货" onclick="qrsh();" class="button3">
				</span>
                </c:if>
                <c:if test="${sorder.ddbs=='3'}">
                    <span>
					<input type="button" value="催促卖家发货" onclick="ccfh();" class="button3">
				</span>
                </c:if>
                <c:if test="${sorder.ddbs=='4'}">
                    <span>
					<div style="width: 100%;">
            <textarea id="${sorder.id}" style="width: 100%;height: 70px;margin: 0;border: 1px solid #ddd;padding: .4rem;box-sizing: border-box;font-size: 0.8em;line-height: 18px;" placeholder="评价内容"></textarea>
            </div><input type="button" onclick="tjpj('${sorder.id}');" value="提交评价"  class="button3">
				</span>
                </c:if>

            </div>
        </div>
        <div style="clear: both;"></div>
    </div>
</c:forEach>


    <%--<div calss=".carddiv">--%>
        <%----%>
    <%--</div>--%>

    </div>
    <!-- 隐藏区域-->
    <div class="hide">
        <div class="bg">
            <p class="queding">确定取消该订单？</p>
            <p class="anniu">
                <input type="button" value="取消" class="anniu1">
                <input type="button" value="确认" class="anniu2">
            </p>
        </div>
    </div>
</div>
<script type="application/javascript">
    $(".text").click(function(){
        cur = $(this).index();
        var huakuai_left = cur * 20;
        $(".huakuai").css({'left':huakuai_left+'%'});
        $(".text").css("color","#999");
        $(this).css("color","#84bd00");
    });
    $(".hide").hide();
    $(".button1").click(function(){
        $(".hide").show();
    });
    $(".anniu1,.anniu2").click(function(){
        $(".hide").hide();
    });

    $(document).ready(function () {
        $(".hide").hide();
        ss();
    })

function ss(data) {
    $.ajax({
        url: "http://localhost:8080/s/myList",
        type: "POST",
        data:{bs:data,},
        dataType:"json",
        success: function(data){
            for(var i=0;i<data.length;i++){
//                alert(data[i].id);
                addv(data[i]);
            }
        }
    });
}
    function addv(data) {
        var table = document.body.querySelector('.carddiv');
        var dc=	document.createElement('div');



//        var dfk = (data.ddbs=="1")?'待付款':"";
//        var dfh = (data.ddbs=="2")?'待发货':"";
//        var dsh = (data.ddbs=="3")?'待收货':"";
//        var dpj = (data.ddbs=="4")?'待评价':"";
//        var dfkbtn = (data.ddbs=="1")?'<input type="button" value="取消订单" onclick="qxdd();" class="button1"><input type="button" onclick="qfk();" value="去付款" class="button2">':"";
//        var dfhbtn = (data.ddbs=="2")?'<input type="button" value="确认收货" onclick="qrsh();" class="button3">':"";
//        var dshbtn = (data.ddbs=="3")?'<input type="button" value="催促卖家发货" onclick="ccfh();" class="button3">':"";
//        var dpjbtn = (data.ddbs=="4")?'<div style="width: 100%;">'+
//            '<textarea id="'+data.id+'" style="width: 100%;height: 70px;margin: 0;border: 1px solid #ddd;padding: .4rem;box-sizing: border-box;font-size: 0.8em;line-height: 18px;" placeholder="评价内容"></textarea>'+
//            '</div><input type="button" onclick="tjpj('+' \' ' +data.id+' \' '+');" value="提交评价"  class="button3">':"";
//        dc.innerHTML='	<div class="dingdan_box">'+
//            '<p style="width: 100%;"><p class="text_left">订单编号：'+data.orderNo+'</p><p class="text_right">'+dfk+dfh+dsh+dpj+'</p></p>'+
//            '<p class="time">下单时间：'+data.createDate+'</p>'+
//            '<div class="products">'+
//            '<div class="images"><img src="'+data.goods.image+'"></div>'+
//            '<div class="name">'+
//            '<p class="products_name">'+data.goods.name+'</p>'+
//            '<p class="time">规格:'+data.specIds+'包</p>'+
//            '<p class="price">￥'+data.price+'</p>'+
//            '</div>'+
//            '<div style="clear: both;"></div>'+
//            '<div class="button_group">'+
//            '<span>'+
//            dfkbtn+dfhbtn+dshbtn+dpjbtn+
//            '</span>'+
//            '</div>'+
//            '</div>'+
//            '<div style="clear: both;"></div>'+
//            '</div>';
        document.body.appendChild(dc);
    }
    
    function qxdd() {
        $(".hide").show();
    }
    function qfk() {
        alert("付款成功");
    }
    function qrsh() {
        alert("收货成功");
    }
    function ccfh() {
        alert("催促成功");
    }
    function tjpj(data) {
//        alert("回复提交成功");
//        alert(data);
        var tt=document.getElementById(data.trim()).value;
//        alert(tt);
        $.ajax({
            url: "${shop}/saveHf",
            type: "POST",
            data:{
                hf:tt,
                ids:data
            },
            success: function(data){
                alert("回复成功");
                window.location.href="${shop}/myDdList?data=4";
            }
        });
    }
    function qb() {
        $(".dingdan_box").remove();
        window.location.href="http://localhost:8080/s/myDdList";
    }
    function ff() {
        $(".dingdan_box").remove();
        window.location.href="http://localhost:8080/s/myDdList?data=1";
    }
    function fa() {
        $(".dingdan_box").remove();
        window.location.href="http://localhost:8080/s/myDdList?data=2";
    }
    function sh() {
        $(".dingdan_box").remove();
        window.location.href="http://localhost:8080/s/myDdList?data=3";
    }
    function pin() {
        $(".dingdan_box").remove();
        window.location.href="http://localhost:8080/s/myDdList?data=4";
    }
</script>
</body>
</html>
