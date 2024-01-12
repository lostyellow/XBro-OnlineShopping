package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.DealList;
import bean.User;
import dao.TransactionDao;
import dao.impl.TransactionDaoImpl;
@WebServlet("/ShowBuyerDeal")
public class ShowBuyerDeal extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ShowBuyerDeal() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		if("userCheck".equals(method)) { // 买家查看自己信息
//			TransactionDao td = new TransactionDaoImpl();
//			DealList dl = new DealList();
//			User user = (User)request.getSession().getAttribute("curUser");
//			int buyer_id = user.getId();
//			dl = td.findDealsByBuyer_id(buyer_id);
//			request.getSession().setAttribute("dealList",dl);
			User curUser = (User)request.getSession().getAttribute("curUser");
		    response.sendRedirect("record2.jsp?buyer_id=" + curUser.getId());
		}else { // 卖家查看买家信息
//		    show(request,response);
			int buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
			response.sendRedirect("record2.jsp?buyer_id=" + buyer_id);
		}
		
		
	}
	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
	    	request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
			TransactionDao td = new TransactionDaoImpl();
			DealList dl = new DealList();
			int buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
//			dl = td.findDealsByBuyer_id(product_id);
//			request.getSession().setAttribute("dealList",dl);
		    response.sendRedirect("record2.jsp" + buyer_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
}