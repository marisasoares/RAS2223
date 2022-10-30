package UI;

import Model.Game;

import java.util.Scanner;

public class RasBetUI {

	/**
	 * Scanner para leitura
	 * */
	private transient Scanner scanner;

	public RasBetUI(){

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
				"Apostar"
		});

		/*Registar pré-condições das transições
		/menu.setPreCondition(1, ()->this.database.existemUtilizadores());

		//Registar os handlers das transições
		menu.setHandler(1,() -> login());
		menu.setHandler(2,() -> register(0));
		//Executar o menu*/
		menu.run();
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

	public void showHomePageApostador() {
		// TODO - implement UI.RasBetUI.showHomePageApostador
		throw new UnsupportedOperationException();
	}

	public void showHomePageEspecialista() {
		// TODO - implement UI.RasBetUI.showHomePageEspecialista
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