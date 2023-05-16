package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
	private static Connection connection = getConnectionUtil("jdbc:mysql://localhost:3306/fruitshop", "root", "17195802");
	
	public JDBCConnection() {}
	
	public static Connection getConnection() {
		return connection;
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
