package junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bean.Goods;
import test.UpdateGoodTest;

public class UpdateGoodJUnit {
	List<Goods> goodList = new ArrayList<>();
	List<String> expectedList = new ArrayList<>();

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
