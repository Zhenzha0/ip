package Task.Parser;

public class DeadlineParser {
    public static String[] argumentParser(String argument){
        String[] parsedArgument = new String[2];
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


        parsedArgument[0] = descriptionBuilder.toString().trim();
        parsedArgument[1] = dateBuilder.toString().trim();
        return parsedArgument;
    }

    public static String checkArgumentFormat(String description, String by) {
        String errors = "";
        if (description.isEmpty()) {
            errors += "\n-description";
        }
        if (by.isEmpty()) {
            errors += "\n-by";
        }

        return errors;
    }
}
