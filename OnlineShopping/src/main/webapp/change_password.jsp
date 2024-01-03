<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link type="text/css" rel="stylesheet" href="./CSS/changepwd.css">
</head>
<body>
<img src="./img/bg2.jpg">
<div class="main-content">
    <!--  如果商家已经登录可以更改密码 -->
    <c:if test="${not empty sessionScope.curUser }">
        <form action="ChangePasswordServlet?method=changepwd" method="post">
            <div class="divtop">
                <p>
                <h2>修改密码</h2></p>
            </div>
            <div class="divcenter">
                <!-- table中的内容是获取数据 -->
                <table>
                    <tr>
                        <td class="tdLeft"><label for="OldPwd">旧密码</label></td>
                        <td class="tdRight"><input type="text" name="OldPwd" id="OldPwd" placeholder="请输入旧密码">
                        </td>
                        <c:if test="${requestScope.pbm == 'pwdnull'}">
                            <td class="tdRight"><p style="color:red;">信息不能为空</p></td>
                        </c:if>
                        <c:if test="${requestScope.pbm == 'oldPwdWrong'}">
                            <td class="tdRight"><p style="color:red;">旧密码是错的</p></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td class="tdLeft"><label for="NewPassword1">新密码</label></td>
                        <td class="tdRight"><input type="password" name="NewPassword1" id="NewPassword1"
                                                   placeholder="请输入新密码"></td>
                        <c:if test="${requestScope.pbm == 'samePwd'}">
                            <td class="tdRight"><p style="color:red;">新旧密码一致</p></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td class="tdLeft"><label for="NewPassword2">验证新密码</label></td>
                        <td class="tdRight"><input type="password" name="NewPassword2" id="NewPassword2"
                                                   placeholder="请二次输入新密码"></td>
                        <c:if test="${requestScope.pbm == 'notSameNewPwd'}">
                            <td class="tdRight"><p style="color:red;">两次新密码不一致</p></td>
                        </c:if>
                    </tr>
                </table>
                <input type="submit" value="修改密码" class="login-button">
            </div>
        </form>
    </c:if>
    <!-- 如果商家没登陆就要求登录 -->
    <c:if test="${empty sessionScope.curUser }">
        <p style="top:20%;left:10%;position:absolute;">你还未登录，请先<a href="login.jsp">登录</a>。</p>
    </c:if>
</div>
</body>
</html>