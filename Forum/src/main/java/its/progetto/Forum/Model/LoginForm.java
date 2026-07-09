package its.progetto.Forum.Model;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {
    @NotBlank(message = "Username richiesto")
    private String username;

    @NotBlank(message = "Password richiesta")
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
