package com.rasbet.model;

import java.time.LocalDateTime;

public class Transfer {

	/**
	 * Identificador da transferência
	 * */
	private int id;
	/**
	 * Valor da transferencia
	 * */
	private float value;
	/**
	 * Data da transferência
	 * */
	private LocalDateTime date;

	/**
	 * Descrição da transferência
	 * */
	private String description;

	/**
	 * Email do utilizador
	 * */
	private String email;

	public Transfer(int id, float value, LocalDateTime date, String description,String email) {
		this.id = id;
		this.value = value;
		this.date = date;
		this.description = description;
		this.email = email;
	}

	public Transfer(float value, LocalDateTime date, String description,String email) {
		this.id = Utils.geraIdentificadorUnicoInteger(Utils.transfers);
		Utils.transfers.put(id,id);
		this.value = value;
		this.date = date;
		this.description = description;
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public String getFormattedDate(){
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear() +  " às " + date.getHour()+ ":" + String.format("%02d",date.getMinute());
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Transfer{" +
				"id=" + id +
				", value=" + value +
				", date=" + date +
				", description='" + description + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}