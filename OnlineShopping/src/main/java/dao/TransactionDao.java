package dao;

import bean.Deal;
import bean.DealList;

public interface TransactionDao {

    /*对transacions表的操作*/
    /*
     * 购买商品操作，需要商品信息以及交易状态，交易时间，交易金额（建议增加交易订单类进行封装）
     * **/
    public int findTrans_ID(int product_id, String time);//通过商品id查找订单号

    public void purchase(Deal deal,int seller_id);//根据buy页面的购买按钮实现交易商品信息以及时间的那个的上传。

    public void updateTrans(int trans_id, String status);//更新订单状态,没有删除,交易失败则状态设置为“失败”

    public void closedeal(int product_id, int transaction_id);//把某个商品所有的订单的状态变成end，成功的那个订单设为success

    public int unfreezeGood(int transaction_id);//把订单的状态变回wait并且把product_id返回

    public DealList findAllDeal();//寻找所有订单

    public DealList findDealsByProduct_id(int product_id);//寻找某个商品的所有订单

	public Boolean IsExistIngDeal(int product_id);
	
	public DealList findDealsByBuyer_id(int buyer_id);//查看单个用户的所有订单

}
