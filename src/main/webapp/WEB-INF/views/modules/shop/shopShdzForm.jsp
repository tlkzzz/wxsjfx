<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑地址</title>
    <link rel="stylesheet" href="${ctxStatic}/shop/style.css" />
    <link rel="stylesheet" href=".${ctxStatic}/shop/AlterUserInfo.css" />
    <script src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
    <style>
        input{
            font-size: 3em;
            border: 0;
        }
    </style>
</head>

<body>
<div style="background-color: #eee;width: 100%;height: 100%;">
    <div class="AUInfo-main">
        <ul style="width:100%;height: 100%;">
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="font-size: 3em;float: left;text-align: right;">收货人:</p>
                <input type="text" id="shr" placeholder="请输入真实姓名"   style="border-bottom: 1px solid #666; color: #ada2a2;text-align: left;padding: 0 2%;margin: 0;background-color: transparent;">
            </li>
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="font-size: 3em;float: left;text-align: right;">手机号码:</p>
                <input type="text" id="sjhm" placeholder="请填写手机号码" style="border-bottom: 1px solid #666;color: #ada2a2;text-align: left;padding: 0;margin: 0;background-color: transparent;">
            </li>
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="font-size: 3em;float: left;text-align: right;">确认手机号码:</p>
                <input type="text" id="qrsjhm"  placeholder="请再次输入手机号码" style="border-bottom: 1px solid #666;color: #ada2a2;text-align: left;padding: 0;margin: 0;background-color: transparent;">
            </li>
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="font-size: 3em;float: left;text-align: right;">省市区:</p>
                <select onchange="sheng(this.value);" name="level1" id="level1" style="width:100px;">
                    <%--<option value="请选择省份" selected>--%>
                    <option value="">请选择</option>
                    <c:forEach items="${areaList}" var="area">
                        <option value="${area.id}">${area.name}</option>
                    </c:forEach>
                </select>
                <select onchange="shi(this.value);" name="level2" id="level2" style="width:100px;">
                    <option value="">请选择</option>
                </select>
                <select name="level3" id="level3" style="width:100px;">
                    <option value="">请选择</option>
                </select>
            </li>
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="text-align: right;"><input type="text" id="xqdz" placeholder="请输入收货人的详情地址" style="width: 100%;border: 1px solid #ada2a2;color: #ada2a2;text-align: left;padding: 0;margin: 0;background-color: transparent;font-size: 3em;"></p>
            </li>
            <li style="padding: 6% 4% 0;margin-top: 10px !important;border-bottom: none;background-color: transparent;box-sizing: border-box;">
                <input type="checkbox" style="margin-left: 1rem;margin-top: -1.6%;background-color: #fff;vertical-align: middle;width: 4%;height: 4%;"><span style="font-size: 2.2em;"> 是否默认为收货地址</span>
            </li>
            <li id="dian" style="width:100%;margin-top: 50px !important;background-color: transparent;box-sizing: border-box;">
                <button class="bt-border-r" id="save" onclick="savege();" style="font-size: 3em;width: 90%;height: 150px;margin-left: 5%;">保 存 地 址</button>
            </li>
        </ul>
    </div>
    <div id="tan" style="position: absolute;top: 0; width: 100%;height: 100%;background-color: rgba(0,0,0,0.3);display: none;">
        <div style="width: 70%;margin: 0 auto;background-color: #fff;margin-top: 25%;border-radius: 8px;padding: 1rem 4rem;font-size: 1em;">
            <p style="text-align: center;margin-bottom: 2rem;font-size: 3em;">请确认收货信息</p>
            <p style="float: left;width: 35%;margin-bottom: .4rem;font-size: 2em;">收货人：</p>
            <p style="margin-bottom: .4rem;"><input type="text" disabled="disabled" id="shouh"/></p>
            <p style="float: left;width: 35%;margin-bottom: .6rem;font-size: 2em;">手机号码：</p>
            <p style="margin-bottom: .4rem;"><input type="text" disabled="disabled" id="shouj"/></p>
            <p style="float: left;width: 35%;margin-bottom: .4rem;font-size: 2em;">收货地址：</p>
            <p style="float: left; margin-bottom: .4rem;"><input type="text" disabled="disabled" id="shoudz"/></p>
            <p style="clear: both;"></p>
            <div style="width: 100%;margin-top: 3rem;">
                <p id="qu" style="width: 50%;float: left;"><input type="button" value="取消" style="width: 100%;border: 0;font-size:3em;margin: 0;background-color: transparent;color: #999;"></p>
                <p style="width: 50%;float: right;"><a href="xinzengyige.html"><input type="button" onclick="tjSave();" value="确定" style="width: 100%;border: 0;font-size:3em;margin: 0;background-color: transparent;color: #999;"></a></p>


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
        var ssq=document.getElementById('level3').value;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/s/shdzSave",
            data:{
                shr:shr,
                sjhm:sjhm,
                xqdz:xqdz,
                ssq:ssq
            },
            success: function(data){
                window.location.href="http://localhost:8080/s/shdzList";
            }
        });
    }

    function sheng(data) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/s/shiqu",
            data:{
                ids:data,
            },
            success: function(data){
               for(var i=0;i<data.length;i++){
//                   alert(data[0].name);
                   var s=document.getElementById("level2");
                   s.add(new Option(data[i].name,data[i].id));
               }
            }
        });
    }
    function shi(data) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/s/shiqu",
            data:{
                ids:data,
            },
            success: function(data){
                for(var i=0;i<data.length;i++){
//                    alert(data[0].name);
                    var s=document.getElementById("level3");
                    s.add(new Option(data[i].name,data[i].id));
                }
            }
        });
    }
</script>
</body>

</html>
