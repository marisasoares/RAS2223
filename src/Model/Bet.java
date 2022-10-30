package Model;

public class Bet {
	/**
	 * Identificador da aposta
	 * */
	public String id;
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
	 * 2 - empate
	 * */
	public int equipaApostada;

	public Bet(String id, String gameId, float value, int equipaVencedora) {
		this.id = id;
		this.gameId = gameId;
		this.value = value;
		this.equipaApostada = equipaVencedora;
	}

	public Bet(String gameId, float value, int equipaVencedora) {
		this.id = null;
		this.gameId = gameId;
		this.value = value;
		this.equipaApostada = equipaVencedora;
	}

	public String getBetId(){
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

	public int getEquipaApostada() {
		return this.equipaApostada;
	}

	public void setEquipaApostada(int equipaApostada) {
		this.equipaApostada = equipaApostada;
	}
}