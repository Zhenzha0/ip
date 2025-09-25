package Task.Parser;

import Exception.ZukeException;



public class DeadlineParser {

    private String description;
    DateTimeParser by;
    private String errors = "";

    public String getErrors() {
        return errors;
    }

    public String getDescription() {
        return description;
    }
    
    public DateTimeParser getBy() {
        return by;
    }

    public DeadlineParser(String argument) throws ZukeException.MissingArgumentException {
        argumentParser(argument);
        if(!errors.isEmpty()) {
            throw new ZukeException.MissingArgumentException(errors);
        }

    }

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





