package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {
	/**
	 * Identificador de jogo
	 * */
	public String id;
	/**
	 * Equipa que joga em casa
	 * */
	public String homeTeam;
	/**
	 * Equipa que joga fora
	 * */
	public String awayTeam;

	/**
	 * Data agendada do jogo
	 * */
	public String commenceTime;

	/**
	 * Indica se o jogo foi marcado como concluido
	 * */
	public boolean completed;

	/**
	 * O result do jogo Exemplos: 0x0, 0x1, 2x3, 4x4 etc..
	 * */
	public String scores;
	/**
	 * Bookmakers com a informação sobre as odds
	 * */
	public List<Bookmaker> bookmakers;

	/**
	 * Classe que guarda o result
	 * */
	public Result result;

	/**
	 * Nome do desporto associado
	 * */
	public int sportId;

	public Game(String id, String homeTeam, String awayTeam, String commenceTime, boolean completed, String scores,Result result,int sportId) {
		this.id = id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.commenceTime = commenceTime;
		this.completed = completed;
		this.scores = scores;
		this.bookmakers = new ArrayList<>();
		this.result = result;
		this.sportId = sportId;
	}

	public Game(String id, String homeTeam, String awayTeam, String commenceTime, boolean completed,Result result,int sportId) {
		this.id = id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.commenceTime = commenceTime;
		this.completed = completed;
		this.scores = "0x0";
		this.bookmakers = new ArrayList<>();
		this.result = result;
		this.sportId = sportId;
	}

	public Game(String id, String homeTeam, String awayTeam, String commenceTime,int sportId) {
		this.id = id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.commenceTime = commenceTime;
		this.completed = false;
		this.scores = "0x0";
		this.bookmakers = new ArrayList<>();
		this.result = new Result();
		this.sportId = sportId;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public String getCommenceTime() {
		return this.commenceTime;
	}

	public void setCommenceTime(LocalDateTime commenceTime) {
		this.commenceTime = commenceTime.toString();
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

	public Result getResult() {
		return this.result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public int getSportId() {
		return this.sportId;
	}

	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	public void generateResult(){
		this.result = new Result();
	}

	/**
	 * Alterar o estado de conclusão do jogo
	 * */
	public void changeState() {
		if(this.completed) this.completed= false;
		else this.completed = true;
	}

	@Override
	public String toString() {
		return "Game{" +
				"id='" + id + '\'' +
				", homeTeam='" + homeTeam + '\'' +
				", awayTeam='" + awayTeam + '\'' +
				", commenceTime='" + commenceTime + '\'' +
				", completed=" + completed +
				", scores='" + scores + '\'' +
				", bookmakers=" + bookmakers +
				", result=" + result +
				", sportId=" + sportId +
				'}';
	}
}