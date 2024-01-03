package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.DealList;
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
		show(request,response);
	}
	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			TransactionDao td = new TransactionDaoImpl();
			DealList dl = new DealList();
			int product_id = Integer.parseInt(request.getParameter("buyer_id"));
			dl = td.findDealsByBuyer_id(product_id);
			request.getSession().setAttribute("dealList",dl);
		    response.sendRedirect("record2.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		doGet(request, response);
	}
	
}