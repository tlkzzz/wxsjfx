<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑地址ss</title>
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
    <c:forEach items="${sAddressList}" var="ss">
    <input id="asddid" hidden value="${ss.id}"/>
    <input id="parent" hidden value="${ss.area.id}"/>
<div style="background-color: #eee;width: 100%;height: 100%;">
    <div class="AUInfo-main">
        <ul style="width:100%;height: 100%;">
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="font-size: 3em;float: left;text-align: right;">收货人</p>
                <input value="${ss.shr}" type="text" id="shr" placeholder="请输入真实姓名"   style="border-bottom: 1px solid #666; color: #ada2a2;text-align: left;padding: 0 2%;margin: 0;background-color: transparent;">

            </li>
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="font-size: 3em;float: left;text-align: right;">手机号码</p>
                <input value="${ss.tel}" type="text" id="sjhm" placeholder="请填写手机号码" style="border-bottom: 1px solid #666;color: #ada2a2;text-align: left;padding: 0;margin: 0;background-color: transparent;">
            </li>
            <%--<li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">--%>
                <%--<p style="font-size: 3em;float: left;text-align: right;">确认手机号码</p>--%>
                <%--<input value="${ss.tel}" type="text" id="qrsjhm"  placeholder="请再次输入手机号码" style="border-bottom: 1px solid #666;color: #ada2a2;text-align: left;padding: 0;margin: 0;background-color: transparent;">--%>
            <%--</li>--%>
            <li  style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="font-size: 3em;float: left;text-align: right;">省市区</p>
                <%--<input value="${ss.area}" type="text" id="ssq" placeholder="请选择城市地址" style="width:20%;height:80px;font-size:3em;-webkit-appearance:none;margin-right: 2%;">--%>
                <select onchange="sheng(this.value,'level2');" name="level1" id="level1" style="width:20%;height:80px;font-size:3em;-webkit-appearance:none;margin-right: 2%;">
                        <%--<option value="请选择省份" selected>--%>
                    <option value="">请选择</option>
                    <c:forEach items="${areaList}" var="area">
                            <option value="${area.id}" >${area.name}</option>
                    </c:forEach>
                </select>
                <select onchange="sheng(this.value,'level3');" name="level2" id="level2" style="width:20%;height:80px;font-size:3em;-webkit-appearance:none;margin-right: 2%;">
                    <option value="">请选择</option>
                </select>
                <select name="level3" id="level3" style="width:20%;height:80px;font-size:3em;-webkit-appearance:none;margin-right: 2%;">
                    <option value="">请选择</option>
                </select>
            </li>
            <li style="width: 100%;padding: 6% 4% 0;box-sizing: border-box;">
                <p style="text-align: right;"><input value="${ss.address}" type="text" id="xqdz" placeholder="请输入收货人的详情地址" style="width: 100%;border: 1px solid #ada2a2;color: #ada2a2;text-align: left;padding: 0;margin: 0;background-color: transparent;font-size: 3em;"></p>
            </li>
            <li style="padding: 6% 4% 0;margin-top: 10px !important;border-bottom: none;background-color: transparent;box-sizing: border-box;">
                <input type="checkbox" style="margin-left: 1rem;margin-top: -1.6%;background-color: #fff;vertical-align: middle;width: 4%;height: 4%;"><span style="font-size: 2.2em;"> 是否默认为收货地址</span>
            </li>
            <li id="dian" style="width:100%;margin-top: 50px !important;background-color: transparent;box-sizing: border-box;">
                <button class="save_ bt-border-r" id="save" onclick="tjSave();" style="font-size: 3em;width: 90%;height: 150px;margin-left: 5%;">保 存 地 址</button>
            </li>
        </ul>
    </div>
    </c:forEach>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#dian").click(function(){
            $("#tan").show()
        })
        $("#qu").click(function(){
            $("#tan").hide()
        })
        $("#level1").val('${sAddressList[0].area.parent.parent.id}');
        $("#level2").append('<option value="${sAddressList[0].area.parent.id}" selected="selected">${sAddressList[0].area.parent.name}</option>');
        $("#level3").append('<option value="${sAddressList[0].area.id}" selected="selected">${sAddressList[0].area.name}</option>');
    })

    function quchu() {
        $("#tan").hide();
    }

    function savege() {
//        var sjhm=document.getElementById('sjhm').value;
//        var qrsjhm=document.getElementById('qrsjhm').value;
//        if(sjhm!=qrsjhm){
//            alert("确认手机号码！");
//            return;
//        }else{
            document.getElementById('shouh').value=document.getElementById('shr').value;
            document.getElementById('shouj').value=document.getElementById('sjhm').value;
            document.getElementById('shoudz').value=document.getElementById('xqdz').value;
            $("#dian").click(function(){
                $("#tan").show()
            })
//        }
    }
    function tjSave() {
        var  idss=document.getElementById('asddid').value;
        var  shr=document.getElementById('shr').value;
        if(shr==''||shr==null){
            window.parent.Message("请填写收货人");
            return false;
        }
        var sjhm=document.getElementById('sjhm').value;
        if(shr==''||shr==null){
            window.parent.Message("请填写手机号码");
            return false;
        }
        var xqdz=document.getElementById('xqdz').value;
        if(shr==''||shr==null){
            window.parent.Message("请填写详情地址");
            return false;
        }
        var ssq=document.getElementById('level3').value;
        if(shr==''||shr==null){
            window.parent.Message("请选择省市区");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "${shop}/xgdzSave",
            data:{
                ids:idss,
                shr:shr,
                sjhm:sjhm,
                xqdz:xqdz,
                ssq:ssq
            },
            success: function(data){
                if(data){
                    window.location.href='${shop}/shdzList';
                }else{
                    window.parent.Message("系统错误，请联系管理员");
                }

            }
        });
    }
    function celerOption(eleId) {
        $("#"+eleId).empty();
        $("#"+eleId).append('<option value="">请选择</option>');
        if(eleId=="level2"){
            $("#level3").empty();
            $("#level3").append('<option value="">请选择</option>');
        }
    }
    function sheng(data,eleId) {
        celerOption(eleId);
        if(data=="")return;
        $.ajax({
            type: "POST",
            url: "${shop}/shiqu",
            data:{
                ids:data,
            },
            success: function(data){
                if(!data||data==null)return;

                for(var i=0;i<data.length;i++){
                    var s=document.getElementById(eleId);
                    s.add(new Option(data[i].name,data[i].id));
                }
            }
        });
    }
</script>
</body>

</html>
