import java.io.Serializable;

public class Grievance implements Serializable {
    private static int counter = 1001;
    private int id;
    private String userName, department, category, description, status, assignedOfficer;

    public Grievance(String userName, String department, String category, String description) {
        this.id = counter++;
        this.userName = userName;
        this.department = department;
        this.category = category;
        this.description = description;
        this.status = "Pending";
        this.assignedOfficer = "Unassigned";
    }

    public int getId() { return id; }
    public String getUserName() { return userName; }
    public String getStatus() { return status; }

    public void display() {
        System.out.println("Grievance ID: " + id);
        System.out.println("User: " + userName);
        System.out.println("Dept: " + department);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
        System.out.println("Officer: " + assignedOfficer);
        System.out.println("Status: " + status);
        System.out.println("--------------------------------");
    }

    public String toCSV() {
        return id + "," + userName + "," + department + "," + category + "," +
               description.replace(",", ";") + "," + assignedOfficer + "," + status;
    }

    public static Grievance fromCSV(String line) {
        String[] p = line.split(",", 7);
        Grievance g = new Grievance(p[1], p[2], p[3], p[4].replace(";", ","));
        g.id = Integer.parseInt(p[0]);
        g.assignedOfficer = p[5];
        g.status = p[6];
        return g;
    }
}