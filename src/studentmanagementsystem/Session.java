package studentmanagementsystem;

public class Session {

    private static String fullName;
    private static String role;

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        Session.fullName = fullName;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Session.role = role;
    }

    public static void clearSession() {
        fullName = null;
        role = null;
    }

}