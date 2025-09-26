package Searchers;

import Task.Task;
import Task.TaskList;
import Task.Event;
import Task.Deadline;

import java.time.LocalDate;

/**
 * Searches for tasks that occur on or around a specific date.
 * Handles both Deadline tasks (matching the deadline date) and Event tasks (date within event period).
 */
public class DateSearcher {
    private TaskList tasks;
    private LocalDate searchDate;
    private TaskList matchingTasks;

    /**
     * Gets the list of tasks matching the search criteria.
     *
     * @return A TaskList containing all matching tasks.
     */
    public TaskList getMatchingTasks() {
        return matchingTasks;
    }

    /**
     * Creates a new DateSearcher and performs the search immediately.
     *
     * @param tasks The TaskList to search through.
     * @param searchDate The date to search for.
     */
    public DateSearcher(TaskList tasks, LocalDate searchDate){
        this.tasks = tasks;
        this.searchDate = searchDate;
        search();
    }

    /**
     * Performs the search operation through all tasks.
     * Checks Deadline tasks for exact date match and Event tasks for date within range.
     */
    public void search() {
        matchingTasks = new TaskList();
        for(Task task : tasks.getTasks()){
            if(task instanceof Deadline deadline){
                LocalDate by = deadline.getDeadline().getParsedDate();
                DeadlineComparator(deadline, by);
            }

            if(task instanceof Event event){
                LocalDate from = event.getFrom().getParsedDate();
                LocalDate to = event.getTo().getParsedDate();
                EventComparator(event, from, to);

            }

        }
    }

    /**
     * Checks if a Deadline task matches the search date.
     * Adds the task to matching tasks if the deadline equals the search date.
     *
     * @param deadline The Deadline task to check.
     * @param by The deadline date of the task.
     */
    public void DeadlineComparator(Deadline deadline, LocalDate by){
        if(by.isEqual(searchDate)) {
            matchingTasks.getTasks().add(deadline);
        }
    }

    /**
     * Checks if an Event task overlaps with the search date.
     * Adds the task to matching tasks if the search date falls within the event period.
     *
     * @param event The Event task to check.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public void EventComparator(Event event, LocalDate from, LocalDate to){
        if(from.isBefore(searchDate) && to.isAfter(searchDate)) {
            matchingTasks.getTasks().add(event);
        }
    }

}
