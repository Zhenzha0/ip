package Searchers;

import Task.Task;
import Task.TaskList;
import Task.Event;
import Task.Deadline;

import java.time.LocalDate;


public class DateSearcher {
    private TaskList tasks;
    private LocalDate searchDate;
    private TaskList matchingTasks;

    public TaskList getMatchingTasks() {
        return matchingTasks;
    }

    public DateSearcher(TaskList tasks, LocalDate searchDate){
        this.tasks = tasks;
        this.searchDate = searchDate;
        search();
    }

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

    public void DeadlineComparator(Deadline deadline, LocalDate by){
        if(by.isEqual(searchDate)) {
            matchingTasks.getTasks().add(deadline);
        }
    }

    public void EventComparator(Event event, LocalDate from, LocalDate to){
        if(from.isBefore(searchDate) && to.isAfter(searchDate)) {
            matchingTasks.getTasks().add(event);
        }
    }

}
