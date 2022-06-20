import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    void create_settings() {
        try {
            File file = new File("../downloads/direction_to.txt"); file.createNewFile();
            FileWriter writer = new FileWriter(file.getAbsolutePath());
            writer.write("../downloads");
        }
        
        catch (Exception e) {System.out.println("An error ocurred while creating file");}
    }

    boolean check_settings_file() {
        File file = new File("../downloads/direction_to.txt");

        if (file.isFile() && file.exists()){return true;}
        return false;
    }

    String get_settings() {

        try {
            File file = new File("../downloads/direction_to.txt"); 
            Scanner reader = new Scanner(file);
            return reader.nextLine();
        }
        
        catch (Exception e) {
            System.out.println("An error has ocurred while reading file");
            return "../downloads";
        }
    }
}
