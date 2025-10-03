package task.parser;

import exception.ZukeException;

/**
 * Parses event command arguments into structured components.
 * Extracts the task description, start time, and end time from the input string.
 */
public class EventParser {
    private String description;
    DateTimeParser from;
    DateTimeParser to;
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
     * Gets the parsed event description.
     *
     * @return The event description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the parsed start date/time.
     *
     * @return The DateTimeParser containing the start time.
     */
    public DateTimeParser getFrom() {
        return from;
    }

    /**
     * Gets the parsed end date/time.
     *
     * @return The DateTimeParser containing the end time.
     */
    public DateTimeParser getTo() {
        return to;
    }

    /**
     * Creates an EventParser and parses the argument string.
     *
     * @param argument The argument string containing description /from date /to date.
     * @throws ZukeException.MissingArgumentException If required parts are missing.
     */
    public EventParser(String argument) throws ZukeException.MissingArgumentException {
        parseArgument(argument);
        if(!errors.isEmpty()) {
            throw new ZukeException.MissingArgumentException(errors);
        }
    }

    /**
     * Parses the argument string to extract description, start time, and end time.
     * The format expected is: description /from start_date_time /to end_date_time
     * Accumulates error messages for any missing components.
     *
     * @param argument The argument string to parse.
     */
    public void parseArgument(String argument){
        String[] words = argument.split("\\s+");
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder fromBuilder = new StringBuilder();
        StringBuilder toBuilder = new StringBuilder();
        String currentFlag = "description";// split by whitespace

        for (int i = 0; i < words.length; i++) {

            if (words[i].equalsIgnoreCase("/from")) {
                currentFlag = "from";
                continue;
            } else if (words[i].equalsIgnoreCase("/to")) {
                currentFlag = "to";
                continue;
            }

            if(currentFlag.equals("description")){
                descriptionBuilder.append(words[i]);
                descriptionBuilder.append(" ");
            } else if(currentFlag.equals("from")){
                fromBuilder.append(words[i]);
                fromBuilder.append(" ");
            } else if(currentFlag.equals("to")){
                toBuilder.append(words[i]);
                toBuilder.append(" ");
            }

        }

        description = descriptionBuilder.toString().trim();
        if (description.isEmpty()) {
            errors += "\n-description";
        }

        try {
            from = new DateTimeParser(fromBuilder.toString().trim());

        } catch (ZukeException.MissingTimeException e) {
            errors += "\n-from";
        }

        try {
            to = new DateTimeParser(toBuilder.toString().trim());

        } catch (ZukeException.MissingTimeException e) {
            errors += "\n-to";
        }



    }

}
