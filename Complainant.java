import java.util.Scanner;

public class Complainant extends User {

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
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    Grievance g = new Grievance(username, dept, cat, desc);
                    gm.addGrievance(g);
                    System.out.println("Submitted with ID: " + g.getId());
                }
                case 2 -> gm.viewGrievancesByUser(username);
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}