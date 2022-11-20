package com.rasbet.model;

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
	public List<Outcome> outcomes;


	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Outcome> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}

	/**
	 * Adicionar um outcome à lista de outcomes
	 * @param outcome A outcome a adicionar
	 * */
	public void addOutcome(Outcome outcome){
		this.outcomes.add(outcome);
	}

	/**
	 * Remover um outcome da lista de outcomes
	 * @param name Nome da outcome a remover
	 * */
	public void removeOutcome(String name){
		this.outcomes.remove(name);
	}
}