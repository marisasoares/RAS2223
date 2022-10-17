public class Utilizador {

    private String username;
    private String password;
    private String email;

    public Utilizador(){
        this.username = "NaN";
        this.password = "NaN";
        this.email = "NaN";
    }

    public Utilizador(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "username=" + username +
                ", password=" + password +
                ", email=" + email +
                '}';
    }

}
