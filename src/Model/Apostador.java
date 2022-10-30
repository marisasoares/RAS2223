package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Apostador extends User {

	/**
	 * Mapa de apostas do utilizador (APOSTAS ATIVAS????)
	 * */
	private Map<String,Bet> bets;
	/**
	 * Histórico de todas as apostas realizadas por um utilizador
	 * */
	private Map<String,Bet> betHistory;
	/**
	 * Histórico de todas as transferências efetuadas pelo utilizador
	 * */
	private Map<String,Transfer> transHistory;

	public Apostador(String nome, String mail, int passwordHash, String nif) {
		super(nome, mail, passwordHash, nif);
	}

	/**
	 * Devolve o histórico de apostas efetuadas
	 * @return Lista das apostas em formato String
	 * */
	public List<String> getBetHistory() {
		List<String> history = new ArrayList<>();
		for (Map.Entry<String, Bet> t: this.betHistory.entrySet()) {
			history.add(t.getValue().toString());
		}
		return history;
	}

	/**
	 * Devolve o histórico de transações efetuadas
	 * @return Lista das transações em formato String
	 * */
	public List<String> getTransHistory() {
		List<String> history = new ArrayList<>();
		for (Map.Entry<String, Transfer> t: this.transHistory.entrySet()) {
			history.add(t.getValue().toString());
		}
		return history;
	}

	/**
	 * Adicionar uma transferência ao histórico de transferências
	 * @param value Valor movimentado
	 * @return id da transferencia
	 */
	public String addMovement(float value,String description) {
		String id = Utils.geraIdentificadorUnico(this.transHistory);
		Transfer transfer = new Transfer(id,value, LocalDateTime.now(),description);
		this.getCarteira().addEuros(value);
		this.transHistory.put(id,transfer);
		return id;
	}

	/**
	 * Efetuar uma aposta num jogo
	 * @param game id do jogo
	 * @param value valor da aposta
	 * @param equipaApostada 0 - homeTeam, 1 - awayTeam w 2 - empate
	 * @return id da aposta
	 */
	public String addBet(String game, float value,int equipaApostada) {
		String id = Utils.geraIdentificadorUnico(this.bets);
		Bet bet = new Bet(id,game,value,equipaApostada);
		this.bets.put(id,bet);
		return id;
	}

	/**
	 * Diz se determinada aposta existe
	 * @param id identificador da aposta
	 * @return true se existir, false caso contrário
	 * */
	public boolean betExists(String id){
		return this.bets.containsKey(id);
	}

}