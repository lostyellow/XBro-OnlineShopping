<%@page import="dao.TransactionDao" %>
<%@page import="dao.impl.TransactionDaoImpl" %>
<%@page import="dao.impl.OrderDaoImpl" %>
<%@page import="dao.OrderDao" %>
<%@page import="dao.impl.GoodDaoImpl" %>
<%@page import="dao.GoodDao" %>
<%@page import="dao.impl.UserDaoImpl" %>
<%@page import="dao.UserDao" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@page import="bean.Details" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="./CSS/detail.css">
</head>
<body>
<div class="head">
    <div class="header">
        <a href="main.jsp">首页</a>
        <a href="back_stage.jsp">进入后台</a>
        <% if (request.getSession().getAttribute("curUser") == null) { %>
            <a href="login.jsp">登录</a>
            <a href="register.jsp">注册</a>
        <% } else { %>
            <a href="QuitServlet">退出登录</a>
        <% } %>
    </div>
</div>
<div class="main">
    <div class="navbar">
        <img src="./img/Xbro.png">
    </div>
    <div class="menu"></div>
    <div class="content">
        <h2>交易信息</h2>
        <%
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int transactionId = Integer.parseInt(request.getParameter("transaction_id"));

            UserDao ud = new UserDaoImpl();
            GoodDao gd = new GoodDaoImpl();
            TransactionDao td = new TransactionDaoImpl();
            OrderDao od = new OrderDaoImpl();

            Details d = od.findDetails(transactionId);
        %>
        <p><strong>交易时间：</strong><%=d.getAppointment_time()%></p>
        <p><strong>地址：</strong><%=d.getAddress()%></p>
        <p><strong>交易人：</strong><%=d.getBuyer_name()+" "+d.getBuyer_gender()%></p>
        <p><strong>身份证号码：</strong><%=d.getBuyer_identification()%></p>
        <p><strong>手机号：</strong><%=d.getBuyer_phone_number()%></p>
        <p><strong>备注：</strong><%=d.getText()%></p>
        <%
            String status = String.valueOf(request.getParameter("status"));

            if ("wait".equals(status)) {
                if (td.IsExistIngDeal(productId)) {
        %>
                    <input type="button" disabled="disabled" value="同意" onclick="window.location.href='AgreeServlet?transaction_id=<%=transactionId %>&product_id=<%=productId %>'">
        <%
                } else {
        %>
                    <input type="button" value="同意" onclick="window.location.href='AgreeServlet?transaction_id=<%=transactionId %>&product_id=<%=productId %>'">
        <%
                }
            } else if ("ing".equals(status)) {
        %>
                <input type="button" value="交易成功" onclick="window.location.href='ProcessOrderStatusServlet?transaction_id=<%=transactionId %>&product_id=<%=productId %>&method=success'">
                <input type="button" value="交易失败" onclick="window.location.href='ProcessOrderStatusServlet?transaction_id=<%=transactionId %>&method=fail'">
        <%
            } else if ("end".equals(status)) {
        %>
                <button disabled="disabled">交易结束</button>
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
