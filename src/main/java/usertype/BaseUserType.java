package usertype;

public class BaseUserType {
    private String name;
    private String password;

    public BaseUserType(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
