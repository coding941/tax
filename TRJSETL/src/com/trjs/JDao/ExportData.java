package com.trjs.JDao;

public interface ExportData {
	
	/**
	 * 功能：导出表数据到文本文件,一个任务对应一张表
	 * @param tableName
	 * @param sqlStr
	 */
	public String exportTable2Txt(String tableName,String sqlStr);
	/**
	 * 功能：导出表数据到文本文件,一个任务包含多张表
	 * @param tableName:t1Name;t2Name;t3Name
	 * @param sqlStr:sql1Name;sql2Name;sql3Name
	 */
	public String exportTable2TxtFromMulti(String tableName,String sqlStr);
	
}
