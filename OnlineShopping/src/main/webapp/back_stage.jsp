<%@page import="bean.User"%>
<%@page import="bean.GoodList" %>
<%@page import="dao.impl.GoodDaoImpl" %>
<%@page import="dao.GoodDao" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="dao.UserDao" %>
<%@page import="bean.Good" %>
<%@page import="dao.impl.UserDaoImpl" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="./CSS/back_stage.css">
    <link href="./CSS/quill.bubble.css" rel="stylesheet">
</head>

<body>
<%
    Good good = new Good();
    UserDao ud = new UserDaoImpl();
    GoodDao gd = new GoodDaoImpl();
    List<Good> goods = new ArrayList<Good>();
%>

<div class="head">
    <div class="header">
        <a href="main.jsp">首页</a> 
        <a href="back_stage.jsp">进入后台</a> 
        <a href="change_password.jsp">修改密码</a>
        <%
        	User curUser = (User)request.getSession().getAttribute("curUser");
            if (curUser==null || !"seller".equals(curUser.getUser_group())) {
            	response.sendRedirect("login.jsp");
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
            <a href="buyer_information.jsp">查看用户信息</a>
        </div>
        <div class="yp">

            <div class="tianjia">
                <form action="AddGoodServlet?id=<%=curUser.getId() %>" method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
                    上传图片: <input type="file" name="picture"/>
                    <div class="discribe">
                        <p>
                            商品名称:<input type="text" name="name" required>
                        </p>
                        <p>
                            商品描述:<input type="text" name="detail" style="display:none" id="description"><br>
                            <div id="editor" style="width:300px; height: 150px; border:1px solid black; border-radius:2px;"></div>
                        </p>
                        <p>
                            商品生产批次:<input type="text" name="batch" required>
                        </p>
                        <p>
                            商品有效期:<input type="date" name="date" required>
                        </p>
					    <p>
		    商品是否为处方药:
		        <input type="radio" name="option3" value="yes" onchange="showSubCategory(this.value)" required>是
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

<script src="./JS/quill.js"></script>
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

const toolbarOptions = [
	['bold', 'italic', 'underline', 'strike'],
	['blockquote', 'code-block'],
	[{ 'list': 'ordered'}, { 'list': 'bullet' }],
	[{ 'script': 'sub'}, { 'script': 'super' }],
	[{ 'indent': '-1'}, { 'indent': '+1' }],
	[{ 'direction': 'rtl' }],
	[{ 'size': ['small', false, 'large', 'huge'] }],
	[{ 'header': [1, 2, 3, 4, false] }],
	['clean']
]
var quill = new Quill('#editor', {
	theme: 'bubble',
	modules: {
		toolbar: toolbarOptions
	}
});

function checkForm(){
	document.getElementById('description').value = (document.getElementsByClassName('ql-editor')[0]).innerHTML;
	return true;
}
</script>
</body>
</html>
