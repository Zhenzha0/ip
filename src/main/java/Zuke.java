import Task.Parser.CommandParser;
import Task.Parser.DeadlineParser;
import Task.Parser.EventParser;
import Task.Task;
import Task.TaskList;
import App.Ui;
import Exception.ZukeException;

import java.util.Scanner;

public class Zuke {
    public static void main(String[] args) {
        Ui.logo();
        Ui.hello();

        TaskList tasks = new TaskList();
        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            CommandParser.Command c = CommandParser.parse(line);

            try{
                switch (c.type) {
                case BYE:
                    Ui.bye();
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

                    String[] arguments = DeadlineParser.argumentParser(c.arg);
                    String argumentErrors = DeadlineParser.checkArgumentFormat(arguments[0], arguments[1]);

                    if(!argumentErrors.isEmpty()) {
                        throw new ZukeException.MissingArgumentException("The following parts are empty:" + argumentErrors + "\nplease enter an event with valid format.");
                    }

                    tasks.addDeadline(arguments[0], arguments[1]);
                    Ui.showAdded(tasks);
                    break;
                }

                case EVENT: {
                    if(c.arg == null) {
                        throw new ZukeException.MissingDescriptionException();
                    }

                    String[] arguments = EventParser.argumentParser(c.arg);
                    String argumentErrors = EventParser.checkArgumentFormat(arguments[0], arguments[1], arguments[2]);
                    if(!argumentErrors.isEmpty()) {
                        throw new ZukeException.MissingArgumentException("The following parts are empty:" + argumentErrors + "\nplease enter an event with valid format.");
                    }

                    tasks.addEvent(arguments[0], arguments[1], arguments[2]);
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

            } catch (ZukeException.UnknowInputException | ZukeException.MissingArgumentException | ZukeException.EmptyListException | ZukeException.MissingDescriptionException e) {
                Ui.error(e.getMessage());
            }


        }
    }
}
