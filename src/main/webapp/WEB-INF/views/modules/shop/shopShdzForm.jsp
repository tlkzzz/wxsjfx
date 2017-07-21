<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑地址</title>
    <link rel="stylesheet" href="${ctxStatic}/shop/style.css" />
    <link rel="stylesheet" href=".${ctxStatic}/shop/AlterUserInfo.css" />
    <script src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
</head>

<body>
<div style="background-color: #eee;width: 100%;height: 100%;position: relative;">
    <div class="AUInfo-main">
        <ul>
            <li>
                <p style="font-size: 0.9em;">收货人</p>
                <input type="text" id="shr" placeholder="请输入真实姓名"   style="height: 45px;color: #ada2a2;border-bottom: 0px;text-align: left;padding: 0;margin: 0;background-color: transparent;">
            </li>
            <li>
                <p style="font-size: 0.9em;">手机号码</p>
                <input type="text" id="sjhm" placeholder="请填写手机号码" style="height: 45px;color: #ada2a2;border-bottom: 0px;text-align: left;padding: 0;margin: 0;background-color: transparent;">
            </li>
            <li>
                <p style="font-size: 0.9em;">确认手机号码</p>
                <input type="text" id="qrsjhm"  placeholder="请再次输入手机号码" style="height: 45px;color: #ada2a2;border-bottom: 0px;text-align: left;padding: 0;margin: 0;background-color: transparent;">
            </li>
            <li>
                <p style="font-size: 0.9em;">省市区</p>
                <input type="text" id="ssq" placeholder="请选择城市地址" style="height: 45px;color: #ada2a2;border-bottom: 0px;text-align: left;padding: 0;margin: 0;background-color: transparent;">
            </li>
            <li>
                <p style="width: 100%;font-size: 0.9em;"><input type="text" id="xqdz" placeholder="请输入收货人的详情地址" style="height: 45px;color: #ada2a2;border: 0px;text-align: left;padding: 0;margin: 0;background-color: transparent;font-size: 0.9em;"></p>
            </li>
            <li style="margin-top: 10px !important;border-bottom: none;background-color: transparent;">
                <input type="checkbox" style="margin-left: 1rem;background-color: #fff;vertical-align: middle;"><span style="font-size: 0.6em;"> 是否默认为收货地址</span>
            </li>
            <li id="dian" style="margin-top: 50px !important;border-bottom: none;background-color: transparent;">
                <button class="save_ bt-border-r" id="save" onclick="savege();" style="font-size: 1.2em;">保 存 地 址</button>
            </li>
        </ul>
    </div>
    <div id="tan" style="position: absolute;top: 0; width: 100%;height: 100%;background-color: rgba(0,0,0,0.3);display: none;">
        <div style="width: 70%;margin: 0 auto;background-color: #fff;margin-top: 50%;border-radius: 8px;padding: 1rem;font-size: .8em;">
            <p style="text-align: center;margin-bottom: .8rem;">请确认收货信息</p>
            <p style="float: left;width: 35%;margin-bottom: .4rem;">收货人：</p>
            <p style="margin-bottom: .4rem;"><input type="text" disabled="disabled" id="shouh"/></p>
            <p style="float: left;width: 35%;margin-bottom: .4rem;">手机号码：</p>
            <p style="margin-bottom: .4rem;"><input type="text" disabled="disabled" id="shouj"/></p>
            <p style="float: left;width: 35%;margin-bottom: .4rem;">收货地址：</p>
            <p style="float: left;width: 65%; margin-bottom: .4rem;"><input type="text" disabled="disabled" id="shoudz"/></p>
            <p style="clear: both;"></p>
            <div style="width: 100%;margin-top: 3rem;">
                <p id="qu" style="width: 50%;float: left;"><input type="button" value="取消" style="width: 100%;border: 0;margin: 0;background-color: transparent;color: #999;"></p>
                <p style="width: 50%;float: right;"><a href="xinzengyige.html"><input type="button" onclick="tjSave();" value="确定" style="width: 100%;border: 0;margin: 0;background-color: transparent;color: #999;"></a></p>
                <p style="clear: both;"></p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $.ready(function () {
        $("#dian").click(function(){
            $("#tan").show()
        })
        $("#qu").click(function(){
            $("#tan").hide()
        })
    })
    function savege() {
        var sjhm=document.getElementById('sjhm').value;
        var qrsjhm=document.getElementById('qrsjhm').value;
        if(sjhm!=qrsjhm){
            alert("确认手机号码！");
            return;
        }else{
            document.getElementById('shouh').value=document.getElementById('shr').value;
            document.getElementById('shouj').value=document.getElementById('sjhm').value;
            document.getElementById('shoudz').value=document.getElementById('xqdz').value;
            $("#dian").click(function(){
                $("#tan").show()
            })
        }
    }
    function tjSave() {
        var  shr=document.getElementById('shr').value;
        var sjhm=document.getElementById('sjhm').value;
        var qrsjhm=document.getElementById('qrsjhm').value;
        var xqdz=document.getElementById('xqdz').value;
        var ssq=document.getElementById('ssq').value;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/s/shdzSave",
            data:{
                shr:shr,
                sjhm:sjhm,
                qrsjhm:qrsjhm,
                xqdz:xqdz,
                ssq:ssq
            },
            success: function(data){
                $("#myDiv").html('<h2>'+data+'</h2>');
            }
        });
    }
</script>
</body>

</html>
