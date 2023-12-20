package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodDao;
import dao.UserDao;
import dao.impl.GoodDaoImpl;
import dao.impl.UserDaoImpl;
import bean.Good;
import bean.User;

/**
 * Servlet implementation class AddGoodServlet
 */
@WebServlet("/AddGoodServlet")
public class AddGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		addGood(request,response);
	}

	private void addGood(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			User user = new User();
			user = (User)request.getSession().getAttribute("curUser");
			UserDao ud = new UserDaoImpl();
			GoodDao gd = new GoodDaoImpl();
			
			int seller_id = ud.findSeller_ID(user);
			
			Good good = new Good();
			
			String itemName = request.getParameter("name");
			String itemDescription = request.getParameter("detail");
			String imgURL = "./img/yp.png";
			Float price = Float.parseFloat(request.getParameter("price"));
			String number = request.getParameter("batch");//生产批次号
			String date = request.getParameter("date");//有效期
			Boolean isPres;
			Boolean isFrozen = false;
			if(request.getParameter("option3").equals("yes")) {
				isPres = true;
			}else {
				isPres = false;
			}
			
			good.setItemName(itemName);
			good.setItemDescription(itemDescription);
			good.setImgURL(imgURL);
			good.setPrice(price);
			good.setNumber(number);
			good.setDate(date);
			good.setIsPres(isPres);
			good.setIsFrozen(isFrozen);
			
			gd.addGoods(seller_id, good);
			response.sendRedirect("ShowGoodsList");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
