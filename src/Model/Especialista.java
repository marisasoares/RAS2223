package Model;

import Model.User;

public class Especialista extends User {
    public Especialista(String nome, String mail, int passwordHash) {
        super(nome, mail, passwordHash);
    }
}