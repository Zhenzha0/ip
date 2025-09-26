package Searchers;
import Task.Task;
import Task.TaskList;

/**
 * Searches for tasks whose descriptions contain a specified search string.
 * The search is case-insensitive.
 */
public class DescriptionSearcher {
    private TaskList tasks;
    private String searchString;
    private TaskList matchingTasks;

    /**
     * Gets the list of tasks matching the search criteria.
     *
     * @return A TaskList containing all tasks with descriptions matching the search string.
     */
    public TaskList getMatchingTasks() {
        return matchingTasks;
    }

    /**
     * Creates a new DescriptionSearcher and performs the search immediately.
     *
     * @param tasks The TaskList to search through.
     * @param searchString The string to search for in task descriptions.
     */
    public DescriptionSearcher(TaskList tasks, String searchString){
        this.tasks = tasks;
        this.searchString = searchString;
        search();
    }

    /**
     * Performs a case-insensitive search through all task descriptions.
     * Adds tasks to the matching list if their description contains the search string.
     */
    public void search() {
        matchingTasks = new TaskList();
        for(Task task : tasks.getTasks()){
            if(task.getDescription().toLowerCase().contains(searchString.toLowerCase())){
                matchingTasks.getTasks().add(task);
            }
        }
    }

}
