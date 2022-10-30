package Model;

public class Result {
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
	 * Pontuação equipa da casa
	 * */
	private float scoreHomeTeam;
	/**
	 * Pontuação equipa de fora
	 * */
	private float scoreAwayTeam;

	public Result(float oddAwayTeam, float oddHomeTeam, float oddDraw, float scoreHomeTeam, float scoreAwayTeam) {
		this.oddAwayTeam = oddAwayTeam;
		this.oddHomeTeam = oddHomeTeam;
		this.oddDraw = oddDraw;
		this.scoreHomeTeam = scoreHomeTeam;
		this.scoreAwayTeam = scoreAwayTeam;
	}

	public Result(float oddAwayTeam, float oddHomeTeam, float oddDraw) {
		this.oddAwayTeam = oddAwayTeam;
		this.oddHomeTeam = oddHomeTeam;
		this.oddDraw = oddDraw;
		this.scoreAwayTeam = 0;
		this.scoreHomeTeam = 0;
	}

	public Result() {
		this.oddAwayTeam = 0;
		this.oddHomeTeam = 0;
		this.oddDraw = 0;
		this.scoreAwayTeam = 0;
		this.scoreHomeTeam = 0;
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

	public float getScoreHomeTeam() {
		return this.scoreHomeTeam;
	}

	public void setScoreHomeTeam(float scoreHomeTeam) {
		this.scoreHomeTeam = scoreHomeTeam;
	}

	public float getScoreAwayTeam() {
		return this.scoreAwayTeam;
	}

	public void setScoreAwayTeam(float scoreAwayTeam) {
		this.scoreAwayTeam = scoreAwayTeam;
	}
}