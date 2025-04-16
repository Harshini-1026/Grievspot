import java.io.*;
import java.util.*;

public class FileStorage {
    private static final String FILE = "grievances.txt";

    public static List<Grievance> loadGrievances() {
        List<Grievance> list = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return list;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                list.add(Grievance.fromCSV(line));
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static void saveGrievances(List<Grievance> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Grievance g : list) {
                pw.println(g.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving grievances.");
        }
    }
}