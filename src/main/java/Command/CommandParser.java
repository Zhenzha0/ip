package Command;

import Exception.ZukeException;
import App.Ui;


public class CommandParser {

    public enum Type { LIST, MARK, UNMARK, BYE, TODO, DEADLINE, EVENT, DELETE, FIND, FIND_DATE, UNKNOWN }

    public static class Command {
        public final Type type;
        public final String arg;   // for ADD or index text for MARK/UNMARK

        public Command(Type type, String arg) {
            this.type = type;
            this.arg = arg;
        }
    }

    public static Command parse(String line) {
        String[] parts = line.trim().split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String rest = parts.length > 1 ? parts[1] : null;

        switch (cmd) {
        case "list":   return new Command(Type.LIST, null);
        case "mark":   return new Command(Type.MARK, rest);
        case "unmark": return new Command(Type.UNMARK, rest);
        case "bye":    return new Command(Type.BYE, null);
        case "todo":    return new Command(Type.TODO, rest);
        case "event": return new Command(Type.EVENT, rest);
        case "deadline": return new Command(Type.DEADLINE, rest);
        case "delete": return new Command(Type.DELETE, rest);
        case "find":    return new Command(Type.FIND, rest);
        case "date": return new Command(Type.FIND_DATE, rest);
        default:       return new Command(Type.UNKNOWN, line); // treat as add
        }
    }

    /** returns zero-based index or null (and prints error via Ui) */
    public static Integer parseIndexOrNull(String text, int max) {

        try {
            if (text == null) {
                throw new ZukeException.MissingIndexException();
            }

            int idx1 = Integer.parseInt(text);
            if (idx1 < 1 || idx1 > max) {
                throw new ZukeException.IndexOutOfRangeException(max);
            }
            return idx1 - 1;
        } catch (NumberFormatException e) {
            Ui.error("Index must be a number, e.g., 'unmark 3'.");
            return null;
        } catch (ZukeException.MissingIndexException | ZukeException.IndexOutOfRangeException e) {
            Ui.error(e.getMessage());
            return null;
        }
    }
}
