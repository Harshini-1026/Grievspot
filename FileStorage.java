import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStorage {
    private static final String FILE = "grievances.txt";

    public static List<Grievance> loadGrievances() {
        List<Grievance> list = new ArrayList<>();
        try {
            File file = new File(FILE);
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    list.add(Grievance.fromLine(line));
                }
                sc.close();
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static void saveGrievances(List<Grievance> list) {
        try {
            FileWriter fw = new FileWriter(FILE);
            for (Grievance g : list) {
                fw.write(g.toLine() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving grievances: " + e.getMessage());
        }
    }
}
