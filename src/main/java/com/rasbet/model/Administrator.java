package com.rasbet.model;

public class Administrator extends User {
    public Administrator(String nome, String mail, int passwordHash) {
        super(nome, mail, passwordHash);
    }
}