package test;

import bean.Goods;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UpdateGoodTest {
	public String UpdateGood(Goods good) {
		try {
			UserDao ud = new UserDaoImpl();

			ud.updateGoods(good, 11);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "failed";
		}

	}
}
