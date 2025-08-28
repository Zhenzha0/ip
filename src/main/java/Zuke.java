import java.util.Scanner;

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

        //echo implementation
        Scanner in = new Scanner(System.in);

        while (true){
            String line = in.nextLine();

            if (line.equals("bye")){
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            printLine();
            System.out.println(line);
            printLine();

        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
