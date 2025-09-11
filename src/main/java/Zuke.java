import Task.Parser.CommandParser;
import Task.Parser.DeadlineParser;
import Task.Parser.EventParser;
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
                    try {
                        if (tasks.isEmpty()) {
                            throw new ZukeException.EmptyListException();
                        } else {
                            Ui.showList(tasks);
                            break;
                        }
                    } catch (ZukeException.EmptyListException e) {
                        Ui.error(e.getMessage());
                        break;
                    }


                case MARK: {

                    try {
                        if (tasks.isEmpty()) {
                            throw new ZukeException.EmptyListException();
                        } else {
                            Integer idx = CommandParser.parseIndexOrNull(c.arg, tasks.size());
                            if (idx == null) {
                                break;
                            }

                            tasks.mark(idx);
                            Ui.showMarked(tasks.get(idx), true);
                            break;
                        }
                    } catch (ZukeException.EmptyListException e) {
                        Ui.error(e.getMessage());
                        break;
                    }

                }

                case UNMARK: {

                    try {
                        if (tasks.isEmpty()) {
                            throw new ZukeException.EmptyListException();
                        } else {
                            Integer idx = CommandParser.parseIndexOrNull(c.arg, tasks.size());
                            if (idx == null) break;
                            tasks.unmark(idx);
                            Ui.showMarked(tasks.get(idx), false);
                            break;
                        }
                    } catch (ZukeException.EmptyListException e) {
                        Ui.error(e.getMessage());
                        break;
                    }
                }

                case TODO:{

                    try {
                        if(c.arg == null) {
                            throw new ZukeException.MissingDescriptionException();
                        }
                        tasks.addTodo(c.arg);                 // c.arg == original line
                        Ui.showAdded(tasks);
                        break;

                    } catch (ZukeException.MissingDescriptionException e) {
                        Ui.error(e.getMessage());
                        break;
                    }

                }

                case DEADLINE: {



                    try {
                        if(c.arg == null) {
                            throw new ZukeException.MissingDescriptionException();
                        }

                        String[] arguments = DeadlineParser.argumentParser(c.arg);
                        String argumentErrors = DeadlineParser.checkArgumentFormat(arguments[0], arguments[1]);
                        if(!argumentErrors.isEmpty()) {
                            throw new ZukeException.MissingArgumentException("The following parts are empty:" + argumentErrors + "\nplease enter an event with valid format.");
                        }

                        tasks.addEvent(arguments[0], arguments[1], arguments[2]);
                        Ui.showAdded(tasks);
                        break;

                    } catch (ZukeException.MissingDescriptionException | ZukeException.MissingArgumentException e) {
                        Ui.error(e.getMessage());
                        break;

                    }
                }

                case EVENT: {

                    try {
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

                    } catch (ZukeException.MissingDescriptionException | ZukeException.MissingArgumentException e) {
                        Ui.error(e.getMessage());
                        break;

                    }

                }


                case UNKNOWN:
                    throw new ZukeException.UnknowInputException();
                }

            } catch (ZukeException.UnknowInputException e) {
                Ui.error(e.getMessage());
            }


        }
    }
}
