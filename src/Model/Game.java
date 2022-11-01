package Model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Game {
	/**
	 * Identificador de jogo
	 * */
	public int id;
	/**
	 * Equipa que joga em casa
	 * */
	private String homeTeam;
	/**
	 * Equipa que joga fora
	 * */
	private String awayTeam;
	/**
	 * Data agendada do jogo
	 * */
	private LocalDateTime commenceTime;
	/**
	 * Indica se o jogo foi marcado como concluido
	 * */
	private boolean completed;
	/**
	 * O resultado do jogo Exemplos: 0x0, 0x1, 2x3, 4x4 etc..
	 * */
	private String scores;
	/**
	 * Classe que guarda o resultado
	 * */
	private Result resultado;
	/**
	 * Bookmakers com a informação sobre as odds
	 * */
	private Map<String,Bookmaker> bookmakers;

	public Game(int id, String homeTeam, String awayTeam, LocalDateTime commenceTime, boolean completed, String scores) {
		this.id = id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.commenceTime = commenceTime;
		this.completed = completed;
		this.scores = scores;
		this.resultado = new Result();
		this.bookmakers = new HashMap<>();
	}

	public Game(int id, String homeTeam, String awayTeam, LocalDateTime commenceTime) {
		this.id = id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.commenceTime = commenceTime;
		this.completed = false;
		this.scores = "0x0";
		this.resultado = new Result();
		this.bookmakers = new HashMap<>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHomeTeam() {
		return this.homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return this.awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public LocalDateTime getCommenceTime() {
		return this.commenceTime;
	}

	public void setCommenceTime(LocalDateTime commenceTime) {
		this.commenceTime = commenceTime;
	}

	public boolean getCompleted() {
		return this.completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getScores() {
		return this.scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public Result getResultado() {
		return this.resultado;
	}

	public void setResultado(Result resultado) {
		this.resultado = resultado;
	}

	/**
	 * Alterar o estado de conclusão do jogo
	 * */
	public void changeState() {
		if(this.completed) this.completed= false;
		else this.completed = true;
	}

	/**
	 * Adicionar uma odd ao resultado
	 * @param odd o valor da odd
	 * @param type 0 - caso homeTeam ganhe, 1 - caso awayTeam ganhe, 2 - empate
	 */
	public void add(float odd,int type) {
		switch (type){
			case 0:
				this.resultado.setOddHomeTeam(odd);
				break;
			case 1:
				this.resultado.setOddAwayTeam(odd);
				break;
			case 2:
				this.resultado.setOddDraw(odd);
				break;
			default:
				break;
		}
	}

}