package Model;

public class Bet {
	/**
	 * Identificador da aposta
	 * */
	public int id;
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

	public Bet(int id, String gameId, float value, int bettedTeam,String email) {
		this.id = id;
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
	}

	public Bet(String gameId, float value, int bettedTeam,String email) {
		this.id = 0;
		this.gameId = gameId;
		this.value = value;
		this.bettedTeam = bettedTeam;
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

	@Override
	public String toString() {
		return "Bet{" +
				"id=" + id +
				", gameId='" + gameId + '\'' +
				", value=" + value +
				", bettedTeam=" + bettedTeam +
				", email='" + email + '\'' +
				'}';
	}
}