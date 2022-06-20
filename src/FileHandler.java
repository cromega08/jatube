import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileHandler {

    void create_settings() {
        try {
            File dir = new File("../downloads"); 
            if (!dir.exists()) {dir.mkdir();}

            File file = new File("../downloads/direction_to.txt");
            file.createNewFile(); write_settings("../downloads");
        }
        
        catch (Exception e) {System.out.println("An error ocurred while creating dir or file");}
    }

    boolean check_settings_file() {
        File file = new File("../downloads/direction_to.txt");

        if (file.isFile() && file.exists()){return true;}
        return false;
    }

    void write_settings(String path) {

        try {
            RandomAccessFile writer = new RandomAccessFile("../downloads/direction_to.txt", "rw");
            writer.seek(0);
            writer.writeBytes(path);
            writer.close();
        }
        
        catch (Exception e) {System.out.println("An error ocurred while writing file");}
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
