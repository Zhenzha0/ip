package Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void addDeadline(String description, String deadline) {
        tasks.add(new Deadline(description, deadline));
    }

    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    public int size() { return tasks.size(); }
    public boolean isEmpty() { return tasks.isEmpty(); }

    public Task get(int zeroBasedIndex) {
        return tasks.get(zeroBasedIndex);
    }

    public void mark(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).mark();
    }

    public void unmark(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).unmark();
    }

public List<Task> getTasks() { return tasks; }

    public String render() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
        }
        return sb.toString();
    }
}

