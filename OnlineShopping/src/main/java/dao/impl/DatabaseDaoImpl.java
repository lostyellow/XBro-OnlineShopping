package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import dao.DatabaseDao;

public class DatabaseDaoImpl implements DatabaseDao {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:xbro.db";
    public static final String USER = "root";
    public static final String PWD = "root";

    @Override
    public void createDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL);
            stmt = conn.createStatement();

            //创建 xbro 数据库
            String sqlXBroDB = "";

            // 创建 drugs 表
            String sqlDrugs = "CREATE TABLE IF NOT EXISTS drugs (" +
                    "    product_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    seller_id INTEGER NOT NULL," +
                    "    product_name TEXT NOT NULL UNIQUE," +
                    "    product_description TEXT," +
                    // "    product_image TEXT," +
                    "    product_price REAL NOT NULL," +
                    "    batch_number TEXT DEFAULT NULL," +
                    "    expiration_date TEXT DEFAULT NULL," +
                    "    prescription_required BOOLEAN DEFAULT NULL," +
                    "    is_frozen BOOLEAN DEFAULT NULL," +
                    "    inventory INTEGER DEFAULT 0," +
                    "    FOREIGN KEY (seller_id) REFERENCES users(user_id)" +
                    ");";
            stmt.executeUpdate(sqlDrugs);

            // 创建 users 表
            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "    user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    username TEXT NOT NULL," +
                    "    password TEXT NOT NULL," +
                    "    name TEXT NOT NULL UNIQUE," +
                    "    id_card TEXT NOT NULL," +
                    "    sex TEXT NOT NULL," +
                    "    tele TEXT NOT NULL," +
                    "    e_mail TEXT NOT NULL," +
                    "    user_group TEXT NOT NULL," +
                    "    birth TEXT NOT NULL" +
                    ");";
            stmt.executeUpdate(sqlUsers);

            // 创建 user_group 表
            String sqlUser_Group = "CREATE TABLE IF NOT EXISTS user_group (" +
                    "    user_group TEXT PRIMARY KEY" +
                    ");";
            stmt.executeUpdate(sqlUser_Group);

            // 创建 transactions 表
            String sqlTransactions = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "    transaction_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    product_id INTEGER NOT NULL," +
                    "    buyer_id INTEGER NOT NULL," +
                    "    transaction_status TEXT NOT NULL," +
                    "    transaction_time TEXT NOT NULL," +
                    "    transaction_amount REAL NOT NULL," +
                    "    FOREIGN KEY (product_id) REFERENCES drugs(product_id)," +
                    "    FOREIGN KEY (buyer_id) REFERENCES users(user_id)" +
                    ");";
            stmt.executeUpdate(sqlTransactions);

            //创建 order_details 表
            String sqlOrder_Details = "CREATE TABLE IF NOT EXISTS order_details (" +
                    "    transaction_id INTEGER PRIMARY KEY NOT NULL," +
                    "    appointment_time TEXT DEFAULT NULL," +
                    "    address TEXT DEFAULT NULL," +
                    "    buyer_name TEXT DEFAULT NULL," +
                    "    buyer_gender TEXT DEFAULT NULL," +
                    "    buyer_identification TEXT DEFAULT NULL," +
                    "    buyer_phone_number TEXT DEFAULT NULL," +
                    "    notes TEXT," +
                    "    FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id)" +
                    ");";
            stmt.executeUpdate(sqlOrder_Details);

            //创建 pictures 表
            String sqlPictures = "CREATE TABLE IF NOT EXISTS pictures (" +
                    "	img_url TEXT DEFAUT NULL," +
                    "	product_id," +
                    "	FOREIGN KEY (product_id) REFERENCES drugs(product_id)" +
                    ");";
            stmt.executeUpdate(sqlPictures);

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}