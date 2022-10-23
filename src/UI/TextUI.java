package src.UI;

import java.util.Scanner;

import src.DataLayer.Database;
import src.Model.Administrador;
import src.Model.Apostador;
import src.Model.Especialista;

import src.UI.Menu;

public class TextUI {

    //Scanner para leitura
    private transient Scanner scanner;
    public Database database = new Database();

    /**
    * Construtor.
    *
    * Cria os menus e a camada de negócio.
    */
    public TextUI() {
    }

    public void pressEnterToContinue(){
        System.out.println(" - press enter to continue -");
        this.scanner.nextLine();
    }

    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
    */
    public void run() {
        scanner = new Scanner(System.in);
        database.loadUsersFromDB();
        this.menuPrincipal();
        clearScreen();
    }

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
    }

    //Métodos auxiliares 

    private void menuPrincipal() {
        clearScreen();
        Menu menu = new Menu(new String[]{
                "Login",
                "Registar",
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

    private void errorMessage(String s){
        System.out.println("\033[0;31m[Erro] " + s + "\033[0m");
    }

    private void register(){
        clearScreen();
        System.out.print("\033[0;36m");
        System.out.println("╔══════════╗");
        System.out.println("║  Rasbet  ╠════════════════════════");
        System.out.println("╚══════════╝ ");
        System.out.println("\n    REGISTO      \033[0m\n\n");
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
        Apostador user = new Apostador(username, password, email);
        this.database.addUtilizador(user);
        pressEnterToContinue();
    }

    private void login(){
        clearScreen();
        System.out.print("\033[0;36m");
        System.out.println("╔══════════╗");
        System.out.println("║  Rasbet  ╠════════════════════════");
        System.out.println("╚══════════╝ ");
        System.out.println("\n    LOGIN      \033[0m\n");
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
                "Apostar"
        },"Bem vindo " + apostador.getUsername() + "\n" + apostador.getEmail()+"\n");
        menu.setTitulo("Menu Apostador");
        menu.run();
    }

    private void menuEspecialista(Especialista esp){
        clearScreen();
        Menu menu = new Menu(new String[]{
                "Apostar",
                "Definir odds"
        },"Bem vindo " + esp.getUsername() + "\n" + esp.getEmail()+"\n");
        menu.setTitulo("Menu Especialista");
        menu.run();
    }

    private void menuAdministrador(Administrador admin){
        clearScreen();
        Menu menu = new Menu(new String[]{
                "Alterar estado aposta",
                "Lista apostas ativas",
                "Alterar odds"
        },"Bem vindo " + admin.getUsername() + "\n" + admin.getEmail()+"\n");
        menu.setTitulo("Menu Administrador");
        menu.run();
    }
}
