package src.Model;

public class Administrador extends Utilizador{
    
    public Administrador() {
        super();
    }

    public Administrador(String username, String password, String email) {
        super(username,password,email);
    }      


    @Override
    public String toString() {
        return "{" +
        " username='" + getUsername() + "'" +
        ", email='" + getEmail() + "'" +
        ", tipo='administrador'" +
        "}";
    }

    
}
