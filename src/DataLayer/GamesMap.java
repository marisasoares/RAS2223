package DataLayer;

import Model.Game;
import Model.Sport;
import Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class GamesMap {

	/**
	 * Mapa de desportos
	 * */
	private Map<String, Game> games;

	public GamesMap() {
		this.games = new HashMap<>();
	}

	public void addGame(Game game) {
		this.games.put(game.getId(),game);
	}

	/**
	 * Remove desporto dado seu id
	 * @param id identificador do desporto
	 */
	public void removeGame(String id) {
		this.games.remove(id);
	}

	/**
	 * Devolve um desporto dado o seu id, null caso contrário
	 * @param id identificador do desporto
	 */
	public Game getGame(String id) {
		return this.games.get(id);
	}

	/**
	 * Diz se determinado desporto existe
	 * @param id identificador do desporto
	 * @return true se existir, false caso contrário
	 * */
	public boolean gameExists(String id){
		return this.games.containsKey(id);
	}

}