package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Deal;
import bean.DealList;
import dao.TransactionDao;

public class TransactionDaoImpl implements TransactionDao {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:xbro.db";
    public static final String USER = "root";
    public static final String PWD = "root";

    @Override
    public int findTrans_ID(int product_id, String time) {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL);

            String sql = "select transaction_id from transactions "
                    + "where product_id = ? and transaction_time = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, product_id);
            ps.setString(2, time);
            ResultSet rs = ps.executeQuery();

            int trans_id = 0;
            if (rs.next()) {
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
	public void purchase(Deal deal,int buy_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);
			
			Connection conn = DriverManager.getConnection(URL);
			String sql = "insert into transactions"
					+ "(product_id,buyer_id,transaction_status,transaction_time,transaction_amount)"
					+ "values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, deal.getProduct_id());
			//Test
			ps.setInt(2, buy_id);
			ps.setString(3, deal.getStatus());
			ps.setString(4, deal.getTime());
			ps.setFloat(5, deal.getAmount());
			
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
    public DealList findAllDeal() {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL);
            String sql = "select product_id,transaction_time,transaction_status,transaction_amount from transactions";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DealList dealList = new DealList();
            while (rs.next()) {
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
    public void closedeal(int product_id, int transaction_id) {
        try {
            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL);

            String sql = "update transactions "
                    + "set transaction_status = ? "
                    + "where product_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "end");
            ps.setInt(2, product_id);
            ps.executeUpdate();

            sql = "update transactions set transaction_status = ? where transaction_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "success");
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
            if (rs.next()) {
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
    public DealList findDealsByProduct_id(int product_id) {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL);
            String sql = "select product_id,transaction_time,transaction_status,transaction_amount from transactions where product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();

            DealList dealList = new DealList();
            while (rs.next()) {
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
        }
        return null;
    }

    @Override
    public Boolean IsExistIngDeal(int product_id) {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL);
            String sql = "select * from transactions where transaction_status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "ing");
            ResultSet rs = ps.executeQuery();

            Boolean exist = false;
            if (rs.next()) {
                exist = true;
            }

            ps.close();
            conn.close();

            return exist;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    
	@Override
	public DealList findDealsByBuyer_id(int buyer_id) {
		// TODO Auto-generated method stub
		try {
			Class.forName(DRIVER);

			Connection conn = DriverManager.getConnection(URL);
			String sql = "select product_id,transaction_time,transaction_status,transaction_amount,buyer_id from transactions where buyer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, buyer_id);
			ResultSet rs = ps.executeQuery();

			DealList dealList = new DealList();
			while(rs.next()) {
				Deal deal = new Deal();
				deal.setProduct_id(rs.getInt("product_id"));
				deal.setTime(rs.getString("transaction_time"));
				deal.setStatus(rs.getString("transaction_status"));
				deal.setAmount(rs.getFloat("transaction_amount"));
				deal.setBuyer_id(rs.getInt("buyer_id"));
				dealList.add(deal);
			}

			ps.close();
			conn.close();

			return dealList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
