package Model;

import java.time.LocalDateTime;
import java.util.Map;

public class Bookmaker {
	/**
	 * Identificador
	 * */
	public String key;

	/**
	 * Timestamp dos dados capturados
	 * */
	private LocalDateTime lastUpdate;

	/**
	 * Lista de markets de odds
	 * */
	private Map<String,Market> markets;

	public Bookmaker(String key, LocalDateTime lastUpdate, Map<String, Market> markets) {
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

	public LocalDateTime getLastUpdate() {
		return this.lastUpdate;
	}
	
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * Adicionar um market à lista de markets
	 * @param market O market a adicionar
	 * */
	public void addMarket(Market market){
		this.markets.put(market.getKey(),market);
	}

	/**
	 * Remover um market da lista de markets
	 * @param key key do market a remover
	 * */
	public void removeMarket(String key){
		this.markets.remove(key);
	}

}