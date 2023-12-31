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

import dao.CategoryDao;
import dao.GoodDao;
import dao.UserDao;
import dao.impl.CategoryDaoImpl;
import dao.impl.GoodDaoImpl;
import dao.impl.GoodDaoImpl;
import dao.impl.UserDaoImpl;
import bean.Good;
import bean.GoodList;
import bean.User;
import bean.Good;
import bean.GoodList;
import bean.User;

/**
 * Servlet implementation class UpdateGoodServlet
 */
@WebServlet("/UpdateGoodServlet")
public class UpdateGoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        UpdateGood(request, response);
    }

    private void UpdateGood(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            User user = new User();
            user = (User) request.getSession().getAttribute("curUser");
            UserDao ud = new UserDaoImpl();
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

            int seller_id = ud.findSeller_ID(user);

            Good good = new Good();

            String itemName = suRequest.getParameter("name");
            String itemDescription = suRequest.getParameter("detail");
            String imgURL = url;
            Float price = Float.parseFloat(suRequest.getParameter("price"));
            String number = suRequest.getParameter("batch");//生产批次号
            String date = suRequest.getParameter("date");//有效期
            Boolean isPres;
            Boolean isFrozen;
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
            
            if (suRequest.getParameter("option4").equals("yes")) {
                isFrozen = true;
            } else {
                isFrozen = false;
            }
            
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

            
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            GoodDao gd = new GoodDaoImpl();
            gd.updateGoods(good, product_id);
            gd.addGoodPicture(product_id, imgURL);
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
