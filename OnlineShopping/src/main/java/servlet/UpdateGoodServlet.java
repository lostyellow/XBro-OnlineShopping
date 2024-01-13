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
        	request.setCharacterEncoding("utf-8");
        	response.setCharacterEncoding("utf-8");
            User user = new User();
            user = (User) request.getSession().getAttribute("curUser");
            UserDao ud = new UserDaoImpl();
            
            // !!!不允许访问不是自己发布的商品, 会跳转到登录页面
            int seller_id = user.getId();
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            GoodDao gd = new GoodDaoImpl();
            Good searchGood = gd.findGoods(product_id);
            if(!searchGood.getSellerId().equals(seller_id)) {
            	response.sendRedirect("login.jsp");
            }

            Good good = new Good();

            String itemName = request.getParameter("name");
            String itemDescription = request.getParameter("detail");
//            String imgURL = url;
            Float price = Float.parseFloat(request.getParameter("price"));
            String number = request.getParameter("batch");//生产批次号
            String date = request.getParameter("date");//有效期
            Boolean isPres;
            Boolean isFrozen;
            if (request.getParameter("option3").equals("yes")) {
                isPres = true;
            } else {
                isPres = false;
            }
            
            int parentID = 1;
            if(!isPres) {
            	parentID = 2;
            }
            String subCategory = request.getParameter("subCategory");
            System.out.println(subCategory);
            CategoryDao cd = new CategoryDaoImpl();
            int subID = cd.findSubIDBySubName(subCategory);
            int PSID = cd.findPSIDByPIDAndSID(parentID, subID);
            System.out.println(parentID);
            System.out.println(subID);
            System.out.println(PSID);
            if (request.getParameter("option4").equals("yes")) {
                isFrozen = true;
            } else {
                isFrozen = false;
            }
            
            int inventory = Integer.parseInt(request.getParameter("inventory"));

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

            
            gd.updateGoods(good, product_id);
//            gd.addGoodPicture(product_id, imgURL);
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
    	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

}
