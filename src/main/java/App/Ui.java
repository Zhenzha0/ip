package App;

import Task.Task;
import Task.TaskList;

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

    public static void welcome() {
        logo();
        hello();
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
        showCurrentTaskSize(tasks);
        line();
    }

    public static void showDeleted(Task deletedTasks, TaskList tasks) {
        line();
        System.out.println("Got it. I've deleted this task: ");
        System.out.println(deletedTasks);
        showCurrentTaskSize(tasks);
        line();
    }

    public static void showFind(TaskList tasks) {
        line();
        if(tasks.isEmpty()) {
            System.out.println("No related tasks found");
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            System.out.println(tasks.render());
        }
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

    public static void showCurrentTaskSize(TaskList tasks) {
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public static void showNoStorageFile() {
        System.out.println("No previous data available, start adding your task now");
    }

    public static void showLoadingData() {
        System.out.println("Loading data...");
    }

    public static void showDoneLoadingData() {
        System.out.println("Done loading data");
        line();
    }
}

