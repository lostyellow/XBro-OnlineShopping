package test;

import bean.Good;
import dao.GoodDao;
import dao.UserDao;
import dao.impl.GoodDaoImpl;
import dao.impl.UserDaoImpl;

public class AddGoodTest {
	public String AddGood(Good good) {
		try {
			UserDao ud = new UserDaoImpl();
			GoodDao gd = new GoodDaoImpl();
			
			int seller_id = 1;

			gd.addGoods(seller_id, good);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "failed";
		}
	}
	
}
