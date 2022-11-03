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

	public Bet(int id, String gameId, float value, int bettedTeam,String email, boolean isSuspended) {
		this.id = id;
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
		this.email = email;
		this.multipleId = 0;
		this.isSuspended = isSuspended;
	}

	public Bet(int id, String gameId, float value, int bettedTeam,String email,int multipleId, boolean isSuspended) {
		this.id = id;
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
		this.email = email;
		this.multipleId = multipleId;
		this.isSuspended = isSuspended;
	}

	public Bet(String gameId, float value, int bettedTeam,String email, boolean isSuspended) {
		this.id = 0;
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
		this.email = email;
		this.multipleId = 0;
		this.isSuspended = false;
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

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
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