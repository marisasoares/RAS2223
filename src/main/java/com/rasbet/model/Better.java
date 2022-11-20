package com.rasbet.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Better extends User {

	/**
	 * Número de identificação fiscal do utilizador
	 * */
	private String nif;
	/**
	 * Wallet que guarda a quantia que o utilizador possui nas diversas moedas
	 * */
	private Wallet wallet;

	public Better(String nome, String mail, int passwordHash, String nif) {
		super(nome, mail, passwordHash);
		this.nif = nif;
		this.wallet = new Wallet(this.mail);
	}

	/**
	 * Devolve o histórico de apostas efetuadas
	 * @return Lista das apostas em formato String
	 * */
	public List<String> getBetHistory() {
		//TODO
		/*List<String> history = new ArrayList<>();
		for (Map.Entry<String, Bet> t: this.bets.entrySet()) {
			history.add(t.getValue().toString());
		}
		return history;*/
		return null;
	}

	/**
	 * Devolve o histórico de transações efetuadas
	 * @return Lista das transações em formato String
	 * */
	public List<String> getTransHistory() {
		//TODO
		/*List<String> history = new ArrayList<>();
		for (Map.Entry<String, Transfer> t: this.transfers.entrySet()) {
			history.add(t.getValue().toString());
		}*/
		return null;
	}

	/**
	 * Adicionar uma transferência ao histórico de transferências
	 * @param value Valor movimentado
	 * @return id da transferencia
	 */
	public String addMovement(float value,String description) {
		//TODO
		/*String id = Utils.geraIdentificadorUnico(this.transHistory);
		Transfer transfer = new Transfer(id,value, LocalDateTime.now(),description);
		this.getWallet().addEuros(value);
		this.transHistory.put(id,transfer);*/
		return "";
	}
	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Wallet getWallet(){
		return this.wallet;
	}
	public void setWallet(Wallet wallet){
		this.wallet = wallet;
	}


	/**
	 * Efetuar uma aposta num jogo
	 * @param game id do jogo
	 * @param value valor da aposta
	 * @param equipaApostada 0 - homeTeam, 1 - awayTeam w 2 - empate
	 * @return id da aposta
	 */
	public String addBet(String game, float value,int equipaApostada) {
		//TODO
		/*String id = Utils.geraIdentificadorUnico(this.bets);
		Bet bet = new Bet(id,game,value,equipaApostada);
		this.bets.put(id,bet);
		return id;*/
		return null;
	}

	/**
	 * Diz se determinada aposta existe
	 * @param id identificador da aposta
	 * @return true se existir, false caso contrário
	 * */
	public boolean betExists(String id){
		//TODO
		/*return this.bets.containsKey(id);*/
		return false;
	}

	@Override
	public String toString() {
		return "Better{" +
				", nif='" + nif + '\'' +
				", wallet=" + wallet +
				'}';
	}
}