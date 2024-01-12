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
import bean.Good;
import bean.GoodList;
import bean.User;
import bean.UserList;

public class UserDaoImpl implements UserDao{
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String URL = "jdbc:sqlite:xbro.db";
	public static final String USER = "root";
	public static final String PWD = "root";
	
	public void businessRegister(User user) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into users(username,password,name,id_card,sex,tele,e_mail,user_group,birth) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getId_card());
			ps.setString(5, user.getSex());
			ps.setString(6, user.getTele());
			ps.setString(7, user.getE_mail());
			ps.setString(8, "seller");
			ps.setString(9, user.getBirth());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void userRegister(User user) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into users(username,password,name,id_card,sex,tele,user_group,address) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getId_card());
			ps.setString(5, user.getSex());
			ps.setString(6, user.getTele());
			ps.setString(7, "user");
			ps.setString(8, user.getAddress());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

    @Override
    public UserList findAll() {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL);

            String sql = "select username,password from users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            UserList users = new UserList();
            User user = null;

            while (rs.next()) {
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
    public String checkLogin(String username, String password) {
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL);

            String sql = "select username,password,user_group from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            String dbPassword = null;
            String user_group = null;
            if (rs.next()) {
                dbPassword = rs.getString("password");
                if (dbPassword.equals(password)) {
                	user_group = rs.getString("user_group");
                    ps.close();
                    conn.close();
                    rs.close();
                    return user_group;
                }
            }
            ps.close();
            conn.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findUser_ID(User user) {
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
            if (rs.next()) {
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
    public User findUserByID(int userId) {
    	try {
            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL);
            String sql = "select * from users where user_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            User user = new User();
            if (rs.next()) {
                user.setId(rs.getInt("user_id"));
                user.setUserName(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setId_card(rs.getString("id_card"));
                user.setSex(rs.getString("sex"));
                user.setTele(rs.getString("tele"));
                user.setE_mail(rs.getString("e_mail"));
                user.setAddress(rs.getString("address"));
                user.setUser_group(rs.getString("user_group"));
                return user;
            }

            ps.close();
            conn.close();

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
    public String findUser_PWD(String realname, String id_card) {
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
            if (rs.next()) {
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
	public String onlyOneUser() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "select username from users where user_group = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "seller");
			ResultSet rs = ps.executeQuery();
			
			String exist = "";
			if(rs.next()) {
				exist = rs.getString(1);
			}
			
			ps.close();
			conn.close();
			rs.close();
			
			return exist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserList findBuyer_information() {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);

			String sql = "select username,password,tele,address,user_id from users where user_group = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "user");
			ResultSet rs = ps.executeQuery();

			UserList userlist = new UserList();
			User user = null;

			while(rs.next()) {
				user = new User();
				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setTele(rs.getString(3));
				user.setAddress(rs.getString(4));
				user.setId(rs.getInt(5));
				UserList.add(user);
			}

			ps.close();
			conn.close();
			rs.close();

			return userlist;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
