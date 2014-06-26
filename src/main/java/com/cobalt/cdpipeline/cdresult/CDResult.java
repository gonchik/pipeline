package com.cobalt.cdpipeline.cdresult;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CDResult contains all the information needed for one single row
 * of the table display for CDPipeline Plugin Project.
 */
public class CDResult {
	private String projectName, planName;
	private Date lastDeploymentTime, lastUpdate;
	private int numChanges;
	
	private Set<Contributor> contributors;
	private Build currentBuild;
	private List<PipelineStage> pipelineStages; 
	
	/**
	 * Construct a CDResult Object.
	 * Access modifier left out intentionally for package protection.
	 * 
	 * @param projectName
	 * @param planName
	 */
	CDResult(String projectName, String planName) {
		this.projectName = projectName;
		this.planName = planName;
		
		contributors = new HashSet<Contributor>();
		pipelineStages = new ArrayList<PipelineStage>();
	}
	
	/**
	 * Set lastDeploymentTime to the given date.
	 * Access modifier left out intentionally for package protection.
	 * 
	 * @param lastDeployment
	 */
	void setLastDeploymentTime(Date lastDeployment) {
		this.lastDeploymentTime = lastDeployment;
	}
	
	/**
	 * Set lastUpdate time to the given date.
	 * Access modifier left out intentionally for package protection.
	 * 
	 * @param lastUpdate
	 */
	void setLastUpdateTime(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	/**
	 * Set number of changes to the given number.
	 * Access modifier left out intentionally for package protection.
	 * 
	 * @param numChanges
	 */
	void setNumChanges(int numChanges) {
		this.numChanges = numChanges;
	}
	
	/**
	 * Add given contributor to contributors set.
	 * Access modifier left out intentionally for package protection.
	 * 
	 * @param contributor
	 */
	void addContributor(Contributor contributor) {
		contributors.add(contributor);
	}
	
	/**
	 * Add given PipelineStage to the pipelineStages list.
	 * Access modifier left out intentionally for package protection.
	 * 
	 * @param stage
	 */
	void addPipelineStageToList(PipelineStage stage) {
		pipelineStages.add(stage);
	}
	
	/**
	 * Set current build information to the given Build
	 * 
	 * @param current
	 */
	void setCurrentBuild(Build current){
		this.currentBuild = current;
	}
	
	/**
	 * Get the project name associated with this result.
	 * 
	 * @return project name
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * Get the plan name associated with this result.
	 * 
	 * @return plan name
	 */
	public String getPlanName() {
		return planName;
	}
	
	/**
	 * Return last deployment date and time.
	 * Return null if there's no deployment yet.
	 * 
	 * @return date/time of last deployment
	 */
	public Date getLastDeploymentTime(){
		return this.lastDeploymentTime;
	}
	
	/**
	 * Return days since last deployment until given date
	 * Return -1 if given date is before last deployment date or 
	 * no last deployment is found.
	 * 
	 * @param currentDate The date to compare to
	 * @return the number of days from last deployment date to given date
	 */
	public int getDaysSinceDeploymentFrom(Date currentDate){
		if(this.lastDeploymentTime == null || this.lastDeploymentTime.compareTo(currentDate) > 0){
			return -1;
		}
		long deploymentTime = this.lastDeploymentTime.getTime();
		long currentTime = currentDate.getTime();
		return (int) ((currentTime - deploymentTime) / (1000 * 60 * 60 * 24));
	}
	
	/**
	 * Return last update (the most current build) date and time
	 * 
	 * @return date/time of last update
	 */
	public Date getLastUpdateTime(){
		return this.lastUpdate;
	}
	
	/**
	 * Return changes(commits) since last deployment
	 * 
	 * @return number of changes since last deployment
	 */
	public int getNumChanges(){
		return this.numChanges;
	}
	
	/**
	 * Return current build information.
	 * Return null if there's no build yet.
	 * 
	 * @return a Build object that contains information about the most
	 *         current build.
	 */
	public Build getCurrentBuild(){
		return this.currentBuild;
	}
	
	/**
	 * Return all contributors since last deployment.
	 * 
	 * @return a Set of Contributor
	 */
	public Set<Contributor> getContributors(){
		return this.contributors;
	}
	
	/**
	 * Return all pipeline stages of the current build.
	 * 
	 * @return a List of PipelineStates with the list starting with the first stage.
	 */
	public List<PipelineStage> getPipelineStages(){
		return this.pipelineStages;
	}
}