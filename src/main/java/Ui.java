public class Ui {
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void logo() {
        String logo = "$$$$$$$$\\ $$\\   $$\\ $$\\   $$\\ $$$$$$$$\\ \n" +
                "\\____$$  |$$ |  $$ |$$ | $$  |$$  _____|\n" +
                "    $$  / $$ |  $$ |$$ |$$  / $$ |      \n" +
                "   $$  /  $$ |  $$ |$$$$$  /  $$$$$\\    \n" +
                "  $$  /   $$ |  $$ |$$  $$<   $$  __|   \n" +
                " $$  /    $$ |  $$ |$$ |\\$$\\  $$ |      \n" +
                "$$$$$$$$\\ \\$$$$$$  |$$ | \\$$\\ $$$$$$$$\\ \n" +
                "\\________| \\______/ \\__|  \\__|\\________|\n";
        System.out.println(logo);
    }

    public static void hello() {
        line();
        System.out.println("Hello! I'm Zuke\nWhat can I do for you?");
        line();
    }

    public static void bye() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public static void error(String msg) {
        line();
        System.out.println("Error: " + msg);
        line();
    }

    public static void showList(TaskList tasks) {
        line();
        System.out.print(tasks.render());
        line();
    }

    public static void showAdded(TaskList tasks) {
        line();
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        line();
    }

    public static void showMarked(Task t, boolean nowDone) {
        line();
        System.out.println(nowDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        line();
    }
}

