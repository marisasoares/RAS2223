public class Utilizador {

    private string username;
    private string password;
    private string email;

    public Utilizador(){
        this.username = "NaN";
        this.password = "NaN";
        this.email = "NaN";
    }

    public Utilizador(string username, string password, string email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public string getUsername() {
        return username;
    }

    public void setUsername(string username) {
        this.username = username;
    }

    public string getPassword() {
        return password;
    }

    public void setPassword(string password) {
        this.password = password;
    }

    public string getEmail() {
        return email;
    }

    public void setEmail(string email) {
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
