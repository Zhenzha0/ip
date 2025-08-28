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
        String[] list = new String[100];
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

            if (line.equals("list")){
                //print full list
                printLine();
                for(int i = 0; i<counter; i++){
                    System.out.print((i + 1) + ". ");
                    System.out.println(list[i]);
                }
                printLine();
            } else {
                list[counter] = line;
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
}
