package bean;

import java.util.ArrayList;
import java.util.List;

public class DealList {
	List<Deal> dealList = new ArrayList<Deal>();
	
	public void add(Deal d) {
		this.dealList.add(d);
	}
	public List<Deal> getDeals(){
		return this.dealList;
	}
}
