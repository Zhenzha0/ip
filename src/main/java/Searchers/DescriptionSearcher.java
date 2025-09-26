package Searchers;
import Task.Task;
import Task.TaskList;

public class DescriptionSearcher {
    private TaskList tasks;
    private String searchString;
    private TaskList matchingTasks;

    public TaskList getMatchingTasks() {
        return matchingTasks;
    }

    public DescriptionSearcher(TaskList tasks, String searchString){
        this.tasks = tasks;
        this.searchString = searchString;
        search();
    }

    public void search() {
        matchingTasks = new TaskList();
        for(Task task : tasks.getTasks()){
            if(task.getDescription().toLowerCase().contains(searchString.toLowerCase())){
                matchingTasks.getTasks().add(task);
            }
        }
    }

}
