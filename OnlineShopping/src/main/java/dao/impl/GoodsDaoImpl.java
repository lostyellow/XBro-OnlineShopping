package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dao.GoodsDao;
import bean.Goods;
import bean.GoodsList;

public class GoodsDaoImpl implements GoodsDao {
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String URL = "jdbc:sqlite:xbro.db";
	public static final String USER = "root";
	public static final String PWD = "root";
	
	@Override
	public GoodsList findAllGoods() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			GoodsList goodsList = new GoodsList();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("product_id"));
				goods.setSellerId(rs.getInt("seller_id"));
				goods.setItemName(rs.getString("product_name"));
				goods.setItemDescription(rs.getString("product_description"));
				goods.setImgURL(rs.getString("product_image"));
				goods.setPrice(rs.getFloat("product_price"));
				goods.setNumber(rs.getString("batch_number"));
				goods.setDate(rs.getString("expiration_date"));
				goods.setIsPres(rs.getBoolean("prescription_required"));
				goods.setIsFrozen(rs.getBoolean("is_frozen"));
				goodsList.add(goods);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return goodsList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Goods findGoods(int product_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs where product_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(product_id));
			ResultSet rs = ps.executeQuery();

			Goods goods = new Goods();
			if(rs.next()) {
//				goods = new Goods();
				goods.setId(rs.getInt("product_id"));
				goods.setSellerId(rs.getInt("seller_id"));
				goods.setItemName(rs.getString("product_name"));
				goods.setItemDescription(rs.getString("product_description"));
				goods.setImgURL(rs.getString("product_image"));
				goods.setPrice(rs.getFloat("product_price"));
				goods.setNumber(rs.getString("batch_number"));
				goods.setDate(rs.getString("expiration_date"));
				goods.setIsPres(rs.getBoolean("prescription_required"));
				goods.setIsFrozen(rs.getBoolean("is_frozen"));
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return goods;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void takeOffGood(int product_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "update drugs "
			+ "set is_frozen = ? "
			+ "where product_id = ?";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, false);
			ps.setInt(2, product_id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public GoodsList findForSaleGoods() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs where is_frozen = ? and inventory <> 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, false);
			ResultSet rs = ps.executeQuery();
			
			GoodsList goodsList = new GoodsList();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getInt("product_id"));
				goods.setSellerId(rs.getInt("seller_id"));
				goods.setItemName(rs.getString("product_name"));
				goods.setItemDescription(rs.getString("product_description"));
				goods.setImgURL(rs.getString("product_image"));
				goods.setPrice(rs.getFloat("product_price"));
				goods.setNumber(rs.getString("batch_number"));
				goods.setDate(rs.getString("expiration_date"));
				goods.setIsPres(rs.getBoolean("prescription_required"));
				goods.setIsFrozen(rs.getBoolean("is_frozen"));
				goodsList.add(goods);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return goodsList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void takeOnGood(int product_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "update drugs "
			+ "set is_frozen = ? "
			+ "where product_id = ?";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, false);
			ps.setInt(2, product_id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public void sell(int product_id) {
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "update drugs "
			+ "set inventory = 0 "
			+ "where product_id = ?";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// 仅基线使用
	@Override
	public boolean anyForSale() {
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs where inventory <> 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			boolean res;
			if(rs.next()) res = true;
			else res = false;
			
			ps.close();
			conn.close();
			rs.close();
			
			return res;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
