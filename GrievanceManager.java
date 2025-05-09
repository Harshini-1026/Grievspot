import java.io.*;
import java.util.*;

class Grievance {
    int id;
    String user;
    String dept;
    String cat;
    String desc;
    String officer;
    String status;

    public Grievance(int id, String user, String dept, String cat, String desc, String officer, String status) {
        this.id = id;
        this.user = user;
        this.dept = dept;
        this.cat = cat;
        this.desc = desc;
        this.officer = officer;
        this.status = status;
    }

    public String toCSV() {
        return id + "," + user + "," + dept + "," + cat + "," + desc.replace(",", ";") + "," + officer + "," + status;
    }

    public static Grievance fromCSV(String line) {
        String[] p = line.split(",", 7);
        return new Grievance(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4].replace(";", ","), p[5], p[6]);
    }

    public void display() {
        System.out.println("ID: " + id + " | User: " + user + " | Dept: " + dept + " | Cat: " + cat);
        System.out.println("Desc: " + desc);
        System.out.println("Officer: " + officer + " | Status: " + status);
        System.out.println("-----------------------------");
    }
}

class GrievanceManager {
    List<Grievance> list = new ArrayList<>();
    int nextId = 1001;

    public void load() {
        try {
            FileReader fr = new FileReader("grievances.txt");
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = fr.read()) != -1) sb.append((char)c);
            fr.close();
            String[] lines = sb.toString().split("\n");
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    Grievance g = Grievance.fromCSV(line.trim());
                    list.add(g);
                    nextId = Math.max(nextId, g.id + 1);
                }
            }
        } catch (IOException e) {}
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter("grievances.txt");
            for (Grievance g : list) {
                fw.write(g.toCSV() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not save grievances.");
        }
    }

    public void addGrievance(String user, String dept, String cat, String desc) {
        Grievance g = new Grievance(nextId++, user, dept, cat, desc, "Unassigned", "Pending");
        list.add(g);
        save();
        System.out.println("Grievance Submitted with ID: " + g.id);
    }

    public void viewAllGrievances() {
        for (Grievance g : list) g.display();
    }

    public void viewByUser(String uname) {
        for (Grievance g : list) {
            if (g.user.equals(uname)) g.display();
        }
    }

    public void viewByOfficer(String uname) {
        for (Grievance g : list) {
            if (g.officer.equals(uname)) g.display();
        }
    }

    public void assignGrievance(int id, String officer) {
        for (Grievance g : list) {
            if (g.id == id) {
                g.officer = officer;
                g.status = "Assigned";
                save();
                System.out.println("Grievance assigned.");
                return;
            }
        }
        System.out.println("Grievance not found.");
    }

    public void resolveGrievance(int id, String res, String officer) {
        for (Grievance g : list) {
            if (g.id == id && g.officer.equals(officer)) {
                g.desc += "\nResolution: " + res;
                g.status = "Resolved";
                save();
                System.out.println("Grievance resolved.");
                return;
            }
        }
        System.out.println("Invalid grievance or not assigned to you.");
    }

    public void generateReport() {
        int total = list.size();
        long pending = list.stream().filter(g -> g.status.equals("Pending")).count();
        long assigned = list.stream().filter(g -> g.status.equals("Assigned")).count();
        long resolved = list.stream().filter(g -> g.status.equals("Resolved")).count();

        System.out.println("--- Grievance Report ---");
        System.out.println("Total: " + total);
        System.out.println("Pending: " + pending);
        System.out.println("Assigned: " + assigned);
        System.out.println("Resolved: " + resolved);
    }
}
