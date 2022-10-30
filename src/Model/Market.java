package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Market {

	/**
	 * Nome do mercado de odds
	 * */
	public String key;
	/**
	 * Lista de outcomes suportadas
	 * */
	private Map<String,Outcome> outcomes;


	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Outcome> getOutcomes() {
		List<Outcome> outcomeList = new ArrayList<>();
		for (Map.Entry<String,Outcome> entry: this.outcomes.entrySet()) {
			outcomeList.add(entry.getValue());
		}
		return outcomeList;
	}

	public void setOutcomes(List<Outcome> outcomes) {
		for (Outcome outcome: outcomes) {
			this.outcomes.put(outcome.getName(),outcome);
		}
	}

	/**
	 * Adicionar um outcome à lista de outcomes
	 * @param outcome A outcome a adicionar
	 * */
	public void addOutcome(Outcome outcome){
		this.outcomes.put(outcome.getName(),outcome);
	}

	/**
	 * Remover um outcome da lista de outcomes
	 * @param name Nome da outcome a remover
	 * */
	public void removeOutcome(String name){
		this.outcomes.remove(name);
	}
}