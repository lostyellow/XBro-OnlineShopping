package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Goods;
import bean.GoodsList;
import bean.User;
import dao.GoodsDao;
import dao.UserDao;
import dao.impl.GoodsDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class DeleteGoodServlet
 */
@WebServlet("/DeleteGoodServlet")
public class DeleteGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		deleteGood(request,response);
	}

	private void deleteGood(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			User user = new User();
			user = (User)request.getSession().getAttribute("curUser");
			UserDao ud = new UserDaoImpl();

			int seller_id = ud.findSeller_ID(user);

			GoodsDao gd = new GoodsDaoImpl();
			GoodsList gl = gd.findForSaleGoods();
			Goods old_good = gl.getGoodsList().get(0);
			int product_id = ud.findProduct_ID(seller_id, old_good);
			ud.deleteGoods(product_id);
			response.sendRedirect("ShowGoodsList");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
