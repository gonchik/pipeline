package com.cobalt.bamboo.plugin.pipeline.cdperformance;

import java.util.Date;

public class UptimeGrade {
	public static final int[] GRADE_SCALE = {93, 90, 87, 83, 80, 77, 73, 70, 67, 63, 60};
	public static final String[] LETTER_GRADE = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
	private Date startDate;
	private long totalUptime;
	private boolean currentBuildState;
	private Date currentBuildDate;
	
	public UptimeGrade(Date startDate, long totalUptime, boolean currentBuildSuccess, Date currentBuildDate){
		if(startDate != null){
			this.startDate = new Date(startDate.getTime());
		}
		if(currentBuildDate != null){
			this.currentBuildDate = new Date(currentBuildDate.getTime());
		}
		this.totalUptime = totalUptime;
		this.currentBuildState = currentBuildSuccess;
	}
	
	public double getUptimePercentage(){
		Date current = new Date();
		long totalUptimeToCurrent = this.totalUptime;
		if(currentBuildState){
			totalUptimeToCurrent += current.getTime() - currentBuildDate.getTime();
		}
		return totalUptimeToCurrent * 1.0 / (current.getTime() - startDate.getTime());
	}
	
	public String getGrade(){
		double upTimePercentage = getUptimePercentage();
		for(int i = 0; i < GRADE_SCALE.length; i++){
			if(upTimePercentage >= GRADE_SCALE[i]){
				return LETTER_GRADE[i];
			}
		}
		return LETTER_GRADE[LETTER_GRADE.length - 1];
	}
}
