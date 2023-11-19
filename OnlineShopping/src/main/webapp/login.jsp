<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="./CSS/login.css">
</head>
<body>
    <img src="./img/bg2.jpg">
    <div class="main-content">
        <form action="LoginServlet">
            <div class="divtop">
                <p><h2>登录</h2></p>
            </div>
            <div class="divcenter">
                <!-- table中的内容是获取数据 -->
                <table>
                    <tr>
                        <td class="tdLeft"><label for="UserName">用户名</label></td>
                        <td class="tdRight"><input type="text" name="UserName" id="UserName" placeholder="请输入用户名"></td>
                    </tr>
                    <tr>
                        <td class="tdLeft"><label for="Password">密码</label></td>
                        <td class="tdRight"><input type="password" name="Password" id="Password" placeholder="请输入密码"></td>
                    </tr>
                </table>
                <input type="submit" value="登录" class="login-button">
            </div>
            <div class="divbottom">
                <p>还没有账号？<a href="register.jsp">立即注册</a></p>
                <br/>
                <p>忘记密码？<a href="find_password.jsp">找回密码</a></p>
 				<%
	            	if(session.getAttribute("loginStatus") != null && session.getAttribute("loginStatus").equals("failed")){
	            		%>
	            		<p style="color: red">登录失败</p>
	            		<%
	            		session.removeAttribute("loginStatus");
	            	}
	            	else {
	            		// response.sendRedirect("store_manager.jsp");
	            	}
	            %>
                
            </div>
        </form>
    </div>
</body>
</html>

