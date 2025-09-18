package Memory;

import Task.Task;
import Task.Task;
import Task.Deadline;
import Task.Event;
import Task.Todo;

public class MemoryParser {

    public static Task parseline(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        Task task;

        if(parts[0].equals("T")){
            task = new Todo(parts[2]);
        } else if(parts[0].equals("D")){
            task = new Deadline(parts[2], parts[3]);
        } else {
            task = new Event(parts[2], parts[3], parts[4]);
        }

        if(parts[1].equals("true")){
            task.mark();
        }

        return task;

    }
}
