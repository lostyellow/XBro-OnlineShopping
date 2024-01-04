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
    
    public Good getGoodByName(String product_name) {
    	try {
			//here
    		for (Good good : goodsList) {
                if (good.getItemName().equalsIgnoreCase(product_name)) {
                    return good;
                }
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return null;
    }
}
