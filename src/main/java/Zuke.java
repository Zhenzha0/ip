import command.CommandHandler;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import exception.ZukeException;

import java.io.FileNotFoundException;

/**
 * Main class for the Zuke task management application.
 * Initializes the application, loads stored data, and starts the command processing loop.
 */
public class Zuke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Zuke application instance.
     * Initializes the task list and storage, then attempts to load previously saved tasks.
     *
     * @param filepath The file path for task storage.
     */
    public Zuke(String filepath) {
        Ui.welcome();
        tasks = new TaskList();
        storage = new Storage(filepath);

        try {
            storage.load(tasks);

        } catch (FileNotFoundException e) {
            Ui.showNoStorageFile();
        } catch (ZukeException.MissingTimeException e) {
            System.out.println("Memory corrupted");
        }
    }

    /**
     * Starts the main application loop.
     * Processes user commands until the application is terminated.
     */
    public void run() {
        new CommandHandler(tasks, storage).handleCommands();

    }

    /**
     * Main entry point for the Zuke application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Zuke("data/zuke.text").run();
    }
}
