package com.cobalt.bamboo.plugin.pipeline.cdperformance;

import java.util.Date;

import com.cobalt.bamboo.plugin.pipeline.cdresult.Build;

public class UptimeGrade {
	public static final double[] GRADE_SCALE = {0.93, 0.90, 0.87, 0.83, 0.80, 0.77, 0.73, 0.70, 0.67, 0.63, 0.60};
	public static final String[] LETTER_GRADE = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
	private Date startDate;
	private long totalUptime;
	private boolean currentBuildSuccess;
	private Date currentBuildDate;
	
	public UptimeGrade(Date startDate, long totalUptime, boolean currentBuildSuccess, Date currentBuildDate){
		if(startDate != null && currentBuildDate != null && startDate.compareTo(currentBuildDate) > 0) {
			throw new IllegalArgumentException("Current build completed time shouldn't be before first build completed time.");
		}
		if(startDate != null){
			this.startDate = new Date(startDate.getTime());
		}
		if(currentBuildDate != null){
			this.currentBuildDate = new Date(currentBuildDate.getTime());
		}
		this.totalUptime = totalUptime;
		this.currentBuildSuccess = currentBuildSuccess;
	}
	
	public double getUptimePercentage(){
		if(startDate == null || currentBuildDate == null){
			return -1;
		}
		Date current = new Date();
		long totalUptimeToCurrent = this.totalUptime;
		if(currentBuildSuccess){
			totalUptimeToCurrent += current.getTime() - currentBuildDate.getTime();
		}
		return totalUptimeToCurrent * 1.0 / (current.getTime() - startDate.getTime());
	}
	
	public String getGrade(){
		double uptimePercentage = getUptimePercentage();
		if(uptimePercentage < 0){
			return null;
		}
		for(int i = 0; i < GRADE_SCALE.length; i++){
			if(uptimePercentage >= GRADE_SCALE[i]){
				return LETTER_GRADE[i];
			}
		}
		return LETTER_GRADE[LETTER_GRADE.length - 1];
	}
	
	public void update(Build newBuild) {
		Date buildCompletedDate = newBuild.getBuildCompletedDate();
		if(buildCompletedDate != null){
			if (startDate == null) {
				startDate = buildCompletedDate;
			} else if (currentBuildSuccess) {
				totalUptime += (buildCompletedDate.getTime() - currentBuildDate.getTime());
			}
			
			currentBuildSuccess = newBuild.isSuccessful();
			currentBuildDate = buildCompletedDate;
		}
	}
}
