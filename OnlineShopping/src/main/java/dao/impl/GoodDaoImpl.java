package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dao.GoodDao;
import bean.Good;
import bean.GoodList;

public class GoodDaoImpl implements GoodDao {
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String URL = "jdbc:sqlite:xbro.db";
	public static final String USER = "root";
	public static final String PWD = "root";
	
	@Override
	public GoodList findAllGoods() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			GoodList goodsList = new GoodList();
			while(rs.next()) {
				Good goods = new Good();
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
	public Good findGoods(int product_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs where product_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(product_id));
			ResultSet rs = ps.executeQuery();

			Good goods = new Good();
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
	public GoodList findForSaleGoods() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs where is_frozen = ? and inventory <> 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, false);
			ResultSet rs = ps.executeQuery();
			
			GoodList goodsList = new GoodList();
			while(rs.next()) {
				Good goods = new Good();
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
			+ "set inventory = inventory - 1 "
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
	
	@Override
	public boolean isSoldOut(int product_id) {
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select inventory from drugs where product_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			ResultSet rs = ps.executeQuery();
			
			boolean SoldOut;
			int inventory = 1;
			try {
				rs.next();
				inventory = rs.getInt("inventory");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (inventory == 0) {
				SoldOut = true;
			}else {
				SoldOut = false;
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return SoldOut;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public GoodList findOnSaleGood() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select * from drugs where inventory <> 0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			GoodList goodsList = new GoodList();
			while(rs.next()) {
				Good goods = new Good();
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
	public int findProduct_ID(int seller_id,Good good) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			
			String sql = "select product_id from drugs where seller_id=? and product_name=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, seller_id);
			ps.setString(2, good.getItemName());
			ResultSet rs = ps.executeQuery();
			
			int product_id = 0;
			if(rs.next()) {
				product_id = rs.getInt(1);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return product_id;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addGoods(int seller_id,Good good) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into drugs"
					+ "(seller_id,product_name,product_description,product_image,product_price,"
					+ "batch_number,expiration_date,prescription_required,is_frozen,inventory) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, seller_id);
			ps.setString(2, good.getItemName());
			ps.setString(3, good.getItemDescription());
			ps.setString(4, good.getImgURL());
			ps.setFloat(5, good.getPrice());
			ps.setString(6, good.getNumber());
			ps.setString(7, good.getDate());
			ps.setBoolean(8, good.getIsPres());
			ps.setBoolean(9, good.getIsFrozen());
			ps.setInt(10, good.getInventory());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void updateGoods(Good good,int product_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "update drugs set product_name=?,product_description=?,product_image=?,product_price=?, batch_number=?,expiration_date=?,prescription_required=?,is_frozen=? where product_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, good.getItemName());
			ps.setString(2, good.getItemDescription());
			ps.setString(3, good.getImgURL());
			ps.setFloat(4, good.getPrice());
			ps.setString(5, good.getNumber());
			ps.setString(6, good.getDate());
			ps.setBoolean(7, good.getIsPres());
			ps.setBoolean(8, good.getIsFrozen());
			ps.setInt(9, product_id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void deleteGoods(int product_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			String sql = "delete from drugs where product_id=?";
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
	
	@Override
	public void frozenGood(int product_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "update drugs set is_frozen = ? where product_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, product_id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
