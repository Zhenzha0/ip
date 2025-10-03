package task;

/**
 * Represents a simple todo task without any date/time constraints.
 * A todo task only has a description and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the marker string indicating task type and completion status.
     *
     * @return A string marker like "[T][X]" or "[T][ ]".
     */
    public String marker(){
        String type = "[T]";
        return isDone ? type + "[X]" : type + "[ ]";
    }

}
