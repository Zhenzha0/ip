package Task.Parser;

import Exception.ZukeException;

/**
 * Parses deadline command arguments into structured components.
 * Extracts the task description and deadline date/time from the input string.
 */
public class DeadlineParser {

    private String description;
    DateTimeParser by;
    private String errors = "";

    /**
     * Gets any error messages encountered during parsing.
     *
     * @return A string containing error messages, empty if no errors.
     */
    public String getErrors() {
        return errors;
    }

    /**
     * Gets the parsed task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the parsed deadline date/time.
     *
     * @return The DateTimeParser containing the deadline.
     */
    public DateTimeParser getBy() {
        return by;
    }

    /**
     * Creates a DeadlineParser and parses the argument string.
     *
     * @param argument The argument string containing description and /by date.
     * @throws ZukeException.MissingArgumentException If required parts are missing.
     */
    public DeadlineParser(String argument) throws ZukeException.MissingArgumentException {
        argumentParser(argument);
        if(!errors.isEmpty()) {
            throw new ZukeException.MissingArgumentException(errors);
        }

    }

    /**
     * Parses the argument string to extract description and deadline.
     * The format expected is: description /by date_time
     * Accumulates error messages for any missing components.
     *
     * @param argument The argument string to parse.
     */
    public void argumentParser(String argument){
        String[] words = argument.split("\\s+");
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder dateBuilder = new StringBuilder();
        String currentFlag = "description";// split by whitespace

        for (String word : words) {
            if (word.equalsIgnoreCase("/by")) {
                currentFlag = "by";
                continue;
            }

            if (currentFlag.equals("description")) {
                descriptionBuilder.append(word);
                descriptionBuilder.append(" ");
            } else {
                dateBuilder.append(word);
                dateBuilder.append(" ");
            }
        }

        description = descriptionBuilder.toString().trim();
        if (description.isEmpty()) {
            errors += "\n-description";
        }

        try {
            by = new DateTimeParser(dateBuilder.toString().trim());

        } catch (ZukeException.MissingTimeException e) {
            errors += "\n-by";
        }
    }
}





