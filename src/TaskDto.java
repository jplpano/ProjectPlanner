import java.util.Date;


public class TaskDto {
	
	private int taskId;
	
	private int duration;
	
	private String taskName;
	
	private Date startDate;
	
	private Date endDate;
	
	private String dependencies;
	
	public int getTaskId() {
		return taskId;
	}
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getDependencies() {
		return dependencies;
	}
	
	public void setDependencies(String dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public String toString() {
		return "TaskDto [taskId=" + taskId + ", duration=" + duration
				+ ", taskName=" + taskName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", dependencies=" + dependencies
				+ "]";
	}
	
}
