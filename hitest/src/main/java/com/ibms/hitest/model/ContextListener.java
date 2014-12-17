package com.ibms.hitest.model;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ibms.hitest.util.ConnectionPool;

public class ContextListener implements ServletContextListener {

	public ContextListener() {
		try {
			Class.forName("org.postgresql.Driver");
			ConnectionPool.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String contextPath(ServletContextEvent arg0) {
		return arg0.getServletContext().getContextPath();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ConnectionPool.destroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
