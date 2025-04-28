import java.util.ArrayList;
import java.util.List;

public class Grievance {
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

    public String toLine() {
        return id + "|" + userName + "|" + department + "|" + category + "|" +
               description + "|" + assignedOfficer + "|" + status;
    }

    public static Grievance fromLine(String line) {
        String[] p = line.split("\\|");
        Grievance g = new Grievance(p[1], p[2], p[3], p[4]);
        g.id = Integer.parseInt(p[0]);
        g.assignedOfficer = p[5];
        g.status = p[6];
        return g;
    }
}


class GrievanceManager {
    private List<Grievance> grievanceList = new ArrayList<>();

    public void load(List<Grievance> loaded) {
        grievanceList = loaded;
    }

    public void addGrievance(Grievance g) {
        grievanceList.add(g);
        FileStorage.saveGrievances(grievanceList);
        NotificationManager.notifyUser(g.getUserName(), "Grievance submitted. ID: " + g.getId());
    }

    public void viewGrievancesByUser(String username) {
        for (Grievance g : grievanceList) {
            if (g.getUserName().equals(username)) {
                g.display();
            }
        }
    }
}
class NotificationManager {
    public static void notifyUser(String user, String msg) {
        System.out.println("[Notify] To: " + user + " | " + msg);
    }
}
