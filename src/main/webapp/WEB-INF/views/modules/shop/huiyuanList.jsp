<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>会员列表</title>
	<style>
		ul,p,body{
			margin: 0;
			padding: 0;
		}
		li{
			padding: 0;
			list-style: none;
		}

	</style>
	<script type="text/javascript" src="${ctxStatic}/shop/jquery-1.9.1.js"></script>
</head>

<body>
<div style="width: 100%;height: 100%;">
<c:forEach items="${smlist}" var="sMember">
	<ul style="width: 100%;height: 100%;">
		<li style="width: 100%;margin-bottom: 2%;padding: 2% 4%;border-bottom: 1px solid #ccc;box-sizing: border-box;">
			<img height="200px" width="200px"src="${sMember.newMember.photo}"/>
			<div style="width: 80%;margin-left: 22%;padding-top: 2rem;">
				<p style="margin-bottom: 2%;font-size: 3em;">用户名称:<span>${sMember.newMember.name}</span></p>
				<p style="color: #666;font-size: 2.4em;">注册时间:<span><fmt:formatDate value="${sMember.buildDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
			</div>
			<div style="clear: both;"></div>
		</li>
	</ul>
</c:forEach>
	<div id="none" style="position: fixed;top: 0;width: 100%;height: 100%;background-color: rgba(0,0,0,.6);display: none;">
		<div style="width: 80%;top: 25%;left: 25%;margin-left: 10%;margin-top: 20%;">
			<img src="qq.png" style="width: 100%;background-color: #fff;">
		</div>
	</div>
</div>
<script>
    $("#dian").click(function(){
        $("#none").toggle()
    })
    $("#none").click(function(){
        $("#none").css("display","none")
    })
</script>
</body>
</html>
