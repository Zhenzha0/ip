package Memory;

import App.Ui;
import Task.Task;
import Task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import java.io.FileWriter;
import java.util.Scanner;

import Exception.ZukeException;

/**
 * Handles loading and saving of task data to persistent storage.
 * Manages file operations for task persistence across application sessions.
 */
public class Storage {

    private String filePath;

    /**
     * Sets the file path for storage operations.
     *
     * @param filePath The path to the storage file.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        setFilePath(filePath);

    }

    /**
     * Loads tasks from the storage file into the provided TaskList.
     * Displays loading progress messages to the user.
     *
     * @param tasks The TaskList to populate with loaded tasks.
     * @throws FileNotFoundException If the storage file does not exist.
     * @throws ZukeException.MissingTimeException If time data in the file is invalid.
     */
    public void load(TaskList tasks) throws FileNotFoundException, ZukeException.MissingTimeException {
        Ui.showLoadingData();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        if (f.length() == 0) {
            System.out.println("Data file is empty");
            return;
        }
        while(s.hasNextLine()){
            String line = s.nextLine();
            tasks.getTasks().add(MemoryParser.parseline(line));
        }
        Ui.showDoneLoadingData();
    }

    /**
     * Saves all tasks from the TaskList to the storage file.
     * Overwrites the existing file with the current task data.
     *
     * @param tasks The TaskList containing tasks to save.
     * @throws IOException If an error occurs during file writing.
     */
    public void save(TaskList tasks) throws IOException {
        try{

            File file = new File(filePath);

            if (file.exists()) {
                file.delete(); // deletes the physical file
            }

            FileWriter fw = new FileWriter(file);

            if(tasks.getTasks().isEmpty()){
                System.out.println("Nothing to save");
                fw.close();
                file.delete();
                return;
            }
            for(Task task : tasks.getTasks()) {
                fw.write(formatTask(task) + System.lineSeparator());
            }

            fw.close();

        } catch(Exception e){
            System.out.println("Something went wrong: " + e.getMessage());

        }
    }

    /**
     * Formats a Task object into a string suitable for storage.
     * The format varies based on task type (Todo, Deadline, or Event).
     *
     * @param task The Task to format.
     * @return A formatted string representation of the task for storage.
     */

    public static String formatTask(Task task){
        String formatedString = "";
        if (task instanceof Todo) {
            formatedString = "T | ";
        } else if (task instanceof Deadline) {
            formatedString = "D | ";
        } else if (task instanceof Event) {
            formatedString = "E | ";
        }


        formatedString += task.getDone();

        if (task instanceof Todo) {
            formatedString += " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            formatedString += " | "  + task.getDescription() + " | " + ((Deadline) task).toMemoryBy();
        } else if (task instanceof Event) {
            formatedString += " | "  + task.getDescription() + " | " + ((Event) task).toMemoryFrom() + " | " + ((Event) task).toMemoryTo();
        }

        return formatedString;

    }

}
