public class DeadlineParser {
    public static String[] argumentParser(String argument){
        String[] words = argument.split("\\s+");
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder dateBuilder = new StringBuilder();
        String currentFlag = "description";// split by whitespace

        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("/by") && i + 1 < words.length) {
                currentFlag = "by";
                continue;
            }

            if(currentFlag.equals("description")){
                descriptionBuilder.append(words[i]);
                descriptionBuilder.append(" ");
            } else {
                dateBuilder.append(words[i]);
                dateBuilder.append(" ");
            }
        }

        String[] parsedArgument = new String[2];
        parsedArgument[0] = descriptionBuilder.toString().trim();
        parsedArgument[1] = dateBuilder.toString().trim();
        return parsedArgument;

    }
}
