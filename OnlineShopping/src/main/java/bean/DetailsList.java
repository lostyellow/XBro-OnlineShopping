package bean;

import java.util.ArrayList;
import java.util.List;

public class DetailsList {
	List<Details> detailsList = new ArrayList<Details>();
	
	public void add(Details d) {
		this.detailsList.add(d);
	}
	public List<Details> getDetails(){
		return this.detailsList;
	}
}