<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="./CSS/buy.css">
</head>

<body>
<div class="head">
    <div class="header">
        <a href="ShowGoodsList">首页</a>
        <c:if test="${curUser.user_group eq 'seller' }">
	        <a href="back_stage.jsp">进入后台</a>
        </c:if>
        <c:if test="${curUser.user_group eq 'user' }">
	        <a href="ShowBuyerDeal?method=userCheck">查看下单详情</a>
        </c:if>
        <c:choose>
            <c:when test="${empty sessionScope.curUser}">
                <a href="login.jsp">登录</a>
                <a href="register.jsp">注册</a>
            </c:when>
            <c:otherwise>
                <a href="QuitServlet">退出登录</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="main">
    <div class="navbar">
        <img src="./img/Xbro.jpg"/>
    </div>
    <div class="menu"></div>
    <div class="content">
        <h1>输入个人信息</h1>
        <form method="post" action="BuyServlet?method=submitinfo">
            交易时间：<input type="datetime-local" name="date"><br>
            地址：<input type="text" name="address"><br>
            交易人：<input type="text" name="realname" style="width: 16px;">
            <input type="radio" name="option" value="male" checked>先生
            <input type="radio" name="option" value="female">女士<br>
            身份证号码：<input type="text" name="idcard"><br>
            手机号：<input type="text" name="phone"><br>
            备注：<br>
            <textarea name="remark" cols="50" rows="10">请输入内容...</textarea><br>
            <input type="submit" value="提交">
        </form>
    </div>
</div>
<div class="sidebar"></div>
<div class="footer">
    <a href="#">关于我们</a>
    <a href="#">联系我们</a>
    <a href="#">隐私政策</a>
    <a href="#">公益活动</a>
</div>
</body>
</html>
