package com.easysoft.build.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LogFileUtil {
	
	private File logFile;
	private PrintStream writer;		
	
	public LogFileUtil(File logFile){
		this.logFile = logFile;			
	}
	
	public void startWriter()throws IOException {
		if (writer == null) {
			writer = new PrintStream(logFile);
			writer.print("[" + new SimpleDateFormat("MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "]\r\n");
			writer.flush();
		}
	}
	
	public void logMessage(String message,String buildName) {			
		writer.print("【" + buildName + "】\r\n");
		writer.print(message + "\r\n");
		writer.flush();
	}
	
	public void logMessageBd(String message,String buildName) {	
		writer.print("\r\n");
		writer.print(message+"【【" + buildName + "】】\r\n");		
		writer.flush();
	}
	
	public void closeWriter() throws IOException {	
		if (writer != null) {
			writer.flush();
			writer.close();
			writer = null;			
		}
	}

}
