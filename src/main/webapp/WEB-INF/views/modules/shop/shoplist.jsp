<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>购物车</title>
    <style>
        /* 公共 */
        body{
            margin: 0;
        }
        p{
            margin: 0;
        }
        input{
            background-color: transparent;
            border: 0;
        }
        .bg{
            background-color: #ececec;
            background-size: cover;
            width: 100%;
            height: 100%;
            padding: 2% 0;
            /*position: fixed;*/
        }
        .clearfix{
            clear: both;
        }
        /* 购物车商品 */
        .shopping_cart{
            width: 92%;
            box-sizing: border-box;
            margin: 4%;
            padding: 2%;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 8px;
        }
        /* 删除 */
        .delete{
            float: right;
            margin-bottom: 2%;
        }
        /* 商品 */
        .commodity{
            width: 100%;
        }
        .choose,.product,.name_box{
            float: left;
        }
        /* 选择圆点 */
        .choose{
            width: 10%;
            vertical-align: middle;
        }
        /* 商品图片*/
        .product{
            width: 30%;
        }
        .product div{
            width: 80%;
            height: 90%;
            color: #fff;
            text-align: center;
            line-height: 80px;
            background-color: #ccc;
        }
        /* 商品名称 */
        .name_box{
            width: 60%;
        }
        .name{
            color: #333;
            font-size: 1em;
            margin-bottom: 2%;
        }
        .spec{
            color: #999;
            font-size: 0.9em;
            margin-bottom: 10%;
        }
        .price{
            color: #999;
            font-size: 0.9em;
            float: left;
        }
        .price span{
            margin-right: 14%;
        }
        .amount{
            float: right;
            color: #999;
            font-size: 0.9em;
        }
        /* 购买数量 */
        .quantity_box{
            width: 100%;
            margin-top: 4%;
            border-top: 1px solid #ccc;
            padding-top: 2%;
        }
        .quantity{
            float: left;
            color: #999;
            font-size: 1em;
        }
        .button{
            float: right;
            width: 40%;
        }
        .anniu{
            width: 100%;
        }
        .anniu_box{
            width: 100%;
            text-align: center;
            font-size: 1em;
            color: #999;
        }
        .anniu_jj{
            width: 100%;
            text-align: center;
        }
        .anniu_jj input{
            border: 1px solid #ccc;
            padding: 3% 12%;
            border-radius: 4px;
            margin-top: 4%;
        }
        .jiesuan{
            background-color: #84bd00;
            color: #fff;
            font-size: 1em;
        }
        .guang{
            color: #999;
            margin-bottom: 4%;
            font-size: 1em;
        }
    </style>
    <script src="${ctxStatic}/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script>
        $(document).ready(function() {
//            var help = $('.je').text();

            var sum=0;
            var priceList = $('.htje');
            $.each($('.jjsl'),function (index,ele) {
                var a=  ele.value;
                var b = priceList[index].innerText;
                sum += parseInt(a)*parseFloat(b);

            });
           $('.je').text(sum);
//        var b=    $('#jjsl').attr('class');
            var arrPrePrice = [];//定义数组
            //htje的值，为数组赋值
           /* .each(function () {
//                index,ele
//            $('.htje').text();
//                alert(ele.text());
                arrPrePrice.push($(this).html());
            });*/
            /*for(var i=0;i<arrPrePrice.length;i++){

                var sum =sum+a*arrPrePrice[i];
            }*/

        });

        function jian(num , no) {
            if(isNaN(num)||num<0){
                num = 1;
            }
            num = parseInt(num);
          var a=   $("#sl_"+no).val(num);
            var jjsl = $('.jjsl').val(num);
         setje();
        }
         function setje() {
             var htje = $('.htje').text();
             var jjsl = $('.jjsl').val();
//             var je =   $('.je').text();
                var sum = parseInt(htje) * parseInt(jjsl)
             $('.je').text(sum);
//                alert( $('.je').text(sum));

         }
         function deleted(){
             $('.shopping_cart').remove();
             $('.je').text(0);
         }
    </script>
