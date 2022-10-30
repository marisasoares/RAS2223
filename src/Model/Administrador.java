package Model;

public class Administrador extends User {
    public Administrador(String nome, String mail, int passwordHash, String nif) {
        super(nome, mail, passwordHash, nif);
    }
}