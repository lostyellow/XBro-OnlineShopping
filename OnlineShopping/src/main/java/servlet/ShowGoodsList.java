package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseDao;
import dao.GoodDao;
import dao.impl.DatabaseDaoImpl;
import dao.impl.GoodDaoImpl;
import bean.GoodList;

/**
 * Servlet implementation class ShowGoodsList
 */
@WebServlet("/ShowGoodsList")
public class ShowGoodsList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGoodsList() {
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
        WebInit();
        show(request, response);
    }

    private void WebInit() {
        DatabaseDao DBdao = new DatabaseDaoImpl();
        DBdao.createDB();
    }

    private void show(HttpServletRequest request, HttpServletResponse response) {
        try {
            GoodDao gd = new GoodDaoImpl();
            GoodList gl = new GoodList();
            gl = gd.findForSaleGoods();
            request.getSession().setAttribute("goodsList", gl);
            response.sendRedirect("main.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
