package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.impl.UserDaoImpl;

@WebServlet("/FindPasswordServlet")
public class FindPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FindPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void findpwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String realname = request.getParameter("RealName");
        String idcard = request.getParameter("id_card");
        UserDao ud = new UserDaoImpl();
        String pwd = ud.findSeller_PWD(realname, idcard);
        request.setAttribute("pwd", pwd);
        request.getRequestDispatcher("find_password.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        findpwd(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

}
