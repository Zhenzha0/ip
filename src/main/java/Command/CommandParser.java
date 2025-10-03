package command;

import exception.ZukeException;
import ui.Ui;

/**
 * Parses user input into executable commands.
 * This class handles the conversion of text input into structured Command objects.
 */
public class CommandParser {

    /**
     * Enumeration of all possible command types in the application.
     */
    public enum Type { LIST, MARK, UNMARK, BYE, TODO, DEADLINE, EVENT, DELETE, FIND, FIND_DATE, GUIDE, UNKNOWN }

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
    public static Command parseCommand(String line) {
        String[] parts = line.trim().split("\\s+", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : null;

        switch (commandWord) {
        case "list":   return new Command(Type.LIST, null);
        case "mark":   return new Command(Type.MARK, arguments);
        case "unmark": return new Command(Type.UNMARK, arguments);
        case "bye":    return new Command(Type.BYE, null);
        case "todo":    return new Command(Type.TODO, arguments);
        case "event": return new Command(Type.EVENT, arguments);
        case "deadline": return new Command(Type.DEADLINE, arguments);
        case "delete": return new Command(Type.DELETE, arguments);
        case "find":    return new Command(Type.FIND, arguments);
        case "date": return new Command(Type.FIND_DATE, arguments);
        case "guide": return new Command(Type.GUIDE, null);
        default:       return new Command(Type.UNKNOWN, line); // treat as add
        }
    }

    /**
     * Parses an index from a string and validates it against the maximum allowed value.
     * Returns null and displays an error message if parsing fails or index is out of range.
     *
     * @param indexText The string to parse as an index.
     * @param maxIndex The maximum allowed index value.
     * @return The zero-based index if valid, null otherwise.
     */
    public static Integer parseIndexOrNull(String indexText, int maxIndex) {

        try {
            if (indexText == null) {
                throw new ZukeException.MissingIndexException();
            }

            int idx1 = Integer.parseInt(indexText);
            if (idx1 < 1 || idx1 > maxIndex) {
                throw new ZukeException.IndexOutOfRangeException(maxIndex);
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
