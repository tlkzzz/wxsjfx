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
    <title>个人中心</title>
    <style>
        /* 公共 */
        body{
            margin: 0;
        }
        p{
            margin: 0;
        }
        .bg{
            background-color: #ececec;
            background-size: cover;
            width: 100%;
            height: 100%;
        }
        .clearfix{
            clear: both;
        }
        /* 个人中心头像 */
        .personal{
            width: 100%;
            background-color: #84bd00;
        }
        .head{
            width: 100%;
            float: left;
            margin-top: 8%;
            margin-bottom: 4%;
        }
        .head_touxiang{
            width: 300px;
            height: 300px;
            border-radius: 100%;
            background-color: #fff;
            text-align: center;
            line-height: 100px;
            margin: 0 auto;
            overflow: hidden;
        }
        .member{
            width: 100%;
            font-size: 2em;
            font-family: "微软雅黑";
            text-align: center;
            color: #fff;
            margin-top: 4%;
        }
        .member p{
            margin-bottom: 2%;
        }
        .member img{
            width: 3%;
            margin-top: -0.5%;
            margin-left: 1%;
            vertical-align: middle;
        }
        /* 菜单分栏-第一栏*/
        .menu_box{
            width: 100%;
            background-color: #fff;
            margin-bottom: 2%;
        }
        .menu_turnover,.menu_save,.menu_popu{
            float: left;
            width: 33.3333%;
            text-align: center;
            box-sizing: border-box;
            font-size: 2em;
            font-family: "微软雅黑";
            color: #8E8E8E;
            border-top: 1px solid #ccc;
            padding: 4% 0;
        }
        .menu_turnover p,.menu_save p,.menu_popu p{
            margin-bottom: 4%;
        }
        .menu_turnover,.menu_save{
            border-bottom: 1px solid #ccc;
            border-right: 1px solid #ccc;
        }
        .menu_popu{
            border-bottom: 1px solid #ccc;
        }
        .menu_popu img{
            width: 13%;
            vertical-align: middle;
            margin-left: 1%;
        }
        .turnover_num{
            color: #f79353;
        }
        /* 菜单分栏-第二栏*/
        .menu_code,.menu_order,.menu_commission{
            text-align: center;
            float: left;
            width: 25%;
            box-sizing: border-box;
            font-size: 2em;
            font-family: "微软雅黑";
            color: #8E8E8E;
            padding: 3% 0;
        }
        .menu_code img,.menu_order img,.menu_commission img{
            width: 22%;
            margin-bottom: 2%;
        }
        .menu_img img{
            width: 20%;
            vertical-align: middle;
            margin-left: 1%;
        }
        /* 我的会员 */
        .my_member{
            padding: 4% 2%;
            font-size: 2em;
            font-family: "微软雅黑";
            color: #666;
            border-bottom: 1px solid #ddd;
            background-color: #fff;
        }
        .member_left{
            float: left;
        }
        .member_right{
            text-align: right;
        }
        .member_right img{
            width: 5%;
            vertical-align: middle;
        }
        .member_shuzi{
            text-align: center;
            color: #f79353;
            margin-bottom:1rem;
        }
        /* 底部操作栏 */
        /*	.bottom{
                width: 100%;
                position: absolute;
                bottom: 0;
            }
            .bottom_box{
                border-top: 1px solid #ccc;
                width: 100%;
                padding: 2% 0;
                background-color: #fff;
                font-size: 0.6em;
                font-family: "微软雅黑";
                color: #aeaeae;
            }
            .home,.shopping,.personal_center{
                float: left;
                width: 33.3333%;
                text-align: center;
            }
            .home img,.shopping img,.personal_center img{
                width: 14%;
            }
            /* 个人中心 */
        .personal_center img{
        }
    </style>
    <script>
       function shouhuo() {
           alert(11111);
       }

    </script>
</head>

