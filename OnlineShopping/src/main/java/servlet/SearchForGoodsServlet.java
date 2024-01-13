package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Good;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
        String words = request.getParameter("words");
        // 根据 psid 从数据库获取商品信息
        GoodDao gd = new GoodDaoImpl();
        List<Good> goodList = (ArrayList<Good>)gd.searchForGoodByName(words);
        // 将商品信息转换为 HTML 格式
        StringBuilder htmlResponse = new StringBuilder();
        
        for (Good good : goodList) {
        	List<String> pictures = gd.findAllPictures(good.getId());
        	if(pictures.size()>0) {
	            htmlResponse.append("<div class='yp'>")
	            			.append("<a href='ShowGoodsDetail?product_id=").append(good.getId()).append("'>")
	            			.append("<img src='").append(pictures.get(0)).append("' alt='商品图片' />")
	                        .append("</a>")
	            			.append("<a href='ShowGoodsDetail?product_id=").append(good.getId()).append("'>")
	                        .append("<p>").append(good.getItemName()).append("</p>")
	                        .append("</a>")
	                        .append("</div>");
        	} else {
        		htmlResponse.append("<div class='yp'>")
			    			.append("<a href='ShowGoodsDetail?product_id=").append(good.getId()).append("'>")
			                .append("</a>")
			    			.append("<a href='ShowGoodsDetail?product_id=").append(good.getId()).append("'>")
			                .append("<p>").append(good.getItemName()).append("</p>")
			                .append("</a>")
			                .append("</div>");
        	}
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(htmlResponse.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
