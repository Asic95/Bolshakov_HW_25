package Bolshakov_HW_25;

public enum Users_data {

    SET1 ("standard_user","secret_sauce"),
    SET2 ("locked_out_user","secret_sauce"),
    SET3 ("problem_user","secret_sauce"),
    SET4 ("performance_glitch_user","secret_sauce");

    private String username;
    private String password;

    Users_data(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Users_data{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
