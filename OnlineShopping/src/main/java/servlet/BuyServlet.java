package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodDao;
import dao.OrderDao;
import dao.TransactionDao;
import dao.UserDao;
import dao.impl.GoodDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.TransactionDaoImpl;
import dao.impl.UserDaoImpl;
import dao.OrderDao;
import dao.TransactionDao;
import dao.UserDao;
import dao.impl.OrderDaoImpl;
import dao.impl.TransactionDaoImpl;
import dao.impl.UserDaoImpl;
import bean.Buyer;
import bean.Deal;
import bean.Good;
import bean.User;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuyServlet() {
        super();
    }
    
    protected Integer CreatDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		GoodDao gd = new GoodDaoImpl();
    		Good g = gd.findGoods(Integer.parseInt(request.getParameter("product_id")));
			String time = request.getParameter("date");
			Integer sellerId = g.getSellerId();
    		
			Deal deal = new Deal();
			deal.setProduct_id(g.getId());
			deal.setStatus("wait");
			deal.setTime(time);
			deal.setAmount(g.getPrice());
			
			UserDao ud = new UserDaoImpl();
			TransactionDao td = new TransactionDaoImpl();

			// !!!未登录购买时 buyerId 设置成 0
			Integer buyerId = 0;
			if(request.getParameter("buyer_id") != null) buyerId = Integer.parseInt(request.getParameter("buyer_id"));
			return td.purchase(deal, buyerId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return null;
	}
    
    protected void SubmitInformation(HttpServletRequest request, HttpServletResponse response, Integer transId) throws ServletException, IOException {
    	try {
//    		UserDao ud = new UserDaoImpl();
    		TransactionDao td = new TransactionDaoImpl();
    		OrderDao od = new OrderDaoImpl();
    		GoodDao gd = new GoodDaoImpl();
    		Good g = gd.findGoods(Integer.parseInt(request.getParameter("product_id")));
    		
//    		Integer id = td.findTrans_ID(g.getId(), "wait");
    		Integer id = transId;
			String realname = request.getParameter("realname");
			String address = request.getParameter("address");
			String dealtime = request.getParameter("date");
			String idcard = request.getParameter("idcard");
			String phone = request.getParameter("phone");
			String gender = request.getParameter("option");
			String remark =request.getParameter("remark");
		
			Buyer buyer = new Buyer();
			buyer.setId(id);
			buyer.setBuyer_name(realname);
			buyer.setAddress(address);
			buyer.setAppointment_time(dealtime);
			buyer.setBuyer_identification(idcard);
			buyer.setBuyer_gender(gender);
			buyer.setBuyer_phone_number(phone);
			buyer.setText(remark);
			
			od.submitdeal(buyer);
			response.sendRedirect("main.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Integer transId = CreatDeal(request, response);
		SubmitInformation(request, response, transId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
