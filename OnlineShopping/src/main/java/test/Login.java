package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class Login {
	public String login(String username, String password) {
		try {
			UserDao ud = new UserDaoImpl();
			if(ud.checkLogin(username, password)) {
				return "success";
			}
			else {
				return "fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
