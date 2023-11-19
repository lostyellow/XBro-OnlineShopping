package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bean.Goods;
import test.AddGoodTest;
import test.UpdateGoodTest;

public class UpdateGoodJUnit {
	List<Goods> goodList = new ArrayList<Goods>();
	List<String> expectedList = new ArrayList<String>();
	
	@Before
	public void dataRead() {
		CsvTestData td = new CsvTestData("src/main/java/example/修改商品单元测试用例.csv");
		
		goodList.addAll(td.addGoodReader());
		expectedList.addAll(td.expectedReader());
	}

	@Test
	public void test() {
		int count = 0;
		for (Goods good : goodList) {
			assertEquals(expectedList.get(count), new UpdateGoodTest().UpdateGood(good));
			count++;
		}
	}

}
