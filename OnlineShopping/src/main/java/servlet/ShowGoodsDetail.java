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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		showDetail(request, response);
	}
	
	private void showDetail(HttpServletRequest request, HttpServletResponse response) {
		try {
			GoodDao gd = new GoodDaoImpl();
			Good g = new Good();
			String id = request.getParameter("id");
			g = gd.findGoods(Integer.parseInt(id));
			request.getSession().setAttribute("goods", g);
			response.sendRedirect("information.jsp?id=" + id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
