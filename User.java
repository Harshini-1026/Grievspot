import java.util.Scanner;
public abstract class User {
    protected String username, password, role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public abstract void showMenu(GrievanceManager gm);
}

class Complainant extends User {

    public Complainant(String username, String password) {
        super(username, password, "Complainant");
    }

    @Override
    public void showMenu(GrievanceManager gm) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Complainant Menu ---");
            System.out.println("1. Submit Grievance");
            System.out.println("2. View My Grievances");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    Grievance g = new Grievance(username, dept, cat, desc);
                    gm.addGrievance(g);
                    System.out.println("Submitted with ID: " + g.getId());
                    break;
                case 2:
                    gm.viewGrievancesByUser(username);
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
