package ui;

import task.Task;
import task.TaskList;

/**
 * Handles all user interface interactions for the Zuke application.
 * This class provides methods to display messages, task lists, and errors to the user.
 */
public class Ui {

    /**
     * Prints a horizontal line separator to the console.
     */
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the Zuke application logo in ASCII art format.
     */
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

    /**
     * Displays a quick start guide showing available commands.
     */
    public static void showQuickGuide() {
        line();
        System.out.println("Quick Guide:");
        System.out.println("  todo <description>           - Add a todo task");
        System.out.println("  deadline <desc> /by <date>   - Add a deadline");
        System.out.println("  event <desc> /from <date> /to <date> - Add an event");
        System.out.println("  list                         - View all tasks");
        System.out.println("  mark <index>                 - Mark task as done");
        System.out.println("  unmark <index>               - Mark task as not done");
        System.out.println("  delete <index>               - Delete a task");
        System.out.println("  find <keyword>               - Search tasks by keyword");
        System.out.println("  date <date>                  - Find tasks on a date");
        System.out.println("  guide                          - View command guide");
        System.out.println("  bye                          - Exit the app");
        System.out.println();
        System.out.println("Date formats: yyyy-MM-dd or d/M/yyyy (optional time: HHmm or HH:mm)");
        line();
    }

    /**
     * Displays a welcome greeting message to the user.
     */
    public static void hello() {
        line();
        System.out.println("Hello! I'm Zuke, a fast minimal CLI task manager");
    }

    /**
     * Displays the complete welcome screen with logo and greeting.
     */
    public static void welcome() {
        logo();
        hello();
        showQuickGuide();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public static void bye() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg The error message to display.
     */

    public static void error(String msg) {
        line();
        System.out.println("Error: " + msg);
        line();
    }

    /**
     * Displays the complete list of tasks.
     *
     * @param tasks The TaskList containing all tasks to display.
     */
    public static void showList(TaskList tasks) {
        line();
        System.out.println("Here are the tasks in your list:");
        System.out.print(tasks.render());
        line();
    }

    /**
     * Displays confirmation message after a task has been added.
     * Shows the newly added task and the updated task count.
     *
     * @param tasks The TaskList containing the newly added task.
     */
    public static void showAdded(TaskList tasks) {
        line();
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        showCurrentTaskSize(tasks);
        line();
    }

    /**
     * Displays confirmation message after a task has been deleted.
     * Shows the deleted task and the updated task count.
     *
     * @param deletedTasks The Task that was deleted.
     * @param tasks The TaskList after deletion.
     */
    public static void showDeleted(Task deletedTasks, TaskList tasks) {
        line();
        System.out.println("Got it. I've deleted this task: ");
        System.out.println(deletedTasks);
        showCurrentTaskSize(tasks);
        line();
    }

    /**
     * Displays the search results for tasks matching a query.
     * If no tasks are found, displays a message indicating no matches.
     *
     * @param tasks The TaskList containing matching tasks.
     */
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

    /**
     * Displays confirmation message after marking or unmarking a task.
     *
     * @param t The Task that was marked or unmarked.
     * @param nowDone True if the task was marked as done, false if unmarked.
     */
    public static void showMarked(Task t, boolean nowDone) {
        line();
        System.out.println(nowDone
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        line();
    }

    /**
     * Displays the current number of tasks in the list.
     *
     * @param tasks The TaskList to count.
     */
    public static void showCurrentTaskSize(TaskList tasks) {
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Displays a message indicating no previous storage file was found.
     */
    public static void showNoStorageFile() {
        System.out.println("No previous data available, start adding your task now");
        line();
    }

    /**
     * Displays a message indicating data is being loaded.
     */
    public static void showLoadingData() {
        System.out.println("Loading data...");
    }

    /**
     * Displays a message indicating data loading is complete.
     */
    public static void showDoneLoadingData() {
        System.out.println("Done loading data");
        line();
    }
}

