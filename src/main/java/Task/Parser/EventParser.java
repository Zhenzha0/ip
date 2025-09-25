package Task.Parser;

import Exception.ZukeException;

public class EventParser {
    private String description;
    DateTimeParser from;
    DateTimeParser to;
    private String errors = "";

    public String getErrors() {
        return errors;
    }

    public String getDescription() {
        return description;
    }

    public DateTimeParser getFrom() {
        return from;
    }

    public DateTimeParser getTo() {
        return to;
    }



    public EventParser(String argument) throws ZukeException.MissingArgumentException {
        argumentParser(argument);
        if(!errors.isEmpty()) {
            throw new ZukeException.MissingArgumentException(errors);
        }
    }

    public void argumentParser(String argument){
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
