<%@page import="bean.GoodsList"%>
<%@page import="bean.Goods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link type="text/css" rel="stylesheet" href="./CSS/history.css">
</head>

<body>
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
           		<p>商品名称</p>
                <p>商品描述</p>
                <p>商品生产批次</p>
                <p>商品有效期</p>
                <p>商品是否为处方药</p>
                <p>商品价格</p>
                <p>详情</p>
	            <%
	            	GoodsList goodsList = (GoodsList) request.getSession().getAttribute("goodsList");
	            	List<Goods> gl = goodsList.getGoodsList();
	            	for(Goods g:gl){
	            		%>
	            		<p><%=g.getItemName() %></p>
	            		<p><%=g.getItemDescription() %></p>
	            		<p><%=g.getNumber() %></p>
	            		<p><%=g.getDate() %></p>
	            		<p><%=g.getIsPres() %></p>
	            		<p><%=g.getPrice() %></p>
	            		<p><a href="ShowDealList">查看订单</a></p>
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
