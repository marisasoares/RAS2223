package src.UI;

import java.util.Scanner;

import src.DataLayer.Database;
import src.Model.Administrador;
import src.Model.Apostador;
import src.Model.Especialista;
import src.Model.Utilizador;

public class TextUI {

    //Scanner para leitura
    private transient Scanner scin;
    public Database database = new Database();

    /**
    * Construtor.
    *
    * Cria os menus e a camada de negócio.
    */
    public TextUI() {
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
    */
    public void run() {
        scin = new Scanner(System.in);
        database.loadUsersFromDB();
        this.menuPrincipal();
        System.out.println("Até breve...");
    }

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
    }

    //Métodos auxiliares 

    private void menuPrincipal() {
        Menu menu = new Menu(new String[]{
                "Login",
                "Register",
                "Apostar"
        });

        //Registar pré-condições das transições
        menu.setPreCondition(1, ()->this.database.existemApostadoresRegistados());

        //Registar os handlers das transições
        menu.setHandler(1,() -> login());
        menu.setHandler(2,() -> register());
        //Executar o menu
        menu.run();
    }

    private void register(){
        System.out.println("---- Rasbet ----");
        System.out.println(" ");
        System.out.println("     Registo      ");
        String username = "",email = "", password = "";
        int tentativa = 0;
        do {
            if(tentativa != 0) System.out.println("Username já utilizado ou inválido");
            System.out.print("Insira username: ");
            username = this.scin.nextLine();
            tentativa++;
        } while (this.database.usernameExists(username) || username.trim().length() == 0);
        tentativa = 0;
        do {
            if(tentativa != 0) System.out.println("Email inválido");
            System.out.print("Insira email: ");
            email = this.scin.nextLine();
            tentativa++;
        } while (email.trim().length() == 0 || !email.matches("\\w+@\\w+\\.\\w+"));
        tentativa = 0;
        do {
            if(tentativa != 0) System.out.println("A password deve conter pelo menos 8 caractéres");
            System.out.print("Insira password: ");
            password = this.scin.nextLine();
            tentativa++;
        } while (password.length() < 8);
        Apostador user = new Apostador(username, password, email);
        this.database.addUtilizador(user);
    }

    private void login(){
        System.out.println("---- Rasbet ----");
        System.out.println(" ");
        System.out.println("     Login      ");
        System.out.print("Username: ");
        String username = this.scin.nextLine();
        System.out.print("Password: ");
        String password = this.scin.nextLine();
        boolean login_Successful = database.login(username, password);
        if(login_Successful){
            Object user = database.getUtilizador(username, password);
            if(user instanceof Especialista) menuEspecialista((Especialista) user);
            else if(user instanceof Administrador) menuAdministrador(((Administrador) user));
            else menuApostador((Apostador)user);
            
        } else System.out.println("Login Inválido");
        scin.nextLine();
        
    }

    private void menuApostador(Apostador apostador){
        clearScreen();
        System.out.println("Bem vindo " + apostador.getUsername());
        System.out.println(apostador.getEmail());
        Menu menu = new Menu(new String[]{
                "Apostar"
        });
        menu.setTitulo("Menu Apostador");
        menu.run();
    }

    private void menuEspecialista(Especialista esp){
        clearScreen();
        System.out.println("Bem vindo " + esp.getUsername());
        System.out.println(esp.getEmail());
        Menu menu = new Menu(new String[]{
                "Apostar",
                "Definir odds"
        });
        menu.setTitulo("Menu Especialista");
        menu.run();
    }

    private void menuAdministrador(Administrador admin){
        clearScreen();
        System.out.println("Bem vindo " + admin.getUsername());
        System.out.println(admin.getEmail());
        Menu menu = new Menu(new String[]{
                "Alterar estado aposta",
                "Lista apostas ativas",
                "Alterar odds"
        });
        menu.setTitulo("Menu Administrador");
        menu.run();
    }
}
