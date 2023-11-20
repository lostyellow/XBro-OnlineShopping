package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.UserDao;
import bean.Buyer;
import bean.Deal;
import bean.DealList;
import bean.Details;
import bean.Goods;
import bean.GoodsList;
import bean.User;
import bean.Users;

public class UserDaoImpl implements UserDao{
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String URL = "jdbc:sqlite:xbro.db";
	public static final String USER = "root";
	public static final String PWD = "root";
	
	public void register(User user) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into users(username,password,name,id_card,sex,tele,e_mail,birth) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getId_card());
			ps.setString(5, user.getSex());
			ps.setString(6, user.getTele());
			ps.setString(7, user.getE_mail());
			ps.setString(8, user.getBirth());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Users findAll() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			
			String sql = "select username,password from users";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			Users users = new Users();
			User user = null;
			
			while(rs.next()) {
				user = new User();
				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(2));
				users.add(user);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return users;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Boolean checkLogin(String username, String password) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			
			String sql = "select username,password from users where username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			String dbPassword = null;
			if(rs.next()) {
				dbPassword = rs.getString("password");
//				dbPassword = rs.getString(3);
				if(dbPassword.equals(password)) {
					ps.close();
					conn.close();
					rs.close();
					return true;
				}
			}
			ps.close();
			conn.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public int findSeller_ID(User user) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			
			String sql = "select user_id from users where username=? and password=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			
			int seller_id = 0;
			if(rs.next()) {
				seller_id = rs.getInt(1);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return seller_id;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int findProduct_ID(int seller_id,Goods good) {
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
	public void addGoods(int seller_id,Goods good) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into drugs"
					+ "(seller_id,product_name,product_description,product_image,product_price,"
					+ "batch_number,expiration_date,prescription_required,is_frozen) "
					+ "values(?,?,?,?,?,?,?,?,?)";
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
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void updateGoods(Goods good,int product_id) {
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
	public int findTrans_ID(int product_id, String time) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			
			String sql = "select transaction_id from transactions "
					+"where product_id = ? and transaction_time = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			ps.setString(2, time);
			ResultSet rs = ps.executeQuery();
			
			int trans_id = 0;
			if(rs.next()) {
				trans_id = rs.getInt(1);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return trans_id;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void purchase(Deal deal) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into transactions"
					+ "(product_id,transaction_status,transaction_time,transaction_amount)"
					+ "values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, deal.getProduct_id());
			ps.setString(2, deal.getStatus());
			ps.setString(3, deal.getTime());
			ps.setFloat(4, deal.getAmount());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void updateTrans(int trans_id, String status) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "update transactions "
					+ "set transaction_status = ? "
					+ "where transaction_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, trans_id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void submitdeal(Buyer buyer) {
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into order_details"
					+ "(appointment_time,address,buyer_name,buyer_gender,buyer_identification,buyer_phone_number,notes) "
					+ "values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, buyer.getAppointment_time());
			ps.setString(2, buyer.getAddress());
			ps.setString(3, buyer.getBuyer_name());
			ps.setString(4, buyer.getBuyer_gender());
			ps.setString(5, buyer.getBuyer_identification());
			ps.setString(6, buyer.getBuyer_phone_number());
			ps.setString(7, buyer.getText());
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
	}
	@Override
	public DealList findAllDeal() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select product_id,transaction_time,transaction_status,transaction_amount from transactions";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			DealList dealList = new DealList();
			while(rs.next()) {
				Deal deal = new Deal();
				deal.setProduct_id(rs.getInt("product_id"));
				deal.setTime(rs.getString("transaction_time"));
				deal.setStatus(rs.getString("transaction_status"));
				deal.setAmount(rs.getFloat("transaction_amount"));
				dealList.add(deal);
			}
			
			ps.close();
			conn.close();
			
			return dealList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new DealList();
		}
	}
	@Override
	public Details findDetails(int transaction_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select appointment_time,address,buyer_name,buyer_gender,buyer_identification,buyer_phone_number,notes from order_details where transaction_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, transaction_id);
			ResultSet rs = ps.executeQuery();
			
			Details details = null;
			if(rs.next()) {
				details = new Details();
				details.setAppointment_time(rs.getString("appointment_time"));
				details.setAddress(rs.getString("address"));
				details.setBuyer_name(rs.getString("buyer_name"));
				details.setBuyer_gender(rs.getString("buyer_gender"));
				details.setBuyer_identification(rs.getString("buyer_identification"));
				details.setBuyer_phone_number(rs.getString("buyer_phone_number"));
				details.setText(rs.getString("notes"));
				
			}
			
			ps.close();
			conn.close();
			
			return details;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void changePwd(int userId, String newPwd) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "update users "
			+ "set password = ? "
			+ "where user_id = ?";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newPwd);
			ps.setInt(2, userId);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public String findSeller_PWD(String realname, String id_card) {
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select password "
			+ "from users "
			+ "where name = ? and id_card = ?";
		
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, realname);
			ps.setString(2, id_card);
			ResultSet rs = ps.executeQuery();
			
			String pwd = "";
			if(rs.next()) {
				pwd = rs.getString(1);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return pwd;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void closedeal(int transaction_id) {
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			
			String sql = "update transactions "
					+ "set transaction_status = ? "
					+ "where transaction_id = ?";
				
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "end");
			ps.setInt(2, transaction_id);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public int unfreezeGood(int transaction_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			
			String sql = "update transactions "
					+ "set transaction_status = ? "
					+ "where transaction_id = ?";
				
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "wait");
			ps.setInt(2, transaction_id);
			ps.executeUpdate();
			
			sql = "select product_id "
					+ "from transactions "
					+ "where transaction_id = ?";
				
			ps = conn.prepareStatement(sql);
			ps.setInt(1, transaction_id);
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
