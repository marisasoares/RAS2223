package com.rasbet.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Bookmaker {
	/**
	 * Identificador
	 * */
	public String key;

	/**
	 * Timestamp dos dados capturados
	 * */
	public String lastUpdate;

	/**
	 * Lista de markets de odds
	 * */
	public List<Market> markets;

	public Bookmaker(String key, String lastUpdate, List<Market> markets) {
		this.key = key;
		this.lastUpdate = lastUpdate;
		this.markets = markets;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * Adicionar um market à lista de markets
	 * @param market O market a adicionar
	 * */
	public void addMarket(Market market){
		this.markets.add(market);
	}

	/**
	 * Remover um market da lista de markets
	 * @param key key do market a remover
	 * */
	public void removeMarket(String key){
		this.markets.remove(key);
	}

}