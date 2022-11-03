package Model;
import DataLayer.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RasBetFacade {

	public String emailAuthenticatedUser;
	public String idCurrentSelectedGame;


	public RasBetFacade(){
		this.emailAuthenticatedUser = null;
	}

	public String getIdCurrentSelectedGame() {
		return idCurrentSelectedGame;
	}

	public void setIdCurrentSelectedGame(String idCurrentSelectedGame) {
		this.idCurrentSelectedGame = idCurrentSelectedGame;
	}

	/**
	 * Dado um email de utilizador devolver o histórico de transações
	 * @param email email do utilizador
	 */
	public List<Transfer> transHistory(String email) {
		return UserDAO.getTransHistory(email);
	}

	/**
	 * Mudar a informação de um utilizador
	 * @param nome novo nome
	 * @param password nova password
	 * @param email email do utilizador a alterar
	 * @return true se alterado, false caso erro
	 */
	public boolean changeInfo(String nome, String password, String email) {
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
	 * Mudar a informação de um utilizador
	 * @param nome novo nome
	 * @param email email do utilizador a alterar
	 * @return true se alterado, false caso erro
	 */
	public boolean changeInfo(String nome,String email) {
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
	 * @param sportID id do desporto
	 * @param gameID id do jogo
	 * @param value o valor da aposta
	 * @param bettedTeam 0 - equipa da casa, 1 - equipa de fora e 2 - empate
	 * @param email email do utilizador
	 * @param multipleId id do grupo de apostas(apostas multiplas)
	 * @return true se adicionada, false caso contrário
	 */
	public boolean addBet(int sportID,String gameID,String email, float value, int bettedTeam, int multipleId,boolean isSuspended) {
		Bet bet = new Bet(sportID,gameID,value,bettedTeam,email,multipleId,isSuspended);

		return BetDAO.store(bet);
	}

	/**
	 * Autentica um utilizador
	 * @param email email do utilizador
	 * @param pwd password do utilizador
	 * @return true se dados válidos, false caso contrário
	 */
	public boolean login(String email, String pwd) {
		boolean loginValid = false;
		User user = UserDAO.get(email);
		if(user != null){
			if (user.getPasswordHash() == pwd.hashCode()) {
				loginValid = true;
				this.emailAuthenticatedUser = user.getMail();
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
	public boolean registerUser(String nome, String email, String pwd, String nif, int type) {
		boolean added = false;
		switch (type){
			case 1:
				Specialist esp = new Specialist(nome,email,pwd.hashCode());
				added = UserDAO.store(esp);
				break;
			case 2:
				Administrator adm = new Administrator(nome,email,pwd.hashCode());
				System.out.println("[RasBetFacade:119] Adicionado admin: " + adm.toString());
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
	public boolean validatePayment(float valor,String email) {
		// TODO - validação dos pagamentoss
		return true;
	}

	/**
	 * Validar transferencia em euros
	 * @param valor valor da transferencia
	 * @param email email do utilizador
	 * @return true se válida, false caso contrário
	 */
	public boolean validateTransferEuros(float valor, String email) {
		boolean validTransfer = true;
		User user = UserDAO.get(email);
		if(user != null && user instanceof Better){
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
	public boolean validateTransferDollars(float valor, String email) {
		boolean validTransfer = true;
		User user = UserDAO.get(email);
		if(user != null && user instanceof Better){
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
	public boolean addMovementEuros(float value, String email,String description) {
		boolean addedMov = false;
		if(validateTransferEuros(value,email)){
			Transfer transfer = new Transfer(value,LocalDateTime.now(),description,email);
			User user = UserDAO.get(email);
			if (user != null && user instanceof Better){
				((Better) user).getWallet().addEuros(value);
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
	public boolean addMovementDollars(float value, String email,String description) {
		boolean addedMov = false;
		if(validateTransferDollars(value,email)){
			Transfer transfer = new Transfer(value,LocalDateTime.now(),description,email);
			User user = UserDAO.get(email);
			if(user != null && user instanceof Better){
				((Better) user).getWallet().addDollars(value);
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
	public void inserirChange(float odd, String gameID,int type) {
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
	public User getAuthenticatedUser() {
		return UserDAO.get(this.emailAuthenticatedUser);
	}

	/**
	 * Define o utilizador autenticado
	 * @param emailAuthenticatedUser o email do utilizador autenticado
	 */
	public void setEmailAuthenticatedUser(String emailAuthenticatedUser) {
		this.emailAuthenticatedUser = emailAuthenticatedUser;
	}

	/**
	 * Faz um request ao servidor "http://ucras.di.uminho.pt/v1/games/"
	 * e devolve o json
	 * @return o json de resposta ou null caso timeout
	 * */
	public static String getServerData() {
		StringBuffer content = new StringBuffer();
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
		return (List<Game>) g.fromJson(json, listType);
	}

	/**
	 * Diz se existem utilizadores registados na base de dados
	 * */
	public static boolean existemUtilizadores(){
		return UserDAO.countUsers() == 0? false : true;
	}

	/**
	 * Devolve uma lista com o nome de todos os desportos
	 * */
	public List<String> listSports(){
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
	public List<Sport> getSportList(){
		return SportDAO.getSportList();
	}

	/**
	 * Devolve a lista de jogos de um desporto
	 * */
	public List<Game> getGameList(int sportId){
		return SportGameDAO.get_AllGames_by_SportID(sportId);
	}

	/**
	 * Devolve uma lista com os jogos ativos
	 * */
	public List<Game> listActivesGames(int sportId){
		List<Game> gameList = new ArrayList<>();
		List<Game> games = SportGameDAO.get_AllGames_by_SportID(sportId);
		for (Game gm : games ) {
			if(!gm.getCompleted()) {
				gameList.add(gm);
			}
		}
		return gameList;
	}

}