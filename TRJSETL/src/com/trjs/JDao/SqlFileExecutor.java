package com.trjs.JDao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取 SQL 脚本并执行
 * 
 */
public class SqlFileExecutor {

	/**
	 * 读取 SQL 文件，获取 SQL 语句
	 * @param sqlFile SQL 脚本文件
	 * @return List<sql> 返回所有 SQL 语句的 List
	 * @throws Exception
	 */
	public List<String> loadSql(String sqlFile) throws Exception {
		List<String> sqlList = new ArrayList<String>();
		BufferedReader sqlFileIn = null;
		try {
			sqlFileIn = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFile),"UTF-8"));  
			//sqlFileIn = new FileInputStream(sqlFile);
			StringBuilder sqlSb = new StringBuilder();
			String line;
	        while ((line = sqlFileIn.readLine()) != null)   {   
	        	System.out.println(line);
				sqlSb.append(line);  
	        } 
//			byte[] buff = new byte[1024];
//			int byteRead = 0;
//			while ((byteRead = sqlFileIn.read(buff)) != -1) {
//				System.out.println(new String(buff, 0, byteRead));
//				sqlSb.append(new String(buff, 0, byteRead,"UTF-8"));
//			}
			//System.out.println(sqlSb.toString());
			// Windows 下换行是 /r/n, Linux 下是 /n
			String[] sqlArr = sqlSb.toString().split("(;//s*//r//n)|(;//s*//n)");
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();//System.out.println(sql);
				if (!sql.equals("")) {
					sqlList.add(sql);
				}
			}
			return sqlList;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}finally{
			sqlFileIn.close();
		}
	}

	public static void main(String[] args) throws Exception {
		List<String> sqlList = new SqlFileExecutor().loadSql("D:\\DBVersion[4].sql");
		for (String sql : sqlList) {
			String[] ArrSql = sql.split(";;");
			for(int i= 0; i < ArrSql.length; i++){
				//System.out.println(ArrSql[i]);
			}
		}
		
	}
	
}