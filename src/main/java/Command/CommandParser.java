package Command;

import Exception.ZukeException;
import App.Ui;

/**
 * Parses user input into executable commands.
 * This class handles the conversion of text input into structured Command objects.
 */
public class CommandParser {

    /**
     * Enumeration of all possible command types in the application.
     */
    public enum Type { LIST, MARK, UNMARK, BYE, TODO, DEADLINE, EVENT, DELETE, FIND, FIND_DATE, UNKNOWN }

    /**
     * Represents a parsed command with its type and arguments.
     */
    public static class Command {
        public final Type type;
        public final String arg;   // for ADD or index text for MARK/UNMARK

        public Command(Type type, String arg) {
            this.type = type;
            this.arg = arg;
        }
    }

    /**
     * Parses a user input line into a Command object.
     * Identifies the command type from the first word and extracts any arguments.
     *
     * @param line The user input string to parse.
     * @return A Command object representing the parsed input.
     */
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

    /**
     * Parses an index from a string and validates it against the maximum allowed value.
     * Returns null and displays an error message if parsing fails or index is out of range.
     *
     * @param text The string to parse as an index.
     * @param max The maximum allowed index value.
     * @return The zero-based index if valid, null otherwise.
     */
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
