package Task.Parser;

public class EventParser {
    public static String[] argumentParser(String argument){
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

        String[] parsedArgument = new String[3];
        parsedArgument[0] = descriptionBuilder.toString().trim();
        parsedArgument[1] = fromBuilder.toString().trim();
        parsedArgument[2] = toBuilder.toString().trim();
        return parsedArgument;

    }



    public static String checkArgumentFormat(String description, String from , String to) {
        String errors = "";
        if (description.isEmpty()) {
            errors += "\n-description";
        }
        if (from.isEmpty()) {
            errors += "\n-from";
        }
        if (to.isEmpty()) {
            errors += "\n-to";
        }

        return errors;
    }


}
