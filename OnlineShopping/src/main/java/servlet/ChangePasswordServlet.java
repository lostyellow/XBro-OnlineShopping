package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePasswordServlet() {
        super();
    }

	protected void verifyOldPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User curUser = (User)request.getSession().getAttribute("curUser");
		String oldPwd = request.getParameter("OldPwd");
		String newPwd1 = request.getParameter("NewPassword1");
		String newPwd2 = request.getParameter("NewPassword2");
		String pbm = "";
		if(oldPwd != "" && newPwd1 != "" && newPwd2 != "") {
			if(oldPwd.equals(curUser.getPassword())) {//判断密码是不是正确
				 if(oldPwd.equals(newPwd1)) {//密码正确判断新旧是不是一样的
					 pbm = "samePwd";
				 }else if(!newPwd1.equals(newPwd2)) {//判断新密码的两个是不是一样的
					 pbm = "notSameNewPwd";
				 }else { //都一样去更改密码
					 UserDao ud = new UserDaoImpl();
					 int userId = ud.findSeller_ID(curUser);
					 System.out.println("------------------"+userId);
					 ud.changePwd(userId, newPwd1);
					 request.getSession().removeAttribute("curUser");
					 response.sendRedirect("login.jsp");
					 return;
				 }
			}else{//旧密码不一致
				pbm = "oldPwdWrong";
			}
		}else {
			pbm = "pwdnull";
		}
		request.setAttribute("pbm", pbm);
		request.getRequestDispatcher("change_password.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("changepwd".equals(method)) {
			verifyOldPwd(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
