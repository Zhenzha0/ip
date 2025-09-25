package Command;

import Command.CommandParser;
import Memory.Storage;
import Task.Parser.DeadlineParser;
import Task.Parser.EventParser;
import Task.Task;
import Task.TaskList;
import App.Ui;
import Exception.ZukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class CommandHandler {
    private Storage storage;
    private TaskList tasks;

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public CommandHandler(TaskList tasks, Storage storage){
        setStorage(storage);
        setTasks(tasks);
    }

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

                case UNKNOWN:
                    throw new ZukeException.UnknowInputException();
                }

            } catch (ZukeException.UnknowInputException | ZukeException.MissingArgumentException | ZukeException.EmptyListException | ZukeException.MissingDescriptionException | IOException e) {
                Ui.error(e.getMessage());
            }

        }

    }

}
