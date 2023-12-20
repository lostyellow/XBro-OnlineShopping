package dao;

import bean.Goods;
import bean.GoodsList;

public interface GoodsDao {
	/*
	 * findAllGoods()
	 * 无输入参数
	 * 返回值为商品列表类, 仅设置商品的以下信息:
	 * 	商品ID (主页面商品url)
	 * 	商品名称 (主页面商品名)
	 * 	商品图片 (主页面商品图片)
	 * 用于显示历史商品
	 * **/
	public GoodsList findAllGoods();

	/*
	 * findGoods()
	 * 输入参数为 商品id
	 * 返回值为商品对象
	 * 用于进入特定商品页面后显示详细信息
	 * **/
	public Goods findGoods(int product_id);

	//把商品冻结状态取消
	public void takeOffGood(int product_id);

	/*
	 * findForSaleGoods()
	 * 无输入参数
	 * 返回值为在售且未冻结的商品列表类, 仅设置商品的以下信息:
	 * 	商品ID (主页面商品url)
	 * 	商品名称 (主页面商品名)
	 * 	商品图片 (主页面商品图片)
	 * 用于在主页面显示商品列表
	 * **/

	public GoodsList findForSaleGoods();

	/*
	 * findOnSaleGood()
	 * 无输入参数
	 * 返回值为在售的商品列表类, 设置商品的所有信息
	 * **/
	public GoodsList findOnSaleGood();

	/*
	 * anyForSale()
	 * 无输入参数
	 * 返回值为是否允许发布新商品
	 * - 仅基线有用
	 * 用于在基线判断是否允许上新商品（逻辑为inventory<>0）
	 * **/
	public boolean anyForSale();

	public void takeOnGood(int product_id);

	/*
	 * sell()
	 * 输入参数为 商品id
	 * 无返回值
	 * 用于将对应商品库存-1
	 * **/
	public void sell(int product_id);
}
