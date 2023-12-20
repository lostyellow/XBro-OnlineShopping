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
import bean.GoodList;
import bean.User;

/**
 * Servlet implementation class UpdateGoodServlet
 */
@WebServlet("/UpdateGoodServlet")
public class UpdateGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UpdateGood(request,response);
	}

	private void UpdateGood(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			User user = new User();
			user = (User)request.getSession().getAttribute("curUser");
			UserDao ud = new UserDaoImpl();
			
			int seller_id = ud.findSeller_ID(user);
			
			Good good = new Good();
			
			String itemName = request.getParameter("name");
			String itemDescription = request.getParameter("detail");
			String imgURL = "/OnlineShopping/src/main/webapp/img/yp.jpg";
			Float price = Float.parseFloat(request.getParameter("price"));
			String number = request.getParameter("batch");//生产批次号
			String date = request.getParameter("date");//有效期
			Boolean isPres;
			Boolean isFrozen;
			if(request.getParameter("option3").equals("yes")) {
				isPres = true;
			}else {
				isPres = false;
			}
			if(request.getParameter("option4").equals("yes")) {
				isFrozen = true;
			}else {
				isFrozen = false;
			}
			
			good.setItemName(itemName);
			good.setItemDescription(itemDescription);
			good.setImgURL(imgURL);
			good.setPrice(price);
			good.setNumber(number);
			good.setDate(date);
			good.setIsPres(isPres);
			good.setIsFrozen(isFrozen);
			
			int product_id = 1;
			/*
			 * 需要旧的商品信息，从获取功能得到封装old_good
			 * **/
			//product_id = ud.findProduct_ID(seller_id, old_good);
			
			// 以下是基线内获取单个商品的id，若有多个商品则获取的是第一个
			GoodDao gd = new GoodDaoImpl();
			GoodList gl = gd.findAllGoods();
			product_id = gl.getGoodsList().get(0).getId();
			
			gd.updateGoods(good, product_id);
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
