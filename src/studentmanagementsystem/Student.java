
package studentmanagementsystem;

public class Student extends Person {

    private String registrationNumber;
    private String course;
    private String yearLevel;
    private double feeBalance;
    private String status;
    private String photo;

    // Default Constructor
    public Student() {
        super();
    }

    // Parameterized Constructor
    public Student(String registrationNumber,
                   String firstName,
                   String lastName,
                   String gender,
                   String phone,
                   String email,
                   String course,
                   String yearLevel,
                   double feeBalance,
                   String status,
                   String photo) {

        super(firstName, lastName, gender, phone, email);

        this.registrationNumber = registrationNumber;
        this.course = course;
        this.yearLevel = yearLevel;
        this.feeBalance = feeBalance;
        this.status = status;
        this.photo = photo;
    }

    // Student-specific Getters and Setters

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    public double getFeeBalance() {
        return feeBalance;
    }

    public void setFeeBalance(double feeBalance) {
        this.feeBalance = feeBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // Polymorphism (Method Overriding)
    @Override
    public String getRole() {
        return "Student";
    }
}