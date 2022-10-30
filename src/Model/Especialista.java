package Model;

import Model.User;

public class Especialista extends User {
    public Especialista(String nome, String mail, int passwordHash, String nif) {
        super(nome, mail, passwordHash, nif);
    }
}