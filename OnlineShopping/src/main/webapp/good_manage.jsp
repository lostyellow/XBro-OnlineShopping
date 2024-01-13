<%@page import="bean.User"%>
<%@page import="bean.GoodList"%>
<%@page import="dao.impl.GoodDaoImpl"%>
<%@page import="dao.GoodDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDao"%>
<%@page import="bean.Good"%>
<%@page import="dao.impl.UserDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<link type="text/css" rel="stylesheet" href="./CSS/good_manage.css">
	<link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">
</head>
<%
	User curUser = (User)request.getSession().getAttribute("curUser");
	if (curUser==null || !"seller".equals(curUser.getUser_group())) {
		response.sendRedirect("login.jsp");
	}
	
	int product_id = Integer.parseInt(request.getParameter("product_id"));
	GoodDao gd = new GoodDaoImpl();
	Good g = gd.findGoods(product_id);
	int inventory =g.getInventory();
   	boolean isPres = g.getIsPres();
    List<String> pictures = gd.findAllPictures(product_id);
%>

<%if(isPres){ %>
<body onload="showSubCategory('yes')">
<%}else{ %>
<body onload="showSubCategory('no')">
<%} %>

	<%
		Good good = new Good();
		UserDao ud = new UserDaoImpl();
		
		List<Good> goods = new ArrayList<Good>();
		if(session.getAttribute("loginStatus") == null || session.getAttribute("loginStatus").equals("failed")){
			response.sendRedirect("login.jsp");
		}
	%>

    <div class="head">
        <div class="header">
          <a href="main.jsp">首页</a>
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
	        </div>
	        <div class="yp">
	        	<select name="imageSelector" id="imageSelector" onchange="showSelectedImage()">
				    <% for(int i = 0; i < pictures.size(); i++) { %>
				        <option value="image<%= i+1 %>">图片 <%= i+1 %></option>
				    <% } %>
				    <option value="newImage">新增图片</option>
				</select>
					        	
	        	<form id="uploadForm" action="AddPictureServlet?product_id=<%=g.getId() %>" method="post" enctype="multipart/form-data">
		        	<p>
	                	上传图片: <input type="file" name="picture"/>
	                </p>
	                <p>
	                	<input type="submit" value="上传图片">
	                </p>
                </form>
                <form action="UpdateGoodServlet?product_id=<%=g.getId() %>" method="post" onsubmit="return checkForm();">
                <div class="discribe">
                    <p>
                        商品名称:<input type="text" name="name" value=<%=g.getItemName() %>>
                    </p>
                    <p>
                        商品描述:<input type="text" name="detail" style="display:none" id="description"><br>
		                <div id="editor" style="width:300px; height: 150px; border: 1px solid black; border-radius:2px;">
		                	<%= g.getItemDescription() %>
		                </div>
                    </p>
                    <p>
                        商品生产批次:<input type="text" name="batch" value=<%=g.getNumber() %>>
                    </p>
                    <p>
                        商品有效期:<input type="date" name="date" value=<%=g.getDate() %>>
                    </p>
                    <p>
                    <%
                    	if(isPres){
                    %>
		    商品是否为处方药:
		        <input type="radio" name="option3" value="yes" onchange="showSubCategory(this.value)" checked required>是
		        <input type="radio" name="option3" value="no" onchange="showSubCategory(this.value)">否
		        	<%}else{ %>
			商品是否为处方药:
		        <input type="radio" name="option3" value="yes" onchange="showSubCategory(this.value)" required>是
		        <input type="radio" name="option3" value="no" onchange="showSubCategory(this.value)" checked>否
		        	<%} %>
					    </p>
					    
					    <!-- 隐藏的子类下拉列表 -->
					    <p id="subCategoryContainer" style="display: none;">
		    商品子类别:
		        <select id="subCategorySelect" name="subCategory">
		            <!-- 这里可以根据需要填充选项 -->
		        </select>
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
                    <p>
                    	商品数量：<input type="text" name="inventory" value=<%=g.getInventory() %>>
                    </p>
                    
                    <%if(g.getInventory() != 0){ %>
                    <input type="submit" value="修改">
                    <input type="submit" formaction="DeleteGoodServlet?product_name=<%=g.getItemName() %>" value="下架">
                    <%}else{ %>
                    <input type="submit" value="重新上架">
		        	<%} %>
		        	<p style="color:red">请重新选择商品子类别和是否冻结</p>
	        	</div>
	        	</form>
	        <div id="imageContainer">
		        <img src="" id="displayedImage">
		        <button id="deleteButton" class="delete-btn">删除</button>
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
<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
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
<!-- select切换图片 -->
<script>
var images = {
    <% for(int index = 1; index <= pictures.size(); index++) {
        String picture = pictures.get(index - 1);
        out.println("'image" + index + "': '" + picture + "'" + (index < pictures.size() ? "," : ""));
    } %>
};
function showSelectedImage() {
    var imageSelector = document.getElementById('imageSelector');
    var selectedValue = imageSelector.value;

    var imageContainer = document.getElementById('imageContainer');
    var uploadForm = document.getElementById('uploadForm');
    var deleteButton = document.getElementById('deleteButton'); // 假设删除按钮的 ID 是 'deleteButton'

    if(selectedValue === 'newImage') {
        // 显示上传表单，隐藏图片容器
        uploadForm.style.display = 'block';
        imageContainer.style.display = 'none';
    } else {
        // 显示图片，隐藏上传表单
        uploadForm.style.display = 'none';
        imageContainer.style.display = 'block';

        // 根据选项的值获取对应的图片 URL
        var imageUrl = getUrlForImage(selectedValue);

        // 设置图片的 src 属性
        var displayedImage = document.getElementById('displayedImage');
        displayedImage.src = imageUrl;

        // 更新删除按钮的 onclick 事件
        deleteButton.onclick = function() {
            deleteImage(imageUrl);
        };
    }
}

function deleteImage(imageUrl) {
    window.location.href = 'DeletePictureServlet?product_id=<%=product_id %>&url=' + encodeURIComponent(imageUrl);
}
function getUrlForImage(imageId) {
    // 这个函数需要返回对应 imageId 的 URL
    return images[imageId];
}

// 初始时加载默认图片
window.onload = function() {
    showSelectedImage();
}
</script>
</body>
</html>