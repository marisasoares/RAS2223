package com.rasbet.model;

public class User {
	/**
	 * name do utilizador
	 */
	private String name;
	/**
	 * Email
	 */
	public String mail;
	/**
	 * Hash da password utilizando o metodo hashCode da classe String
	 */
	private int passwordHash;



	public User(String name, String mail, int passwordHash) {
		this.name = name;
		this.mail = mail;
		this.passwordHash = passwordHash;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return this.mail;
	}

	public int getPasswordHash() {
		return this.passwordHash;
	}

	public void setPassword(String password) {
		this.passwordHash = password.hashCode();
	}

	@Override
	public String toString() {
		return " • nome = " + name  +" , mail = " + mail ;
	}
}