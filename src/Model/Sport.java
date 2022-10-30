package Model;

import java.util.HashMap;
import java.util.Map;

public class Sport {

	/**
	 * Identificador do desporto
	 * */
	private String id;
	/**
	 * Nome do desporto
	 * */
	private String nome;
	/**
	 * Mapa de jogos
	 * */
	private Map<String,Game> jogos;

	public Sport(String id,String nome) {
		this.id = id;
		this.nome = nome;
		this.jogos = new HashMap<>();
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addGame(Game game) {
		this.jogos.put(game.getId(),game);
	}

	public void removeGame(String id) {
		this.jogos.remove(id);
	}

	public Game getGame(String id) {
		return this.jogos.get(id);
	}

	public  boolean gameExists(String id){
		return this.jogos.containsKey(id);
	}

}