package auto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import bean.User;


public class AutoRegisterTest {
	//Log create
	private static final Logger logger = Logger.getLogger(AutoRegisterTest.class.getName());
	
	public static String WebPath = "http://localhost:8080/OnlineShopping/register.jsp";
	private static WebDriver d;
	CsvTestData td = new CsvTestData("src/main/java/example/用户注册单元测试用例.csv");
	List<User> userList = new ArrayList<User>();
	
	public void register(){
		try {
			//Log setting
			FileHandler fileHandler = new FileHandler("src/main/java/auto/autoRegisterExecute.log");
			fileHandler.setFormatter(new SimpleFormatter());
			
			logger.addHandler(fileHandler);
			
			System.setProperty("webdriver.edge.driver", "D:\\edgedriver\\msedgedriver.exe");
			d = new EdgeDriver();
			userList.addAll(td.registerTestData());
			
			for(User user:userList) {
				//Log record
				logger.info("开始执行 测试用例");
				
				logger.info("执行步骤一:打开网站"+WebPath);
				d.get(WebPath);
				Thread.sleep(500);
				
				logger.info("执行步骤二:填写用户名: "+"autoTest");
				d.findElement(By.name("UserName")).sendKeys("autoTest");
				Thread.sleep(500);
				
				logger.info("执行步骤三:填写密码: "+"123456");
				d.findElement(By.name("Password")).sendKeys("123456");
				Thread.sleep(500);
				
				logger.info("执行步骤四:填写真实姓名: "+user.getName());
				d.findElement(By.name("Name")).sendKeys(user.getName());
				Thread.sleep(500);
				
				logger.info("执行步骤五:填写身份证件: "+user.getId_card());
				d.findElement(By.name("Id")).sendKeys(user.getId_card());
				Thread.sleep(500);
				
				logger.info("执行步骤六:勾选性别: "+"男");
				d.findElement(By.xpath("/html/body/div/form/div[2]/table/tbody/tr[5]/td[2]/input[1]")).click();
				Thread.sleep(500);
				
				logger.info("执行步骤七:填写手机号: "+user.getTele());
				d.findElement(By.name("tel")).sendKeys(user.getTele());
				Thread.sleep(500);
				
				logger.info("执行步骤八:填写邮箱: "+user.getE_mail());
				d.findElement(By.name("email")).sendKeys(user.getE_mail());
				Thread.sleep(500);
				
				logger.info("执行步骤九:填写出生年月日: "+"2002-04-04");
				//d.findElement(By.name("birthday")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2002-04-04");
				((JavascriptExecutor) d).executeScript("arguments[0].value = '2002-04-04';", d.findElement(By.name("birthday")));
				Thread.sleep(500);
				
				logger.info("执行步骤十:点击注册按钮");
				d.findElement(By.xpath("/html/body/div/form/div[2]/input")).click();
				Thread.sleep(3000);
				
				//isSuccess?
				if("http://localhost:8080/OnlineShopping/login.jsp".equals(d.getCurrentUrl())) {
					logger.info("结果分支:注册成功");
				}else {
					logger.info("结果分支:注册失败");
				}
			}
			
			d.quit();
			logger.info("结束执行测试用例");
		}catch (Exception e) {
			logger.info("发生错误，错误原因:\n"+e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		AutoRegisterTest art = new AutoRegisterTest();
		art.register();
	}
	
}
