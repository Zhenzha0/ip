public class Parser {

    public enum Type { LIST, MARK, UNMARK, ADD, BYE }

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
        default:       return new Command(Type.ADD, line); // treat as add
        }
    }

    /** returns zero-based index or null (and prints error via Ui) */
    public static Integer parseIndexOrNull(String text, int max) {
        if (text == null) {
            Ui.error("Please provide an index, e.g., 'mark 2'.");
            return null;
        }
        try {
            int idx1 = Integer.parseInt(text);
            if (idx1 < 1 || idx1 > max) {
                Ui.error("Index out of range. Valid: 1.." + max + ".");
                return null;
            }
            return idx1 - 1;
        } catch (NumberFormatException e) {
            Ui.error("Index must be a number, e.g., 'unmark 3'.");
            return null;
        }
    }
}
