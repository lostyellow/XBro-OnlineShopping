package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

import bean.Good;
import bean.User;
import dao.CategoryDao;
import dao.GoodDao;
import dao.UserDao;
import dao.impl.CategoryDaoImpl;
import dao.impl.GoodDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class AddPictureServlet
 */
@WebServlet("/AddPictureServlet")
public class AddPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		addPicture(request, response);
	}
	
	private void addPicture(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new User();
            user = (User) request.getSession().getAttribute("curUser");
            UserDao ud = new UserDaoImpl();
            
            // !!!不允许访问不是自己发布的商品, 会跳转到登录页面
            int seller_id = ud.findSeller_ID(user);
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            GoodDao gd = new GoodDaoImpl();
            Good searchGood = gd.findGoods(product_id);
            if(!searchGood.getSellerId().equals(seller_id)) {
            	response.sendRedirect("login.jsp");
            }
            
            SmartUpload su = new SmartUpload();
            JspFactory factory = JspFactory.getDefaultFactory();
            PageContext pageContext = factory.getPageContext(this, request, response, null, false, 1024, true);
            su.initialize(pageContext);
            su.upload();
            File file = su.getFiles().getFile(0);
            String fileName = file.getFileName();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dateTime = new Date();
            String formatDateTime = sdf.format(dateTime);
            
            String url = "./img/customs/" + formatDateTime + "_" + fileName;
            file.saveAs(url, SmartUpload.SAVE_VIRTUAL);
//            Request suRequest = su.getRequest();

            String imgURL = url;


            gd.addGoodPicture(product_id, imgURL);
            response.sendRedirect("good_manage.jsp?method=1&product_id="+Integer.toString(product_id));
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
