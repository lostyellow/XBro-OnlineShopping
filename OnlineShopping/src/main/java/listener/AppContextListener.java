package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.DatabaseDao;
import dao.impl.DatabaseDaoImpl;

@WebListener
public class AppContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("quit success");
//		DatabaseDao dd = new DatabaseDaoImpl();
//		dd.destroyDB();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO 自动生成的方法存根
		DatabaseDao dd = new DatabaseDaoImpl();
		dd.createDB();
	}
	
}
