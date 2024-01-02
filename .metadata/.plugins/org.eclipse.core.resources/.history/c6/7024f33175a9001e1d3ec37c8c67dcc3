package test;

import bean.Goods;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class AddGoodTest {
	public String AddGood(Goods good) {
		try {
			UserDao ud = new UserDaoImpl();

			int seller_id = 1;

			ud.addGoods(seller_id, good);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "failed";
		}
	}

}
