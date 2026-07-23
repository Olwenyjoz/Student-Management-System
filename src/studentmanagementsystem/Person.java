
package studentmanagementsystem;


public abstract class Person {

    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;

    // Default Constructor
    public Person() {
    }

    // Parameterized Constructor
    public Person(String firstName, String lastName,
                  String gender, String phone, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public abstract String getRole();
}