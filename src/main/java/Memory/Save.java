package Memory;

import Task.Task;
import Task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import java.io.FileWriter;


public class Save {

    public static String filePath = "data/zuke.text";

    public static void load(TaskList tasks) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f);
        if (f.length() == 0) {
            System.out.println("File is empty");
            return;
        }
        while(s.hasNextLine()){
            String line = s.nextLine();
            tasks.getTasks().add(MemoryParser.parseline(line));
        }
    }

    public static void save(TaskList tasks) throws IOException {
        try{

            File file = new File(filePath);

            if (file.exists()) {
                file.delete(); // deletes the physical file
            }

            FileWriter fw = new FileWriter(file);
            if(tasks.getTasks().isEmpty()){
                System.out.println("Nothing to save");
                return;
            }
            for(Task task : tasks.getTasks()) {
                fw.write(saveFormat(task) + System.lineSeparator());
            }

            fw.close();

        } catch(Exception e){
            System.out.println("Something went wrong: " + e.getMessage());

        }
    }

    public static String saveFormat(Task task){
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
            formatedString += " | "  + task.getDescription() + " | " + ((Deadline) task).getDeadline();
        } else if (task instanceof Event) {
            formatedString += " | "  + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }

        return formatedString;

    }

}
