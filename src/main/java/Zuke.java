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

            switch (c.type) {
            case BYE:
                Ui.bye();
                return;

            case LIST:
                if (tasks.isEmpty()) {
                    Ui.error("Your list is empty. Add a task first.");
                } else {
                    Ui.showList(tasks);
                }
                break;

            case MARK: {
                if (tasks.isEmpty()) {
                    Ui.error("Your list is empty. Add a task first."); break;
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
                if (tasks.isEmpty()) { Ui.error("Your list is empty. Add a task first."); break; }
                Integer idx = CommandParser.parseIndexOrNull(c.arg, tasks.size());
                if (idx == null) break;
                tasks.unmark(idx);
                Ui.showMarked(tasks.get(idx), false);
                break;
            }

            case TODO:{
                int index = tasks.addTodo(c.arg);                 // c.arg == original line
                Ui.showAdded(tasks, index);
                break;
            }

            case DEADLINE: {
                String[] arguments = DeadlineParser.argumentParser(c.arg);
                int index = tasks.addDeadline(arguments[0], arguments[1]);
                Ui.showAdded(tasks, index);
                break;
            }

            case EVENT: {
                String[] arguments = EventParser.argumentParser(c.arg);
                int index = tasks.addEvent(arguments[0], arguments[1], arguments[2]);
                Ui.showAdded(tasks, index);
                break;
            }


            case UNKNOWN:

                break;
            }
        }
    }
}
