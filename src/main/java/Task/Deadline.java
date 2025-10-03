package task;

import task.parser.DateTimeParser;
import task.parser.DeadlineParser;

/**
 * Represents a task with a deadline.
 * A deadline task has a description and a specific date/time by which it must be completed.
 */
public class Deadline extends Task {

    private DateTimeParser deadline;

    /**
     * Creates a Deadline task with a description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline for the task.
     */
    public Deadline(String description, DateTimeParser deadline) {
        super(description);
        setDeadline(deadline);
    }

    /**
     * Creates a Deadline task from a parsed deadline object.
     *
     * @param parsedDeadline The DeadlineParser containing parsed task information.
     */
    public Deadline(DeadlineParser parsedDeadline) {
        super(parsedDeadline.getDescription());
        setDeadline(parsedDeadline.getBy());
    }

    /**
     * Gets the deadline for this task.
     *
     * @return The DateTimeParser containing the deadline information.
     */
    public DateTimeParser getDeadline() {
        return deadline;
    }


    /**
     * Sets the deadline for this task.
     *
     * @param deadline The deadline to set.
     */
    public void setDeadline(DateTimeParser deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns the marker string indicating task type and completion status.
     *
     * @return A string marker like "[D][X]" or "[D][ ]".
     */
    public String marker(){
        String type = "[D]";
        return isDone ? type + "[X]" : type + "[ ]";
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string showing the task marker, description, and deadline.
     */
    public String toString() {
        return marker() + " " + description + " (" + "by: " + deadline + ")" ;
    }

    /**
     * Returns the deadline in memory storage format.
     *
     * @return The deadline string formatted for file storage.
     */
    public String toMemoryBy() {
        return deadline.toMemoryDateTime();
    }

}
