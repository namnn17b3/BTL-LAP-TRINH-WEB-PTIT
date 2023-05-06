package fruitshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	private static Connection connection = getConnectionUtil("jdbc:mysql://localhost:3306/fruitshop", "root", "17195802");
	
	public Dao() {}
	
	public static Connection getConnection() {
		return Dao.connection;
	}
	
	public static Connection getConnectionUtil(String dbURL, String userName, String password) {
		Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
	}
}
