package Objects;

/**
 * Created by Tymek on 03.03.2017.
 */
public class User {
    String login;
    String password;
    String firstName;
    String secondName;
    String email;
    String address;
    String postalCode;
    String phoneNumber;
    Boolean manager;

    public User(String login, String password, String firstName, String secondName, String email, String address, String postalCode, String phoneNumber, Boolean manager) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.manager = manager;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Boolean getManager() {
        return this.manager;
    }
}
