<%@page import="dao.impl.UserDaoImpl"%>
<%@page import="dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.Details"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="./CSS/detail.css">
</head>
<body>
    <div class="head">
        <div class="header">
          <a href="ShowGoodsList">首页</a>
          <a href="back_stage.jsp">进入后台</a>
          <%if(request.getSession().getAttribute("curUser")==null){ %>
          <a href="login.jsp">登录</a><a href="register.jsp">注册</a><%}%><% else{ %>
          <a href="QuitServlet">退出登录</a><%} %></a>
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
                	int product_id = Integer.parseInt(request.getParameter("product_id"));
               		int transaction_id = Integer.parseInt(request.getParameter("transaction_id"));
                    UserDao ud = new UserDaoImpl();
                    Details d = ud.findDetails(transaction_id); 
                    //session.setAttribute("details", d);
                %>
                <p><strong>交易时间：</strong><%=d.getAppointment_time()%></p>
                <p><strong>地址：</strong><%=d.getAddress()%></p>
                <p><strong>交易人：</strong><%=d.getBuyer_name()+d.getBuyer_gender()%></p>
                <p><strong>身份证号码：</strong><%=d.getBuyer_identification()%></p>
                <p><strong>手机号：</strong><%=d.getBuyer_phone_number()%></p>
                <p><strong>备注：</strong><%=d.getText()%></p>
                <%
                	String status = String.valueOf(request.getParameter("status"));
                	if(status.equals("wait")){
                		if(ud.IsExistIngDeal(product_id)){
                			%><input type="button" disabled="disabled" value="同意" onclick="window.location.href='AgreeServlet?transaction_id=<%=transaction_id %>&product_id=<%=product_id %>'" ><%
                		}else{
                			%><input type="button" value="同意" onclick="window.location.href='AgreeServlet?transaction_id=<%=transaction_id %>&product_id=<%=product_id %>'" ><%
                		}
                	}else if(status.equals("ing")){
                		%><input type="button" value="交易成功" onclick="window.location.href='ProcessOrderStatusServlet?product_id=<%=product_id %>&method=success'" ><%
                		%><input type="button" value="交易失败" onclick="window.location.href='ProcessOrderStatusServlet?transaction_id=<%=transaction_id %>&method=fail'" ><%
                	}else if(status.equals("end")){
                		%><button disabled="disabled">交易结束</button>
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
