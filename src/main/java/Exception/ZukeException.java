package Exception;

/**
 * Container class for all custom exceptions used in the Zuke application.
 */
public class ZukeException {

    /**
     * Exception thrown when an unknown or invalid command is entered.
     */
    public static class UnknowInputException extends Exception {
        public UnknowInputException() {
            super("Unknown command, please follow format");
        }
    }

    /**
     * Exception thrown when a command is missing its required description.
     */
    public static class MissingDescriptionException extends Exception {
        public MissingDescriptionException() {
            super("Bro stop trolling, you only entered the command...");
        }
    }

    /**
     * Exception thrown when required arguments are missing from a command.
     */
    public static class MissingArgumentException extends Exception {

        /**
         * Creates an exception with details about which arguments are missing.
         *
         * @param message Description of the missing arguments.
         */
        public MissingArgumentException(String message) {
            super("The following parts are empty:" + message + "\nplease enter an event with valid format.");
        }
    }

    /**
     * Exception thrown when attempting to perform operations on an empty task list.
     */
    public static class EmptyListException extends Exception {
        public EmptyListException() {
            super("Your list is empty. Add a task first.");
        }
    }

    /**
     * Exception thrown when an index is required but not provided.
     */
    public static class MissingIndexException extends Exception {
        public MissingIndexException() {
            super("Please provide an index, e.g., 'mark 2'.");
        }
    }

    /**
     * Exception thrown when a provided index is outside the valid range.
     */
    public static class IndexOutOfRangeException extends Exception {

        /**
         * Creates an exception indicating the valid index range.
         *
         * @param max The maximum valid index value.
         */
        public IndexOutOfRangeException(int max) {
            super("Index out of range. Valid: 1.." + max + ".");
        }
    }

    /**
     * Exception thrown when a time value is required but not provided.
     */
    public static class MissingTimeException extends Exception {
        public MissingTimeException() { super("No time is provided."); }

    }

}
