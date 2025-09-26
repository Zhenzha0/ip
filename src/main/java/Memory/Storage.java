package Memory;

import App.Ui;
import Task.Task;
import Task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import java.io.FileWriter;
import java.util.Scanner;

import Exception.ZukeException;

public class Storage {

    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Storage(String filePath) {
        setFilePath(filePath);

    }

    public void load(TaskList tasks) throws FileNotFoundException, ZukeException.MissingTimeException {
        Ui.showLoadingData();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        if (f.length() == 0) {
            System.out.println("Data file is empty");
            return;
        }
        while(s.hasNextLine()){
            String line = s.nextLine();
            tasks.getTasks().add(MemoryParser.parseline(line));
        }
        Ui.showDoneLoadingData();
    }

    public void save(TaskList tasks) throws IOException {
        try{

            File file = new File(filePath);

            if (file.exists()) {
                file.delete(); // deletes the physical file
            }

            FileWriter fw = new FileWriter(file);

            if(tasks.getTasks().isEmpty()){
                System.out.println("Nothing to save");
                fw.close();
                file.delete();
                return;
            }
            for(Task task : tasks.getTasks()) {
                fw.write(formatTask(task) + System.lineSeparator());
            }

            fw.close();

        } catch(Exception e){
            System.out.println("Something went wrong: " + e.getMessage());

        }
    }

    public static String formatTask(Task task){
        String formatedString = "";
        if (task instanceof Todo) {
            formatedString = "T | ";
        } else if (task instanceof Deadline) {
            formatedString = "D | ";
        } else if (task instanceof Event) {
            formatedString = "E | ";
        }


        formatedString += task.getDone();

        if (task instanceof Todo) {
            formatedString += " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            formatedString += " | "  + task.getDescription() + " | " + ((Deadline) task).toMemoryBy();
        } else if (task instanceof Event) {
            formatedString += " | "  + task.getDescription() + " | " + ((Event) task).toMemoryFrom() + " | " + ((Event) task).toMemoryTo();
        }

        return formatedString;

    }

}
