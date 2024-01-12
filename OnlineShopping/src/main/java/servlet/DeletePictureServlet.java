package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodDao;
import dao.impl.GoodDaoImpl;

/**
 * Servlet implementation class DeletePictureServlet
 */
@WebServlet("/DeletePictureServlet")
public class DeletePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		deletePicture(request, response);
	}
	
	private void deletePicture(HttpServletRequest request, HttpServletResponse response) {
		try {
	    	request.setCharacterEncoding("utf-8");
	        response.setCharacterEncoding("utf-8");
			String pictureURL = request.getParameter("url");
			String product_id = request.getParameter("product_id");
			GoodDao gd = new GoodDaoImpl();
			gd.deleteGoodPicture(pictureURL);
			response.sendRedirect("good_manage.jsp?product_id=" + product_id);
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
