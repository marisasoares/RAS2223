package com.rasbet.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sport {

	/**
	 * Identificador do desporto
	 * */
	private int id;

	/**
	 * Nome do desporto
	 * */
	private String nome;

	public Sport(int id,String nome){
		this.id = id;
		this.nome = nome;
	}

	public Sport(String nome) {
		this.id = Utils.geraIdentificadorUnicoInteger(Utils.sports);
		Utils.sports.put(id,id);
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}