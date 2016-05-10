package com.trjs.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBUtil
{
	
	private static final Logger log=LoggerFactory.getLogger(Class.class);
	private static final Map<String,BasicDataSource> dsMap = new HashMap<String,BasicDataSource>();
//	private static Connection uniqueConOracle = null;
//	
//	private static Connection uniqueConSybase = null;
	
	static {
//		for(Map<String,List<Map<String,String>>> mapDs:AppConstants.SCHEDULE_TASK_LIST){
//			Map<String,String> mapDb=mapDs.get("ds").get(0);
//			BasicDataSource ds = new BasicDataSource();
//			ds.setDriverClassName(mapDb.get("jdbc.driverClassName"));
//			ds.setUrl(mapDb.get("jdbc.url"));
//			ds.setUsername(mapDb.get("jdbc.username"));
//			ds.setPassword(mapDb.get("jdbc.password"));
//			ds.setMaxActive(Integer.parseInt(mapDb.get("jdbc.maxActive")));
//			ds.setInitialSize(8);
//			ds.setMaxWait(3000l);
//			ds.setDefaultAutoCommit(true);
//			ds.setDefaultReadOnly(false);
//			ds.setTestOnBorrow(true);
//			ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
////				ds.setDefaultCatalog(CATALOG);	
////			ds.setConnectionInitSqls(Arrays.asList(new String[] { "SELECT DUMMY FROM DUAL", "select 2 from dual"}));
//			if("oracle".equals(mapDb.get("jdbc.type"))){
//				ds.setValidationQuery(" select 1 from dual ");
//			}
//			if("sybase".equals(mapDb.get("jdbc.type"))){
//				ds.setValidationQuery(" select 1 ");
//			}
//			dsMap.put(mapDb.get("jdbc.url")+mapDb.get("jdbc.username"), ds);
			
			
//		}
//	    log.info("datasource configuration complete.");
	}
	
	public static synchronized Connection getConnection(Map<String,String> mapDb) throws SQLException{
		return dsMap.get(mapDb.get("jdbc.url")+mapDb.get("jdbc.username")).getConnection();
	}
//	public static synchronized Connection getConnectionSybase(Map<String,String> mapDb) throws SQLException {
//		
//		if(uniqueConSybase==null){
//			uniqueConSybase=dsSybase.getConnection();
//		}
//        return uniqueConSybase;
//    }
	
//	public static synchronized Connection getConnectionOracle() throws SQLException {
//		if(uniqueConOracle==null){
//			uniqueConOracle=dsOracle.getConnection();
//		}
//        return uniqueConOracle;
//    }
	
	
	public static void main(String[] ar){
//		try {
//			DBUtil.getConnection();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		log.info("class name:");
	}
	
    
}
