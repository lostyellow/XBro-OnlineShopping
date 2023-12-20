<%@page import="bean.GoodList"%>
<%@page import="dao.impl.GoodDaoImpl"%>
<%@page import="dao.GoodDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDao"%>
<%@page import="bean.Good"%>
<%@page import="dao.impl.UserDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link type="text/css" rel="stylesheet" href="./CSS/back_stage.css">
</head>

<body>

	<%
		Good good = new Good();
		UserDao ud = new UserDaoImpl();
		GoodDao gd = new GoodDaoImpl();
		List<Good> goods = new ArrayList<Good>();
		if(session.getAttribute("loginStatus") == null || session.getAttribute("loginStatus").equals("failed")){
			response.sendRedirect("login.jsp");
		}
	%>

    <div class="head">
        <div class="header">
          <a href="ShowGoodsList">首页</a>
          <a href="back_stage.jsp">进入后台</a>
	  <a href="change_password.jsp">修改密码</a>	
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
	        <img src="./img/Xbro.png">
	    </div>
	    <div class="menu"></div>
	    <div class="content">
	        <div class="shop" >
	            <span>店名</span>
		<a href="history.jsp">查看历史商品</a>
	        </div>
	        <div class="yp">
	        <%
	        	if(gd.anyForSale()){
	        %>
	        <%
	        	GoodList gl = gd.findOnSaleGood();
     	        List<Good> goodList = gl.getGoodsList();
     	        Good g = goodList.get(0);
	        %>
	        	<form action="UpdateGoodServlet" method="post">
		        	<img src="./img/yp.png">
		            <div class="discribe">
		                <p>商品名称:<input type="text" name="name" value=<%=g.getItemName() %>></p>
		                <p>商品描述:<input type="text" name="detail" value=<%=g.getItemDescription() %>></p>
		                <p>商品生产批次:<input type="text" name="batch" value=<%=g.getNumber() %>></p>
		                <p>商品有效期:<input type="date" name="date" value=<%=g.getDate() %>></p>
		                <p>商品是否为处方药：
		                <%if(g.getIsPres()){ %>
		                <input type="radio" name="option3" value="yes" checked>是
		                <input type="radio" name="option3" value="no">否
		                <%}else{ %>
		                <input type="radio" name="option3" value="yes" >是
		                <input type="radio" name="option3" value="no" checked>否
		                <%} %>
		                </p>
		                <p>商品价格:<input type="text" name="price" value=<%=g.getPrice() %>></p>
		                <p>商品是否被冻结：
		                <%if(g.getIsFrozen()){ %>
		                <input type="radio" name="option4" value="yes" checked>是
		                <input type="radio" name="option4" value="no">否
		                <%}else{ %>
		                <input type="radio" name="option4" value="yes" >是
		                <input type="radio" name="option4" value="no" checked>否
		                <%} %>
		                </p>
		                <input type="submit" value="修改"><input type="submit" formaction="DeleteGoodServlet" value="下架">
		            </div>
		        </form>
	        <%}else{ %>
	        <div class="tianjia">
                <form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data">
					上传图片: <input type="file" name="file" />
					<input type="submit" value="上传" />
				</form>
             </div>
	            <form action="AddGoodServlet" method="post">
	             <div class="discribe2">
	                 <p>商品名称:<input type="text" name="name" ></p>
	                 <p>商品描述:<input type="text" name="detail" ></p>
	                 <p>商品生产批次:<input type="text" name="batch" ></p>
	                 <p>商品有效期:<input type="date" name="date" ></p>
	                 <p>商品是否为处方药：<input type="radio" name="option3" value="yes">是<input type="radio" name="option3" value="no">否</p>
	                 <p>商品价格:<input type="text" name="price"></p>
	                 <input type="submit" value="上架">
	             </div>
	            </form>
	            <%} %>
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
