<%@page import="dao.impl.GoodsDaoImpl"%>
<%@page import="dao.GoodsDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDao"%>
<%@page import="bean.Goods"%>
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
		Goods good = new Goods();
		UserDao ud = new UserDaoImpl();
		List<Goods> goods = new ArrayList<Goods>();
		if(session.getAttribute("loginStatus") == null || session.getAttribute("loginStatus").equals("failed")){
			response.sendRedirect("login.jsp");
		}
	%>

    <div class="head">
        <div class="header">
          <a href="ShowGoodsList">首页</a>
          <a href="back_stage.jsp">进入后台</a>
	  <a href="change_password.jsp">修改密码</a>	
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
	            <span>店名</span>
		<a href="history.jsp">查看历史商品</a>
	        </div>
	        <div class="yp">
		        <form action="UpdateGoodServlet" method="post">
		        	<img src="./img/yp.png">
		            <div class="discribe">
		                <p>商品名称:<input type="text" name="name" ></p>
		                <p>商品描述:<input type="text" name="detail" ></p>
		                <p>商品生产批次:<input type="text" name="batch" ></p>
		                <p>商品有效期:<input type="date" name="date" ></p>
		                <p>商品是否为处方药：<input type="radio" name="option3" value="yes">是<input type="radio" name="option3" value="no">否</p>
		                <p>商品价格:<input type="text" name="price"></p>
		                <p>商品是否被冻结：<input type="radio" name="option4" value="yes">是<input type="radio" name="option4" value="no">否</p>
		                <input type="submit" value="修改"><input type="submit" formaction="DeleteGoodServlet" value="下架">
		            </div>
		        </form>
		        
	            <form action="AddGoodServlet" method="post">
	             <div class="tianjia">
	                 <a href="#">+添加图片</a>
	             </div>
	             <div class="discribe2">
	                 <p>商品名称:<input type="text" name="name" ></p>
	                 <p>商品描述:<input type="text" name="detail" ></p>
	                 <p>商品生产批次:<input type="text" name="batch" ></p>
	                 <p>商品有效期:<input type="date" name="date" ></p>
	                 <p>商品是否为处方药：<input type="radio" name="option3" value="yes">是<input type="radio" name="option3" value="no">否</p>
	                 <p>商品价格:<input type="text" name="price"></p>
	                 <%
	                 	GoodsDao gd = new GoodsDaoImpl();
	                 	if(gd.findAllGoods().getLength() == 0){
	                 %>
	                 <input type="submit" value="上架">
	                 <%
	                 	}else{
	                 %>
	                 <input type="submit" value="已发布商品" disabled>
	                 <%} %>
	             </div>
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
    </div>
</body>
</html>
