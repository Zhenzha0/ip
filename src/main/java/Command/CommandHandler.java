package command;

import storage.Storage;
import task.parser.DateTimeParser;
import task.parser.DeadlineParser;
import task.parser.EventParser;
import task.Task;
import task.TaskList;
import ui.Ui;
import exception.ZukeException;
import searchers.DateSearcher;
import searchers.DescriptionSearcher;

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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            CommandParser.Command command = CommandParser.parseCommand(line);

            try{
                switch (command.type) {
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

                    Integer idx = CommandParser.parseIndexOrNull(command.arg, tasks.size());
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

                    Integer idx = CommandParser.parseIndexOrNull(command.arg, tasks.size());
                    if (idx == null) break;
                    tasks.unmark(idx);
                    Ui.showMarked(tasks.get(idx), false);
                    break;
                }

                case TODO: {
                    if(command.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    tasks.addTodo(command.arg);                 // c.arg == original line
                    Ui.showAdded(tasks);
                    break;
                }

                case DEADLINE: {
                    if(command.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    DeadlineParser parsedDeadline = new DeadlineParser(command.arg);

                    tasks.addDeadline(parsedDeadline);
                    Ui.showAdded(tasks);
                    break;
                }

                case EVENT: {
                    if(command.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    EventParser parsedEvent = new EventParser(command.arg);

                    tasks.addEvent(parsedEvent);
                    Ui.showAdded(tasks);
                    break;
                }

                case DELETE: {
                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    Integer idx = CommandParser.parseIndexOrNull(command.arg, tasks.size());
                    if (idx == null) {
                        break;
                    }

                    Task deletedTask = tasks.delete(idx);
                    Ui.showDeleted(deletedTask, tasks);
                    break;
                }

                case FIND: {
                    if(command.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    DescriptionSearcher matchingTasks = new DescriptionSearcher(tasks, command.arg);
                    Ui.showFind(matchingTasks.getMatchingTasks());
                    break;
                }

                case FIND_DATE: {
                    if(command.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    if (tasks.isEmpty()) {
                        throw new ZukeException.EmptyListException();
                    }

                    DateTimeParser finder = new DateTimeParser(command.arg);

                    DateSearcher matchingTasks = new DateSearcher(tasks, finder.getParsedDate());
                    Ui.showFind(matchingTasks.getMatchingTasks());
                    break;

                }

                case GUIDE: {
                    Ui.showQuickGuide();
                    break;
                }

                case UNKNOWN:
                    throw new ZukeException.UnknownInputException();
                }

            } catch (ZukeException.UnknownInputException | ZukeException.MissingArgumentException | ZukeException.EmptyListException | ZukeException.MissingDescriptionException | ZukeException.MissingTimeException | IOException | IllegalArgumentException e) {
                Ui.error(e.getMessage());
            }

        }

    }

}
