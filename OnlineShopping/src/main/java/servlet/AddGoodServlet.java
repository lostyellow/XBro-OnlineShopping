package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;

import bean.Goods;
import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;

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
	@Override
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
			SmartUpload su = new SmartUpload();
			
			JspFactory factory = JspFactory.getDefaultFactory();
			PageContext pageContext = factory.getPageContext(this, request, response, null, false, 1024, true);
			su.initialize(pageContext);
			su.upload();
			File file = su.getFiles().getFile(0);
			String fileName = file.getFileName();
			String url = "./img/customs/" + fileName;
			file.saveAs(url, SmartUpload.SAVE_VIRTUAL);
			Request suRequest = su.getRequest();

			int seller_id = ud.findSeller_ID(user);

			Goods good = new Goods();

			String itemName = suRequest.getParameter("name");
			String itemDescription = suRequest.getParameter("detail");
			String imgURL = url;
			Float price = Float.parseFloat(suRequest.getParameter("price"));
			String number = suRequest.getParameter("batch");//生产批次号
			String date = suRequest.getParameter("date");//有效期
			Boolean isPres;
			Boolean isFrozen = false;
			if(suRequest.getParameter("option3").equals("yes")) {
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

			ud.addGoods(seller_id, good);
			response.sendRedirect("ShowGoodsList");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
