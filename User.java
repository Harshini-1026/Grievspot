import java.util.Scanner;

abstract class User {
    protected String username;
    protected String password;
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public abstract void showMenu(GrievanceManager gm);
}

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password, "Admin");
    }

    @Override
    public void showMenu(GrievanceManager gm) {
        Scanner sc =Main.sc;
        int choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Grievances");
            System.out.println("2. Assign Grievance");
            System.out.println("3. Generate Report");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: gm.viewAllGrievances(); break;
                case 2:
                    System.out.print("Enter Grievance ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Officer username: ");
                    String officer = sc.nextLine();
                    gm.assignGrievance(id, officer);
                    break;
                case 3: gm.generateReport(); break;
                case 0: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}

class Officer extends User {
    public Officer(String username, String password) {
        super(username, password, "Officer");
    }

    @Override
    public void showMenu(GrievanceManager gm) {
        Scanner sc = Main.sc;
        int choice;
        do {
            System.out.println("\n--- Officer Menu ---");
            System.out.println("1. View Assigned Grievances");
            System.out.println("2. Resolve Grievance");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: gm.viewByOfficer(username); break;
                case 2:
                    System.out.print("Enter Grievance ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter resolution: ");
                    String res = sc.nextLine();
                    gm.resolveGrievance(id, res, username);
                    break;
                case 0: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}

class Complainant extends User {
    public Complainant(String username, String password) {
        super(username, password, "Complainant");
    }

    @Override
    public void showMenu(GrievanceManager gm) {
        Scanner sc = Main.sc;
        int choice;
        do {
            System.out.println("\n--- Complainant Menu ---");
            System.out.println("1. Submit Grievance");
            System.out.println("2. View My Grievances");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();
                    gm.addGrievance(username, dept, cat, desc);
                    break;
                case 2: gm.viewByUser(username); break;
                case 0: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}

class Reviewer extends User {
    public Reviewer(String username, String password) {
        super(username, password, "Reviewer");
    }

    @Override
    public void showMenu(GrievanceManager gm) {
        gm.generateReport();
    }
}
