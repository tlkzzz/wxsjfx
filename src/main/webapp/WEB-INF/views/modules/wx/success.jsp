<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/25
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功！！</title>

    <link rel="stylesheet" href="static/css/jquery.mobile-1.3.0.min.css" />
    <link rel="stylesheet" href="static/css/jquery.mobile-new.css" />
</head>
<body>

<div data-role="page">
    <div data-role="header" data-theme="e">
        <h1>业务付款</h1>
    </div>

    <div data-role="content">
        <form method="post">
            <div data-role="fieldcontain">
                <label for="paymoney">支付金额：</label>
                <input type="text" name="paymoney" id="paymoney" placeholder="请输入支付金额..">
                <p id="p1">你输入的金额为：</p>
                <p>微信支付需要加收1%手续费</p>
                <p id="p3">合计需要支付金额</p>
                <a data-role="button" href="javascript:doWeixinPay()" data-theme="e">立即支付</a>
            </div>
        </form>
    </div>
</div>
</body>

<script type="text/javascript" src="/static/jquery/jquery.js"></script>
<script type="text/javascript" src="/static/jquery/jquery.mobile-1.3.0.min.js"></script>
<script>
    $(document).ready(function(){
        $("#paymoney").change(function(){
            var pay=$("#paymoney").val();
            $("#p1").html('你输入的金额为：'+pay+'元');
            $("#p3").html('合计需要支付金额'+parseFloat((pay*rate).toPrecision(12))+'元');
        });
    });
</script>
<script type="text/javascript">
    function doWeixinPay(){
        var pay=$("#paymoney").val();
//        if(pay == null || pay == ""){
//            alert("请输入支付金额！");
//            return;
//        }else{
            <%--var rate = ${rate};--%>
//            pay = parseFloat((pay*rate).toPrecision(12));
            window.location.href="http://wx.tlkzzz.com/a/wx/wxpay/toPay";
//        }
    }
</script>



</html>
