package com.trjs.JDao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

import com.trjs.util.AppConstants;
import com.trjs.util.DateUtil;


public class DaochuTxtFromDX extends DaochuTxt{
	
	private Map<String,String> mapDb;
	private String tableName;
	private String sqlName;
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public DaochuTxtFromDX(){
		
	}
	public DaochuTxtFromDX(Map<String,String> mapDb,String tableName,String sqlName){
		this.mapDb=mapDb;
		this.tableName=tableName;
		this.sqlName=sqlName;
	}
	public void run() {
		ExportDataImplSyb readDX = new ExportDataImplSyb(this.mapDb);
		String str="    ******************************************\r\n\r\n";
		str+=(DateUtil.date2Str(new Date())+"    开始执行抽取数据表任务"+this.getFileName()+"\r\n\r\n");
		str+=readDX.exportTable2TxtFromMulti(tableName,sqlName);
		BufferedWriter fos=null;
		
//		生成日志文件
		try {
			String filePath=AppConstants.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			filePath = URLDecoder.decode(filePath.substring(1, filePath.lastIndexOf("/") + 1),"UTF-8");
			filePath = filePath+"log/"+this.getFileName()+".log";
			File f=new File(filePath);
			if(!f.exists()){
				f.createNewFile();
			}
			fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,true),"GBK"));
			fos.write(DateUtil.date2Str(new Date()));
			fos.write(str);
			fos.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
