<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>新增一个收货地址</title>
    <style>
        p{
            margin: 0;
            padding: 0;
        }
    </style>
    <script src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
</head>

<body style="padding: 0;margin: 0;background-color: #eee;">
<div style="width: 100%;">
        <div style="width: 100%;">
            <a href="${shop}/listss"><input type="button" value="新增一个收货地址" style="width: 100%;background-color:#84bd00;border: 0px solid;padding: 3rem 3rem;color: #fff;font-size: 3em; -webkit-appearance: none;">
            </a></div>

    <div calss=".carddiv" style="width: 100%;background-color: #fff;"></div>

</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        ddd();
    })

    function ddd() {
        $.ajax({
            url: "http://localhost:8080/s/dzList",
            type: "POST",
            dataType:"json",
            success: function(data){
               for(var i=0;i<data.length;i++){
                   addv(data[i]);
               }
            }
        });
    }


    function addv(data) {
        var table = document.body.querySelector('.carddiv');
        var dc=	document.createElement('div');
        var text = (data.isDefault=="1")?'<p style="float: left;"><input type="radio" checked  value="'+data.id+'" onclick="ra('+' \' ' +data.id+' \' '+');" style="vertical-align: middle;margin: 0;padding: 0;margin-right: .4rem;margin-left: 1rem;"><span style="font-size: 0.8em;text-align: center;">设为默认</span></p>':"";
        var test = (data.isDefault=="0")?'<p style="float: left;"><input type="radio"  value="'+data.id+'" onclick="ra('+' \' ' +data.id+' \' '+');" style="vertical-align: middle;margin: 0;padding: 0;margin-right: .4rem;margin-left: 1rem;"><span style="font-size: 0.8em;text-align: center;">设为默认</span></p>':"";
        dc.innerHTML='<p style="float: left;margin: .6rem 1rem;font-size: 1em;color: #333;">'+data.shr+'</p>'+
            '<p style="float: left;margin: .6rem 1rem;font-size: 1em;color: #333;">'+data.tel+'</p>'+
        '<div style="clear: both;"></div>'+
            '<p style="margin-left: 1rem; font-size: 0.8em;color: #999;">'+data.area.name+data.address+'</p>'+
            '<hr style="border-top: 1px solid #ccc;">'+
            '<div style="width: 100%;padding-bottom: 0.6em;margin-bottom: 0.6em">'+text+test+

            <%--'<c:if test="data.isDefault==0">'+--%>
            <%--'<p style="float: left;"><input type="radio" value="'+data.id+'" onclick="ra('+' \' ' +data.id+' \' '+');" style="vertical-align: middle;margin: 0;padding: 0;margin-right: .4rem;margin-left: 1rem;"><span style="font-size: 0.8em;text-align: center;">设为默认</span></p>'+--%>
            <%--'</c:if>'+--%>
            '<p style="float: right;font-size: 0.8em;margin-right: 2rem;margin-top: .2rem;"><input type="button" value="删除" onclick="sc('+' \' ' +data.id+' \' '+' );"></p>'+
            '<p style="float: right;font-size: 0.8em;margin-right: 1rem;margin-top: .2rem;"><input type="button" value="修改" onclick="xg('+' \' ' +data.id+' \' '+' );"></p>'+
            '<p style="clear: both;"></p>';

            document.body.appendChild(dc);
    }

    function sc(data) {
        $.ajax({
            url: "http://localhost:8080/s/scshList",
            type: "POST",
            data:{
              ids:data,
            },
            success: function(data){
                alert("删除成功");
            }
        });
    }
    function xg(data) {
//        alert(data);
//        $.ajax({
//            url: "http://localhost:8080/s/xgshList",
//            type: "POST",
//            data:{
//                ids:data,
//            },
//            success: function(data){
                window.location.href="http://localhost:8080/s/xgshList?data="+data;
//            }
//        });
    }

    function ra(data) {
        alert(data);
        $.ajax({
            url: "http://localhost:8080/s/updateIs",
            type: "POST",
            data:{
                ids:data,
            },
            success: function(data){
                alert("设置成功");
                window.location.href="http://localhost:8080/s/shdzList?";
            }
        });
//        window.location.href="http://localhost:8080/s/updateIs?data="+data;
    }

//      window.location=addv();
</script>
</html>
