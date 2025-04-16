import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, User> users = new HashMap<>();
        users.put("harshini", new Complainant("harshini", "1234"));

        GrievanceManager gm = new GrievanceManager();
        gm.load(FileStorage.loadGrievances());

        while (true) {
            System.out.println("\n====== GrievSpot ======");
            System.out.print("Username (or 'exit'): ");
            String uname = sc.nextLine();
            if (uname.equalsIgnoreCase("exit")) break;

            System.out.print("Password: ");
            String pwd = sc.nextLine();

            User u = users.get(uname);
            if (u != null && u.authenticate(pwd)) {
                System.out.println("Login success! Role: " + u.getRole());
                u.showMenu(gm);
            } else {
                System.out.println("Invalid login.");
            }
        }

        System.out.println("Exiting system...");
    }
}
