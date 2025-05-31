package api.requestModels;

public class AuthoriseRequest {

    private String password;
    private String username;

    public AuthoriseRequest(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
