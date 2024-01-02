package junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.Login;

public class LoginTest {

	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	System.out.println("this is beforeclass");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    	System.out.println("this is afterclass");
    }

    @Before
    public void setUp() throws Exception {
    	System.out.println("-------样例前--------");
    }

    @After
    public void tearDown() throws Exception {
    	System.out.println("-------样例后--------");
    }

	@Test
	public void test1() {
		assertEquals("success", new Login().login("hanyuyellow","123456"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test2() {
		assertEquals("fail", new Login().login("hanyuyellow","123"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test3() {
		assertEquals("fail", new Login().login("hanyuyello","123456"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test4() {
		assertEquals("fail", new Login().login("","123456"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test5() {
		assertEquals("fail", new Login().login("hanyuyellow",""));
    	System.out.println("测试成功！");
	}

	@Test
	public void test6() {
		assertEquals("fail", new Login().login("",""));
    	System.out.println("测试成功！");
	}

}
