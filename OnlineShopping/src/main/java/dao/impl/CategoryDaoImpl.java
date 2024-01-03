package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.CategoryDao;

public class CategoryDaoImpl implements CategoryDao{
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:xbro.db";
    public static final String USER = "root";
    public static final String PWD = "root";

	@Override
	public int findSubIDBySubName(String subCategory) {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL);
            
            //System.out.println(subCategory);
            String sql = "select SubID from SubCategory where SubName= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, subCategory);
            ResultSet rs = ps.executeQuery();

            int SubID = 0;
            if (rs.next()) {
                SubID = rs.getInt("SubID");
            }

            ps.close();
            conn.close();
            rs.close();

            return SubID;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
		return 0;
	}

	@Override
	public int findPSIDByPIDAndSID(int parentID, int subID) {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL);

            //System.out.println(parentID);
            //System.out.println(subID);
            String sql = "select ParentSubID from ParentSubRelation where ParentID = ? and SubID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, parentID);
            ps.setInt(2, subID);
            ResultSet rs = ps.executeQuery();

            int ParentSubID = 0;
            if (rs.next()) {
            	ParentSubID = rs.getInt("ParentSubID");
            }

            ps.close();
            conn.close();
            rs.close();

            return ParentSubID;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
		return 0;
	}

}
