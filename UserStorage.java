import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserStorage {
    private static final String FILE = "user_credentials.txt";

    public static List<User> loadUsers() {
        List<User> list = new ArrayList<>();
        try {
            File file = new File(FILE);
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split("\\|");
                    if (parts.length == 2) {
                        String username = parts[0];
                        String password = parts[1];
                        list.add(new Complainant(username, password));
                    }
                }
                sc.close();
            }
        } catch (Exception e) {
            System.out.println("Error reading users: " + e.getMessage());
        }
        return list;
    }

    public static void saveUser(String username, String password) {
        try {
            FileWriter fw = new FileWriter(FILE, true); // append mode
            fw.write(username + "|" + password + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }
}