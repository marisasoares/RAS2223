package Model;

import java.time.LocalDateTime;

public class Transfer {

	/**
	 * Identificador da transferência
	 * */
	public String id;
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

	public Transfer(String id, float value, LocalDateTime date, String description) {
		this.id = id;
		this.value = value;
		this.date = date;
		this.description = description;
	}

	public Transfer(float value, LocalDateTime date, String description) {
		this.value = value;
		this.date = date;
		this.description = description;
	}

	public String getId() {
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

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}