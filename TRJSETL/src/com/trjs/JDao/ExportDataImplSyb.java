package com.trjs.JDao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trjs.util.AppConstants;
import com.trjs.util.DateUtil;


public class ExportDataImplSyb implements ExportData {
//	private static String Tablesname;//配置文件table名称
//	private static String Tableswhere;//配置文件table条件
//	private static String Tablescolumn;//配置文件table查询字段
	private static final Logger log = LoggerFactory.getLogger(Class.class);
	private Map<String,String> mapDb;
	
	public ExportDataImplSyb(){
		
	}
	public ExportDataImplSyb(Map<String,String> mapDb){
		this.mapDb=mapDb;
	}
	public synchronized String exportTable2TxtFromMulti(String tableName,String sqlStr){
		StringBuffer sbRet=new StringBuffer("");
		BufferedWriter fos = null;
		JDaoBasicDXNew dao=new JDaoBasicDXNew(mapDb);
		ResultSet rs=null;
		
		String[] tmpTableStr=tableName.split(";");
		String[] tmpSqlStr=sqlStr.split(";");
		int counter=0;
		for(String subTabName:tmpTableStr){
			long startTime=System.currentTimeMillis();
			try{
				log.info("--"+subTabName+  "表开始导出--");
				sbRet.append(DateUtil.date2Str(new Date()));
				sbRet.append("    --表"+subTabName+  "开始导出--\r\n\r\n");
				rs=dao.getResultSet(tmpSqlStr[counter]);
				if(!new File(AppConstants.SAVE_FILE_PATH).exists()){
					new File(AppConstants.SAVE_FILE_PATH).mkdir();
				}
				fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(AppConstants.SAVE_FILE_PATH+subTabName+".txt",false),"GBK"));
				//输出字段
			    ResultSetMetaData rsmd=rs.getMetaData();
			    int numCols=rsmd.getColumnCount();
			     /*--写入表头.最后一个字段没有分号--start-*/
			    for(int i=1;i<=numCols;i++){
			       if(i!=numCols)
			    	   fos.write(rsmd.getColumnLabel(i).trim()+"&!&");
			       else
			    	   fos.write(rsmd.getColumnLabel(i).trim());
			     }
			     /*--写入表头.最后一个字段没有分号--start-*/
			    fos.write("\r\n\r\n");
			    int cou = 0;
				if(rs.next()){
					String str;
					
					do{	
						cou++;
						for(int i=1;i<=numCols;i++) {
							   if(i!=numCols){
								   if(rs.getString(i) != null){
									   str=""+rs.getString(i).trim().replaceAll("[\\n\\r]", "")+"&!&";
								   }else{
									   str=""+"&!&";
								   }
							   }else{
								   if(rs.getString(i) != null){
									   str=""+rs.getString(i).trim().replaceAll("[\\n\\r]", "");
								   }else{
									   str="";
								   }
							   }
						       fos.write(str);
						       str="";
					     }
		                 fos.write("\r\n\r\n");
						
					}while(rs.next());
				}
				fos.close();
	            rs.close();
//	            log.info("--执行数据抽取任务已结束--");
				log.info(subTabName+"表共导出"+ cou+"条数据;用时"+(System.currentTimeMillis()-startTime)+"ms.");
				sbRet.append(DateUtil.date2Str(new Date()));
				sbRet.append("    表"+subTabName+"共导出"+ cou+"条数据;用时"+(System.currentTimeMillis()-startTime)+"ms.\r\n\r\n");
			}catch(Exception e){
				e.printStackTrace();
				log.error("   "+e+"   表"+subTabName  +"导出失败--");
			}
			
			counter++;
		}
		dao.freeConn();
		return sbRet.toString();	
		
	}
    
    public String exportTable2Txt(String tableName,String sqlStr){
    	StringBuffer sbRet=new StringBuffer("");
    	BufferedWriter fos = null;
		JDaoBasicDXNew dao=new JDaoBasicDXNew(mapDb);
		ResultSet rs=null;
		long startTime=System.currentTimeMillis();
//		sbRet.append(DateUtil.date2Str(new Date()));
//		sbRet.append("    ******************************************\r\n\r\n");
//		log.info("   开始执行抽取数据表任务");
//		sbRet.append(DateUtil.date2Str(new Date()));
//		sbRet.append("    开始执行定时抽取数据表任务\r\n\r\n");		
		try{
			log.info("--"+tableName+  "表开始导出--");
			sbRet.append(DateUtil.date2Str(new Date()));
			sbRet.append("    --表"+tableName+  "开始导出--\r\n\r\n");
			rs=dao.getResultSet(sqlStr);
			if(!new File(AppConstants.SAVE_FILE_PATH).exists()){
				new File(AppConstants.SAVE_FILE_PATH).mkdir();
			}
			fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(AppConstants.SAVE_FILE_PATH+tableName+".txt",false),"GBK"));
			//输出字段
		    ResultSetMetaData rsmd=rs.getMetaData();
		    int numCols=rsmd.getColumnCount();
		     /*--写入表头.最后一个字段没有分号--start-*/
		    for(int i=1;i<=numCols;i++){
		       if(i!=numCols)
		    	   fos.write(rsmd.getColumnLabel(i).trim()+"&!&");
		       else
		    	   fos.write(rsmd.getColumnLabel(i).trim());
		     }
		     /*--写入表头.最后一个字段没有分号--start-*/
		    fos.write("\r\n\r\n");
		    int cou = 0;
			if(rs.next()){
				String str;
				
				do{	
					cou++;
					for(int i=1;i<=numCols;i++) {
						   if(i!=numCols){
							   if(rs.getString(i) != null){
								   str=""+rs.getString(i).trim().replaceAll("[\\n\\r]", "")+"&!&";
							   }else{
								   str=""+"&!&";
							   }
						   }else{
							   if(rs.getString(i) != null){
								   str=""+rs.getString(i).trim().replaceAll("[\\n\\r]", "");
							   }else{
								   str="";
							   }
						   }
					       fos.write(str);
					       str="";
				     }
	                 fos.write("\r\n\r\n");
					
				}while(rs.next());
			}
			fos.close();
            rs.close();
//            log.info("--执行数据抽取任务已结束--");
			log.info(tableName+"表共导出"+ cou+"条数据;用时"+(System.currentTimeMillis()-startTime)+"ms.");
			sbRet.append(DateUtil.date2Str(new Date()));
			sbRet.append("    表"+tableName+"共导出"+ cou+"条数据;用时"+(System.currentTimeMillis()-startTime)+"ms.\r\n\r\n");
		}catch(Exception e){
			e.printStackTrace();
			log.error("   "+e+"   表"+tableName  +"导出失败--");
		}
//		sbRet.append(DateUtil.date2Str(new Date()));
//		sbRet.append("    执行定时抽取数据表任务已结束--\r\n\r\n");
		return sbRet.toString();
		
	}
    
    
    
}    
