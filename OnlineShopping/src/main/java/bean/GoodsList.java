package bean;

import java.util.ArrayList;
import java.util.List;

public class GoodsList {
	List<Goods> goodsList = new ArrayList<>();

	public void add(Goods g) {
		this.goodsList.add(g);
	}

	public List<Goods> getGoodsList() {
		return this.goodsList;
	}

	public int getLength() {
		return this.goodsList.size();
	}
}
