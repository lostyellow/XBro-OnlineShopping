<%@page import="bean.Good"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="./CSS/information.css">
</head>

<body>
    <%
    	Good g = (Good)session.getAttribute("goods");
    %>
    <div class="head">
        <div class="header">
          <a href="ShowGoodsList">首页</a>
          <a href="back_stage.jsp">进入后台</a>
          <%if(request.getSession().getAttribute("curUser")==null){ %>
          <a href="login.jsp">登录</a><a href="register.jsp">注册</a><%}%><% else{ %>
          <a href="QuitServlet">退出登录</a><%} %>
        </div>
    </div>   
    <div class="main">
            <div class="navbar">
                <img src="./img/Xbro.png">
            </div>
            <div class="menu"></div>
            <div class="content">
                <div class="shop" >
                	 <!-- 暂时不使用根据商家id获取商家名, 暂时显示商家id --> 
                    <span><%=g.getSellerId()%></span>
                </div>
                <div class="yp">
                    <div class="photo">
                        <img src=<%=g.getImgURL() %>>
                    </div>
                    <div class="discribe">
                        <p>商品名称:<%=g.getItemName() %></p>
                        <p>商品描述:<%=g.getItemDescription() %></p>
                        <p>商品生产批次:<%=g.getNumber() %></p>
                        <p>商品有效期:<%=g.getDate() %></p>
                        <p>商品是否为处方药：
                        <%
                        	if(g.getIsPres()==null || g.getIsPres()) {
                        		%><input type="radio" name="option1" checked disabled>是<input type="radio" name="option1" disabled>否<%
                        	}
                        	else {
                        		%><input type="radio" name="option1" disabled>是<input type="radio" name="option1" checked disabled>否<%
                        	}
                        %></p>
                        <p>商品价格:<%=g.getPrice() %></p>
                        <p>商品是否被冻结：
                        <%
                        	if(g.getIsFrozen()==null || g.getIsFrozen()) {
                        		%><input type="radio" name="option2" checked disabled>是<input type="radio" name="option2" disabled>否<%
                        	}
                        	else {
                        		%><input type="radio" name="option2" disabled>是<input type="radio" name="option2" checked disabled>否<%
                        	}
                        %></p>
                        <a href="buy.jsp"><button>购买</button></a>
                    </div>
                </div>
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
