package dao;

import java.util.List;

import bean.*;

public interface GoodsDao {
	/*
	 * findAllGoods()
	 * 无输入参数
	 * 返回值为商品列表类, 仅设置商品的以下信息:
	 * 	商品ID (主页面商品url)
	 * 	商品名称 (主页面商品名)
	 * 	商品图片 (主页面商品图片)
	 * 用于在主页面显示商品列表
	 * **/
	public GoodsList findAllGoods();
	
	/*
	 * findGoods()
	 * 输入参数为 商品id
	 * 返回值为商品对象
	 * 用于进入特定商品页面后显示详细信息
	 * **/
	public Goods findGoods(int product_id);
}
