<%@page import="java.util.List"%>
<%@page import="dao.impl.GoodDaoImpl"%>
<%@page import="dao.GoodDao"%>
<%@page import="bean.Good"%>
<%@page import="bean.GoodList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="./CSS/main.css">
</head>

<body>
    <div class="head">
        <div class="header">
          <a href="ShowGoodsList">首页</a>
          <a href="back_stage.jsp">进入后台</a>
          <%
          	if(request.getSession().getAttribute("curUser")==null){
          %>
          <a href="login.jsp">登录</a><a href="register.jsp">注册</a><%
          	}
          %><%
          	else{
          %>
          <a href="QuitServlet">退出登录</a><%
          	}
          %>
          
        </div>
    </div>   
    <div class="main">
            <div class="navbar">
                <img src="./img/Xbro.jpg">
            </div>
            <div class="menu"></div>
            <div class="content">
                <div class="yp">
                	<%
                		GoodDao gd = new GoodDaoImpl();
                		GoodList gl = (GoodList)session.getAttribute("goodsList");
                	    for(Good g:gl.getGoodsList()){
                	    	List<String> pictures = gd.findAllPictures(g.getId());
                	%>
                			<a href="ShowGoodsDetail?id=<%=Integer.toString(g.getId())%>"><img src=<%=pictures.get(0)%>></a>
                    		<a href="ShowGoodsDetail?id=<%=Integer.toString(g.getId())%>"><p><%=g.getItemName()%></p></a>
                			<%
                		}
                	%>
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
