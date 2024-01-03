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
                    "    e_mail TEXT," +
                    "    user_group TEXT NOT NULL," +
                    "    birth TEXT," +
                    "    address TEXT" +
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
            
            //创建 ParentCategory 表
            String sqlParentCategory = "CREATE TABLE IF NOT EXISTS ParentCategory (" +
                    "    ParentID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    ParentName TEXT UNIQUE NOT NULL" +
                    ");";
            stmt.executeUpdate(sqlParentCategory);

            //创建 SubCategory 表
            String sqlSubCategory = "CREATE TABLE IF NOT EXISTS SubCategory (" +
                    "    SubID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    SubName TEXT UNIQUE NOT NULL" +
                    ");";
            stmt.executeUpdate(sqlSubCategory);
            
            //创建 ParentSubRelation表
            String sqlParentSubRelation = "CREATE TABLE IF NOT EXISTS ParentSubRelation (" +
                    "    ParentSubID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    ParentID INTEGER," +
                    "    SubID INTEGER," +
                    "    FOREIGN KEY (ParentID) REFERENCES ParentCategory(ParentID)," +
                    "    FOREIGN KEY (SubID) REFERENCES SubCategory(SubID)" +
                    ");";
            stmt.executeUpdate(sqlParentSubRelation);

            //插入数据
            String insertParent1 = "INSERT INTO ParentCategory (ParentName) VALUES ('处方药');";
            stmt.executeUpdate(insertParent1);

            String insertParent2 = "INSERT INTO ParentCategory (ParentName) VALUES ('非处方药（OTC）');";
            stmt.executeUpdate(insertParent2);

            // 处方药的子类
            String[] subCategoryPrescription = {"心血管药物", "抗生素", "抗抑郁药", "镇痛药"};
            for (String sub : subCategoryPrescription) {
                String sql = "INSERT INTO SubCategory (SubName) VALUES ('" + sub + "');";
                stmt.executeUpdate(sql);
            }

            // 非处方药（OTC）的子类
            String[] subCategoryOTC = {"感冒和流感", "止痛药", "消化系统药物", "皮肤护理药膏"};
            for (String sub : subCategoryOTC) {
                String sql = "INSERT INTO SubCategory (SubName) VALUES ('" + sub + "');";
                stmt.executeUpdate(sql);
            }

			// 假设处方药的 ParentID 是 1，非处方药的 ParentID 是 2
			// 假设子类 ID 从 1 开始，按照插入顺序递增
			int[] prescriptionSubIDs = {1, 2, 3, 4}; // 对应处方药的子类
			for (int subID : prescriptionSubIDs) {
			    String sql = "INSERT INTO ParentSubRelation (ParentID, SubID) VALUES (1, " + subID + ");";
			    stmt.executeUpdate(sql);
			}
			
			int[] otcSubIDs = {5, 6, 7, 8}; // 对应非处方药（OTC）的子类
			for (int subID : otcSubIDs) {
			    String sql = "INSERT INTO ParentSubRelation (ParentID, SubID) VALUES (2, " + subID + ");";
			    stmt.executeUpdate(sql);
			}


            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}