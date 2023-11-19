package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.DetailsList;
import dao.UserDao;
import dao.impl.UserDaoImpl;
@WebServlet("/ShowDetailsList")
public class ShowDetailsList extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ShowDetailsList() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		show(request,response);
	}
	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			UserDao ud = new UserDaoImpl();
			DetailsList dl = new DetailsList();
			dl = ud.findDetails(1);
			request.getSession().setAttribute("detailsList",dl);
		    response.sendRedirect("record.jsp");
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