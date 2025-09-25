package Exception;

public class ZukeException {
    public static class UnknowInputException extends Exception {
        public UnknowInputException() {
            super("Unknown command, please follow format");
        }
    }

    // Exception for missing data
    public static class MissingDescriptionException extends Exception {
        public MissingDescriptionException() {
            super("Bro stop trolling, you only entered the command...");
        }
    }

    // Exception for too many operations
    public static class MissingArgumentException extends Exception {
        public MissingArgumentException(String message) {
            super("The following parts are empty:" + message + "\nplease enter an event with valid format.");
        }
    }

    public static class EmptyListException extends Exception {
        public EmptyListException() {
            super("Your list is empty. Add a task first.");
        }
    }

    public static class MissingIndexException extends Exception {
        public MissingIndexException() {
            super("Please provide an index, e.g., 'mark 2'.");
        }
    }

    public static class IndexOutOfRangeException extends Exception {
        public IndexOutOfRangeException(int max) {
            super("Index out of range. Valid: 1.." + max + ".");
        }
    }

    public static class MissingTimeException extends Exception {

    }

}
