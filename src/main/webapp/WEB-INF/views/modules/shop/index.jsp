<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<style>
	body{
		margin: 0;
	}
	div{
		width: 100%;
	}
	iframe{
		margin: 0;
		padding: 0;
	}
</style>
<script type="text/javascript" src="static/shop/jquery-1.9.1.js"></script>
<script type="text/javascript">
	var countTime = 60;
	function sendSmsVCode(ele) {
		var mobile = $("#mobile").val();
		if(mobile==""){$("#mobile").focus();return;}
		if(!checkMobile(mobile)){$("#mobile").focus();return;}
		if(ele.attr("disabled"))return;
		$("#vCode").focus();countTime=60;
		$.get("s/sendSmsVCode?mobile="+mobile,function (data){
			if(data||data=="true"){
				ele.css("color","#999");
				ele.attr("disabled",true);
				timeOut(ele);
			}
		});
	}
	function checkSmsVCode(ele) {
        var mobile = $("#mobile").val();
        var vCode = $("#vCode").val();
        if(mobile==""){$("#mobile").focus();return;}
        if(vCode==""){$("#vCode").focus();return;}
        ele.attr("disabled",true);
        $.get("",function (data) {
			if(data||data=="true"){
			    showSms();
			}else {
			    Message("验证失败,请重新输入验证码！");
                ele.attr("disabled",false);
			}
        });
    }
	function timeOut(ele) {
		if(countTime>0) {
			ele.val("重新发送(" + countTime + ")");
			countTime--;
			setTimeout(function () {
				timeOut(ele);
			}, 1000);
		}else {
			ele.val("获取验证码");
			ele.css("color","#fff");
			ele.attr("disabled",false);
		}
	}
	function checkMobile(mobile) {
        var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        var message = "";
        if(mobile == ''){
            message = "手机号码不能为空！";
        }else if(mobile.length !=11){
            message = "请输入有效的手机号码！";
        }else if(!myreg.test(mobile)){
            message = "请输入有效的手机号码！";
        }
        if(message!=""){
            Message(message);
            return false;
        }else {
            return true;
		}
    }
	function Message(context) {
		alert(context);
    }
    function showSms() {
		var ele = $("#SMS");
		if(!ele)return;
		if(ele.css("display")=="none"){
		    ele.css("display","block");
		}else {
		    ele.css("display","none");
		}
    }
</script>
</head>
<body>
<div style="position: relative;width: 100%;height: 100%;">
	<iframe name="jj" frameborder="0" marginheight="0" marginwidth="0" src="s/home" style="width: 100%;height: 90%;"></iframe>
	<iframe id="iframeTwo" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" src="s/foot" style="width: 100%;height: 10%;position: fixed;bottom: 0;left: 0;"></iframe>
</div>
<div id="SMS" style="width: 100%;height: 100%;background-color: rgba(255,255,255,.8);padding: 50% 0;position: fixed;top: 0;left: 0;display: none">
	<div style="width: 70%;margin: 0 auto;padding: 1rem 0;border-radius: 8px;background-color: #fff;box-shadow: 0 0 20px #999;">
		<div style="text-align: center; color: #666;font-size: 3rem;"><p>验证手机号码</p></div>
		<div style="width: 80%;margin: 0 auto;">
			<p><input id="mobile" type="text" placeholder="请输入手机号码" style="font-size:2rem;width: 100%;box-sizing: border-box;color: #999;padding: 1rem;"></p>
		</div>
		<div style="width: 80%;margin: 2rem auto;">
			<p><input id="vCode" type="text" placeholder="请输入验证码" style="font-size:2rem;width: 65%;box-sizing: border-box;float: left;margin-right: 2%;color: #999;padding: 1rem;"></p>
			<p><input type="button" onclick="sendSmsVCode($(this))" value="获取验证码" style="width: 33%;box-sizing: border-box;color: #fff;padding: 1.1rem 0;font-size: 2em;text-align: center; background-color: #84bd00;border: 0;border-radius: 4px;"></p>
			<p style="clear: both;"></p>
		</div>
		<div style="width: 80%;margin: 0 auto;">
			<p><input type="button" value="确定" onclick="checkSmsVCode($(this))" style="font-size:2rem;width: 100%;box-sizing: border-box;color: #fff;background-color: #84bd00;border: 0;padding: 2rem 1rem;margin-bottom: 2rem;"></p>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>
</body>
</html>
