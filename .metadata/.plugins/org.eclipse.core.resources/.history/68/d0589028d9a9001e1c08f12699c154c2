<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>register</title>
<link rel="stylesheet" type="text/css" href="./CSS/register.css">
</head>
<body>
	<!-- 图片的src需要修改 -->
	<img src="./img/bg2.jpg" alt="">

	<div class="main-content">
		<div class="divleft">
			<p>
			<h2>注册信息</h2>
			</p>
		</div>
		<div class="divcenter">
			<!-- 商家注册 -->
			<form action="RegisterServlet?method=business" method="post">
				<div id="business-information">
					<table>
						<tr>
							<td class="tdLeft"><label for="UserName">用户名</label></td>
							<td class="tdRight"><input type="text" name="UserName"
								id="UserName" placeholder="请输入用户名"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="Password">密码</label></td>
							<td class="tdRight"><input type="password" name="Password"
								id="Password" placeholder="请输入密码"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="Name">姓名</label></td>
							<td class="tdRight"><input type="text" name="Name" id="Name"
								placeholder="请输入真实姓名"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="Id">身份证件</label></td>
							<td class="tdRight"><input type="text" name="Id" id="Id"
								placeholder="请输入身份证号码"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label>性别</label></td>
							<td class="tdRight"><input type="radio" name="sex" id="male"
								value="male" style="width: 50px; height: 20px;"><label
								for="male">男</label> <input type="radio" name="sex" id="female"
								value="female" style="width: 50px; height: 20px;"><label
								for="female">女</label></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="tel">手机号</label></td>
							<td class="tdRight"><input type="text" name="tel" id="tel"
								placeholder="请输入手机号"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="email">邮箱</label></td>
							<td class="tdRight"><input type="text" name="email"
								id="email" placeholder="请输入邮箱"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="birthday">出生年月</label></td>
							<td class="tdRight"><input type="date" name="birthday"
								id="birthday" placeholder="请输入邮箱"></td>
						</tr>
					</table>
					<input type="submit" value="商家注册" class="register-button"
						style="top: 85%;">
				</div>
			</form>
				<!--用户注册 -->
			<form action="RegisterServlet?method=user" method="post">
				<div id="user-information">
					<table>
						<tr>
							<td class="tdLeft"><label for="UserName">用户名</label></td>
							<td class="tdRight"><input type="text" name="UserName"
								id="UserName" placeholder="请输入用户名"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="Password">密码</label></td>
							<td class="tdRight"><input type="password" name="Password"
								id="Password" placeholder="请输入密码"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="Name">姓名</label></td>
							<td class="tdRight"><input type="text" name="Name" id="Name"
								placeholder="请输入真实姓名"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="Id">身份证件</label></td>
							<td class="tdRight"><input type="text" name="Id" id="Id"
								placeholder="请输入身份证号码"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label>性别</label></td>
							<td class="tdRight"><input type="radio" name="sex" id="male"
								value="male" style="width: 50px; height: 20px;"><label
								for="male">男</label> <input type="radio" name="sex" id="female"
								value="female" style="width: 50px; height: 20px;"><label
								for="female">女</label></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="tel">手机号</label></td>
							<td class="tdRight"><input type="text" name="tel" id="tel"
								placeholder="请输入手机号"></td>
						</tr>
						<tr>
							<td class="tdLeft"><label for="defaultAddress">默认地址</label></td>
							<td class="tdRight"><input type="text" name="defaultAddress"
								id="defaultAddress" placeholder="请输入默认地址"></td>
						</tr>
					</table>
					<input type="submit" value="用户注册" class="register-button"
						style="top: 78%;">
				</div>
			</form>
		</div>
		<div class="divright">
			<ul class="nav">
				<li id="business-register" class="default register" tabindex="1">商家注册</li>
				<li id="user-regiseter" class="register" tabindex="1">用户注册</li>
			</ul>
			<br>
			<br>
			<p id="p3">
				已有账号？<a href="login.jsp">立即登录</a>
			</p>
		</div>
	</div>
</body>

<!-- 板块实现id切换 -->
<script>
    var userInformation = document.getElementById("user-information");
    var businessInformation = document.getElementById("business-information");
    var userRegiseter = document.getElementById("user-regiseter");
    var businessRegister = document.getElementById("business-register");

    userRegiseter.addEventListener('click', function () {
        userInformation.style.display = 'block';
        businessInformation.style.display = 'none';
    });

    businessRegister.addEventListener('click', function () {
        userInformation.style.display = 'none';
        businessInformation.style.display = 'block';
    })
</script>

<!-- 实现选择后的切换 -->
<script>
    var removeDefaulyt = document.getElementById("business-register");
    const listItems = document.querySelectorAll('.nav li');
    listItems.forEach(item => {
            item.addEventListener('click', function() {
                listItems.forEach(item => {
                    item.classList.remove('default');
                });
                this.classList.add('default');
            });
        });
</script>
</html>
