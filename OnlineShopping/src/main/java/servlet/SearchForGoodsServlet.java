package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodList;
import dao.GoodDao;
import dao.impl.GoodDaoImpl;

@WebServlet("/SearchForGoodsServlet")
public class SearchForGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchForGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void findGoodsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name = request.getParameter("goodName");
		GoodDao gd = new GoodDaoImpl();
		GoodList gl = new GoodList();
		if(name.equals("")) {
			gl =  gd.findForSaleGoods();
		}else if(gd.searchForGoodByName(name) != null) {
			gl = gd.searchForGoodByName(name);
		}else {
			request.getSession().invalidate();
			response.sendRedirect("main.jsp");
		}
		request.getSession().setAttribute("goodsList", gl);
		response.sendRedirect("main.jsp");
	}
    
    protected void selectGoodsByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String medicineType = request.getParameter("medicineType");
		GoodDao gd= new GoodDaoImpl();
		GoodList gl = new GoodList();
		if("prescription".equals(medicineType)) {
			gl = gd.selectGoodsByTypes(true);
		}else if("non-prescription".equals(medicineType)) {
			gl = gd.selectGoodsByTypes(false);
		}
		request.getSession().setAttribute("goodsList", gl);
		response.sendRedirect("main.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("namesearch".equals(method)) {
			findGoodsByName(request, response);
		}else if("select".equals(method)){
			selectGoodsByType(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		doGet(request, response);
	}

}
