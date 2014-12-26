package org.jwebap.plugin.tracer.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.jwebap.core.TraceLiftcycleManager;

/**
 * ����DataSource
 * 
 * @author leadyu(yu-lead@163.com)
 * @since Jwebap 0.5
 * @date Mar 11, 2008
 */
public class ProxyDataSource implements DataSource {

	private DataSource delegate;

	private ConnectionEventListener[] listeners;

	/**
	 * ���й켣����
	 */
	private transient TraceLiftcycleManager container = null;

	public ProxyDataSource(TraceLiftcycleManager container,
			DataSource delegate, ConnectionEventListener[] listeners) {
		this.container = container;
		this.listeners = listeners;
		this.delegate = delegate;
	}

	public Connection getConnection() throws SQLException {
		Connection conn = delegate.getConnection();

		return getProxy(conn);
	}

	public Connection getConnection(String arg0, String arg1)
			throws SQLException {
		Connection conn = delegate.getConnection(arg0, arg1);
		return getProxy(conn);
	}

	public PrintWriter getLogWriter() throws SQLException {
		return delegate.getLogWriter();
	}

	public int getLoginTimeout() throws SQLException {
		return delegate.getLoginTimeout();
	}

	public void setLogWriter(PrintWriter arg0) throws SQLException {
		delegate.setLogWriter(arg0);
	}

	public void setLoginTimeout(int arg0) throws SQLException {
		delegate.setLoginTimeout(arg0);
	}

	/**
	 * �õ����Ӵ���
	 * 
	 * @param conn
	 * @return
	 */
	private Connection getProxy(Connection conn) {
		if (!(conn instanceof ProxyConnection)) {
			return new ProxyConnection(container, conn, listeners);
		}

		return conn;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
