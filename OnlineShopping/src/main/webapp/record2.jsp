<%@page import="dao.impl.TransactionDaoImpl"%>
<%@page import="dao.TransactionDao"%>
<%@page import="dao.impl.UserDaoImpl"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.Deal"%>
<%@page import="bean.DealList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link type="text/css" rel="stylesheet" href="./CSS/record2.css">
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
                <img src="./img/Xbro.png">
            </div>
            <div class="menu"></div>
            <div class="content">
                <p>商品ID</p>
                <p>交易时间</p>
                <p>交易状态</p>
                <p>交易金额</p>
                	<%
                		UserDao ud = new UserDaoImpl();
                		TransactionDao td = new TransactionDaoImpl();
                		DealList dl = (DealList)session.getAttribute("dealList");
                		for(Deal d:dl.getDeals()){
                			%>
                    		<p><%=d.getProduct_id()%></p>
                            <p><%=d.getTime()%></p>
                            <p><%=d.getStatus()%></p>
                            <p><%=d.getAmount()%></p>
                    <%
                    }
                    %>

            </div>
            <div class="sidebar"></div>
            <div class="footer">
                <a href="#">关于我们</a>
                <a href="#">联系我们</a>
                <a href="#">隐私政策</a>
                <a href="#">公益活动</a>
            </div>
    </div>
</body>
</html>
