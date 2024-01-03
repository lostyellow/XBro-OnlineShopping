package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dao.TransactionDao;
import dao.UserDao;
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
    
    protected void CreatDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		Good g = (Good)request.getSession().getAttribute("goods");
			String time = request.getParameter("date");
			User curu = (User)request.getSession().getAttribute("curUser");
    		
			Deal deal = new Deal();
			deal.setProduct_id(g.getId());
			deal.setStatus("wait");
			deal.setTime(time);
			deal.setAmount(g.getPrice());
			
			UserDao ud = new UserDaoImpl();
			TransactionDao td = new TransactionDaoImpl();
			
			td.purchase(deal,ud.findSeller_ID(curu));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
    
    protected void SubmitInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		UserDao ud = new UserDaoImpl();
    		TransactionDao td = new TransactionDaoImpl();
    		OrderDao od = new OrderDaoImpl();
    		Good g = (Good)request.getSession().getAttribute("goods");
    		
    		Integer id = td.findTrans_ID(g.getId(), "wait");
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
			response.sendRedirect("ShowGoodsList");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		String method = request.getParameter("method");
		if("submitinfo".equals(method)) {
			CreatDeal(request, response);
			SubmitInformation(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
