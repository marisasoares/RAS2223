package UI;

import Model.*;

import java.util.Scanner;

public class RasBetUI {

	/**
	 * Scanner para leitura
	 * */
	private transient Scanner scanner;
	private RasBetFacade rasBetFacade;

	public RasBetUI(RasBetFacade rasBetFacade){
		this.rasBetFacade = rasBetFacade;
	}


	// Funções auxiliares ---------------------------------------------------------------

	public void pressEnterToContinue(){
		System.out.println(" - press enter to continue -");
		this.scanner.nextLine();
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

	private void errorMessage(String s){
		System.out.println("\033[0;31m[Erro] " + s + "\033[0m");
	}



	// -------------------------------------------------------------------------------------

	/**
	 * Executa o menu principal e invoca o método correspondente à opção seleccionada.
	 */
	public void run() {
		scanner = new Scanner(System.in);
		//database.loadUsersFromDB();
		//database.loadJogosAndSportsFromDB();
		//database.loadApostasFromDB();
		this.menuPrincipal();
		clearScreen();
	}

	private void menuPrincipal() {
		clearScreen();
		System.out.println("v 3.0");
		Menu menu = new Menu(new String[]{
				"Login",
				"Registar",
		});

		//Registar pré-condições das transições
		//menu.setPreCondition(1, ()->this.database.existemUtilizadores());

		//Registar os handlers das transições
		menu.setHandler(1,() -> login());
		menu.setHandler(2,() -> register(0));
		//Executar o menu
		menu.run();
	}

	private void login(){
		clearScreen();
		header("LOGIN");
		System.out.print(" Email: ");
		String email = this.scanner.nextLine();
		System.out.print(" Password: ");
		String password = this.scanner.nextLine();
		boolean login_Successful = rasBetFacade.login(email, password);
		if(login_Successful){
			Object user = rasBetFacade.getauthenticatedUser();
			if(user instanceof Specialist) menuSpecialist((Specialist) user);
			else if(user instanceof Administrator) menuAdministrator(((Administrator) user));
			else menuBetter((Better)user);

		} else {
			errorMessage("Login Inválido");
			pressEnterToContinue();
		}
		return;
	}

	private void menuBetter(Better Better){
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Apostar",
				"Alterar password"
		},"Bem vindo " +  Better.getName() + "\nSaldo: \n" + Better.getWallet().getEuros()+ " €"
		+ "\n" + Better.getWallet().getDollars()+ " $\n");
		menu.setTitulo("Menu Better");
		menu.run();
	}

	private void menuSpecialist(Specialist esp){
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Apostar",
				"Definir odds"
		},"Bem vindo" + esp.getName());
		menu.setTitulo("Menu Specialist");
		menu.run();
	}

	private void menuAdministrator(Administrator admin){
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Adicionar Model.Administrator",
				"Adicionar Model.Specialist",
				"Alterar estado aposta",
				"Alterar odds",
				"Listar Apostas",
				"Listar Jogos",
				"Listar Desportos",
				"Listar Betteres",
				"Listar Model.Specialist",
				"Listar Administratores"
		},"Bem vindo" + admin.getName());
		menu.setTitulo("Menu Administrator");
		menu.run();
	}

	/*
	 * UI para registo de um utilizador
	 * @param tipo_de_utilizador 0 - Better, 1 - Specialist, 2 - Administrator
	 */
    private void register(int tipo_de_utilizador){
        clearScreen();
        header("REGISTO");
        String name = "",email = "", password = "",nif = "";
        int tentativa = 0;
        do {
            if(tentativa != 0) {
                //Apagar as duas linhas superiores
                System.out.print(String.format("\033[%dA",1));
                System.out.print("\033[2K");
                System.out.print(String.format("\033[%dA",1));
                System.out.print("\033[2K");
                errorMessage("Nome Inválido");
            }
            System.out.print("Insira o seu nome: ");
            name = this.scanner.nextLine();
            tentativa++;
        } while (name.trim().length() == 0);
        tentativa = 0;
        System.out.println();
        do {
            if(tentativa != 0) {
                //Apagar as duas linhas superiores
                System.out.print(String.format("\033[%dA",1));
                System.out.print("\033[2K");
                System.out.print(String.format("\033[%dA",1));
                System.out.print("\033[2K");
                errorMessage("Email inválido ou já utilizado");
            }
            System.out.print("Insira email: ");
            email = this.scanner.nextLine();
            tentativa++;
        } while ( rasBetFacade.usersDataBase.userExists(email) || email.trim().length() == 0 || !email.matches("\\w+@\\w+\\.\\w+"));
        tentativa = 0;
		System.out.println();
		do {
			if(tentativa != 0) {
				//Apagar as duas linhas superiores
				System.out.print(String.format("\033[%dA",1));
				System.out.print("\033[2K");
				System.out.print(String.format("\033[%dA",1));
				System.out.print("\033[2K");
				errorMessage("NIF no formato incorreto (9 digitos)");
			}
			System.out.print("Insira NIF: ");
			nif = this.scanner.nextLine();
			tentativa++;
		} while (nif.length() != 9);
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
        this.rasBetFacade.register(name,email,password,nif,tipo_de_utilizador);
        pressEnterToContinue();
    }


	/**
	 *
	 * @param email
	 * @param pwd
	 */
	public void loginData(String email, String pwd) {
		// TODO - implement UI.RasBetUI.loginData
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param email
	 * @param pwd
	 * @param nif
	 */
	public void registerData(String email, String pwd, String nif) {
		// TODO - implement UI.RasBetUI.registerData
		throw new UnsupportedOperationException();
	}

	public void showRegisterPage() {
		// TODO - implement UI.RasBetUI.showRegisterPage
		throw new UnsupportedOperationException();
	}

	public void showLoginPage() {
		// TODO - implement UI.RasBetUI.showLoginPage
		throw new UnsupportedOperationException();
	}

	public void showHomePageBetter() {
		// TODO - implement UI.RasBetUI.showHomePageBetter
		throw new UnsupportedOperationException();
	}

	public void showHomePageSpecialist() {
		// TODO - implement UI.RasBetUI.showHomePageSpecialist
		throw new UnsupportedOperationException();
	}

	public void showHomePageAdmin() {
		// TODO - implement UI.RasBetUI.showHomePageAdmin
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param message
	 */
	public void showPopUp(String message) {
		// TODO - implement UI.RasBetUI.showPopUp
		throw new UnsupportedOperationException();
	}

	public void showPaymentPage() {
		// TODO - implement UI.RasBetUI.showPaymentPage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param game
	 */
	public void showGamePage(Game game) {
		// TODO - implement UI.RasBetUI.showGamePage
		throw new UnsupportedOperationException();
	}

	public void showHistPage() {
		// TODO - implement UI.RasBetUI.showHistPage
		throw new UnsupportedOperationException();
	}

	public void showTransHist() {
		// TODO - implement UI.RasBetUI.showTransHist
		throw new UnsupportedOperationException();
	}

	public void showTransferPage() {
		// TODO - implement UI.RasBetUI.showTransferPage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param game
	 */
	public void showGamesRequest(String game) {
		// TODO - implement UI.RasBetUI.showGamesRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param game
	 */
	public void showChangePage(Game game) {
		// TODO - implement UI.RasBetUI.showChangePage
		throw new UnsupportedOperationException();
	}

	public void showAssurancePage() {
		// TODO - implement UI.RasBetUI.showAssurancePage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param valor
	 */
	public void transfer(float valor) {
		// TODO - implement UI.RasBetUI.transfer
		throw new UnsupportedOperationException();
	}

	public void loginRequest() {
		// TODO - implement UI.RasBetUI.loginRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	public void changeInfoRequest(String nome, String password) {
		// TODO - implement UI.RasBetUI.changeInfoRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param game
	 * @param value
	 */
	public void betRequest(String game, float value) {
		// TODO - implement UI.RasBetUI.betRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param valor
	 */
	public void paymentRequest(float valor) {
		// TODO - implement UI.RasBetUI.paymentRequest
		throw new UnsupportedOperationException();
	}

	public void betHistoryRequest() {
		// TODO - implement UI.RasBetUI.betHistoryRequest
		throw new UnsupportedOperationException();
	}

	public void transHistoryRequest() {
		// TODO - implement UI.RasBetUI.transHistoryRequest
		throw new UnsupportedOperationException();
	}

	public void transferRequest() {
		// TODO - implement UI.RasBetUI.transferRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param odd
	 */
	public void insereChange(float odd) {
		// TODO - implement UI.RasBetUI.insereChange
		throw new UnsupportedOperationException();
	}

}