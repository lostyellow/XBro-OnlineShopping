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
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL);
			Statement stmt = conn.createStatement();
			String sql = "create table if not exists drugs" +
						"(product_id integer primary key autoincrement, " +
						"seller_id integer not null, " +
						"product_name text not null, " +
						"product_description text, " +
						"product_image text, " +
						"product_price real not null, " +
						"batch_number text default null, " +
						"expiration_date text default null, " +
						"prescription_required boolean default null, " +
						"is_frozen boolean default null, " +
						"inventory integer default 0, " +
						"foreign key(seller_id) references users(user_id));";
//						+ "insert into drugs values(1, 1, 'Dname', 'description', null, 12.3)";
			sql += "create table if not exists users" +
					"(user_id integer primary key autoincrement, " +
					"username text not null, " +
					"password text not null, " +
					"name text not null unique, " +
					"id_card text not null, " +
					"sex text not null, " +
					"tele text not null, " +
					"e_mail text not null, " +
					"user_group text not null, " +
					"birth text not null);";
			sql += "create table if not exists user_group"+
					"(user_group text primary key);";
			sql += "create table if not exists order_details" +
					"(transaction_id integer primary key autoincrement, " +
					"appointment_time text default null, " +
					"address text default null, " +
					"buyer_name text default null, " +
					"buyer_gender text default null, " +
					"buyer_identification text default null, " +
					"buyer_phone_number text default null, " +
					"notes text, " +
					"foreign key(transaction_id) references transactions(transaction_id));";
			sql += "create table if not exists transactions" +
					"(transaction_id integer primary key autoincrement, " +
					"product_id integer not null, " +
					"buyer_id integer not null, " +
					"transaction_status text not null, " +
					"transaction_time text not null, " +
					"transaction_amount real not null, " +
					"foreign key(product_id) references drugs(product_id));";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
