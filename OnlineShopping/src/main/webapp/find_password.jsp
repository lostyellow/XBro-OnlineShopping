<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Find Password</title>
    <link rel="stylesheet" type="text/css" href="./CSS/findpwd.css">
</head>
<body>
<img src="./img/bg2.jpg">
<div class="main-content">
    <form action="FindPasswordServlet" method="post">
        <div class="divtop">
            <p>
            <h2>修改密码</h2></p>
        </div>
        <div class="divcenter">
            <table>
                <tr>
                    <td class="tdLeft"><label for="RealName">真实姓名</label></td>
                    <td class="tdRight"><input type="text" name="RealName" id="RealName" placeholder="请输入姓名"></td>
                </tr>
                <tr>
                    <td class="tdLeft"><label for="id_card">身份证号码</label></td>
                    <td class="tdRight"><input type=text name="id_card" id="id_card" placeholder="请输入身份证号"></td>
                </tr>
            </table>
            <input type="submit" value="查看" class="login-button">
        </div>
        <div class="divbottom">
            <c:if test="${not empty requestScope.pwd }">
                <p style="color:red;">密码是:${requestScope.pwd}</p>
            </c:if>
            <c:if test="${ requestScope.pwd eq '' }">
                <p style="color:red;">信息有误!</p>
            </c:if>
        </div>
    </form>
</div>
</body>
</html>