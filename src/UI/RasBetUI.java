package UI;

import DataLayer.BetDAO;
import DataLayer.GameDAO;
import DataLayer.ResultDAO;
import DataLayer.UserDAO;
import Model.*;
import com.sun.jdi.Value;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;
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
		System.out.println("    " + string + "      \033[0m\n");
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
		//TODO MUDAR TODO
		this.menuPrincipal();
		//rasBetFacade.setEmailAuthenticatedUser("user1@gmail.com");
		//this.menuBetter("user1@gmail.com");
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
		menu.setPreCondition(1, ()-> RasBetFacade.existemUtilizadores());
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
			Object user = rasBetFacade.getAuthenticatedUser();
			if(user instanceof Specialist) menuSpecialist(((User) user).getMail());
			else if(user instanceof Administrator) menuAdministrator(((User) user).getMail());
			else menuBetter(((User) user).getMail());

		} else {
			errorMessage("Login Inválido");
			pressEnterToContinue();
		}
		return;
	}

	private void menuBetter(String email){
		Better better = (Better) UserDAO.get(email);
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Apostar",
				"Alterar Nome",
				"Alterar password"
		},"Bem vindo\nSaldo: \n" + better.getWallet().getEuros()+ " €"
		+ "\n" + better.getWallet().getDollars()+ " $\n");
		menu.setTitulo("Menu Better");
		menu.setHandler(1,() -> menuAposta());
		menu.setHandler(2,() -> alterarNome());
		menu.setHandler(3,() -> alterarPassword());
		menu.run();
	}

	private void menuAposta(){
		int sportId = selectSport();
		String gameId = selectGame(sportId);
		rasBetFacade.setIdCurrentSelectedGame(gameId);
		Game game = GameDAO.get(gameId);
		int bettedteam = selectResult(game);
		ListMenu menu = new ListMenu();
		menu.adicionaOpcao("Dollars "+" Dinheiro Disponível: " + ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getDollars());
		menu.adicionaOpcao("Euros " +" Dinheiro Disponível: "+ ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getEuros());
		menu.show(true);
		int escolha = Utils.InputInteger(this.scanner,1,2);

		System.out.print("Insira o valor a apostar: ");
		float valor = 0;
		valor = Utils.InputFloat(this.scanner);
		String email = rasBetFacade.getAuthenticatedUser().getMail();
		if(escolha == 1) {
			if(rasBetFacade.validateTransferDollars(-valor,email)){
				int multipleId = Utils.geraIdentificadorUnicoInteger(Utils.multipleIds);
				Utils.multipleIds.put(multipleId,multipleId);
				rasBetFacade.addBet(sportId,gameId,email,valor,bettedteam,multipleId,false);
				rasBetFacade.addMovementDollars(-valor,email,"Aposta");
				System.out.println("Aposta Realizada");
				pressEnterToContinue();
			}
			else {
				errorMessage("Não tem dinheiro suficiente");
				pressEnterToContinue();
			}
		}
		else {
			if (rasBetFacade.validateTransferEuros(-valor, rasBetFacade.getAuthenticatedUser().getMail())) {
				int multipleId = Utils.geraIdentificadorUnicoInteger(Utils.multipleIds);
				Utils.multipleIds.put(multipleId, multipleId);
				rasBetFacade.addBet(sportId, gameId, email, valor, bettedteam, multipleId, false);
				rasBetFacade.addMovementEuros(-valor,email,"Aposta");
				System.out.println("Aposta Realizada");
				pressEnterToContinue();
			}
			else {
				errorMessage("Não tem dinheiro suficiente");
				pressEnterToContinue();
			}
		}


	}

	public int selectSport(){
		clearScreen();
		int selected = -1;
		header("Lista de desportos");
		ListMenu menuSports = new ListMenu();
		menuSports.setTitulo("Desportos disponíveis");
		List<Sport> sportList = rasBetFacade.getSportList();
		for (Sport sport : sportList) {
			menuSports.adicionaOpcao(sport.getNome());
		}
		menuSports.show(true);
		selected = Utils.InputInteger(this.scanner,1,sportList.size());
		return sportList.get(selected-1).getId();
	}

	public String selectGame(int sportId){
		int selected = -1;
		clearScreen();
		ListMenu menuGames = new ListMenu();
		List<Game> games = rasBetFacade.listActivesGames(sportId);
		menuGames.setTitulo("Jogos disponíveis");
		for (Game game : games ) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			LocalDateTime dateTime = LocalDateTime.parse(game.getCommenceTime(), formatter);
			String date = dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + " " + dateTime.getHour()+ ":" + dateTime.getMinute();
			menuGames.adicionaOpcao(game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + date);
		}
		menuGames.show(true);
		selected = Utils.InputInteger(this.scanner,0,games.size());
		return games.get(selected-1).getId();
	}

	public int selectResult(Game game){
		clearScreen();
		header("Selecionar um resultado");
		ListMenu menuAposta = new ListMenu();
		menuAposta.adicionaOpcao( game.getHomeTeam()  + " - " +  game.getResult().getOddHomeTeam());
		menuAposta.adicionaOpcao( game.getAwayTeam()  + " - " +  game.getResult().getOddAwayTeam());
		menuAposta.adicionaOpcao( "Empate - " + game.getResult().getOddDraw());
		menuAposta.show(true);
		int selected = Utils.InputInteger(this.scanner,1,3);
		return selected-1;
	}

	private void alterarNome(){
		clearScreen();
		header("Alterar Nome");
		System.out.println("Insira novo nome: ");
		String nome;
		do{
			nome = this.scanner.nextLine();
		}while (nome.trim().length() == 0);
		User user = rasBetFacade.getAuthenticatedUser();
		rasBetFacade.changeInfo(nome,user.getMail());
		System.out.println("Nome alterado com sucesso");
		pressEnterToContinue();
	}

	private void alterarPassword(){
		clearScreen();
		header("Alterar Password");
		System.out.println("Insira nova Password");
		String password;
		do{
			password = this.scanner.nextLine();
		}while (password.length() < 8);

		User user = rasBetFacade.getAuthenticatedUser();
		rasBetFacade.changeInfo(user.getName(),password,user.getMail());
		System.out.println("Password alterada com sucesso");
		pressEnterToContinue();
	}

	private void menuSpecialist(String email){
		Specialist esp = (Specialist) UserDAO.get(email);
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Alterar odds"
		},"Bem vindo " + esp.getName()+"\n");
		menu.setTitulo("Menu Specialist");
		menu.setHandler(1,()->menuAlterarOdd());
		menu.run();
	}

	private boolean menuAlterarOdd(){
		int sportId = selectSport();
		String gameId = selectGame(sportId);
		boolean sair = false;
		while(!sair){
			sair = menuOdds(gameId);
		}

		return false;
	}

	private boolean menuOdds(String gameId){
		boolean sair = false;
		Game game = GameDAO.get(gameId);
		Result result = ResultDAO.get(game.getResult().getResultID());
		clearScreen();
		header("Selecione uma Odd");
		ListMenu odds = new ListMenu();
		odds.adicionaOpcao( "Odd Casa " + result.getOddHomeTeam() + " " +game.getHomeTeam());
		odds.adicionaOpcao( "Odd Fora " + result.getOddAwayTeam() + " " +game.getAwayTeam());
		odds.adicionaOpcao( "Odd Empate - " + result.getOddDraw() + "Empate");
		odds.show(true);
		int escolha = Utils.InputInteger(this.scanner,0,3);
		if(escolha == 0) return true;
		System.out.println("Insira nova Odd");
		float odd;
		odd = Utils.InputFloat(this.scanner);
		switch (escolha) {
			case 1 -> rasBetFacade.inserirChange(odd, game.getId(), 0);
			case 2 -> rasBetFacade.inserirChange(odd, game.getId(), 1);
			default -> rasBetFacade.inserirChange(odd, game.getId(), 2);
		}
		return sair;
	}

	private void menuAdministrator(String email){
		Administrator admin = (Administrator) UserDAO.get(email);
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Adicionar Administrator",
				"Adicionar Especialista",
				"Alterar estado aposta",
				"Alterar odds",
				"Listar Apostas",
				"Listar Jogos",
				"Listar Desportos",
				"Listar Apostadores",
				"Listar Especialistas",
				"Listar Administratores"
		},"Bem vindo " + admin.getName()+"\n");
		menu.setTitulo("Menu Administrator");
		menu.setHandler(1,()-> register(2));
		menu.setHandler(2,()-> register(1));
		menu.setHandler(3,()-> menuAlterarEstadoAposta());
		menu.setHandler(4,()-> menuAlterarOdd());
		menu.setHandler(5,()-> menuAlterarOdd());
		menu.run();
	}

	private void menuAlterarEstadoAposta() {
		int sportId = selectSport();
		String gameID = selectGame(sportId);
		boolean sair = false;
		while (!sair){
			sair = escolherAlterarApostaAtiva(gameID);

		}


	}

	public boolean escolherAlterarApostaAtiva(String gameId){
		boolean sair = false;
		List<Bet> bets = rasBetFacade.getBetList(gameId);
		clearScreen();
		header("Apostas Ativas");
		ListMenu betsMenu = new ListMenu();
		for (Bet b:bets) {
			String winningTeam = "" ;
				if(b.getBettedTeam() == 0) winningTeam = rasBetFacade.getGame(gameId).getHomeTeam();
				else if(b.getBettedTeam() == 1) winningTeam = rasBetFacade.getGame(gameId).getAwayTeam();
				else winningTeam = "Empate";
				String bet = "ID da aposta: " + b.getBetId() + ", Equipa apostada: " + winningTeam + ", Valor: " + b.getValue() + " , Email do apostador: " + b.getEmail() + ", Suspensa: " + b.getisSuspended();
			betsMenu.adicionaOpcao(bet);
		}
		betsMenu.show(true);
		int escolha = Utils.InputInteger(this.scanner,0,bets.size());
		Bet betselected = null;
		if(escolha != 0){
			betselected = bets.get(escolha-1);
		}
		else sair = true;
		ListMenu betMenu = new ListMenu();
		betMenu.adicionaOpcao("Alterar estado de suspensão");
		betMenu.show(true);
		escolha = Utils.InputInteger(this.scanner,0,1);
		if(escolha == 1) rasBetFacade.alteraEstado(betselected.getBetId());
		return  sair;
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
                erraseLines();
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
                erraseLines();
                errorMessage("Email inválido ou já utilizado");
            }
            System.out.print("Insira email: ");
            email = this.scanner.nextLine();
            tentativa++;
        } while ( email.trim().length() == 0 || !email.matches("\\w+@\\w+\\.\\w+"));
        tentativa = 0;
		System.out.println();
		do {
			if(tentativa != 0) {
				erraseLines();
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
                erraseLines();
                errorMessage("A password deve conter pelo menos 8 caractéres");
                }
            System.out.print("Insira password: ");
            password = this.scanner.nextLine();
            tentativa++;
        } while (password.length() < 8);
        this.rasBetFacade.registerUser(name,email,password,nif,tipo_de_utilizador);
        pressEnterToContinue();
    }

	private void erraseLines(){
		System.out.print(String.format("\033[%dA",1));
		System.out.print("\033[2K");
		System.out.print(String.format("\033[%dA",1));
		System.out.print("\033[2K");
	};


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