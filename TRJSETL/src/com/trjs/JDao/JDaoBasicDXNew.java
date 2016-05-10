package com.trjs.JDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库连接管理类
 * @author admin
 *
 */
public class JDaoBasicDXNew {
	private static final Logger log = LoggerFactory.getLogger(Class.class);
	protected DataSource ds = null;
	protected  Connection conn = null;
	private int fetchSize=1<<12;
	
	public JDaoBasicDXNew(){
		
	}
	public JDaoBasicDXNew(Map<String,String> mapDb) {
		try {
//			this.conn=DBUtil.getConnection(mapDb);
			Class.forName(mapDb.get("jdbc.driverClassName"));
			this.conn = DriverManager.getConnection(mapDb.get("jdbc.url"),mapDb.get("jdbc.username"),mapDb.get("jdbc.password"));
			if(this.fetchSize<Integer.parseInt(mapDb.get("jdbc.fetchSize")))this.fetchSize=Integer.parseInt(mapDb.get("jdbc.fetchSize"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JDaoBasicDXNew(Connection conn) {
		this.conn = conn;
	}

	public Connection getConnection(){
		return this.conn;
	}
	public boolean freeConn() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("   "+e);
			return false;
		}
		return true;
	}

	/**
	 * @param sql
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String sql) throws SQLException {
		ResultSet rs = null;
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		stmt.setFetchSize(this.fetchSize);
		try
		{
			log.info(this.getClass().getName()+"   "+sql);
			rs = stmt.executeQuery(sql);
			log.info(this.getClass().getName()+"   "+sql+" sql over.");
			return rs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("   "+e);
		}
		return null;
	}
	
	public Statement getStatement() throws SQLException {
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		return stmt;
	}

	/**
	 * @param sql
	 * @throws SQLException
	 */
	public Map<String,Object> getRowMap(String sql) throws SQLException {
		ResultSet rs = getResultSet(sql);
		Map<String,Object> hashmap = new HashMap<String,Object>();
		if (rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int fieldCount = rsmd.getColumnCount();
			for (int i = 1; i <= fieldCount; i++) {
				String key = rsmd.getColumnName(i);
				hashmap.put(key, rs.getObject(i));
			}
		}
		rs.close();
		return hashmap;
	}

	/**
	 * @param sql
	 * @throws SQLException
	 */
	public Object getObject(String sql) throws SQLException {
		Object object = null;
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
			object = rs.getObject(1);
		rs.close();
		return object;
	}

	/**
	 * @param sql
	 *            ????SQL
	 * @throws SQLException
	 */
	public int update(String sql) throws SQLException {
		Statement stmt = conn.createStatement();
		return stmt.executeUpdate(sql);
	}

	/**
	 * ???????
	 */
	public void begin() throws SQLException {
		conn.setAutoCommit(false);
	}

	/**
	 */
	public void commit() throws SQLException {
		conn.commit();
		conn.setAutoCommit(true);
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	public boolean rollback() {
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String args[]) throws SQLException, NamingException {

	}
}