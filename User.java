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