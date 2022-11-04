package UI;

import Model.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RasBetUI {

	/**
	 * Scanner para leitura
	 * */
	private transient Scanner scanner;
	private final RasBetFacade rasBetFacade;

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
		this.menuPrincipal();
		System.exit(0);
	}

	private void menuPrincipal() {
		clearScreen();
		System.out.println("v 3.5");
		Menu menu = new Menu(new String[]{
				"Login",
				"Registar",

		});

		//Registar pré-condições das transições
		menu.setPreCondition(1, RasBetFacade::existemUtilizadores);
		//Registar os handlers das transições
		menu.setHandler(1, this::login);
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
			User user = rasBetFacade.getAuthenticatedUser();
			if(user instanceof Specialist) menuSpecialist();
			else if(user instanceof Administrator) menuAdministrator();
			else menuBetter();

		} else {
			errorMessage("Login Inválido");
			pressEnterToContinue();
		}
	}

	private void menuBetter(){
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
		menu.setHandler(1, this::menuAposta);
		menu.setHandler(2, this::menuHistoricoApostas);
		menu.setHandler(3, this::menuHistoricoTransferencias);
		menu.setHandler(4, this::consultarSaldo);
		menu.setHandler(5, this::menuDepositar);
		menu.setHandler(6, this::menuLevantar);
		menu.setHandler(7, this::menuUnreadNotifications);
		menu.setHandler(8, this::menuNotificationHistory);
		menu.setHandler(9, this::menuStats);
		menu.setHandler(10, this::alterarNome);
		menu.setHandler(11, this::alterarPassword);
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
							}else totalEuros -= b.getValue();
						}else {
							if(winningteam.equalsIgnoreCase(game.getHomeTeam())) {
								totalDollars += result.getOddHomeTeam() * b.getValue();
							}else totalDollars -= b.getValue();
						}
						break;
					case 1:
						if(b.getCurrency().equals("euros")) {
							if(winningteam.equalsIgnoreCase(game.getAwayTeam())) {
								totalEuros += result.getOddAwayTeam() * b.getValue();
							}else totalEuros -= b.getValue();
						}else{
							if(winningteam.equalsIgnoreCase(game.getAwayTeam())) {
								totalDollars += result.getOddAwayTeam() * b.getValue();
							}else totalDollars -= b.getValue();
						}
						break;
					default:
						if(b.getCurrency().equals("euros")) {
							if(winningteam.equalsIgnoreCase("Draw")) {
								totalEuros += result.getOddDraw() * b.getValue();
							}else totalEuros -= b.getValue();
						}else {
							if (winningteam.equalsIgnoreCase("Draw")) {

								totalDollars += result.getOddDraw() * b.getValue();
							}else totalDollars -= b.getValue();
							break;
						}
				}

			}

		}
		System.out.println(totalEuros +" €");
		System.out.println(totalDollars +" $");
		pressEnterToContinue();

	}

	private void menuUnreadNotifications(){
		clearScreen();
		header("Notificações não lidas");
		List<Notification> notifications = rasBetFacade.listNotReadNotifications(rasBetFacade.getAuthenticatedUser().getMail());
		printNotificationsList(notifications);
		if(notifications.isEmpty()) errorMessage("Sem notificações por ler");
		pressEnterToContinue();
	}

	private void menuNotificationHistory() {
		clearScreen();
		header("Histórico de notificações");
		List<Notification> notifications = rasBetFacade.listAllNotifications(rasBetFacade.getAuthenticatedUser().getMail());
		printNotificationsList(notifications);
		if(notifications.isEmpty()) errorMessage("Sem notificações");
		pressEnterToContinue();
	}

	private void printNotificationsList(List<Notification> notifications) {
		for (Notification n : notifications ) {
			String date = n.getDate().getDayOfMonth()+"/"+n.getDate().getMonthValue()+"/"+n.getDate().getYear()+" " + n.getDate().getHour()+":" + String.format("%02d",n.getDate().getMinute());
			System.out.println("•" + date + " " + n.getContent());
		}
	}

	private void menuLevantar() {
		String email = rasBetFacade.getAuthenticatedUser().getMail();

		ListMenu menu = new ListMenu();
		menu.adicionaOpcao("Levantar Dollars" );
		menu.adicionaOpcao("Levantar Euros ");
		menu.show(true);
		int escolha = Utils.InputInteger(this.scanner,0,2);
		if (escolha == 0) return;
		System.out.print("Valor a Levantar: ");
		float value = Utils.InputFloat(this.scanner);
		if(escolha == 1 ){
			if(rasBetFacade.addMovementDollars(-value,email,"Levantamento")) {
				float dollars = ((Better) rasBetFacade.getAuthenticatedUser()).getWallet().getDollars();
				System.out.println("Levantado " + value + " $");
				System.out.println("Saldo Atual " + dollars + " $");
			} else errorMessage("Saldo insuficiente");
		}
		else {
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
		System.out.print("Valor a depositar: ");
		float value = Utils.InputFloat(this.scanner);
		if(escolha == 1 ){
			if(rasBetFacade.addMovementDollars(value,email,"Deposito")) {
				float dollars = ((Better) rasBetFacade.getAuthenticatedUser()).getWallet().getDollars();
				System.out.println("Depositado " + value + " $");
				System.out.println("Saldo Atual " + dollars + " $");
			}
		}
		else {
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
		int multipleIDanterior = -1;
		for (Bet b:bets) {
			Game game = rasBetFacade.getGame(b.getGameId());
			String bettedTeam;
			String estado;
			if (b.getBettedTeam() == 0) bettedTeam = game.getHomeTeam();
			else if (b.getBettedTeam() == 1) bettedTeam = game.getAwayTeam();
			else bettedTeam = "Empate";
			if (b.getBetState() != 0) estado = "Ativa";
			else estado = "Conluída";
			if(multipleIDanterior != b.getMultipleId()) System.out.print("• ");
			else System.out.print("    - ");
			System.out.println("Aposta na equipa: " + bettedTeam + " valor:" +  b.getValue() + ( b.getCurrency().equals("euros")?" €":" $" ) +  " ID da aposta: " + b.getBetId() + ", " + game.getHomeTeam() + " x " + game.getAwayTeam() + ", Estado : " + estado);
			multipleIDanterior = b.getMultipleId();
		}
		pressEnterToContinue();
	}

	/**
	 * Mostra o menu de aposta
	 * */
	private void menuAposta(){
		int multipleId = -1, sportId,escolha;
		String gameId;
		if((sportId = selectSport()) == -1) return;
		if((gameId = selectGame(sportId)) == null) return;
		if((multipleId = realizaAposta(gameId,multipleId,false))== -1) return;
		do {
			if(!yesOrNoInput("Pretende Adicionar mais apostas nesta aposta")) {
				if((escolha = selectCurrency()) == 0) return;
				System.out.print("Insira o valor a apostar: ");
				float valor = Utils.InputFloat(this.scanner);
				List<Bet> bets = rasBetFacade.getBetsByMultipleID(multipleId);
				float oddTotal = 1;
				for (Bet b: bets) {
					b.setValue(valor);
					b.setCurrency(escolha == 1 ? "dollars" : "euros");
					rasBetFacade.updateBet(b);
					float odd;
					Result result = rasBetFacade.getResultByResultId(rasBetFacade.getGame(b.getGameId()).getResult().getResultID());
					switch (b.getBettedTeam()) {
						case 0 -> {
							odd = result.getOddHomeTeam();
							System.out.println("• Aposta com odd: " + odd + " na equipa: " + rasBetFacade.getGame(b.getGameId()).getHomeTeam());
						}
						case 1 -> {
							odd = result.getOddAwayTeam();
							System.out.println("• Aposta com odd: " + odd + " na equipa: " + rasBetFacade.getGame(b.getGameId()).getAwayTeam());
						}
						default -> {
							odd = result.getOddDraw();
							System.out.println("• Aposta com odd: " + odd + " em Empate: " +rasBetFacade.getGame(b.getGameId()).getHomeTeam()+" x " +rasBetFacade.getGame(b.getGameId()).getAwayTeam());
						}
					}
					oddTotal*=odd;
				}
				System.out.println("Valor em aposta: " + valor + " ODD total combinada: " + oddTotal);
				System.out.println("Ganhos possíveis: " + valor*oddTotal + ((escolha == 1) ? " $" : " €"));
				for (Bet b: bets) {
					b.setPossibleGain(valor*oddTotal);
					rasBetFacade.updateBet(b);
				}
				if(!yesOrNoInput("Confirma os dados acima")) {
					errorMessage("Aposta cancelada");
					pressEnterToContinue();
					return;
				} else {
					if(escolha == 1) {
						if(	rasBetFacade.addMovementDollars(-valor,rasBetFacade.getEmailAuthenticatedUser(),"Aposta"))
							System.out.println("Aposta Realizada");
						else errorMessage("Não tem dinheiro suficiente");
						pressEnterToContinue();
					} else {
						if (rasBetFacade.addMovementEuros(-valor,rasBetFacade.getEmailAuthenticatedUser(),"Aposta"))
							System.out.println("Aposta Realizada");
						else errorMessage("Não tem dinheiro suficiente");
						pressEnterToContinue();
					}
				}
				break;
			}
			else{
				if((sportId = selectSport()) == -1) break;
				if((gameId = selectGame(sportId)) == null) break;
				multipleId = realizaAposta(gameId,multipleId,true);
			}
		}while (true);
	}

	/**
	 * Realiza uma aposta
	 *
	 * */
	public int realizaAposta(String gameId,int multipleId,boolean sameAposta){
		Game game = rasBetFacade.getGame(gameId);
		int resultid = game.getResult().getResultID();
		Result result = rasBetFacade.getResultByResultId(resultid);
		int bettedteam;
		float odd;
		if((bettedteam = selectResult(game)) == -1) return -1;
		System.out.println("Jogo: " +  game.getHomeTeam() + " x " + game.getAwayTeam() + " em " + game.commenceTime);
		switch (bettedteam) {
			case 0 -> {
				odd = result.getOddHomeTeam();
				System.out.println("Equipa apostada: " + game.getHomeTeam() + " [" + odd + "]");
			}
			case 1 -> {
				odd = result.getOddAwayTeam();
				System.out.println("Equipa apostada: " + game.getAwayTeam() + " [" + odd + "]");
			}
			default -> {
				odd = result.getOddDraw();
				System.out.println("Aposta em empate [" + odd + "]");
			}
		}
		if(!sameAposta) {
			multipleId = Utils.geraIdentificadorUnicoInteger(Utils.multipleIds);
			Utils.multipleIds.put(multipleId, multipleId);
		}
		rasBetFacade.addBet(gameId,rasBetFacade.getEmailAuthenticatedUser(),0,bettedteam,multipleId,false,1,"euros",0);

		return multipleId;
	}

	/**
	 * Mostra o menu de selecionar uma moeda
	 * Devolve 0 caso tenha escolhido sair, 1 - dollars e 2 - euros
	 * */
	public int selectCurrency(){
		ListMenu menu = new ListMenu();
		menu.adicionaOpcao("Dollars "+" Dinheiro Disponível: " + ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getDollars());
		menu.adicionaOpcao("Euros " +" Dinheiro Disponível: "+ ((Better)rasBetFacade.getAuthenticatedUser()).getWallet().getEuros());
		menu.show(true);
		return Utils.InputInteger(this.scanner,0,2);
	}

	private boolean yesOrNoInput(String message){
		System.out.print(message + " [Y/N]: ");
		String input;
		do {
			input = this.scanner.nextLine();
		} while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

		return input.equals("y");
	}

	public int selectSport(){
		clearScreen();
		int selected;
		header("Lista de desportos");
		ListMenu menuSports = new ListMenu();
		menuSports.setTitulo("Desportos disponíveis");
		List<Sport> sportList = rasBetFacade.getSportList();
		for (Sport sport : sportList) {
			menuSports.adicionaOpcao(sport.getNome());
		}
		menuSports.show(true);
		selected = Utils.InputInteger(this.scanner,0,sportList.size());
		if(selected == 0) return -1;
		return sportList.get(selected-1).getId();
	}

	/**
	 * Mostra uma menu de seleção de jogo de um determinado desporto
	 * @param sportId ID do desporto
	 * @return null se selecionado voltar atrás ou o id do jogo selecionado
	 *
	 * */
	public String selectGame(int sportId){
		int selected;
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
		if(selected == 0) return null;
		return games.get(selected-1).getId();
	}

	/**
	 * Mostra o menu de seleção de resultados
	 * @return A opção escolhida ou -1 se escolhido voltar
	 * */
	public int selectResult(Game game){
		clearScreen();
		header("Selecionar um resultado");
		ListMenu menuAposta = new ListMenu();
		menuAposta.adicionaOpcao( game.getHomeTeam()  + " - " +  game.getResult().getOddHomeTeam());
		menuAposta.adicionaOpcao( game.getAwayTeam()  + " - " +  game.getResult().getOddAwayTeam());
		menuAposta.adicionaOpcao( "Empate - " + game.getResult().getOddDraw());
		menuAposta.show(true);
		int selected = Utils.InputInteger(this.scanner,0,3);
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

	private void menuSpecialist(){
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Alterar odds",
				"Adicionar Jogo",
				"Adicionar Desporto"
		},"Bem vindo " + rasBetFacade.getAuthenticatedUser().getName()+"\n");
		menu.setTitulo("Menu Specialist");
		menu.setHandler(1, this::menuAlterarOdd);
		menu.setHandler(2, this::menuAddGame);
		menu.setHandler(3, this::menuAddSport);
		menu.run();
	}

	private void menuAddSport() {
		System.out.println("Insira nome do Desporto");
		String desporto;
		do {
			desporto = this.scanner.nextLine();
		}while (desporto.trim().length() == 0);
		rasBetFacade.addSport(desporto);
		System.out.println("Adicionado " + desporto);
		pressEnterToContinue();
	}

	private void menuAddGame() {
			int sportId = selectSport();
			String id = Utils.geraIdentificadorUnico(Utils.games);
			System.out.print("Insira Equipa da casa: ");
			String hometeam = this.scanner.nextLine();
			System.out.print("Insira Equipa de fora: ");
			String awayteam = this.scanner.nextLine();
			System.out.print("Insira a data no formato yyyy/MM/dd HH:mm: ");
			boolean dateValid;
			String insertedDate;
		    String commenceTime = "NaN";
			do{
				insertedDate = this.scanner.nextLine();
		    	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
				try{
					Date date = sdf.parse(insertedDate);
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

	private void menuAlterarOdd(){
		int sportId = selectSport();
		String gameId = selectGame(sportId);
		boolean sair = gameId == null;
		while(!sair){
			sair = menuOdds(gameId);
		}
	}

	private boolean menuOdds(String gameId){
		boolean sair = false;
		Game game = rasBetFacade.getGame(gameId);
		Result result = rasBetFacade.getResult(game.getResult().getResultID());
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

	private void menuAdministrator(){
		clearScreen();
		Menu menu = new Menu(new String[]{
				"Adicionar Administrator",
				"Adicionar Especialista",
				"Alterar estado aposta",
				"Alterar odds",
				"Adicionar Jogo",
				"Listar Desportos",
				"Listar Apostadores",
				"Listar Especialistas",
				"Listar Administratores"
		},"Bem vindo " + rasBetFacade.getAuthenticatedUser().getName()+"\n");
		menu.setTitulo("Menu Administrator");
		menu.setHandler(1,()-> register(2));
		menu.setHandler(2,()-> register(1));
		menu.setHandler(3, this::menuAlterarEstadoAposta);
		menu.setHandler(4, this::menuAlterarOdd);
		menu.setHandler(5, this::menuAddGame);
		menu.setHandler(6, this::menuListarDesportos);
		menu.setHandler(7, this::menuListarApostadores);
		menu.setHandler(8, this::menuListarEspecialistas);
		menu.setHandler(9, this::menuListarAdministradores);
		menu.run();
	}

	private void menuListarDesportos() {
	}

	private void menuListarApostadores() {
		clearScreen();
		header("Lista de Apostadores");
		List<User> betters = rasBetFacade.getBetters();
		if(betters.isEmpty()) errorMessage("Lista Vazia");
		for (User b : betters) {
			System.out.println(b.toString());
		}
		pressEnterToContinue();
	}

	private void menuListarAdministradores() {
		clearScreen();
		header("Lista de Administradores");
		List<User> admins = rasBetFacade.getAdmins();
		if(admins.isEmpty()) errorMessage("Lista Vazia");
		for (User a : admins) {
			System.out.println(a.toString());
		}
		pressEnterToContinue();
	}

	private void menuListarEspecialistas() {
		clearScreen();
		header("Lista de Especialistas");
		List<User> especialistas = rasBetFacade.getSpecialist();
		if(especialistas.isEmpty()) errorMessage("Lista Vazia");
		for (User e : especialistas) {
			System.out.println(e.toString());
		}
		pressEnterToContinue();
	}

	private void menuAlterarEstadoAposta() {
		int sportId = selectSport();
		String gameID = selectGame(sportId);
		boolean sair = gameID == null;
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
			String winningTeam;
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
		if(escolha == 1 && betselected != null) {
			rasBetFacade.alteraEstado(betselected.getBetId());
		}
		return  sair;
	}

	/*
	 * UI para registo de um utilizador
	 * @param tipo_de_utilizador 0 - Better, 1 - Specialist, 2 - Administrator
	 */
    private void register(int tipo_de_utilizador){
        clearScreen();
        header("REGISTO");
        String name, email, password;
		String nif = "999999999";
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
		System.out.printf("\033[%dA",1);
		System.out.print("\033[2K");
		System.out.printf("\033[%dA",1);
		System.out.print("\033[2K");
	}
}