package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DealList;
import dao.TransactionDao;
import dao.UserDao;
import dao.impl.TransactionDaoImpl;
import dao.impl.UserDaoImpl;

@WebServlet("/ShowDealList")
public class ShowDealList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowDealList() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        show(request, response);
    }

    private void show(HttpServletRequest request, HttpServletResponse response) {
        try {

            UserDao ud = new UserDaoImpl();
            TransactionDao td = new TransactionDaoImpl();
            DealList dl = new DealList();
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            dl = td.findDealsByProduct_id(product_id);
            request.getSession().setAttribute("dealList", dl);
            response.sendRedirect("record.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        doGet(request, response);
    }

}
