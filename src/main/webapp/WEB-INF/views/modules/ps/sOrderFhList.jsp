<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>订单管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/ps/sOrder/fHList">订单列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="sOrder" action="${ctx}/ps/sOrder/" method="post" class="breadcrumb form-search">

    <ul class="ul-form">
        <li><label>订单编号：</label>
            <form:input path="orderNo" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>订单编号</th>
        <th>商品</th>
        <th>购买会员</th>
        <th>规格</th>
        <th>数量</th>
        <th>价格</th>
        <th>商品成本价</th>
        <th>快递单号</th>
        <th>更新时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sOrderList}" var="sOrder">
        <tr>
            <td>
                    ${sOrder.orderNo}
            </td>
            <td>
                    ${sOrder.goods.name}
            </td>
            <td>
                    ${sOrder.createBy.name}
            </td>
            <td>
                    ${sOrder.specIds}
            </td>
            <td>
                    ${sOrder.num}
            </td>
            <td>
                <fmt:formatNumber value="${sOrder.price}" pattern="#.####"/>
            </td>
            <td>
                <fmt:formatNumber value="${sOrder.costPrice}" pattern="#.####"/>
            </td>
            <td>
                    ${sOrder.kddh}
            </td>
            <td>
                <fmt:formatDate value="${sOrder.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <c:if test="${sOrder.ddbs=='2'}">
                <a href="${ctx}/ps/sOrder/addFh?id=${sOrder.id}">发货</a>
                </c:if>
                <c:if test="${sOrder.ddbs=='3'}">
                    已发货
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>