</head>

<body style="background-color: #eee;">
<c:forEach items="${sshoplist}" var="shop">
<div class="bg">
    <!-- 购物车商品 -->
    <div class="shopping_cart">
  <%--<input hidden="hidden" id="ddinfo" value="${shop.orderNo}" ></input>--%>
        <div class="delete" onclick="deleted()">删除</div>

        <div class="clearfix"></div>

        <div class="commodity">
            <div class="choose">
                <input type="checkbox" value="0" name="a" onclick="this.value=(this.value==0)?1:0">
                <%--<input type="radio" value="0" name="a" onclick="this.value=(this.value==0)?1:0">--%>
            </div>

            <div class="product">
                <div>商品图片  <img src="${shop.goods.image}" />  </div>
            </div>

            <div class="name_box">
                <p class="name">商品名称<span>:${shop.goods.name}</span></p>
                <p class="spec">
                    <%--<c:forEach items="${sGoods.gener.specClassList}" var="specClass">--%>
                    <span>规格 :${specClass.name} </span>
                            <input type="checkbox" name="mm"  value="11111" onclick="chose(this)" />
                </p>
                <%--</c:forEach>--%>
            <%--<c:forEach items="${sGoods.gener.specClassList}" var="specClass">--%>
                    <%--<span style="color: #666;font-size: 2.2em;">${specClass.name}:</span>--%>
                    <%--<c:forEach items="${specClass.sSpecList}" var="spec">--%>
                        <%--<span style="font-size: 2.2rem;">${spec.name}</span>--%>
                    <%--</c:forEach>--%>

                <%--</p>--%>
                <%--</c:forEach>--%>

                <p class="price">￥<span  class="htje">${shop.price}</span><s>￥339</s></p>
                <p class="amount" >*<span id="aa"></span></p>
            </div>

            <div class="clearfix"></div>
            <div class="quantity_box">
                <div class="quantity">购买数量</div>
                <div class="button">
                    <div style="width: 100%;border: 1px solid #ccc;border-radius: 4px;">
                        <input  onclick="jian(parseInt($('#sl_${shop.orderNo}').val())-1,'${shop.orderNo}')"   value="-" style="font-size: 1.2em;text-align: center;padding: 0;color: #999;width: 24%;border-right: 1px solid #ccc;">
                        <input id="sl_${shop.orderNo}" class="jjsl"  type="text"  onchange="jian($(this).val())"  value="1" style="font-size: 1.01em;width: 40%; text-align: right;margin: 0;padding-right: 1%;">
                        <input  onclick="jian(parseInt($('#sl_${shop.orderNo}').val())+1,'${shop.orderNo}')" value="+" style="font-size: 1.2em;text-align: center;padding: 0;margin: 0; color: #999;width: 24%;border-left: 1px solid #ccc;">
                    </div>
                </div>

                <div class="clearfix"></div>
            </div>
        </div>

    </div>
    </c:forEach>
    <!-- 按钮 -->
    <div class="anniu">
        <div class="anniu_box">
            <p style="margin-bottom: 2%;">商品总额：<span style="color: #f79353"  class="je" ></span></p>
            <p>成为会员后，重复消费 <span style="color: #f79353">减20%</span>，相当于 <span style="color: #f79353">8</span> 折优惠</p>
        </div>
        <div class="anniu_jj">
            <a href="dingdanqueren.html"><p><input type="button" value="立刻结算" class="jiesuan"></p></a>
            <a href="chanpin.html" onClick="changeImage()"><p><input type="button" value="继续逛逛" class="guang"></p></a>
        </div>
    </div>

</div>
<script>
    function changeImage(){
        $("#iframeTwo",window.parent.document)[0].contentWindow.home();
    }
</script>
</body>
</html>