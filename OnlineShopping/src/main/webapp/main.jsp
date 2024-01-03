<%@page import="java.util.List" %>
<%@page import="dao.impl.GoodDaoImpl" %>
<%@page import="dao.GoodDao" %>
<%@page import="bean.Good" %>
<%@page import="bean.GoodList" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="./CSS/main.css">
</head>

<body>
<<<<<<< HEAD
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
            <br/>                
            <div class="search-container">
				<div class="search-box">
					<form action="SearchForGoodsServlet?method=namesearch" method="post">
						<input type="text" placeholder="请输入商品名搜索" name="goodName">
						<button type="submit">查找</button>
					</form>
				</div>
				<div class="search-box">
					<form action="SearchForGoodsServlet?method=select" method="post">
						<select id="medicineType" name="medicineType">
						  <option value="prescription" selected>处方药</option>
						  <option value="non-prescription">非处方药</option>
						</select>
						<button type="submit">筛选</button>
					</form>
				</div>
			</div>
            <div class="menu"></div>
            <div class="content">
                <div class="yp">
                	<%
                		GoodList gl = (GoodList)session.getAttribute("goodsList");
                	                	                		for(Good g:gl.getGoodsList()){
                	%>
                			<a href="ShowGoodsDetail?id=<%=Integer.toString(g.getId())%>">
                        <img src=<%=pictures.get(0)%>>
                    </a>
                    		<a href="ShowGoodsDetail?id=<%=Integer.toString(g.getId())%>"><p><%=g.getItemName()%></p></a>
                			<%
                		}
                	%>
                	<!--
                    <a href="information.jsp"><img src="./img/yp.png"></a>
                    <a href="information.jsp"><p>商品名称</p></a>
                    -->
                </div>
                <div class="notice">
                    <p>敬情期待...</p>
                </div>
    
            </div>
            <div class="sidebar"></div>
            <div class="footer">
                <a href="#">关于我们</a>
                <a href="#">联系我们</a>
                <a href="#">隐私政策</a>
                <a href="#">公益活动</a>
            </div>
=======
<div class="head">
    <div class="header">
        <a href="ShowGoodsList">首页</a>
        <a href="back_stage.jsp">进入后台</a>
        <c:choose>
            <c:when test="${empty sessionScope.curUser}">
                <a href="login.jsp">登录</a>
                <a href="register.jsp">注册</a>
            </c:when>
            <c:otherwise>
                <a href="QuitServlet">退出登录</a>
            </c:otherwise>
        </c:choose>
>>>>>>> main
    </div>
</div>
<div class="main">
    <div class="navbar">
        <img src="./img/Xbro.jpg">
    </div>
    <div class="search-container">
		<div class="search-box">
			<form action="SearchForGoodsServlet?method=namesearch" method="post">
				<input type="text" placeholder="请输入商品名搜索" name="goodName">
				<button type="submit">查找</button>
			</form>
		</div>
		<div class="search-box">
			<form action="SearchForGoodsServlet?method=select" method="post">
				<select id="medicineType" name="medicineType">
				  <option value="prescription" selected>处方药</option>
				  <option value="non-prescription">非处方药</option>
				</select>
				<button type="submit">筛选</button>
			</form>
		</div>
	</div>
    <div class="menu">
   	    <ul>
	        <li class="menu-item">
	            <a href="#" onclick="toggleMenu('prescription')">处方药</a>
	            <ul id="prescription" class="submenu" style="display:none;">
	                <li><a href="#">心血管药物</a></li>
	                <li><a href="#">抗生素</a></li>
	                <li><a href="#">抗抑郁药</a></li>
	                <li><a href="#">镇痛药</a></li>
	            </ul>
	        </li>
	        <li class="menu-item">
	            <a href="#" onclick="toggleMenu('otc')">非处方药（OTC）</a>
	            <ul id="otc" class="submenu" style="display:none;">
	                <li><a href="#">感冒和流感</a></li>
	                <li><a href="#">止痛药</a></li>
	                <li><a href="#">消化系统药物</a></li>
	                <li><a href="#">皮肤护理药膏</a></li>
	            </ul>
	        </li>
	    </ul>
    </div>
    <div class="content">
        <div class="yp">
            <%
                GoodDao gd = new GoodDaoImpl();
                GoodList gl = (GoodList) session.getAttribute("goodsList");
                for (Good g : gl.getGoodsList()) {
                    List<String> pictures = gd.findAllPictures(g.getId());
            %>
                    <a href="ShowGoodsDetail?id=<%=Integer.toString(g.getId())%>">
                        <img src=<%=pictures.get(0)%>>
                    </a>
                    <a href="ShowGoodsDetail?id=<%=Integer.toString(g.getId())%>">
                        <p><%=g.getItemName()%></p>
                    </a>
            <%
                }
            %>
        </div>
    </div>
    <div class="sidebar">
    </div>
    <div class="footer">
        <a href="#">关于我们</a>
        <a href="#">联系我们</a>
        <a href="#">隐私政策</a>
        <a href="#">公益活动</a>
    </div>
</div>
<script type="text/javascript">
function toggleMenu(menuId) {
    var menu = document.getElementById(menuId);
    if (menu.style.display === 'none') {
        menu.style.display = 'block';
    } else {
        menu.style.display = 'none';
    }
}
</script>
</body>
</html>
