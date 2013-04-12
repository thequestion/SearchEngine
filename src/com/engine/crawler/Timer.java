package com.engine.crawler;

import java.text.DecimalFormat;

public class Timer {
	public String getTotalCrawlTimeToString(){
		long elapsedTimeInSecond = totalCrawlTime / 1000;
        long remainderInMillis = totalCrawlTime % 1000;
        //show three digits
        DecimalFormat df = new DecimalFormat("#.###");
        String time = "Time: " + elapsedTimeInSecond + "." +
        		df.format(remainderInMillis) + " seconds";
        return time;
	}
	public long getTotalCrawlTime(){
		return totalCrawlTime;
	}
	public void calculateCrawlTime(){
		totalCrawlTime = endTime - startTime;;
	}
	public void setStartTime(long time){
		this.startTime = time;
	}
	public void setEntTime(long time){
		this.endTime = time;
	}
	
	private long startTime;
	private long endTime;
	//time to crawl the entire domain
	private long totalCrawlTime;
}
