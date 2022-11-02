package Model;

public class Outcome {

	/**
	 * Nome da outcome. For totals
	 * markets, this can be 'Over' or
	 * 'Under'. For all other markets, this
	 * will be the name of the team or
	 * participant, or 'Draw'
	 * */
	public String name;
	/**
	 * A odd da outcome
	 * */
	public float price;

	public Outcome(String name, float price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}