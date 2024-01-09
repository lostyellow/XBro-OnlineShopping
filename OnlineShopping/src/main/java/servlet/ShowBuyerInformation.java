
package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.UserList;
import dao.UserDao;
import dao.impl.UserDaoImpl;
@WebServlet("/ShowBuyerInformation")
public class ShowBuyerInformation extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ShowBuyerInformation() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		show(request,response);
	}
	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserDao ud = new UserDaoImpl();
			UserList ul = new UserList();
			ul = ud.findBuyer_information();
			request.getSession().setAttribute("userList",ul);
		    response.sendRedirect("buyer_information.jsp");
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

