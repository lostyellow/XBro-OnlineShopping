package junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import bean.Good;
import test.AddGoodTest;

public class AddGoodJUnit {
    List<Good> goodList = new ArrayList<Good>();
    List<String> expectedList = new ArrayList<String>();

    @Before
    public void dataRead() {
        CsvTestData td = new CsvTestData("src/main/java/example/上架商品单元测试用例.csv");

        goodList.addAll(td.addGoodReader());
        expectedList.addAll(td.expectedReader());
    }

    @Test
    public void test() {
        int count = 0;
        for (Good good : goodList) {
            assertEquals(expectedList.get(count), new AddGoodTest().AddGood(good));
            count++;
        }
    }

}