<body>
<div class="bg">
    <!-- 个人中心头像 -->
    <a href="touxiang.html" style="text-decoration: none;color: #000;">
        <div class="personal">
            <div class="head">
                <div class="head_touxiang"><img height="100%" width="100%"src="${photo}"/> </div>
                <div class="member">
                    <p>会员号 : <span>${name}</span></p>
                    <p><span>会员昵称 : <span>${name}</span></span></p>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </a>
    <div class="clearfix"></div>
    <a href="wodedingdan.html">
        <div class="my_member" style="margin: 0;">
            <span class="member_left"><img src="${ctxStatic}/images/wodedingdan.png" style="width: 15%;vertical-align: middle; margin-right: 4%;">我的订单</span>
            <p class="member_right"><img src="${ctxStatic}/images/btn-fanhui-hui.png"></p>
        </div>
    </a>
    <div class="menu_box">
        <div class="menu2">
            <div class="menu_code">
                <p><img src="${ctxStatic}/images/daishouhuo.png"></p>
                <p class="menu_img"><span>待收货</span></p>
            </div>
            <div class="menu_order">
                <p><img src="${ctxStatic}/images/daipingjia.png"></p>
                <p class="menu_img"><span>待评价</span></p>
            </div>
            <div class="menu_commission">
                <p><img src="${ctxStatic}/images/yipingjia.png"></p>
                <p class="menu_img"><span>已评价</span></p>
            </div>
            <div class="menu_commission">
                <p><img src="${ctxStatic}/images/tuihuodan.png"></p>
                <p class="menu_img"><span>退货单</span></p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

    <!-- 菜单分栏 -->
    <div class="my_member" style="margin: 0;">
        <span class="member_left"><img src="${ctxStatic}/images/wodezhanghu.png" style="width: 15%;vertical-align: middle;margin-right: 4%;">我的账户</span>
        <p class="member_right"><img src="${ctxStatic}/images/btn-fanhui-hui.png"></p>
    </div>
    <%--<div class="menu_box">--%>
        <%--<div class="menu2">--%>
            <%--<div class="menu_code">--%>
                <%--<p class="member_shuzi">0</p>--%>
                <%--<p class="menu_img"><span>账户余额</span></p>--%>
            <%--</div>--%>
            <%--<div class="menu_order">--%>
                <%--<p class="member_shuzi">0</p>--%>
                <%--<p class="menu_img"><span>订单交易</span></p>--%>
            <%--</div>--%>
            <%--<div class="menu_commission">--%>
                <%--<p class="member_shuzi">0</p>--%>
                <%--<p class="menu_img"><span>我的积分</span></p>--%>
            <%--</div>--%>
            <%--<div class="menu_commission">--%>
                <%--<p class="member_shuzi">0</p>--%>
                <%--<p class="menu_img"><span>我的成长值</span></p>--%>
            <%--</div>--%>
            <%--<div class="clearfix"></div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <!-- 我的会员 -->
    <div class="my_member">
        <span class="member_left"><img src="${ctxStatic}/images/wodehuiyuan.png" style="width: 15%;vertical-align: middle;margin-right: 4%;">我的会员</span>
        <p class="member_right"><img src="${ctxStatic}/images/btn-fanhui-hui.png"></p>
    </div>
    <!-- 收货地址 -->
    <a href="${shop}/shdzList">
        <div class="my_member">
            <span class="member_left"><img src="${ctxStatic}/images/shouhuodizhi.png" style="width: 15%;vertical-align: middle;margin-right: 4%;" >收货地址</span>
            <p class="member_right" ><img  src="${ctxStatic}/images/btn-fanhui-hui.png" ></p>
        </div>
    </a>
    <!-- 我的消费额 -->
    <div class="my_member">
        <span class="member_left"><img src="${ctxStatic}/images/xiaofeie.png" style="width: 15%;vertical-align: middle;margin-right: 4%;">我的消费额:</span>
        <p class="member_shuzi">0</p>
    </div>
</div>
</body>
</html>
