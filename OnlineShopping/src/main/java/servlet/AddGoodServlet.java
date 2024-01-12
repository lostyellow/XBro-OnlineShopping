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

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;

import dao.CategoryDao;
import dao.GoodDao;
import dao.UserDao;
import dao.impl.CategoryDaoImpl;
import dao.impl.GoodDaoImpl;
import dao.impl.UserDaoImpl;
import bean.Good;
import bean.User;

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
        addGood(request, response);
    }

    private void addGood(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
        	request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            User user = new User();
            user = (User) request.getSession().getAttribute("curUser");
            UserDao ud = new UserDaoImpl();
            GoodDao gd = new GoodDaoImpl();

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
            Request suRequest = su.getRequest();

            int seller_id = ud.findUser_ID(user);

            Good good = new Good();

            String itemName = suRequest.getParameter("name");
            String itemDescription = suRequest.getParameter("detail");
            String imgURL = url;
            Float price = Float.parseFloat(suRequest.getParameter("price"));
            String number = suRequest.getParameter("batch");//生产批次号
            String date = suRequest.getParameter("date");//有效期
            Boolean isPres;
            Boolean isFrozen = false;
            if (suRequest.getParameter("option3").equals("yes")) {
                isPres = true;
            } else {
                isPres = false;
            }
            
            int parentID = 1;
            if(!isPres) {
            	parentID = 2;
            }
            String subCategory = suRequest.getParameter("subCategory");
            
            CategoryDao cd = new CategoryDaoImpl();
            int subID = cd.findSubIDBySubName(subCategory);
            int PSID = cd.findPSIDByPIDAndSID(parentID, subID);
            
            int inventory = Integer.parseInt(suRequest.getParameter("inventory"));

            good.setItemName(itemName);
            good.setItemDescription(itemDescription);
            // good.setImgURL(imgURL);
            good.setPrice(price);
            good.setNumber(number);
            good.setDate(date);
            good.setIsPres(isPres);
            good.setIsFrozen(isFrozen);
            good.setInventory(inventory);
            good.setPSID(PSID);

            gd.addGoods(seller_id, good);
            int product_id = gd.findProduct_ID(seller_id, good);
            gd.addGoodPicture(product_id, imgURL);
            response.sendRedirect("main.jsp");
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
