package src.UI;

import java.util.Scanner;

import src.DataLayer.Database;
import src.DataLayer.IDatabase;
import src.Model.Administrador;
import src.Model.Apostador;
import src.Model.Especialista;

public class TextUI {

    //Scanner para leitura
    private transient Scanner scanner;
    public IDatabase database = new Database();

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
        database.loadJogosAndSportsFromDB();
        database.loadApostasFromDB();
        this.menuPrincipal();
        clearScreen();
    }

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
    }

    public static void header(String string){
        System.out.print("\033[0;36m");
        System.out.println("╔══════════╗");
        System.out.println("║  Rasbet  ╠════════════════════════");
        System.out.println("╚══════════╝ ");
        System.out.println("\n    " + string + "      \033[0m\n\n");
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
        menu.setPreCondition(1, ()->this.database.existemUtilizadores());

        //Registar os handlers das transições
        menu.setHandler(1,() -> login());
        menu.setHandler(2,() -> register(0));
        //Executar o menu
        menu.run();
    }

    private void errorMessage(String s){
        System.out.println("\033[0;31m[Erro] " + s + "\033[0m");
    }

    /*
     * UI para registo de um utilizador
     * @param tipo_de_utilizador 0 - apostador, 1 - especialista, 2 - administrador
     */
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
                "Adicionar Administrador",
                "Adicionar Especialista",
                "Alterar estado aposta",
                "Alterar odds",
                "Listar Apostas",
                "Listar Jogos",
                "Listar Desportos",
                "Listar Apostadores",
                "Listar Especialista",
                "Listar Administradores"
        },"Bem vindo " + admin.getUsername() + "\n" + admin.getEmail()+"\n");
        menu.setTitulo("Menu Administrador");
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
            System.out.println("Especialista ───────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    private void listarAdministradores(){
        clearScreen();
        header("LISTA DE ADMINISTRADORES");
        for (String s : this.database.listarAdministradores()) {
            System.out.println("Administrador ───────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }

    private void listarApostadores(){
        clearScreen();
        header("LISTA DE APOSTADORES");
        for (String s : this.database.listarApostadores()) {
            System.out.println("Apostador ───────────────────────────");
            System.out.println(s);
        }
        System.out.println("FIM ──────────────────────────────────");
        pressEnterToContinue();
    }
}
