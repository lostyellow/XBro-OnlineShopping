<%@page import="bean.GoodList" %>
<%@page import="dao.impl.GoodDaoImpl" %>
<%@page import="dao.GoodDao" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="dao.UserDao" %>
<%@page import="bean.Good" %>
<%@page import="dao.impl.UserDaoImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    if (session.getAttribute("loginStatus") == null || session.getAttribute("loginStatus").equals("failed")) {
        response.sendRedirect("login.jsp");
    }
%>

<div class="head">
    <div class="header">
        <a href="ShowGoodsList">首页</a> 
        <a href="back_stage.jsp">进入后台</a> 
        <a href="change_password.jsp">修改密码</a>
        <%
            if (request.getSession().getAttribute("curUser") == null) {
        %>
        <a href="login.jsp">登录</a>
        <a href="register.jsp">注册</a>
        <%
            } else {
        %>
        <a href="QuitServlet">退出登录</a>
        <%
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
        <div class="shop">
            <span>店名</span> 
            <a href="history.jsp">查看历史商品</a>
        </div>
        <div class="yp">
            <%
                if (gd.anyForSale()) {
                    GoodList gl = gd.findOnSaleGood();
                    List<Good> goodList = gl.getGoodsList();
                    Good g = goodList.get(0);
            %>
            <form action="UpdateGoodServlet" method="post" enctype="multipart/form-data">
                上传图片: <input type="file" name="picture"/>
                <div class="discribe">
                    <p>
                        商品名称:<input type="text" name="name" value=<%=g.getItemName() %>>
                    </p>
                    <p>
                        商品描述:<input type="text" name="detail" value=<%=g.getItemDescription() %>>
                    </p>
                    <p>
                        商品生产批次:<input type="text" name="batch" value=<%=g.getNumber() %>>
                    </p>
                    <p>
                        商品有效期:<input type="date" name="date" value=<%=g.getDate() %>>
                    </p>
                    <p>
                        商品是否为处方药：
                        <%if (g.getIsPres()) { %>
                        <input type="radio" name="option3" value="yes" checked>是
                        <input type="radio" name="option3" value="no">否
                        <%} else { %>
                        <input type="radio" name="option3" value="yes">是 
                        <input type="radio" name="option3" value="no" checked>否
                        <%} %>
                    </p>
                    <p>
                        商品价格:<input type="text" name="price" value=<%=g.getPrice() %>>
                    </p>
                    <p>
                        商品是否被冻结：
                        <%if (g.getIsFrozen()) { %>
                        <input type="radio" name="option4" value="yes" checked>是
                        <input type="radio" name="option4" value="no">否
                        <%} else { %>
                        <input type="radio" name="option4" value="yes">是 
                        <input type="radio" name="option4" value="no" checked>否
                        <%} %>
                    </p>
                    <input type="submit" value="修改">
                    <input type="submit" formaction="DeleteGoodServlet" value="下架">
                </div>
            </form>
            <%}/* else {*/ %>
            <div class="tianjia">
                <form action="AddGoodServlet" method="post" enctype="multipart/form-data">
                    上传图片: <input type="file" name="picture"/>
                    <div class="discribe2">
                        <p>
                            商品名称:<input type="text" name="name" required>
                        </p>
                        <p>
                            商品描述:<input type="text" name="detail" required>
                        </p>
                        <p>
                            商品生产批次:<input type="text" name="batch" required>
                        </p>
                        <p>
                            商品有效期:<input type="date" name="date" required>
                        </p>
					    <p>
		    商品是否为处方药:
		        <input type="radio" name="option3" value="yes" onchange="showSubCategory(this.value)">是
		        <input type="radio" name="option3" value="no" onchange="showSubCategory(this.value)">否
					    </p>
					    
					    <!-- 隐藏的子类下拉列表 -->
					    <p id="subCategoryContainer" style="display: none;">
		    商品子类别:
		        <select id="subCategorySelect" name="subCategory">
		            <!-- 这里可以根据需要填充选项 -->
		        </select>
					    </p>
                            商品价格:<input type="text" name="price" required>
                        </p>
                        <p>
                            商品数量:<input type="text" name="inventory" required>
                        </p>
                        <input type="submit" value="上架">
                    </div>
                </form>
            </div>
            <%/* } */%>
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
<script type="text/javascript">
function showSubCategory(isPrescription) {
    var container = document.getElementById('subCategoryContainer');
    var select = document.getElementById('subCategorySelect');

    if (isPrescription === 'yes') {
        // 显示处方药的子类别
        container.style.display = 'block';
        // 假设这些是处方药的子类别
        select.innerHTML = '<option value="心血管药物">心血管药物</option>'
        +'<option value="抗生素">抗生素</option>'
        +'<option value="抗抑郁药">抗抑郁药</option>'
        +'<option value="镇痛药">镇痛药</option>'
        +'<option value="其他">其他</option>';
    } else if (isPrescription === 'no') {
        // 显示非处方药的子类别
        container.style.display = 'block';
        // 假设这些是非处方药的子类别
        select.innerHTML = '<option value="感冒和流感">感冒和流感</option>'
        +'<option value="止痛药">止痛药</option>'
        +'<option value="消化系统药物">消化系统药物</option>'
        +'<option value="皮肤护理膏药">皮肤护理膏药</option>'
        +'<option value="其他">其他</option>';
    } else {
        // 如果没有选择，隐藏下拉列表
        container.style.display = 'none';
        select.innerHTML = '';
    }
}
</script>
</body>
</html>
