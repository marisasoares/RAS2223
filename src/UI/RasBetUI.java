package UI;

import DataLayer.GameDAO;
import DataLayer.ResultDAO;
import DataLayer.UserDAO;
import Model.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		System.out.println(" - Pressione enter para continuar -");
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
	}

	private void menuBetter(String email){
		Better better = (Better) UserDAO.get(email);
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Apostar",
				"Histórico de Apostas",
				"Histórico de Transferencias",
				"Consultar saldo",
				"Depositar dinheiro",
				"Retirar dinheiro",
				"Ver notificações",
				"Ver histórico de notificações",
				"Ver estatísticas",
				"Alterar Nome",
				"Alterar password"
		},"Bem vindo\n");
		menu.setTitulo("Menu Better");
		menu.setHandler(1,() -> menuAposta());
		menu.setHandler(2,() -> menuHistoricoApostas());
		menu.setHandler(3,() -> menuHistoricoTransferencias());
		menu.setHandler(4,() -> consultarSaldo());
		menu.setHandler(5,() -> menuDepositar());
		menu.setHandler(6,() -> menuLevantar());
		menu.setHandler(7,() -> menuUnreadNotifications());
		menu.setHandler(8,() -> menuNotificationHistory());
		menu.setHandler(9,() -> menuStats());
		menu.setHandler(10,() -> alterarNome());
		menu.setHandler(11,() -> alterarPassword());
		menu.run();
	}

	private void menuStats(){
		clearScreen();
		header("Estatísticas");
		String email = rasBetFacade.getAuthenticatedUser().getMail();
		System.out.println("Ganhos totais: ");
		List<Bet> betsUser = rasBetFacade.getBetListEmail(email);
		float totalEuros = 0;
		float totalDollars = 0;
		for (Bet b : betsUser) {
			Game game = rasBetFacade.getGame(b.getGameId());
			if(game.getCompleted()){
				Result result = rasBetFacade.getResultByResultId(game.getResult().getResultID());
				String winningteam = result.getwinningTeam();
				int bettedteam = b.getBettedTeam();
				switch (bettedteam){
					case 0:
						if(b.getCurrency().equals("euros")){
							if(winningteam.equalsIgnoreCase(game.getHomeTeam())) {
								totalEuros += result.getOddHomeTeam() * b.getValue();
							}
						}else {
							if(winningteam.equalsIgnoreCase(game.getHomeTeam())) {
								totalDollars += result.getOddHomeTeam() * b.getValue();
							}
						}
						break;
					case 1:
						if(b.getCurrency().equals("euros")) {
							if(winningteam.equalsIgnoreCase(game.getAwayTeam())) {
								totalEuros += result.getOddAwayTeam() * b.getValue();
							}
						}else{
							if(winningteam.equalsIgnoreCase(game.getAwayTeam())) {
								totalDollars += result.getOddAwayTeam() * b.getValue();
							}
						}
						break;
					default:
						if(b.getCurrency().equals("euros")) {
							if(winningteam.equalsIgnoreCase("Draw")) {
								totalEuros += result.getOddAwayTeam() * b.getValue();
							}
						}else {
							if (winningteam.equalsIgnoreCase("Draw")) {
								totalDollars += result.getOddAwayTeam() * b.getValue();
							}
							break;
						}
				}

			}

		}

	}

	private void menuUnreadNotifications(){
		clearScreen();
		header("Notificações não lidas");
		List<Notification> notifications = rasBetFacade.listNotReadNotifications(rasBetFacade.getAuthenticatedUser().getMail());
		for (Notification n : notifications ) {
			String date = n.getDate().getDayOfMonth()+"/"+n.getDate().getMonthValue()+"/"+n.getDate().getYear()+" " + n.getDate().getHour()+":" + String.format("%02d",n.getDate().getMinute());
			System.out.println("•" + date+ " " + n.getContent());
		}
		if(notifications.isEmpty()) errorMessage("Sem notificações por ler");
		pressEnterToContinue();
	}

	private void menuNotificationHistory() {
		clearScreen();
		header("Histórico de notificações");
		List<Notification> notifications = rasBetFacade.listAllNotifications(rasBetFacade.getAuthenticatedUser().getMail());
		for (Notification n : notifications ) {
			String date = n.getDate().getDayOfMonth()+"/"+n.getDate().getMonthValue()+"/"+n.getDate().getYear()+" " + n.getDate().getHour()+":" + String.format("%02d",n.getDate().getMinute());
			System.out.println("•" + date + " " + n.getContent());
		}
		if(notifications.isEmpty()) errorMessage("Sem notificações");
		pressEnterToContinue();
	}

	private void menuLevantar() {
		String email = rasBetFacade.getAuthenticatedUser().getMail();

		ListMenu menu = new ListMenu();
		menu.adicionaOpcao("Levantar Dollars" );
		menu.adicionaOpcao("Levantar Euros ");
		menu.show(true);
		int escolha = Utils.InputInteger(this.scanner,0,2);
		if (escolha == 0) return;
		if(escolha == 1 ){
			System.out.print("Valor a Levantar: ");
			float value = Utils.InputFloat(this.scanner);
			if(rasBetFacade.addMovementDollars(-value,email,"Levantamento")) {
				float dollars = ((Better) rasBetFacade.getAuthenticatedUser()).getWallet().getDollars();
				System.out.println("Levantado " + value + " $");
				System.out.println("Saldo Atual " + dollars + " $");
			} else errorMessage("Saldo insuficiente");

		}
		else {
			System.out.print("Valor a Levantar: ");
			float value = Utils.InputFloat(this.scanner);
			if(rasBetFacade.addMovementEuros(-value,email,"Levantamento")){
				float euros = ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getEuros();
				System.out.println("Levantado " + value + " €");
				System.out.println("Saldo Atual " + euros + " €");
			} else errorMessage("Saldo insuficiente");

		}
		pressEnterToContinue();

	}

	private void menuDepositar() {
		String email = rasBetFacade.getAuthenticatedUser().getMail();
		ListMenu menu = new ListMenu();
		menu.adicionaOpcao("Adicionar Dollars" );
		menu.adicionaOpcao("Adicionar Euros ");
		menu.show(true);
		int escolha = Utils.InputInteger(this.scanner,0,2);
		if (escolha == 0) return;
		if(escolha == 1 ){
			System.out.print("Valor a depositar: ");
			float value = Utils.InputFloat(this.scanner);
			if(rasBetFacade.addMovementDollars(value,email,"Deposito")) {
				float dollars = ((Better) rasBetFacade.getAuthenticatedUser()).getWallet().getDollars();
				System.out.println("Depositado " + value + " $");
				System.out.println("Saldo Atual " + dollars + " $");
			}
		}
		else {
			System.out.print("Valor a depositar: ");
			float value = Utils.InputFloat(this.scanner);
			if(rasBetFacade.addMovementEuros(value,email,"Deposito")) {
				float euros = ((Better) rasBetFacade.getAuthenticatedUser()).getWallet().getEuros();
				System.out.println("Depositado " + value + " €");
				System.out.println("Saldo Atual " + euros + " €");
			}
		}
		pressEnterToContinue();
	}

	public void consultarSaldo(){
		System.out.println("Saldo disponível: ");
		float dollars = ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getDollars();
		float euros = ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getEuros();
		System.out.println(dollars + " $");
		System.out.println(euros + " €");
		pressEnterToContinue();
	}

	private void menuHistoricoTransferencias() {
		clearScreen();
		header("Histórico de Transferências");
		List<Transfer> trans = rasBetFacade.transHistory(rasBetFacade.getAuthenticatedUser().getMail());

		for (Transfer t : trans) {
			String date = t.getDate().getDayOfMonth()+"/"+t.getDate().getMonthValue()+"/"+t.getDate().getYear()+" " + t.getDate().getHour()+":" + String.format("%02d",t.getDate().getMinute());
			System.out.println("•  ID Transferência : " + t.getId() + ", Valor : " + t.getValue()  + ", Data : " + date + ", Descrição : " + t.getDescription());
		}
		pressEnterToContinue();
	}

	private void menuHistoricoApostas(){
		clearScreen();
		header("Histórico de Apostas");
		List<Bet> bets = rasBetFacade.getBetListEmail(rasBetFacade.getAuthenticatedUser().getMail());
		for (Bet b:bets) {
			Game game = rasBetFacade.getGame(b.getGameId());
			String bettedTeam = null;
			String estado = null;
			if (b.getBettedTeam() == 0) bettedTeam = game.getHomeTeam();
			else if (b.getBettedTeam() == 1) bettedTeam = game.getAwayTeam();
			else bettedTeam = "Empate";
			if (b.getBetState() == 1) estado = "Ganha";
			else if (b.getBetState() == 0) estado = "Perdida";
			else estado = "Ativa";
			if (b.getCurrency().equals("euros"))
				System.out.println("• ID da aposta: " + b.getBetId() + ", Valor : " + b.getValue() + " €" + ", " + game.getHomeTeam() + " x " + game.getAwayTeam() + ", Equipa apostada : " + bettedTeam + ", Estado : " + estado);
			else System.out.println("• ID da aposta: " + b.getBetId() + ", Valor : " + b.getValue() + " $" + ", " + game.getHomeTeam() + " x " + game.getAwayTeam() + ", Equipa apostada : " + bettedTeam + ", Estado : " + estado);
		}
		pressEnterToContinue();
	}

	private void menuAposta(){
		int multipleId = -1;
		String input;
		boolean sair = false;
		int sportId = selectSport();
		String gameId = selectGame(sportId);
		rasBetFacade.setIdCurrentSelectedGame(gameId);
		multipleId = realizaAposta(gameId,multipleId,false);
		do {
			    System.out.println("Pretende adicionar mais apostas nesta aposta [Y/N]");
			do {
				input = this.scanner.nextLine();
			} while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
			if(input.equals("n")) sair = true;
			else{
				sportId = selectSport();
				gameId= selectGame(sportId);
				rasBetFacade.setIdCurrentSelectedGame(gameId);
				multipleId = realizaAposta(gameId,multipleId,true);
			}
		}while (!sair);
	}

	public int realizaAposta(String gameId,int multipleId,boolean sameAposta){
		Game game = GameDAO.get(gameId);
		int bettedteam = selectResult(game);
		ListMenu menu = new ListMenu();
		menu.adicionaOpcao("Dollars "+" Dinheiro Disponível: " + ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getDollars());
		menu.adicionaOpcao("Euros " +" Dinheiro Disponível: "+ ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getEuros());
		menu.show(true);
		int escolha = Utils.InputInteger(this.scanner,1,2);
		System.out.print("Insira o valor a apostar: ");
		float valor = Utils.InputFloat(this.scanner);
		String email = rasBetFacade.getAuthenticatedUser().getMail();
		int resultid = game.getResult().getResultID();
		float odd;
		Result result = rasBetFacade.getResultByResultId(resultid);
		System.out.println("Jogo: " +  game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.commenceTime);
		if(bettedteam == 0){
			odd = result.getOddHomeTeam();
			System.out.println("Equipa apostada: " + game.getHomeTeam() + " [" + odd + "]");
		}
		else if(bettedteam == 1){
			odd = result.getOddAwayTeam();
			System.out.println("Equipa apostada: " + game.getAwayTeam() + " [" + odd + "]");
		}
		else odd = result.getOddDraw();
		if(escolha == 1) System.out.println("Ganhos esperados: " + valor*odd + " $");
		else System.out.println("Ganhos esperados: " + valor*odd + " €");
		System.out.print("Confirma os dados acima referidos [Y/N]: ");
		String input;
		do {
			input = this.scanner.nextLine();

		} while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
		if(input.equals("n")) {
			errorMessage("Aposta cancelada");
			pressEnterToContinue();
			return -1;
		}
		if(!sameAposta) {
			multipleId = Utils.geraIdentificadorUnicoInteger(Utils.multipleIds);
			Utils.multipleIds.put(multipleId, multipleId);
		}
		if(escolha == 1) {
			if(rasBetFacade.validateTransferDollars(-valor,email)){
				if(multipleId == -1) {
					multipleId = Utils.geraIdentificadorUnicoInteger(Utils.multipleIds);
					Utils.multipleIds.put(multipleId, multipleId);
				}
				rasBetFacade.addBet(gameId,email,valor,bettedteam,multipleId,false,2,"dollars");
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
				multipleId = Utils.geraIdentificadorUnicoInteger(Utils.multipleIds);
				Utils.multipleIds.put(multipleId, multipleId);
				rasBetFacade.addBet(gameId, email, valor, bettedteam, multipleId, false,2,"euros");
				rasBetFacade.addMovementEuros(-valor,email,"Aposta");
				System.out.println("Aposta Realizada");
				pressEnterToContinue();
			}
			else {
				errorMessage("Não tem dinheiro suficiente");
				pressEnterToContinue();
			}
		}
		return multipleId;
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
				"Alterar odds",
				"Adicionar Jogo"
		},"Bem vindo " + esp.getName()+"\n");
		menu.setTitulo("Menu Specialist");
		menu.setHandler(1,()->menuAlterarOdd());
		menu.setHandler(2,()->menuAddGame());
		menu.run();
	}

	private void menuAddGame() {
			int sportId = selectSport();

			String id = Utils.geraIdentificadorUnico(Utils.games);
			System.out.print("Insira Equipa da casa: ");
			String hometeam = this.scanner.nextLine();
			System.out.print("Insira Equipa de fora: ");
			String awayteam = this.scanner.nextLine();
			System.out.print("Insira a data no formato yyyy/MM/dd HH:mm: ");
			boolean dateValid = true;
			String insertedDate;
		    String commenceTime = "NaN";
			do{
				insertedDate = this.scanner.nextLine();
		    	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
				try{
					Date date = (Date) sdf.parse(insertedDate);
					sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
					commenceTime = sdf.format(date)+"Z";
					dateValid=true;
				}catch (ParseException e){
					errorMessage("Data no formato inválido");
					dateValid = false;
				}
			}while (!dateValid);
			System.out.print("Insira Odd Equipa da casa: ");
			float hometeamOdd = Utils.InputFloat(this.scanner);
			System.out.print("Insira Odd Equipa de fora: ");
			float awayteamOdd = Utils.InputFloat(this.scanner);
			System.out.print("Insira Odd Empate: ");
			float drawOdd= Utils.InputFloat(this.scanner);

			Result result = new Result(awayteamOdd,hometeamOdd,drawOdd);

			Game game = new Game(id,hometeam,awayteam,commenceTime,false,"0x0",result,sportId);

			rasBetFacade.addGame(game);
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
		List<Bet> bets = rasBetFacade.getBetListGameId(gameId);
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
        String name = "",email = "", password = "",nif = "999999999";
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
		if(tipo_de_utilizador == 0) {
			System.out.println();
			do {
				if (tentativa != 0) {
					erraseLines();
					errorMessage("NIF no formato incorreto (9 digitos)");
				}
				System.out.print("Insira NIF: ");
				nif = this.scanner.nextLine();
				tentativa++;
			} while (nif.length() != 9);
		}
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