package Command;

import Command.CommandParser;
import Memory.Storage;
import Task.Parser.DateTimeParser;
import Task.Parser.DeadlineParser;
import Task.Parser.EventParser;
import Task.Task;
import Task.TaskList;
import App.Ui;
import Exception.ZukeException;
import Searchers.DateSearcher;
import Searchers.DescriptionSearcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the processing and execution of user commands.
 * This class manages the main command loop and delegates command execution.
 */
public class CommandHandler {
    private Storage storage;
    private TaskList tasks;

    /**
     * Sets the storage instance for the command handler.
     *
     * @param storage The Storage instance to use for saving/loading tasks.
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Sets the task list for the command handler.
     *
     * @param tasks The TaskList to operate on.
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a new CommandHandler with the specified task list and storage.
     *
     * @param tasks The TaskList to manage.
     * @param storage The Storage instance for persistence.
     */
    public CommandHandler(TaskList tasks, Storage storage){
        setStorage(storage);
        setTasks(tasks);
    }

    /**
     * Handles the main command processing loop.
     * Continuously reads user input, parses commands, and executes the appropriate actions.
     * The loop terminates when the user enters the "bye" command.
     */
    public void handleCommands() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            CommandParser.Command c = CommandParser.parse(line);

            try{
                switch (c.type) {
                case BYE:
                    Ui.bye();

                    storage.save(tasks);

                    return;

                case LIST:
                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    Ui.showList(tasks);
                    break;


                case MARK: {
                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    Integer idx = CommandParser.parseIndexOrNull(c.arg, tasks.size());
                    if (idx == null) {
                        break;
                    }

                    tasks.mark(idx);
                    Ui.showMarked(tasks.get(idx), true);
                    break;
                }

                case UNMARK: {
                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    Integer idx = CommandParser.parseIndexOrNull(c.arg, tasks.size());
                    if (idx == null) break;
                    tasks.unmark(idx);
                    Ui.showMarked(tasks.get(idx), false);
                    break;
                }

                case TODO: {
                    if(c.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    tasks.addTodo(c.arg);                 // c.arg == original line
                    Ui.showAdded(tasks);
                    break;
                }

                case DEADLINE: {
                    if(c.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    DeadlineParser parsedDeadline = new DeadlineParser(c.arg);

                    tasks.addDeadline(parsedDeadline);
                    Ui.showAdded(tasks);
                    break;
                }

                case EVENT: {
                    if(c.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    EventParser parsedEvent = new EventParser(c.arg);

                    tasks.addEvent(parsedEvent);
                    Ui.showAdded(tasks);
                    break;
                }

                case DELETE: {
                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    Integer idx = CommandParser.parseIndexOrNull(c.arg, tasks.size());
                    if (idx == null) {
                        break;
                    }

                    Task deletedTask = tasks.delete(idx);
                    Ui.showDeleted(deletedTask, tasks);
                    break;
                }

                case FIND: {
                    if(c.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    DescriptionSearcher matchingTasks = new DescriptionSearcher(tasks, c.arg);
                    Ui.showFind(matchingTasks.getMatchingTasks());
                    break;
                }

                case FIND_DATE: {
                    if(c.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    DateTimeParser finder = new DateTimeParser(c.arg);

                    DateSearcher matchingTasks = new DateSearcher(tasks, finder.getParsedDate());
                    Ui.showFind(matchingTasks.getMatchingTasks());
                    break;

                }

                case UNKNOWN:
                    throw new ZukeException.UnknowInputException();
                }

            } catch (ZukeException.UnknowInputException | ZukeException.MissingArgumentException | ZukeException.EmptyListException | ZukeException.MissingDescriptionException | ZukeException.MissingTimeException | IOException | IllegalArgumentException e) {
                Ui.error(e.getMessage());
            }

        }

    }

}
