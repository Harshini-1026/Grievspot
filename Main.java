import java.io.*;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        GrievanceManager gm = new GrievanceManager();
        loadUsers();
        gm.load();

        while (true) {
            System.out.println("\n===== GrievSpot =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser(gm);
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void registerUser() {
        System.out.print("Enter new username: ");
        String uname = sc.nextLine();
        if (users.containsKey(uname)) {
            System.out.println("Username already exists.");
            return;
        }

        System.out.print("Enter password: ");
        String pwd = sc.nextLine();
        System.out.print("Enter role (Admin / Officer / Complainant / Reviewer): ");
        String role = sc.nextLine();

        try {
            FileWriter fw = new FileWriter("users.txt", true);
            fw.write(uname + "," + pwd + "," + role + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to users.txt.");
            return;
        }

        switch (role) {
            case "Admin":
                users.put(uname, new Admin(uname, pwd));
                break;
            case "Officer":
                users.put(uname, new Officer(uname, pwd));
                break;
            case "Complainant":
                users.put(uname, new Complainant(uname, pwd));
                break;
            case "Reviewer":
                users.put(uname, new Reviewer(uname, pwd));
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return;
        }

        System.out.println("Registration successful!");
    }

    public static void loginUser(GrievanceManager gm) {
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        User u = users.get(uname);
        if (u != null && u.authenticate(pass)) {
            System.out.println("Welcome, " + u.getUsername() + " (" + u.getRole() + ")");
            u.showMenu(gm);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public static void loadUsers() {
        try {
            FileReader fr = new FileReader("users.txt");
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = fr.read()) != -1) sb.append((char) c);
            fr.close();
            String[] lines = sb.toString().split("\n");
            for (String line : lines) {
                String[] parts = line.trim().split(",");
                if (parts.length == 3) {
                    String u = parts[0], p = parts[1], r = parts[2];
                    switch (r) {
                        case "Admin": users.put(u, new Admin(u, p)); break;
                        case "Officer": users.put(u, new Officer(u, p)); break;
                        case "Complainant": users.put(u, new Complainant(u, p)); break;
                        case "Reviewer": users.put(u, new Reviewer(u, p)); break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("users.txt not found. It will be created on registration.");
        }
    }
}
