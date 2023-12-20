package dao;

import bean.Buyer;
import bean.Details;

public interface OrderDao {

	/*对order_details表的操作
	 * 
	 */
	public Details findDetails(int transaction_id);//寻找所有详细信息

	public void submitdeal(Buyer buyer);//上传买家提交订单信息

}
