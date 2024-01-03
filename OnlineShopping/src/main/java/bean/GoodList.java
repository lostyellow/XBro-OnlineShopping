package bean;

import java.util.ArrayList;
import java.util.List;

public class GoodList {
    List<Good> goodsList = new ArrayList<Good>();

    public void add(Good g) {
        this.goodsList.add(g);
    }

    public List<Good> getGoodsList() {
        return this.goodsList;
    }

    public int getLength() {
        return this.goodsList.size();
    }
}
