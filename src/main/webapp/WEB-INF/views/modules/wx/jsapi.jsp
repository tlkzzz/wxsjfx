<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/27
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">

        function onBridgeReady(){
            WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":"${appid}",     //公众号名称，由商户传入
                    "timeStamp":"${timeStamp}",         //时间戳，自1970年以来的秒数
                    "nonceStr":"${nonceStr}", //随机串
                    "package":"${packageValue}",
                    "signType":"MD5",         //微信签名方式：
                    "paySign":"${sign}" //微信签名
                },
                function(res){
                    // 使用以下方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                    if(res.err_msg == "get_brand_wcpay_request:ok") {
                        alert("支付成功");
                        <%--window.location.href = "${pageContext.request.contextPath}/wx/success?orderId=${orderId}";--%>
                    }else if(res.err_msg == "get_brand_wcpay_request:fail"){
                        alert('支付失败');
                    }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                        alert('支付取消');
                    }else{
                        alert(res.err_msg);
                    }
                }
            );
        }

        if (typeof('WeixinJSBridge') == "undefined"){
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else{
            onBridgeReady();
        }
    </script>
    <title>订单-支付</title>
</head>
<body>
<script type="text/javascript">
    $(document).ready(function () {
        onBridgeReady();
    });

</script>
</body>
</html>
