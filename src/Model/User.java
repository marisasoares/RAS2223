package Model;

public class User {
	/**
	 * Nome do utilizador
	 */
	private String nome;
	/**
	 * Email
	 */
	public String mail;
	/**
	 * Hash da password utilizando o metodo hashCode da classe String
	 */
	private int passwordHash;


	public User(String nome, String mail, int passwordHash) {
		this.nome = nome;
		this.mail = mail;
		this.passwordHash = passwordHash;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}