package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Good;
import bean.GoodList;
import dao.GoodDao;
import dao.impl.GoodDaoImpl;
import bean.Good;
import bean.GoodList;
import dao.GoodDao;
import dao.impl.GoodDaoImpl;

/**
 * Servlet implementation class ShowGoodsDetail
 */
@WebServlet("/ShowGoodsDetail")
public class ShowGoodsDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGoodsDetail() {
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
        showDetail(request, response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) {
        try {
        	request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            GoodDao gd = new GoodDaoImpl();
            String id = request.getParameter("product_id");
            Good g = gd.findGoods(Integer.parseInt(id));
            response.sendRedirect("information.jsp?product_id=" + id);
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
