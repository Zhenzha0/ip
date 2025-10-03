package storage;

import task.parser.DateTimeParser;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import exception.ZukeException;

/**
 * Parses stored task data from file format back into Task objects.
 */
public class StorageParser {

    /**
     * Parses a single line from the storage file into a Task object.
     * The line format is: TaskType | isDone | description | [dates...]
     *
     * @param line The line from the storage file to parse.
     * @return The Task object created from the parsed line.
     * @throws ZukeException.MissingTimeException If the time information is invalid or missing.
     */
    public static Task parseLine(String line) throws ZukeException.MissingTimeException {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        Task task;

        if(parts[0].equals("T")){
            task = new Todo(parts[2]);
        } else if(parts[0].equals("D")){
            task = new Deadline(parts[2], new DateTimeParser(parts[3]));
        } else {
            task = new Event(parts[2], new DateTimeParser(parts[3]), new DateTimeParser(parts[4]));
        }

        if(parts[1].equals("true")){
            task.mark();
        }

        return task;

    }
}
