package com.rasbet.model;
import com.rasbet.data.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RasBetFacade {
	public static  String emailAuthenticatedUser;
	public static  List<Game> games;


	public RasBetFacade(){
		emailAuthenticatedUser = null;
	}


	/**
	 * Dado um email de utilizador devolver o histórico de transações
	 * @param email email do utilizador
	 */
	public static List<Transfer> transHistory(String email) {
		List<Transfer> sortedList =  UserDAO.getTransHistory(email).stream()
				.sorted(Comparator.comparing(Transfer :: getDate).reversed())
				.collect(Collectors.toList());
		return sortedList;
	}

	/**
	 * Mudar a informação de um utilizador
	 * @param nome novo nome
	 * @param password nova password
	 * @param email email do utilizador a alterar
	 * @return true se alterado, false caso erro
	 */
	public static boolean changeInfo(String nome, String password, String email) {
		boolean updatedUser = false;
		User user = UserDAO.get(email);
		if(user != null){
			user.setName(nome);
			user.setPassword(password);
			UserDAO.update(user);
			updatedUser = true;
		}
		return updatedUser;
	}

	/**
	 * Devolve o valor em euros da carteira
	 * @return O valor em euros ou 0
	 */
	public static float getEuros() {
		float euros = 0;
		User user = getAuthenticatedUser();
		if(user instanceof Better){
			euros = ((Better) user).getWallet().getEuros();
		}
		return euros;
	}

	/**
	 * Devolve o valor em dollars da carteira
	 * @return O valor em dollars ou 0
	 */
	public static float getDollars() {
		float dollars = 0;
		User user = getAuthenticatedUser();
		if(user instanceof Better){
			dollars = ((Better) user).getWallet().getDollars();
		}
		return dollars;
	}

	/**
	 * Mudar a informação de um utilizador
	 * @param nome novo nome
	 * @param email email do utilizador a alterar
	 * @return true se alterado, false caso erro
	 */
	public static boolean changeInfo(String nome,String email) {
		boolean updatedUser = false;
		User user = UserDAO.get(email);
		if(user != null){
			user.setName(nome);
			UserDAO.update(user);
			updatedUser = true;
		}
		return updatedUser;
	}

	/**
	 * Adicionar Aposta
	 * @param gameID id do jogo
	 * @param value o valor da aposta
	 * @param bettedTeam 0 - equipa da casa, 1 - equipa de fora e 2 - empate
	 * @param email email do utilizador
	 * @param multipleId id do grupo de apostas(apostas multiplas)
	 * @return true se adicionada, false caso contrário
	 */
	public static boolean addBet(String gameID,String email, float value, int bettedTeam, int multipleId,boolean isSuspended,int betState,String currency,float possibleGain) {
		Bet bet = new Bet(gameID,value,bettedTeam,email,multipleId,isSuspended,betState,currency,possibleGain);
		return BetDAO.store(bet);
	}

	/**
	 * Adicionar Aposta
	 * @param gameID id do jogo
	 * @param value o valor da aposta
	 * @param bettedTeam 0 - equipa da casa, 1 - equipa de fora e 2 - empate
	 * @param email email do utilizador
	 * @param multipleId id do grupo de apostas(apostas multiplas)
	 * @return true se adicionada, false caso contrário
	 */
	public static boolean addBet(String gameID,String email, float value, int bettedTeam, int multipleId,String currency,float possibleGain) {
		boolean validBet = false;
		Bet bet = null;
		switch (currency){
			case "dollars":
				validBet = validateTransferDollars(-value,emailAuthenticatedUser);
				if(validBet) addMovementDollars(-value,emailAuthenticatedUser,"Realizada Aposta: " + RasBetFacade.getGamebyBetId(gameID).getHomeTeam() + " - " + RasBetFacade.getGamebyBetId(gameID).getAwayTeam());
				break;
			default:
				validBet = validateTransferEuros(-value,emailAuthenticatedUser);
				if(validBet) addMovementEuros(-value,emailAuthenticatedUser,"Realizada Aposta: " + RasBetFacade.getGamebyBetId(gameID).getHomeTeam() + " - " + RasBetFacade.getGamebyBetId(gameID).getAwayTeam());
				break;
		}
		if(validBet){
			bet = new Bet(gameID,value,bettedTeam,email,multipleId,false,1,currency,possibleGain);
			BetDAO.store(bet);
		}
		return validBet;
	}

	/**
	 * Autentica um utilizador
	 * @param email email do utilizador
	 * @param pwd password do utilizador
	 * @return true se dados válidos, false caso contrário
	 */
	public static boolean login(String email, String pwd) {
		boolean loginValid = false;
		User user = UserDAO.get(email);
		if(user != null){
			if (user.getPasswordHash() == pwd.hashCode()) {
				loginValid = true;
				emailAuthenticatedUser = user.getMail();
			}
		}
		return loginValid;
	}

	/**
	 * Registar um novo utilizador
	 * @param nome nome do utilizador
	 * @param email email do utilizador
	 * @param pwd password do utilizador
	 * @param nif numero de identificação fiscal
	 * @param type 0 - Model.Better, 1 - Model.Specialist e 2 - Model.Administrador
	 * @return true se adicionado, false caso erro
	 */
	public static boolean registerUser(String nome, String email, String pwd, String nif, int type) {
		boolean added;
		switch (type){
			case 1:
				Specialist esp = new Specialist(nome,email,pwd.hashCode());
				added = UserDAO.store(esp);
				break;
			case 2:
				Administrator adm = new Administrator(nome,email,pwd.hashCode());
				added = UserDAO.store(adm);
				break;
			default:
				Better better = new Better(nome,email,pwd.hashCode(),nif);
				added = UserDAO.store(better);
				break;
		}
		return added;
	}

	/**
	 * Valida dos dados de pagamento
	 * @param valor valor da transação
	 * @param email email do utilizador
	 * @return true se dados validos, false caso contrário
	 */
	public static boolean validatePayment(float valor,String email) {
		// TODO - validação dos pagamentoss
		return true;
	}

	/**
	 * Validar transferencia em euros
	 * @param valor valor da transferencia
	 * @param email email do utilizador
	 * @return true se válida, false caso contrário
	 */
	public static boolean validateTransferEuros(float valor, String email) {
		boolean validTransfer = true;
		User user = UserDAO.get(email);
		if(user instanceof Better){
			//Não permitir tirar mais dinheiro que o existente
			//e.g. valor = -30 e euros = 25 -> 25+(-30) = -5 (inválido)
			if(((Better) user).getWallet().getEuros()+valor < 0) validTransfer = false;
		}
		return validTransfer;
	}

	/**
	 * Validar transferencia em dollars
	 * @param valor valor da transferencia
	 * @param email email do utilizador
	 * @return true se válida, false caso contrário
	 */
	public static boolean validateTransferDollars(float valor, String email) {
		boolean validTransfer = true;
		User user = UserDAO.get(email);
		if(user instanceof Better){
			//Não permitir tirar mais dinheiro que o existente
			//e.g. valor = -30 e euros = 25 -> 25+(-30) = -5 (inválido)
			if(((Better) user).getWallet().getDollars()+valor < 0) validTransfer = false;
		}
		return validTransfer;
	}

	/**
	 * Adicionar movimento a um utilizador
	 * @param value valor movimentado
	 * @param email email do utilizador
	 * @param description descrição do movimento
	 */
	public static boolean addMovementEuros(float value, String email,String description) {
		boolean addedMov = false;
		if(validateTransferEuros(value,email)){
			User user = UserDAO.get(email);
			if (user instanceof Better){
				((Better) user).getWallet().addEuros(value);
				Transfer transfer = new Transfer(value,LocalDateTime.now(),description,email,((Better) user).getWallet().getEuros(),"euros");
				System.out.println("Balance after transfer €: " + transfer.getBalanceAfterTransfer());
				addedMov = TransferDAO.store(transfer);
				UserDAO.update(user);
			}
		}
		return addedMov;
	}



	/**
	 * Adicionar movimento a um utilizador
	 * @param value valor movimentado
	 * @param email email do utilizador
	 * @param description descrição do movimento
	 */
	public static boolean addMovementDollars(float value, String email,String description) {
		boolean addedMov = false;
		if(validateTransferDollars(value,email)){
			User user = UserDAO.get(email);
			if(user instanceof Better){
				((Better) user).getWallet().addDollars(value);
				Transfer transfer = new Transfer(value,LocalDateTime.now(),description,email,((Better) user).getWallet().getDollars(),"dollars");
				System.out.println("Balance after transfer $: " + transfer.getBalanceAfterTransfer());
				addedMov = TransferDAO.store(transfer);
				UserDAO.update(user);
			}
		}
		return addedMov;
	}

	/**
	 * Alterar odd de um determinado jogo
	 * @param odd o valor da odd
	 * @param gameID o id do jogo
	 * @param type o tipo de odd (0 - ganha HomeTeam, 1 - ganha awayTeam e 2 - empate)
	 */
	public static void inserirChange(float odd, String gameID,int type) {
		Game game = GameDAO.get(gameID);
		if(game != null){
			switch (type) {
				case 0:
					game.getResult().setOddHomeTeam(odd);
					break;
				case 1:
					game.getResult().setOddAwayTeam(odd);
					break;
				case 2:
					game.getResult().setOddDraw(odd);
					break;
				default:
					break;

			}
			GameDAO.update(game);
		}

	}

	/**
	 * Devolve o utilizador autenticado no momento
	 * @return o utilizador autenticado
	 * */
	public static User getAuthenticatedUser() {
		return UserDAO.get(emailAuthenticatedUser);
	}

	/**
	 * Devolve o email do utilizador autenticado no momento
	 * @return o email do utilizador autenticado
	 * */
	public static String getEmailAuthenticatedUser() {
		return emailAuthenticatedUser;
	}

	/**
	 * Dado um id de um grupo de apostas devolve todas que possuam esse grupo
	 * @return a lista de aposats que pertencem ao mesmo grupo
	 * */
	public static List<Bet> getBetsByMultipleID(int multipleID){
		return BetDAO.getBetsByMultipleId(multipleID);
	}

	/**
	 * Define o utilizador autenticado
	 * @param emailAuthenticatedUser o email do utilizador autenticado
	 */
	public static void setEmailAuthenticatedUser(String emailAuthenticatedUser) {
		RasBetFacade.emailAuthenticatedUser = emailAuthenticatedUser;
	}

	/**
	 * Faz um request ao servidor "http://ucras.di.uminho.pt/v1/games/"
	 * e devolve o json
	 * @return o json de resposta ou null caso timeout
	 * */
	public static String getServerData() {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL("http://ucras.di.uminho.pt/v1/games/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			return null;
		}
		return content.toString();
	}

	/**
	 * Efetua o parsing do json recebido a partir da API fornecida e devolve uma lista de jogos
	 * @param json A string json
	 * @return A lista de jogos
	 * */
	public static List<Game> parseJson(String json){
		Type listType = new TypeToken<ArrayList<Game>>(){}.getType();
		Gson g = new Gson();
		return g.fromJson(json, listType);
	}

	/**
	 * Diz se existem utilizadores registados na base de dados
	 * */
	public static boolean existemUtilizadores(){
		return UserDAO.countUsers() != 0;
	}

	/**
	 * Diz se existem utilizadores registados na base de dados
	 * */
	public static boolean gameExists(String gameId){
		return (GameDAO.get(gameId) != null);
	}


	/**
	 * Devolve uma lista com o nome de todos os desportos
	 * */
	public static List<String> listSports(){
		List<String> sportList = new ArrayList<>();
		List<Sport> sports = SportDAO.getSportList();
		for (Sport s : sports ) {
			sportList.add(s.getNome());
		}
		return sportList;
	}

	/**
	 * Devolve a lista de desportos
	 * */
	public static List<Sport> getSportList(){
		return SportDAO.getSportList();
	}

	/**
	 * Devolve a lista de apostas de um determinado jogo
	 * */
	public static List<Bet> getBetListGameId(String gameId){
		return BetDAO.getBetsByGameId(gameId);
	}

	/**
	 * Devolve a lista de apostas
	 * */
	public static List<Bet> getBetListEmail(String email){
		return BetDAO.getBetsByEmail(email);
	}

	/**
	 * Devolve a lista de apostas simples
	 * */
	public static List<Bet> getSimpleBetsListByEmail(String email){
		List<Bet> bets = BetDAO.getBetsByEmail(email);
		List<Bet> returnList = new ArrayList<>();
		for (Bet bet: bets) {
			if(bet.getMultipleId() == 0) returnList.add(bet);
		}
		return returnList;
	}

	/**
	 * Devolve a lista de apostas multiplas
	 * */
	public static List<List<Bet>> getMultipleBetsListByEmail(String email) {
		List<Bet> bets = BetDAO.getBetsByEmail(email);
		List<List<Bet>> returnList = new ArrayList<>();
		if(bets.size() == 0) return null;
		int lastMultipleId = bets.get(0).getMultipleId();
		int index = 0;
		returnList.add(new ArrayList<>());
		System.out.println("bets size: " + bets.size());
		System.out.println("return list size: " + returnList.size());
		for (int i = 0; i < bets.size(); i++) {
			System.out.println("i:" + i);
			if(bets.get(i).getMultipleId() == lastMultipleId){
				returnList.get(index).add(bets.get(i));
			} else{
				returnList.add(new ArrayList<>());
				index++;
				returnList.get(index).add(bets.get(i));
			}
			lastMultipleId = bets.get(i).getMultipleId();
		}

		for (List<Bet> l : returnList ) {
			System.out.print("Lista: ");
			for (Bet b : l ) {
				System.out.println(b.toString());
			}
			System.out.println();
		}

		return returnList;
	}

	/**
	 * Devolve a lista de jogos de um desporto
	 * */
	public static List<Game> getGameList(int sportId){
		return SportGameDAO.get_AllGames_by_SportID(sportId);
	}
	/**
	 * Devolve o jogo de uma bet
	 * */

	public static Game getGamebyBetId(String betID){
		return GameDAO.get(betID);
	}
	public static String stringtest(){
		return "TESTE";
	}

	/**
	 * Devolve uma lista com os jogos ativos
	 * */
	public static List<Game> listActivesGames(int sportId){
		List<Game> gameList = new ArrayList<>();
		List<Game> games = SportGameDAO.get_AllGames_by_SportID(sportId);
		for (Game gm : games ) {
			if(!gm.getCompleted()) {
				gameList.add(gm);
			}
		}
		return gameList;
	}

	public static void alteraEstado(int betId) {
		Bet bet = BetDAO.get(betId);
		bet.isSuspended = !bet.isSuspended;
		BetDAO.update(bet);
	}

	public static Game getGame(String gameId) {
		return GameDAO.get(gameId);
	}

	public static void addGame(Game game ){
		GameDAO.store(game);
	}

	/**
	 * Devolve uma lista com as Notificaçoes não lidas
	 * */
	public static List<Notification> listNotReadNotifications(String email){
		List<Notification> returnList = new ArrayList<>();
		List<Notification> notifications = NotificationDAO.get(email);
		for (Notification not : notifications ) {
			if(!not.getIsRead()) returnList.add(not);
		}
		return returnList;
	}

	public static void markNotificationsAsRead(String email){
		List<Notification> notifications = NotificationDAO.get(email);
		for (Notification not : notifications ) {
			if(!not.getIsRead()){
				not.setRead(true);
				NotificationDAO.update(not);
			}
		}
	}

	/**
	 * Devolve uma lista com as Notificaçoes todas
	 * */
	public static List<Notification> listAllNotifications(String email){
		List<Notification> returnList = new ArrayList<>();
		List<Notification> notifications = NotificationDAO.get(email);

		for (Notification not : notifications ) {
			returnList.add(not);
			if(!not.getIsRead()){
				not.setRead(true);
				NotificationDAO.update(not);
			}
		}
		return returnList;
	}

	/**
	 * Notifica um utilizador
	 * @param email o email do utilizador
	 * @param content O conteúdo do utilizador
	 * */
	public static void notificaUser(String email, String content){
		Notification notification = new Notification(email,content,LocalDateTime.now());
		NotificationDAO.store(notification);
	}


	public static Result getResultByResultId(int resultid){
		return ResultDAO.get(resultid);
	}

	public static void updateBet(Bet b){
		BetDAO.update(b);
	}

	public static Result getResult(int resultId){
		return ResultDAO.get(resultId);
	}

	public static List<User> getBetters() {
		return UserDAO.getUsersByType(0);
	}
	public static List<User> getAdmins() {
		return UserDAO.getUsersByType(2);
	}
	public static List<User> getSpecialist() {
		return UserDAO.getUsersByType(1);
	}

	public static void addSport(String desporto) {
		SportDAO.store(new Sport(desporto));
	}

}