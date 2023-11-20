package dao;

import bean.*;

public interface UserDao{
/*对Users表的操作*/
public void register(User user);//注册功能
public Users findAll();//遍历数据库查找账户密码
public Boolean checkLogin(String username, String password);//username不能重复
public int findSeller_ID(User user);//查找卖家ID
public void changePwd(int userId, String newPwd);//查看旧密码是否正确
public String findSeller_PWD(String realname, String id_card);//查找卖家密码
/*对drugs表的操作*/
public int findProduct_ID(int seller_id,Goods good);//查询商品ID
public void addGoods(int seller_id,Goods good);//上架商品
public void updateGoods(Goods good,int product_id);//修改商品,请封装修改的信息
public void deleteGoods(int product_id);//下架商品，通过ID定位
public void frozenGood(int product_id);
/*对transacions表的操作*/
/*
 * 购买商品操作，需要商品信息以及交易状态，交易时间，交易金额（建议增加交易订单类进行封装）
 * **/
public int findTrans_ID(int product_id,String time);//通过商品id查找订单号
public void purchase(Deal deal);//根据buy页面的购买按钮实现交易商品信息以及时间的那个的上传。
public void updateTrans(int trans_id,String status);//更新订单状态,没有删除,交易失败则状态设置为“失败”
public int closedeal(int transaction_id);//把订单的状态变成end并且把product_id返回
/*对order_details表的操作
 * 
 */
public DealList findAllDeal();//寻找所有订单
public Details findDetails(int transaction_id);//寻找所有详细信息
public void submitdeal(Buyer buyer);//上传买家提交订单信息
}
