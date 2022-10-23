package src.Model;

public class Especialista extends Utilizador{
    
    public Especialista() {
        super();
    }

    public Especialista(String username, String password, String email) {
        super(username,password,email);
    }  


    @Override
    public String toString() {
        return super.toString() + "Tipo: Especialista\n";
    }

}
