import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        GrievanceManager gm = new GrievanceManager();
        gm.load(FileStorage.loadGrievances());

        List<User> users = UserStorage.loadUsers();

        while (true) {
            System.out.println("\n====== GrievSpot ======");
            System.out.println("1. Login");
            System.out.println("2. Register New User");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Username: ");
                String uname = sc.nextLine();
                System.out.print("Password: ");
                String pwd = sc.nextLine();

                User currentUser = null;
                for (User u : users) {
                    if (u.getUsername().equals(uname) && u.authenticate(pwd)) {
                        currentUser = u;
                        break;
                    }
                }

                if (currentUser != null) {
                    System.out.println("Login success! Role: " + currentUser.getRole());
                    currentUser.showMenu(gm);
                } else {
                    System.out.println("Invalid login.");
                }
            }
            else if (choice == 2) {
                System.out.print("Enter New Username: ");
                String newUser = sc.nextLine();
                System.out.print("Enter New Password: ");
                String newPass = sc.nextLine();

                users.add(new Complainant(newUser, newPass));
                UserStorage.saveUser(newUser, newPass);
                System.out.println("Registration successful! You can now login.");
            }
            else if (choice == 0) {
                System.out.println("Exiting system...");
                break;
            }
            else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
