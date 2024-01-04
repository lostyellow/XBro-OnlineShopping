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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		login(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("UserName");
			String password = request.getParameter("Password");
			
			UserDao ud = new UserDaoImpl();
			String user_group = ud.checkLogin(username, password);
			if("seller".equals(user_group)) {
				request.getSession().setAttribute("loginStatus", "succeed");
				// 商家登录
				User user = new User();
				user.setUserName(username);
				user.setPassword(password);
				user.setUser_group(user_group);
				request.getSession().setAttribute("curUser", user);
				response.sendRedirect("back_stage.jsp");
			}else if("user".equals(user_group)){
				request.getSession().setAttribute("loginStatus", "succeed");
				//用户登录
				User user = new User();
				user.setUserName(username);
				user.setPassword(password);
				user.setId(ud.findSeller_ID(user));
				user.setUser_group(user_group);
				request.getSession().setAttribute("curUser", user);
				response.sendRedirect("main.jsp");
			}else {
				request.getSession().setAttribute("loginStatus", "failed");
				response.sendRedirect("login.jsp");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		doGet(request, response);
	}

}