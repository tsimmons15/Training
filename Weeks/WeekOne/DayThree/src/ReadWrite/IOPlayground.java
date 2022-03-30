package ReadWrite;

import java.io.*;

public class IOPlayground {
    public static void main(String[] args) {
        try {
            File file = new File("theFile.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.append("This is my file. Hello!");
            writer.close();

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;//reader.readLine();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
