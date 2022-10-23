package src.DataLayer;

import src.Model.*;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class Database implements IDatabase{

    private Map<String, Apostador> apostadores;
    private Map<String, Especialista> especialistas;
    private Map<String, Administrador> administradores;
    private Map<String, Jogo> jogos;
    private Map<String, Desporto> desportos;
    private Map<String, Aposta> apostas;

    public Database(){
        this.apostadores = new HashMap<>();
        this.especialistas = new HashMap<>();
        this.administradores = new HashMap<>();
        this.jogos = new HashMap<>();
        this.desportos = new HashMap<>();
        this.apostas = new HashMap<>();
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

    public void loadJogosAndSportsFromDB() {
        /**
            ------------------------ PARA IMPLEMENTAR DEPOIS --------------------
         */
         

        // Implementação Simulada
        Desporto futebol = new Desporto("Futebol");
        Desporto basquetebol = new Desporto("Basquetebol");
        addDesporto(futebol);
        addDesporto(basquetebol);
        Jogo jogoFutebol = new Jogo(futebol);
        Jogo jogobasquetebol = new Jogo(basquetebol);
        addJogo(jogoFutebol); 
        addJogo(jogobasquetebol); 
    }

    public void loadApostasFromDB() {
        /**
            ------------------------ PARA IMPLEMENTAR DEPOIS --------------------
         */

        // Implementação Simulada
        Desporto rugby = new Desporto("Rugby");
        System.out.println("Added rugby with id: " + addDesporto(rugby));
        Jogo jogorugby = new Jogo(rugby);
        System.out.println("Added jogo with id: " + addJogo(jogorugby));
        Aposta aposta = new ApostaSimples(LocalDateTime.now(),jogorugby);
        System.out.println("Added aposta with id: " + addAposta(aposta));
        
    }

    public String addJogo(Jogo jogo){
        return IDatabase.addToDatabase(jogo, this.jogos);
    }

    public boolean addJogo(Jogo jogo,String id){
        return IDatabase.addToDatabaseWithId(jogo,id, this.jogos);
    }

    public boolean removeJogo(String id){
        return IDatabase.removeFromDatase(id,this.jogos);
    }

    public String addDesporto(Desporto desporto){
        return IDatabase.addToDatabase(desporto, this.desportos);
    }

    public boolean addDesporto(Desporto desporto,String id){
        return IDatabase.addToDatabaseWithId(desporto, id,this.desportos);
    }

    public boolean removeDesporto(String id){
        return IDatabase.removeFromDatase(id,this.desportos);
    }

    public String addAposta(Aposta aposta){
        return IDatabase.addToDatabase(aposta, this.apostas);
    }

    public boolean addAposta(Aposta aposta,String id){
        return IDatabase.addToDatabaseWithId(aposta, id,this.apostas);
    }

    public boolean removeAposta(String id){
        return IDatabase.removeFromDatase(id,this.apostas);
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

    public boolean existemUtilizadores(){
        return existemAdministradores() && existemApostadores() && existemEspecialistas();
    }

    public boolean existemApostadores(){
        return !IDatabase.isEmpty(this.apostadores);
    }

    public boolean existemEspecialistas(){
        return !IDatabase.isEmpty(this.especialistas);
    }

    public boolean existemAdministradores(){
        return !IDatabase.isEmpty(this.administradores);
    }

    public boolean existemJogos(){
        return !IDatabase.isEmpty(this.jogos);
    }

    public boolean existemDesportos(){
        return !IDatabase.isEmpty(this.desportos);
    }

    public boolean existemApostas(){
        return !IDatabase.isEmpty(this.apostas);
    } 

    public ArrayList<String> listarJogos(){
        ArrayList<String> jogos = new ArrayList<>();
        for (Map.Entry<String,Jogo> entry : this.jogos.entrySet()) {
            jogos.add(entry.getValue().toString());
        }
        return jogos;
    }

    public ArrayList<String> listarDesportos(){
        ArrayList<String> desportos = new ArrayList<>();
        for (Map.Entry<String,Desporto> entry : this.desportos.entrySet()) {
            desportos.add(entry.getValue().toString());
        }
        return desportos;
    }

    public ArrayList<String> listarApostas(){
        ArrayList<String> apostas = new ArrayList<>();
        for (Map.Entry<String,Aposta> entry : this.apostas.entrySet()) {
            apostas.add(entry.getValue().toString());
        }
        return apostas;
    }

    public ArrayList<String> listarApostadores(){
        ArrayList<String> apostadores = new ArrayList<>();
        for (Map.Entry<String,Apostador> entry : this.apostadores.entrySet()) {
            apostadores.add(entry.getValue().toString());
        }
        return apostadores;
    }

    public ArrayList<String> listarAdministradores(){
        ArrayList<String> administradores = new ArrayList<>();
        for (Map.Entry<String,Administrador> entry : this.administradores.entrySet()) {
            administradores.add(entry.getValue().toString());
        }
        return administradores;
    }

    public ArrayList<String> listarEspecialistas(){
        ArrayList<String> especialistas = new ArrayList<>();
        for (Map.Entry<String,Especialista> entry : this.especialistas.entrySet()) {
            especialistas.add(entry.getValue().toString());
        }
        return especialistas;
    }
}
