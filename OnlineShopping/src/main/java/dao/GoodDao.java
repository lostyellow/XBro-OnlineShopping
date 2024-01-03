package dao;

import java.util.List;

import bean.Good;
import bean.GoodList;

public interface GoodDao {
    /*
     * findAllGoods()
     * 无输入参数
     * 返回值为商品列表类, 仅设置商品的以下信息:
     * 	商品ID (主页面商品url)
     * 	商品名称 (主页面商品名)
     * 	商品图片 (主页面商品图片)
     * 用于显示历史商品
     * **/
    public GoodList findAllGoods();

    /*
     * findGoods()
     * 输入参数为 商品id
     * 返回值为商品对象
     * 用于进入特定商品页面后显示详细信息
     * **/
    public Good findGoods(int product_id);

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

    public GoodList findForSaleGoods();

    /*
     * findOnSaleGood()
     * 无输入参数
     * 返回值为在售的商品列表类, 设置商品的所有信息
     * **/
    public GoodList findOnSaleGood();

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

    /*
     * 对drugs表的操作
     * */
    public int findProduct_ID(int seller_id, Good good);//查询商品ID

    public void addGoods(int seller_id, Good good);//上架商品

    public void updateGoods(Good good, int product_id);//修改商品,请封装修改的信息

    public void deleteGoods(int product_id);//下架商品，通过ID定位

    public void frozenGood(int product_id);

    public void addGoodPicture(int product_id, String img_url);//添加对应商品的一张图片,图片库加图片,数据库加url

    public void deleteGoodPicture(int product_id, String img_url);//删除对应商品的一张图片,图片库不删图片,数据库删url

    public List<String> findAllPictures(int product_id);//查找对应商品的所有图片,返回字符串列表
}
