package Task;

/**
 * Represents a basic task with a description and completion status.
 * This is the abstract base class for all task types in the application.
 */
public abstract class Task {
    protected String description;
    protected boolean done;

    /**
     * Creates a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns the status marker for the task.
     *
     * @return "[X]" if done, "[ ]" if not done.
     */
    public String marker() { return done ? "[X]" : "[ ]"; }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string showing the status marker and description.
     */
    public String toString() {
        return marker() + " " + description;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getDone() {
        return done;
    }

}
