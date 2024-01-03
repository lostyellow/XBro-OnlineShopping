<%@page import="dao.impl.UserDaoImpl"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.User"%>
<%@page import="bean.UserList"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link type="text/css" rel="stylesheet" href="./CSS/buyer_information.css">
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
                <p>客户名</p>
                <p>密码</p>
                <p>电话</p>
                <p>交易地址</p>
                <p>操作</p>
                	<%
                		UserDao ud = new UserDaoImpl();
                	    UserList ul = (UserList)session.getAttribute("userList");
                	    for(User u:ul.getUsers()){
                			%>
                    		<p><%=u.getUserName()%></p>
                            <p><%=u.getPassword()%></p>
                            <p><%=u.getTele()%></p>
                            <p><%=u.getAddress()%></p>
                            <p><a href="record2.html">查看其订单</a></p>
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