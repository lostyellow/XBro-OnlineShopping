package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodDao;
import dao.TransactionDao;
import dao.UserDao;
import dao.impl.GoodDaoImpl;
import dao.impl.TransactionDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class AgreeServlet
 */
@WebServlet("/AgreeServlet")
public class AgreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgreeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		agree(request,response);
	}

	private void agree(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			int transaction_id = Integer.parseInt(request.getParameter("transaction_id"));
			UserDao ud = new UserDaoImpl();
			GoodDao gd = new GoodDaoImpl();
			TransactionDao td = new TransactionDaoImpl();
			td.updateTrans(transaction_id, "ing");
			gd.frozenGood(product_id);
			response.sendRedirect("back_stage.jsp");
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
