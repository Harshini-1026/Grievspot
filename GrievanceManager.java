import java.util.*;

public class GrievanceManager {
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
            if (g.getUserName().equals(username)) g.display();
        }
    }
}
