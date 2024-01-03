package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Buyer;
import bean.Details;
import dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:xbro.db";
    public static final String USER = "root";
    public static final String PWD = "root";

    @Override
    public void submitdeal(Buyer buyer) {
        try {
            Class.forName(DRIVER);

            Connection conn = DriverManager.getConnection(URL);
            String sql = "insert into order_details"
                    + "(transaction_id,appointment_time,address,buyer_name,buyer_gender,buyer_identification,buyer_phone_number,notes) "
                    + "values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            //test
            ps.setInt(1, 1);
            ps.setString(2, buyer.getAppointment_time());
            ps.setString(3, buyer.getAddress());
            ps.setString(4, buyer.getBuyer_name());
            ps.setString(5, buyer.getBuyer_gender());
            ps.setString(6, buyer.getBuyer_identification());
            ps.setString(7, buyer.getBuyer_phone_number());
            ps.setString(8, buyer.getText());
            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
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
            if (rs.next()) {
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

}
