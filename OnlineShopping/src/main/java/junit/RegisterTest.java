package junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.Register;

public class RegisterTest {
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
		assertEquals("allsuccess", new Register().register("王珊","33030320130721003X","13738341144","123456@qq.com"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test2() {
		assertEquals("telfail", new Register().register("王珊","33030320010522003X","1373834114","123456@qq.com"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test3() {
		assertEquals("idfail", new Register().register("王珊","33030320010522003","13738341144","123456@qq.com"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test4() {
		assertEquals("namefail", new Register().register("王","33030320010522003X","13738341144","123456@qq.com"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test5() {
		assertEquals("fail", new Register().register("王善","33030320010522003X","13738341144","123456qq.com"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test6() {
		assertEquals("namefail", new Register().register("王善雨欣啥","33030320010522003X","13738341144","123456@qq.com"));
    	System.out.println("测试成功！");
	}

	@Test
	public void test7() {
		assertEquals("namefail", new Register().register("Tom","33030320010522003X","13738341144","123456@qq.com"));
    	System.out.println("测试成功！");
	}
}
