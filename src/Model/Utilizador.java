package src.Model;

public class Utilizador {

    private String username;
    private int password_Hash;
    private String email;

    public Utilizador(){
        this.username = "NaN";
        this.password_Hash = "NaN".hashCode();
        this.email = "NaN";
    }

    public Utilizador(String username, String password, String email) {
        this.username = username;
        this.password_Hash = password.hashCode();
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPasswordHash() {
        return password_Hash;
    }

    public void setPassword(String password) {
        this.password_Hash = password.hashCode();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Username: " + username +
               "\nemail: " + email +
               '\n';
    }

}
