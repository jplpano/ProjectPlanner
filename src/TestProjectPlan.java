import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestProjectPlan {

	private static Map<Integer, TaskDto> tasks;
	
	private static void initTasks(String dateInString) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("M-dd-yyyy");
		Date startDate = sdf.parse(dateInString);
		
		tasks = new HashMap<Integer, TaskDto>();
		
		TaskDto taskDto = new TaskDto();
		taskDto.setTaskId(102);
		taskDto.setDuration(5);
		taskDto.setTaskName("Project Initialization");
		taskDto.setStartDate(startDate);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(103);
		taskDto.setDuration(10);
		taskDto.setTaskName("Analysis & Requirements");
		taskDto.setDependencies("102");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(104);
		taskDto.setDuration(10);
		taskDto.setTaskName("Design");
		taskDto.setDependencies("102, 103");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(105);
		taskDto.setDuration(30);
		taskDto.setTaskName("Programming");
		taskDto.setDependencies("102, 103, 104");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(106);
		taskDto.setDuration(5);
		taskDto.setTaskName("Prepare Test Scripts");
		taskDto.setDependencies("102, 103");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(107);
		taskDto.setDuration(8);
		taskDto.setTaskName("Integration and Testing");
		taskDto.setDependencies("102, 103, 104, 105, 106");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(108);
		taskDto.setDuration(3);
		taskDto.setTaskName("Production Deployment");
		taskDto.setDependencies("102, 103, 104, 105, 106, 107");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(109);
		taskDto.setDuration(6);
		taskDto.setTaskName("Users Training & Testing");
		taskDto.setDependencies("102, 103, 104, 105, 106, 107");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
		
		taskDto = new TaskDto();
		taskDto.setTaskId(110);
		taskDto.setDuration(4);
		taskDto.setTaskName("Infra Setup");
		taskDto.setDependencies("102, 103, 104");
		taskDto.setStartDate(null);
		taskDto.setEndDate(null);
		tasks.put(taskDto.getTaskId(), taskDto);
	}
	
	private static void processSchedule() {

		List<TaskDto> taskList = new ArrayList<TaskDto>(tasks.values());
		
		for(TaskDto task : taskList) {
			String[] dependencies = task.getDependencies()!=null ? task.getDependencies().split(", ") : new String[1];
			for(String dependency : dependencies) {
				if(dependency!=null) {
					int dep = Integer.valueOf(dependency);
					TaskDto taskDependency = tasks.get(dep);
					
					if(task.getStartDate()!=null) {
						if(taskDependency.getEndDate()!=null && taskDependency.getEndDate().after(task.getStartDate())) {
							task.setStartDate(taskDependency.getEndDate());
						}
					} else {
						task.setStartDate(taskDependency.getEndDate());
					}
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(task.getStartDate());
					cal.add(Calendar.DAY_OF_MONTH, task.getDuration());
					
					if(task.getEndDate()!=null) {
						if(cal.getTime().after(task.getEndDate())) {
							task.setEndDate(cal.getTime());
						}
					} else {
						task.setEndDate(cal.getTime());
					}
				} else {
					Calendar cal = Calendar.getInstance();
					cal.setTime(task.getStartDate());
					cal.add(Calendar.DAY_OF_MONTH, task.getDuration());
					task.setEndDate(cal.getTime());
				}
			}
		}
		
		Collections.sort(taskList, new Comparator<TaskDto>(){
			
			@Override
			public int compare(TaskDto o1, TaskDto o2) {
				return o1.getStartDate().compareTo(o2.getStartDate()) +
						o1.getEndDate().compareTo(o2.getEndDate());
			}
		});
		
		
		for(TaskDto task : taskList) { // Print the schedule sorted by start and end dates
			System.out.println(task);
		}
		
	}
	
	public static void main(String[] args) throws ParseException {

		initTasks("07-15-2019"); // Project start date AUG 15, 2019
		processSchedule();
		
	}

}
