package test;

import bean.Good;
import bean.GoodList;
import bean.User;
import dao.GoodDao;
import dao.UserDao;
import dao.impl.GoodDaoImpl;
import dao.impl.UserDaoImpl;

public class UpdateGoodTest {
	public String UpdateGood(Good good) {
		try {
			UserDao ud = new UserDaoImpl();
			GoodDao gd = new GoodDaoImpl();
			
			gd.updateGoods(good, 11);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "failed";
		}

	}
}
