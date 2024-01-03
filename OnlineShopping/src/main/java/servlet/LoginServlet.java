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
    @Override
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
            if (ud.checkLogin(username, password)) {
                request.getSession().setAttribute("loginStatus", "succeed");
                // 可能要在session中加入登录的用户信息，多用户缺id!!!
                User user = new User();
                user.setUserName(username);
                user.setPassword(password);
                request.getSession().setAttribute("curUser", user);
                response.sendRedirect("back_stage.jsp");
            } else {
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

}
