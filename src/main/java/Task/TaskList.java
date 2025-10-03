package task;

import task.parser.DeadlineParser;
import task.parser.EventParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks.
 * Provides methods to add, remove, mark, and retrieve tasks.
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Adds a Todo task to the list.
     *
     * @param description The description of the todo task.
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param parsedDeadline The parsed deadline information.
     */
    public void addDeadline(DeadlineParser parsedDeadline) {
        tasks.add(new Deadline(parsedDeadline));
    }

    /**
     * Adds an Event task to the list.
     *
     * @param parsedEvent The parsed event information.
     */
    public void addEvent(EventParser parsedEvent) {
        tasks.add(new Event(parsedEvent));
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() { return tasks.size(); }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() { return tasks.isEmpty(); }

    /**
     * Gets a task at the specified index.
     *
     * @param zeroBasedIndex The zero-based index of the task.
     * @return The Task at the specified index.
     */
    public Task get(int zeroBasedIndex) {
        return tasks.get(zeroBasedIndex);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param zeroBasedIndex The zero-based index of the task to mark.
     */
    public void mark(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).mark();
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param zeroBasedIndex The zero-based index of the task to unmark.
     */
    public void unmark(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).unmark();
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param zeroBasedIndex The zero-based index of the task to delete.
     * @return The deleted Task.
     */
    public Task delete(int zeroBasedIndex) {
        Task returnTask = tasks.get(zeroBasedIndex);
        tasks.remove(zeroBasedIndex);
        return returnTask;

    }

    /**
     * Gets the internal list of tasks.
     *
     * @return The List of all tasks.
     */
    public List<Task> getTasks() { return tasks; }

    /**
     * Renders all tasks as a formatted string.
     * Each task is numbered starting from 1.
     *
     * @return A string containing all tasks with numbering.
     */
    public String render() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
        }
        return sb.toString();
    }
}

