package com.trjs.expd;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trjs.JDao.DaochuTxt;
import com.trjs.JDao.DaochuTxtFromDX;
import com.trjs.JDao.DaochuTxtFromOR;
import com.trjs.util.AppConstants;


public class MyTaskNew {
	private Timer timer = new Timer();
//	private static final long Interval_DAY = 24 * 60 * 60 * 1000;    //默认间隔时间为一天
	private static final Logger log = LoggerFactory.getLogger(Class.class);
	
	
	public void exportDataHandly(){
		
	}
    public void dingshirw(String beginDate,String endDate) {
    	DaochuTxt dao = null;
//    	ExportData r=null;
    	String[] timeStr;
        //调用设置timer启动时间和间隔执行时间配置文件
    	Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,20); //几点执行任务
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND, 0);
        Date date=calendar.getTime(); //第一次执行定时任务的时间
        //如果第一次执行定时任务的时间 小于当前的时间
        //要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。  
//        if (date.before(new Date())) {
//            date = this.addDay(date, 1);
//        }
    	try {
//    		BufferedWriter fos=null;
    		for(Map<String,List<Map<String,String>>> mapDs:AppConstants.SCHEDULE_TASK_LIST){
    			Map<String,String> mapDb=mapDs.get("ds").get(0);
    			for(Map<String,String> mapTask:mapDs.get("task")){
//    				手动任务
    				if("N".equalsIgnoreCase(mapDb.get("Planted"))&&"hand".equals(mapTask.get("taskType"))){
//    					if("oracle".equals(mapDb.get("jdbc.type"))){
//    						r =new ExportDataImplOra(mapDb);
//    					}else if("sybase".equals(mapDb.get("jdbc.type"))){
//    						r =new ExportDataImplSyb(mapDb);
//    					}
//    					String filePath=AppConstants.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//    					filePath = URLDecoder.decode(filePath.substring(1, filePath.lastIndexOf("/") + 1),"UTF-8");
//    					filePath = filePath+"log/"+mapTask.get("taskName")+".log";
//    					File f=new File(filePath);
//    					if(!f.exists()){
//    						f.createNewFile();
//    					}
//    					fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,true),"GBK"));
//    					fos.write(DateUtil.date2Str(new Date()));
//    					fos.write("    ******************************************\r\n\r\n");
//    					fos.write(DateUtil.date2Str(new Date())+"    开始执行手动抽取数据表任务"+mapTask.get("taskName")+"\r\n\r\n");
//    					fos.write(r.exportTable2TxtFromMulti(mapTask.get("tableName"), mapTask.get("sqlName")));
//    					fos.write(DateUtil.date2Str(new Date())+"    手动抽取数据表任务"+mapTask.get("taskName")+"结束\r\n\r\n");
//    					fos.close();
    					if("oracle".equals(mapDb.get("jdbc.type"))){
    						dao =new DaochuTxtFromOR(mapDb,mapTask.get("tableName"),mapTask.get("sqlName").replaceAll("@qsrq@", beginDate).replaceAll("@zzrq@", endDate));
    					}else if("sybase".equals(mapDb.get("jdbc.type"))){
    						dao =new DaochuTxtFromDX(mapDb,mapTask.get("tableName"),mapTask.get("sqlName").replaceAll("@qsrq@", beginDate).replaceAll("@zzrq@", endDate));
    						
    					}
    					dao.setFileName(mapTask.get("taskName"));
    					timer = new Timer();
    					timer.schedule(dao,1000l);
    				}
//    				自动任务
    				if("Y".equalsIgnoreCase(mapDb.get("Planted"))&&"auto".equals(mapTask.get("taskType"))){
    					if("oracle".equals(mapDb.get("jdbc.type"))){
    						dao =new DaochuTxtFromOR(mapDb,mapTask.get("tableName"),mapTask.get("sqlName").replaceAll("@qsrq@", beginDate).replaceAll("@zzrq@", endDate));
    					}else if("sybase".equals(mapDb.get("jdbc.type"))){
    						dao =new DaochuTxtFromDX(mapDb,mapTask.get("tableName"),mapTask.get("sqlName").replaceAll("@qsrq@", beginDate).replaceAll("@zzrq@", endDate));
    						
    					}
    					timeStr = mapTask.get("Hour").split(":");
    					if(timeStr.length>=3){
    						calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeStr[0])); 
    						calendar.set(Calendar.MINUTE,Integer.parseInt(timeStr[1])); 
    						calendar.set(Calendar.SECOND,Integer.parseInt(timeStr[2])); 
    					}
    					date=calendar.getTime();
    					if (date.before(new Date())) {
    			            date = this.addDay(date, 1);
    			        }
    					dao.setFileName(mapTask.get("taskName"));
    					timer = new Timer();
    					timer.schedule(dao,date,new Double(Double.parseDouble(mapTask.get("Interval")) * 60 * 60 * 1000).longValue());
    				}
    			}
    		}
    		
		} catch (Exception e1) {
			e1.printStackTrace();
			log.error(e1.getLocalizedMessage());
		}
    }
    
    
    
    //退出timer方法
    public void cancelTimer(){
    	this.timer.cancel();
    	timer.purge();
    	System.out.println("停止执行");
    }
    
 // 增加或减少天数
    public Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
    
    public static void main(String[] args) {
    	MyTaskNew t = new MyTaskNew();
    	t.dingshirw("","");
    	//float f = 2.000001f;
    	/*SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
    	System.out.println(d.format("2014-08-14"));*/
	}
}