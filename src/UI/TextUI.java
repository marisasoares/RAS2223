package UI;

import java.util.Scanner;


import Model.Administrador;
import Model.Apostador;
import Model.Especialista;

public class TextUI {








    //Métodos auxiliares 




    /*
     * UI para registo de um utilizador
     * @param tipo_de_utilizador 0 - apostador, 1 - especialista, 2 - administrador
     */
    /*
    private void register(int tipo_de_utilizador){
        clearScreen();
        header("REGISTO");
        String username = "",email = "", password = "";
        int tentativa = 0;
        do {
            
            if(tentativa != 0) {
                //Apagar as duas linhas superiores
                System.out.print(String.format("\033[%dA",1)); 
                System.out.print("\033[2K"); 
                System.out.print(String.format("\033[%dA",1)); 
                System.out.print("\033[2K");
                errorMessage("Username já utilizado ou inválido");
            }
            System.out.print("Insira username: ");
            username = this.scanner.nextLine();
            tentativa++;
        } while (this.database.usernameExists(username) || username.trim().length() == 0);
        tentativa = 0;
        System.out.println();
        do {
            if(tentativa != 0) {
                //Apagar as duas linhas superiores
                System.out.print(String.format("\033[%dA",1)); 
                System.out.print("\033[2K"); 
                System.out.print(String.format("\033[%dA",1)); 
                System.out.print("\033[2K");
                errorMessage("Email inválido");
            }
            System.out.print("Insira email: ");
            email = this.scanner.nextLine();
            tentativa++;
        } while (email.trim().length() == 0 || !email.matches("\\w+@\\w+\\.\\w+"));
        tentativa = 0;
        System.out.println();
        do {
            if(tentativa != 0) {
                //Apagar as duas linhas superiores
                System.out.print(String.format("\033[%dA",1)); 
                System.out.print("\033[2K"); 
                System.out.print(String.format("\033[%dA",1)); 
                System.out.print("\033[2K");
                errorMessage("A password deve conter pelo menos 8 caractéres");
                }    
            System.out.print("Insira password: ");
            password = this.scanner.nextLine();
            tentativa++;
        } while (password.length() < 8);
        switch (tipo_de_utilizador) {
            case 1:
                Especialista esp = new Especialista(username, password, email);
                this.database.addUtilizador(esp);
                break;
            case 2:
                Administrador admin = new Administrador(username, password, email);
                this.database.addUtilizador(admin);
                break;
            default:
                Apostador user = new Apostador(username, password, email);
                this.database.addUtilizador(user);
                break;
        }
        
        pressEnterToContinue();
    }

    private void login(){
        clearScreen();
        header("LOGIN");
        System.out.print(" Username: ");
        String username = this.scanner.nextLine();
        System.out.print(" Password: ");
        String password = this.scanner.nextLine();
        boolean login_Successful = database.login(username, password);
        if(login_Successful){
            Object user = database.getUtilizador(username, password);
            if(user instanceof Especialista) menuEspecialista((Especialista) user);
            else if(user instanceof Administrador) menuAdministrador(((Administrador) user));
            else menuApostador((Apostador)user);
            
        } else {
            errorMessage("Login Inválido");
            pressEnterToContinue();
        }
        return;
    }

    private void menuApostador(Apostador apostador){
        clearScreen();
        Menu menu = new Menu(new String[]{
                "Apostar",
                "Alterar password"
        },"Bem vindo " + apostador.getUsername() 
        + "\n" + apostador.getEmail() + "\nSaldo: \n"
        + apostador.getCarteira().getEuros() + " €\n" +
        + apostador.getCarteira().getDollars() + " $\n");
        menu.setTitulo("Menu Model.Apostador");
        menu.run();
    }

    private void menuEspecialista(Especialista esp){
        clearScreen();
        Menu menu = new Menu(new String[]{
                "Apostar",
                "Definir odds"
        },"Bem vindo " + esp.getUsername() + "\n" + esp.getEmail()+"\n");
        menu.setTitulo("Menu Model.Especialista");
        menu.run();
    }

    private void menuAdministrador(Administrador admin){
        clearScreen();
        Menu menu = new Menu(new String[]{
                "Adicionar Model.Administrador",
                "Adicionar Model.Especialista",
                "Alterar estado aposta",
                "Alterar odds",
                "Listar Apostas",
                "Listar Jogos",
                "Listar Desportos",
                "Listar Apostadores",
                "Listar Model.Especialista",
                "Listar Administradores"
        },"Bem vindo " + admin.getUsername() + "\n" + admin.getEmail()+"\n");
        menu.setTitulo("Menu Model.Administrador");
        menu.setPreCondition(5, ()->database.existemApostas());
        menu.setPreCondition(6, ()->database.existemJogos());
        menu.setPreCondition(7, ()->database.existemDesportos());
        menu.setPreCondition(8, ()->database.existemApostadores());
        menu.setPreCondition(9, ()->database.existemEspecialistas());
        menu.setPreCondition(10, ()->database.existemAdministradores());
        menu.setHandler(1, ()->register(2));
        menu.setHandler(2, ()->register(1));
        menu.setHandler(5, ()->listarApostas());
        menu.setHandler(6, ()->listarJogos());
        menu.setHandler(7, ()->listarDesportos());
        menu.setHandler(8, ()->listarApostadores());
        menu.setHandler(9, ()->listarEspecialistas());
        menu.setHandler(10, ()->listarAdministradores());
        menu.run();
    }

    private void listarJogos(){
        clearScreen();
        header("LISTA DE JOGOS");
        for (String s : this.database.listarJogos()) {
            System.out.println("Jogo ─────────────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    private void listarDesportos(){
        clearScreen();
        header("LISTA DE DESPORTOS");
        for (String s : this.database.listarDesportos()) {
            System.out.println("Desporto ─────────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    private void listarApostas(){
        clearScreen();
        header("LISTA DE APOSTAS");
        for (String s : this.database.listarApostas()) {
            System.out.println("Aposta ───────────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    private void listarEspecialistas(){
        clearScreen();
        header("LISTA DE ESPECIALISTAS");
        for (String s : this.database.listarEspecialistas()) {
            System.out.println("Model.Especialista ───────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    private void listarAdministradores(){
        clearScreen();
        header("LISTA DE ADMINISTRADORES");
        for (String s : this.database.listarAdministradores()) {
            System.out.println("Model.Administrador ───────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    private void listarApostadores(){
        clearScreen();
        header("LISTA DE APOSTADORES");
        for (String s : this.database.listarApostadores()) {
            System.out.println("Model.Apostador ───────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    */
}
