package src.DataLayer;

import src.Model.*;
import java.util.Map;
import java.util.HashMap;


public class Database {

    private Map<String, Apostador> apostadores;
    private Map<String, Especialista> especialistas;
    private Map<String, Administrador> administradores;
    private Map<String, Jogo> calendario;

    public Database(){
        this.apostadores = new HashMap<>();
        this.especialistas = new HashMap<>();
        this.administradores = new HashMap<>();
        this.calendario = new HashMap<>();
    }

    public void loadUsersFromDB(){
        /**
            ------------------------ PARA IMPLEMENTAR DEPOIS --------------------
         */
         

        // Implementação Simulada
        for (int i = 1; i <= 20; i++) {
            String username = "user" + i;
            String email = username + "@gmail.com";
            Apostador u = new Apostador(username,"12345",email);
            if(addUtilizador(u)) System.out.println("Utilizador " + i + " carregado");
        }

        Administrador admin = new Administrador("admin","12345","admin@gmail.com");
        if(addUtilizador(admin)) System.out.println("Admin carregado");
        Especialista esp = new Especialista("esp","12345","esp@gmail.com");
        if(addUtilizador(esp)) System.out.println("Especialista carregado");        
    }

    public boolean addUtilizador(Utilizador user){
        boolean addedToUsers = false;
        if(user instanceof Administrador && !this.administradores.containsKey(user.getUsername()) ) {
            this.administradores.put(user.getUsername(), (Administrador)user);
            addedToUsers = true;
        }
        else if(user instanceof Especialista && !this.especialistas.containsKey(user.getUsername()) ) {
            this.especialistas.put(user.getUsername(), (Especialista)user);
            addedToUsers = true;
        } if(user instanceof Apostador && !this.apostadores.containsKey(user.getUsername()) ) {
            this.apostadores.put(user.getUsername(), (Apostador)user);
            addedToUsers = true;
        }
        return addedToUsers;
    }

    public boolean login(String username, String password){
        boolean valid_Password = false;
        Utilizador user = null;
        if(this.especialistas.containsKey(username)) user = this.especialistas.get(username);
        else if(this.administradores.containsKey(username)) user = this.administradores.get(username);
        else if(this.apostadores.containsKey(username)) user = this.apostadores.get(username);    
        if(user != null && password.hashCode() == user.getPasswordHash()) valid_Password = true;
        return valid_Password;   
    }

    public Utilizador getUtilizador(String username,String password){
        Utilizador user = null;
        if(this.login(username, password)){
            if(this.administradores.containsKey(username)) user = this.administradores.get(username);
            if(this.especialistas.containsKey(username)) user = this.especialistas.get(username);
            if(this.apostadores.containsKey(username)) user = this.apostadores.get(username);
        }
        return user;
    }

    public boolean usernameExists(String username){
        boolean usernameExists = false;
        if(this.administradores.containsKey(username)) usernameExists = true;
        if(this.especialistas.containsKey(username)) usernameExists = true;
        if(this.apostadores.containsKey(username)) usernameExists = true;
        
        return usernameExists;
    }

    public boolean existemApostadoresRegistados(){
        return !this.apostadores.isEmpty();
    }
    
}
