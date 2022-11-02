package Model;

import jdk.jshell.execution.Util;

public class Result {
	/**
	 * Identificador do resultado
	 * */
	private int resultID;
	/**
	 * Odd equipa de fora
	 * */
	private float oddAwayTeam;
	/**
	 * Odd equipa da casa
	 * */
	private float oddHomeTeam;
	/**
	 * Odd empate
	 * */
	private float oddDraw;

	/**
	 * Nome da equipa vencedora
	 * */
	private String winningTeam;

	/**
	 * O resultado do jogo Exemplos: 0x0, 0x1, 2x3, 4x4 etc..
	 * */
	private  String scores;

	public Result(int resultID, float oddAwayTeam, float oddHomeTeam, float oddDraw, String winningTeam, String scores) {
		this.resultID = resultID;
		this.oddAwayTeam = oddAwayTeam;
		this.oddHomeTeam = oddHomeTeam;
		this.oddDraw = oddDraw;
		this.winningTeam = winningTeam;
		this.scores = scores;
	}

	public Result(float oddAwayTeam, float oddHomeTeam, float oddDraw, String scores , String winningTeam) {
		this.resultID = Utils.geraIdentificadorUnicoInteger(Utils.results);
		Utils.results.put(resultID,resultID);
		this.oddAwayTeam = oddAwayTeam;
		this.oddHomeTeam = oddHomeTeam;
		this.oddDraw = oddDraw;
		this.scores = scores;
		this.winningTeam = winningTeam;
	}

	public Result(float oddAwayTeam, float oddHomeTeam, float oddDraw) {
		this.resultID = Utils.geraIdentificadorUnicoInteger(Utils.results);
		Utils.results.put(resultID,resultID);
		this.oddAwayTeam = oddAwayTeam;
		this.oddHomeTeam = oddHomeTeam;
		this.oddDraw = oddDraw;
		this.scores = "0x0";
		this.winningTeam = "NaN";
	}

	public Result() {
		this.resultID = Utils.geraIdentificadorUnicoInteger(Utils.results);
		Utils.results.put(resultID,resultID);
		this.oddAwayTeam = 0;
		this.oddHomeTeam = 0;
		this.oddDraw = 0;
		this.scores = "0x0";
		this.winningTeam = "NaN";
	}

	public int getResultID() {
		return resultID;
	}

	public void setResultID(int resultID) {
		this.resultID = resultID;
	}

	public float getOddAwayTeam() {
		return this.oddAwayTeam;
	}

	public void setOddAwayTeam(float oddAwayTeam) {
		this.oddAwayTeam = oddAwayTeam;
	}

	public float getOddHomeTeam() {
		return this.oddHomeTeam;
	}

	public void setOddHomeTeam(float oddHomeTeam) {
		this.oddHomeTeam = oddHomeTeam;
	}

	public float getOddDraw() {
		return this.oddDraw;
	}

	public void setOddDraw(float oddDraw) {
		this.oddDraw = oddDraw;
	}

	public String getwinningTeam() {
		return this.winningTeam;
	}

	public void setwinningTeam(String winningTeam) {
		this.winningTeam = winningTeam;
	}

	public String getScores() {
		return this.scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "Result{" +
				"resultID=" + resultID +
				", oddAwayTeam=" + oddAwayTeam +
				", oddHomeTeam=" + oddHomeTeam +
				", oddDraw=" + oddDraw +
				", winningTeam='" + winningTeam + '\'' +
				", scores='" + scores + '\'' +
				'}';
	}
}