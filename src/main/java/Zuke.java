import Command.CommandHandler;
import Command.CommandParser;
import Memory.Storage;
import Task.Parser.DeadlineParser;
import Task.Parser.EventParser;
import Task.Task;
import Task.TaskList;
import App.Ui;
import Exception.ZukeException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Zuke {
    private Storage storage;
    private TaskList tasks;

    public Zuke(String filepath) {
        Ui.welcome();
        tasks = new TaskList();
        storage = new Storage(filepath);

        try {
            storage.load(tasks);

        } catch (FileNotFoundException e) {
            Ui.showNoStorageFile();
        }
    }

    public void run() {
        new CommandHandler(tasks, storage).handleCommands();

    }

    public static void main(String[] args) {
        new Zuke("data/zuke.text").run();
    }
}
