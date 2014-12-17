package com.ibms.hitest.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class ConnectionPool {

	public static ConnectionPool instance;
	public static void destroy() {
		instance.shutdown();
		instance = null;
	}
	
	public static void init() {
		instance = new ConnectionPool();
	}
	
	public static void init(ConnectionPool ins) {
		instance = ins;
	}
	
	private BoneCP boneCp;
	
	public ConnectionPool(BoneCP boneCp) {
		this.boneCp = boneCp;
	}

	public ConnectionPool() {
		try {  
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {  
			e.printStackTrace();  
			return;  
		}  
		
		Configuration configSet = null;
		try {
			configSet = new PropertiesConfiguration("config.properties");
		} catch (ConfigurationException e1) {
			e1.printStackTrace();
		}
		
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl("jdbc:postgresql://192.168.6.136:5432/"+configSet.getString("database"));
		config.setUsername(configSet.getString("user"));
		config.setPassword(configSet.getString("passwd"));		
		config.setMinConnectionsPerPartition(5);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(1);
		try {
			boneCp = new BoneCP(config); // setup the connection pool
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return boneCp.getConnection();
	}

	public void shutdown() {
		boneCp.shutdown();
	}
	
	public static void closeAll(AutoCloseable... closeables) {
		for (AutoCloseable c : closeables) {
			try {
				if (c != null)
					c.close();
			} catch (Exception e) {
				System.out.println("[Error when closing resource:]"
						+ e.getMessage());
			}
		}
	}
}
