package fruitshop.dao.impl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zaxxer.hikari.HikariDataSource;

@WebListener
public class PoolConnection implements ServletContextListener {
	
	private static HikariDataSource dataSource = new HikariDataSource();
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/fruitshop");
		dataSource.setUsername("root");
		dataSource.setPassword("17195802");
	}
	
	public static HikariDataSource getPoolConnection() {
		return dataSource;
	}
	
	@Override
    public void contextDestroyed(ServletContextEvent event) {
        dataSource.close();
    }
}
