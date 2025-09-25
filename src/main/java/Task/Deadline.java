package Task;

import Task.Parser.DateTimeParser;
import Task.Parser.DeadlineParser;

public class Deadline extends Task {

    private DateTimeParser deadline;

    public Deadline(String description, DateTimeParser deadline) {
        super(description);
        setDeadline(deadline);
    }

    public Deadline(DeadlineParser parsedDeadline) {
        super(parsedDeadline.getDescription());
        setDeadline(parsedDeadline.getBy());
    }

    public DateTimeParser getDeadline() {
        return deadline;
    }

    public DateTimeParser getDate() {
        return deadline;
    }

    public void setDeadline(DateTimeParser deadline) {
        this.deadline = deadline;
    }

    public String marker(){
        String type = "[D]";
        return done ? type + "[X]" : type + "[ ]";
    }

    public String toString() {
        return marker() + " " + description + " (" + "by: " + deadline + ")" ;
    }

    public String toMemoryBy() {
        return deadline.toMemoryDateTime();
    }

}
