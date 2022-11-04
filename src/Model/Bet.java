package Model;

public class Bet {
	/**
	 * Identificador da aposta
	 * */
	public int id;

	/**
	 * Identificador do grupo de apostas
	 * Apostas com o mesmo grupo de aposta constituem uma aposta múltipla
	 * */
	public int multipleId;

	/**
	 * Identificador do jogo correspondente
	 * */
	public String gameId;
	/**
	 * Valor da aposta
	 * */
	public float value;
	/**
	 * Equipa vencedora
	 * 0 - homeTeam
	 * 1 - awayTeam
	 * 2 - draw
	 * */
	public int bettedTeam;

	/**
	 * Email do utilizador
	 * */
	public String email;

	/**
	* Informa se a aposta está ativa ou suspensa
	 **/
	public boolean isSuspended;

	/**
	 * Estado da aposta
	 * 0 - Lost
	 * 1 - Won
	 * 2 - Ativa
	 * */
	public int betState;

	/**
	 * Define a moeda a ser usada e.g "euro" ou "dollar"
	 * */
	public String currency;

	public Bet(int id, String gameId, float value, int bettedTeam,String email, boolean isSuspended,int betState,String currency) {
		this.id = id;
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
		this.email = email;
		this.multipleId = 0;
		this.isSuspended = isSuspended;
		this.betState = betState;
		this.currency = currency;
	}

	public Bet(int id, String gameId, float value, int bettedTeam,String email,int multipleId, boolean isSuspended,int betState,String currency) {
		this.id = id;
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
		this.email = email;
		this.multipleId = multipleId;
		this.isSuspended = isSuspended;
		this.betState = betState;
		this.currency = currency;
	}

	public Bet(String gameId, float value, int bettedTeam,String email,int multipleId, boolean isSuspended,int betState,String currency) {
		this.id = Utils.geraIdentificadorUnicoInteger(Utils.bets);
		Utils.bets.put(id,id);
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
		this.email = email;
		this.multipleId = multipleId;
		this.isSuspended = false;
		this.betState = betState;
		this.currency = currency;
	}

	public int getBetId(){
		return this.id;
	}

	public String getGameId() {
		return this.gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public int getBettedTeam() {
		return this.bettedTeam;
	}

	public void setBettedTeam(int bettedTeam) {
		this.bettedTeam = bettedTeam;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMultipleId() {
		return multipleId;
	}

	public void setMultipleId(int multipleId) {
		this.multipleId = multipleId;
	}

	public boolean getisSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public int getBetState() {
		return betState;
	}

	public void setBetState(int betState) {
		this.betState = betState;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Bet{" +
				"id=" + id +
				", multipleId=" + multipleId +
				", gameId='" + gameId + '\'' +
				", value=" + value +
				", bettedTeam=" + bettedTeam +
				", email='" + email + '\'' +
				", isSuspended=" + isSuspended +
				'}';
	}
}