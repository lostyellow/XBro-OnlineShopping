package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Good;
import bean.GoodList;
import bean.User;
import dao.GoodDao;
import dao.UserDao;
import dao.impl.GoodDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class DeleteGoodServlet
 */
@WebServlet("/DeleteGoodServlet")
public class DeleteGoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        deleteGood(request, response);
    }

    private void deleteGood(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        try {
            User user = null;
            user = (User) request.getSession().getAttribute("curUser");
            UserDao ud = new UserDaoImpl();

            int seller_id = ud.findSeller_ID(user);

            GoodDao gd = new GoodDaoImpl();
            GoodList gl = gd.findForSaleGoods();
            String product_name = request.getParameter("product_name");
            Good old_good = gl.getGoodByName(product_name);
            int product_id = gd.findProduct_ID(seller_id, old_good);
            gd.deleteGoods(product_id);
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
