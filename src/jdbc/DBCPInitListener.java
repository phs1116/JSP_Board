package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent config) {
		String poolConfig = config.getServletContext().getInitParameter("poolConfig");
		Properties properties = new Properties();
		try {
			properties.load(new StringReader(poolConfig));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
			loadJDBCDriver(properties);
		InitConnectPool(properties);
	}

	@Override
	public void contextDestroyed(ServletContextEvent config) {
		// TODO Auto-generated method stub

	}
	
	 private void loadJDBCDriver(Properties prop) {
		 String jdbcDriver = prop.getProperty("jdbcDriver");
	        try {
	            //커넥션 풀에서 사용할 jdbc 드라이버를 로딩
	            Class.forName(jdbcDriver);
	        } catch (ClassNotFoundException ex) {
	            throw new RuntimeException("fail to load JDBC Driver", ex);
	        }
	    }

	private void InitConnectPool(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String dbUser = prop.getProperty("dbUser");
			String dbPass = prop.getProperty("dbPass");

			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(jdbcUrl, dbUser, dbPass);

			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
					null);

			poolableConnectionFactory.setValidationQuery("select 1");

			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 1L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);

			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory,
					poolConfig);

			poolableConnectionFactory.setPool(connectionPool);

			Class.forName("org.apache.commons.dbcp2.PoolingDriver");

			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool);
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
}
