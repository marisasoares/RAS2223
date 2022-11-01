package DataLayer;

import Model.Sport;
import Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class GamesDB {

	/**
	 * Mapa de desportos
	 * */
	private Map<Integer, Sport> sports;

	public GamesDB() {
		this.sports = new HashMap<>();
	}

	/**
	 * Adicionar um novo Desporto
	 * @param name - nome do desporto
	 * @return id do desporto
	 */
	public int addSport(String name) {
		int id = Utils.geraIdentificadorUnicoInteger(this.sports);
		Sport sport = new Sport(id,name);
		this.sports.put(sport.getId(),sport);
		return id;
	}

	/**
	 * Remove desporto dado seu id
	 * @param id identificador do desporto
	 */
	public void removeSport(String id) {
		this.sports.remove(id);
	}

	/**
	 * Devolve um desporto dado o seu id, null caso contrário
	 * @param id identificador do desporto
	 */
	public Sport getSport(String id) {
		return this.sports.get(id);
	}

	/**
	 * Diz se determinado desporto existe
	 * @param id identificador do desporto
	 * @return true se existir, false caso contrário
	 * */
	public boolean sportExists(String id){
		return this.sports.containsKey(id);
	}

}