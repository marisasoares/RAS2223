package Model;

import DataLayer.GamesMap;
import DataLayer.ResultDAO;
import DataLayer.UserDAO;
import DataLayer.UsersMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class RasBetFacade {

	public User authenticatedUser;
	public GamesMap gamesDataBase;
	public UsersMap usersDataBase;

	public RasBetFacade(){
		this.authenticatedUser = null;
		this.gamesDataBase = new GamesMap();
		this.usersDataBase = new UsersMap();
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
		if(usersDataBase.userExists(email)){
			User user = usersDataBase.getUser(email);
			user.setName(nome);
			user.setPassword(password);
			updatedUser = true;
		}
		return updatedUser;
	}

	/**
	 * Adicionar Aposta
	 * @param sportID id do desporto
	 * @param gameID id do jogo
	 * @param value o valor da aposta
	 * @param equipaApostada 0 - equipa da casa, 1 - equipa de fora e 2 empate
	 * @return true se adicionada, false caso contrário
	 */
	public boolean addBet(String sportID,String gameID,String email, float value, int equipaApostada) {
		// TODO
		boolean betAdded = false;
		/*if(gamesDataBase.sportExists(sportID)){
			Sport sport = gamesDataBase.getSport(sportID);
			if(sport.gameExists(gameID)){
				Game game = sport.getGame(gameID);
				if(usersDataBase.userExists(email)) {
					User user = usersDataBase.getUser(email);
					if(user instanceof Better){
						((Better) user).addBet(gameID,value,equipaApostada);
						betAdded = true;
					}
				}
			}
		}*/
		return betAdded;
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
				this.authenticatedUser = user;
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
		// TODO - validação dos pagamentos
		return true;
	}

	/**
	 * Validar transferencia
	 * @param valor valor da transferencia
	 * @param email email do utilizador
	 * @return true se válida, false caso contrário
	 */
	public boolean validateTransfer(float valor, String email) {
		boolean validTransfer = true;
		if(usersDataBase.userExists(email)) {
			User user = usersDataBase.getUser(email);
			if(user instanceof Better){
				//Não permitir tirar mais dinheiro que o existente
				//e.g. valor = -30 e euros = 25 -> 25+(-30) = -5 (inválido)
				if(((Better) user).getWallet().getEuros()+valor < 0) validTransfer = false;
			}


		}
		return validTransfer;
	}

	/**
	 * Adicionar movimento a um utilizador
	 * @param valor valor movimentado
	 * @param email email do utilizador
	 * @param description descrição do movimento
	 */
	public boolean addMovement(float valor, String email,String description) {
		boolean addedMovement = false;
		if(usersDataBase.userExists(email)) {
			User user = usersDataBase.getUser(email);
			if(user instanceof Better){
				((Better) user).addMovement(valor,description);
				addedMovement = true;
			}
		}
		return addedMovement;
	}

	/**
	 * Alterar odd de um determinado jogo
	 * @param odd o valor da odd
	 * @param gameID o id do jogo
	 * @param sportID o id do desporto
	 * @param type o tipo de odd (0 - ganha HomeTeam, 1 - ganha awayTeam e 2 - empate)
	 */
	public void inserirChange(float odd, String gameID,String sportID,int type) {
		//TODO
		/*if(gamesDataBase.sportExists(sportID)) {
			Sport sport = gamesDataBase.getSport(sportID);
			if (sport.gameExists(gameID)) {
				Game game = sport.getGame(gameID);
				switch (type) {
					case 0:
						game.getResultado().setOddHomeTeam(odd);
						break;
					case 1:
						game.getResultado().setOddAwayTeam(odd);
						break;
					case 2:
						game.getResultado().setOddDraw(odd);
						break;
					default:
						break;

			}
		}*/
	}

	/**
	 * Devolve o utilizador autenticado no momento
	 * @return o utilizador autenticado
	 * */
	public User getauthenticatedUser() {
		return this.authenticatedUser;
	}

	/**
	 * Define o utilizador autenticado
	 * @param authenticatedUser o utilizador autenticado
	 */
	public void setauthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
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
}