import java.util.Scanner;
import java.util.Arrays;

public class Zuke {
    public static void main(String[] args) {
        String logo = "$$$$$$$$\\ $$\\   $$\\ $$\\   $$\\ $$$$$$$$\\ \n" +
                "\\____$$  |$$ |  $$ |$$ | $$  |$$  _____|\n" +
                "    $$  / $$ |  $$ |$$ |$$  / $$ |      \n" +
                "   $$  /  $$ |  $$ |$$$$$  /  $$$$$\\    \n" +
                "  $$  /   $$ |  $$ |$$  $$<   $$  __|   \n" +
                " $$  /    $$ |  $$ |$$ |\\$$\\  $$ |      \n" +
                "$$$$$$$$\\ \\$$$$$$  |$$ | \\$$\\ $$$$$$$$\\ \n" +
                "\\________| \\______/ \\__|  \\__|\\________|\n" +
                "                                        \n" +
                "                                        \n" +
                "                                        ";
        System.out.println(logo);
        printLine();
        System.out.println("Hello! I'm Zuke\n" + "What can I do for you?");
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();

        //add list implementation
        String[] taskList = new String[100];
        String[] markerList = new String[100];
        Scanner in = new Scanner(System.in);
        int counter = 0;

        while (true){
            String line = in.nextLine();

            if (line.equals("bye")){
                //saying bye and exiting
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            String[] parts = line.split("\\s+", 2);
            String cmd = parts[0].toLowerCase();

            if (cmd.equals("list")){
                //print full list
                printLine();
                for(int i = 0; i<counter; i++){
                    System.out.println("Here are the tasks in your list: ");
                    System.out.print((i + 1) + "." + markerList[i] + " ");
                    System.out.println(taskList[i]);
                }
                printLine();
            } else if (cmd.equals("mark")){
                if (counter == 0) {
                    error("Your list is empty. Add a task first.");
                    continue;
                }

                Integer idx = parseIndex(parts, counter);
                if (idx == null) {
                    continue;
                }// parseIndex already printed error


                setDone(idx, markerList);

                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("  ");
                System.out.print(markerList[idx] + " ");
                System.out.println(taskList[idx]);
                printLine();


            } else if (cmd.equals("unmark")){
                if (counter == 0) {
                    error("Your list is empty. Add a task first.");
                    continue;
                }

                Integer idx = parseIndex(parts, counter);
                if (idx == null) {
                    continue;
                }// parseIndex already printed error


                unsetDone(idx, markerList);

                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.print("  ");
                System.out.print(markerList[idx] + " ");
                System.out.println(taskList[idx]);
                printLine();


            } else {
                taskList[counter] = line;
                markerList[counter] = "[ ]";
                counter++;
                //print added item
                printLine();
                System.out.print("added: ");
                System.out.println(line);
                printLine();
            }
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void error(String msg) {
        printLine();
        System.out.println("Error: " + msg);
        printLine();
    }

    private static Integer parseIndex(String[] parts, int max) {
        if (parts.length < 2) {
            error("Please provide an index, e.g., 'mark 2'.");
            return null;
        }
        try {
            int idx = Integer.parseInt(parts[1]);
            if (idx < 1 || idx > max) {
                error("Index out of range. Valid: 1.." + max + ".");
                return null;
            }
            return idx - 1;
        } catch (NumberFormatException e) {
            error("Index must be a number, e.g., 'unmark 3'.");
            return null;
        }
    }

    private static void setDone(int index, String[] markerList) {
        markerList[index] = "[X]";
    }

    private static void unsetDone(int index, String[] markerList) {
        markerList[index] = "[ ]";
    }
}
