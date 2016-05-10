package com.trjs.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConstants {

	private static final Logger log = LoggerFactory.getLogger(Class.class);
	public static String SAVE_FILE_PATH="D:\\CWBB";
	private static final String PARAMETER_FILE_NAME="trjssjfx.ini";
	public static final List<Map<String,List<Map<String,String>>>> SCHEDULE_TASK_LIST=new ArrayList<Map<String,List<Map<String,String>>>>();
//	[{ds,[{param1:value1,param2:value2,param3:value3}]
//	task,[{taskType:hand,tableName:t1,sqlName:sql1},
//	{taskType:auto,interval:1,hour:20,tableName:t1,sqlName:sql1}]}] 
	
	static {
//		逐行读取配置文件:定义数据库连接,手动任务,定时任务等
		BufferedReader br=null;
		try {
			String filePath=AppConstants.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			filePath = filePath.substring(1, filePath.lastIndexOf("/") + 1);
			filePath = URLDecoder.decode(filePath,"UTF-8");
			log.info("filePath:"+filePath);
			br=new BufferedReader(new InputStreamReader(new FileInputStream(filePath+PARAMETER_FILE_NAME),"UTF-8"));
			String[] temp;String currentLine=null,tmpSql="";
			List<Map<String,String>> dsList=null;
			List<Map<String,String>> taskList=null;
//			int counter=0;
			Map<String,String> map=null;
			Map<String,List<Map<String,String>>> mapDs=null;
			for(String line=br.readLine();line!=null;line=br.readLine()){
//				log.info(line);
//				数据源开始
				if(StringUtil.isNotBlank(line)&&line.startsWith("DB::Sub")){
					dsList=new ArrayList<Map<String,String>>();
					map=new HashMap<String,String>();
					mapDs=new HashMap<String,List<Map<String,String>>>();
//					counter++;
//					数据源类型jdbc.type
					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
//					数据源驱动jdbc.driverClassName
					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
					
					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
					
					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);

					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
					
					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);

					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
					
					while(!currentLine.startsWith("Planted"))currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
					
					dsList.add(map);
					
					mapDs.put("ds", dsList);
					
					taskList=new ArrayList<Map<String,String>>();
					
				}
//				if(StringUtil.isNotBlank(line)&&line.startsWith("Planted")){
//					
//					log.info("hand flag:");
//				}
				if(StringUtil.isNotBlank(line)&&line.startsWith("Hand::HSub")){
//					手动任务
					map=new HashMap<String,String>();
					map.put("taskName", line.split("::")[1]);
					map.put("taskType", "hand");
					currentLine=br.readLine();
					String tabName="",sqlName="";
					while(!currentLine.startsWith("End::HSub")){
						while(StringUtil.isBlank(currentLine)||currentLine.startsWith("//"))currentLine=br.readLine();
						if(!currentLine.startsWith("End::HSub")){
							temp=currentLine.split("=");
							tabName+=temp[0]+";";
							
							tmpSql=temp[1];
							if(temp.length>=3){
								for(int i=2;i<temp.length;i++)tmpSql+="="+temp[i];
							}
							sqlName+=tmpSql+";";
							currentLine=br.readLine();
						}
						
					}
					if(tabName.length()>0&&sqlName.length()>0){
						map.put("tableName", tabName.substring(0,tabName.length()-1));
						map.put("sqlName", sqlName.substring(0,sqlName.length()-1));
						taskList.add(map);
					}
					
				}
				if(StringUtil.isNotBlank(line)&&line.startsWith("Timers::Sub")){
//					自动任务
					map=new HashMap<String,String>();
					map.put("taskName", line.split("::")[1]);
					map.put("taskType", "auto");
					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
					
					currentLine=br.readLine();
					temp=currentLine.split("=");
					map.put(temp[0], temp[1]);
					currentLine=br.readLine();
					
//					while(StringUtil.isBlank(currentLine))currentLine=br.readLine();
//					temp=currentLine.split("=");
//					map.put("tableName", temp[0]);
//					if(temp.length>=3){
//						tmpSql="";
//						for(int i=2;i<temp.length;i++)tmpSql+="="+temp[i];
//						map.put("sqlName", temp[1]+tmpSql);
//					}else{
//						map.put("sqlName", temp[1]);
//					}
					String tabName="",sqlName="";
					while(!currentLine.startsWith("End::Sub")){
						while(StringUtil.isBlank(currentLine)||currentLine.startsWith("//"))currentLine=br.readLine();
						if(!currentLine.startsWith("End::Sub")){
							temp=currentLine.split("=");
							tabName+=temp[0]+";";
							tmpSql=temp[1];
							if(temp.length>=3){
								for(int i=2;i<temp.length;i++)tmpSql+="="+temp[i];
							}
							sqlName+=tmpSql+";";
							currentLine=br.readLine();
						}
						
					}
					if(tabName.length()>0&&sqlName.length()>0){
						map.put("tableName", tabName.substring(0,tabName.length()-1));
						map.put("sqlName", sqlName.substring(0,sqlName.length()-1));
						taskList.add(map);
					}
					
				}
//				数据源结束
				if(StringUtil.isNotBlank(line)&&line.startsWith("End::Sub")&&!line.contains("_")){
					mapDs.put("task", taskList);
					SCHEDULE_TASK_LIST.add(mapDs);
				}
//				保存路径
				if(StringUtil.isNotBlank(line)&&line.startsWith("save_file_path")){
					temp=line.split("=");
					SAVE_FILE_PATH=temp[1]+"/";
				}
				
			}
			br.close();
		} catch (IOException e) {
			log.error("读取配置文件异常:"+e.getLocalizedMessage());
			System.exit(1);
		}finally{
				try {
					if(null!=br)br.close();
				} catch (IOException e) {
					log.error("关闭配置文件异常:"+e.getLocalizedMessage());
					System.exit(1);
				}
		}
	}

	public static void main(String[] ar){
		if(AppConstants.SCHEDULE_TASK_LIST!=null) log.info("Hello.");
		try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		log.info(":"+(1<<12));
//		try {
//			String filePath=AppConstants.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//			filePath = filePath.substring(1, filePath.lastIndexOf("/") + 1);
//			filePath = URLDecoder.decode(filePath,"UTF-8");
//			log.info("filePath:"+filePath);
//			BufferedReader br=null;
//			br=new BufferedReader(new InputStreamReader(new FileInputStream(filePath+"trgssjfx.ini"),"UTF-8"));
//			for(String line=br.readLine();line!=null;line=br.readLine()){
//				if(StringUtil.isNotBlank(line)&&line.startsWith("DB::Sub")){
//					log.info(line);
//				}
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		String str=null;
		for(Map<String,List<Map<String,String>>> mapDs:AppConstants.SCHEDULE_TASK_LIST){
			Map<String,String> map=mapDs.get("ds").get(0);
			for(Iterator<String> ite=map.keySet().iterator();ite.hasNext();){
				str=ite.next();
				log.info(str+":"+map.get(str));
			}
			log.info("************");
			for(Map<String,String> map1:mapDs.get("task")){
				for(Iterator<String> ite=map1.keySet().iterator();ite.hasNext();){
					str=ite.next();
					log.info(str+":"+map1.get(str));
				}
				log.info("-----------");
			}
		}
		
	}
}
