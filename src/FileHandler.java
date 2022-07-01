//     <Jatube: A Youtube Downloader application implemented with Java and Python>
//     Copyright (C) <2022>  <Cromega>

//     This program is free software: you can redistribute it and/or modify
//     it under the terms of the GNU Affero General Public License as published
//     by the Free Software Foundation, either version 3 of the License, or
//     (at your option) any later version.

//     This program is distributed in the hope that it will be useful,
//     but WITHOUT ANY WARRANTY; without even the implied warranty of
//     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//     GNU Affero General Public License for more details.

//     You should have received a copy of the GNU Affero General Public License
//     along with this program.  If not, see <https://www.gnu.org/licenses/>.

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
