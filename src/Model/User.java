package Model;

public class User {
	/**
	 * Nome do utilizador
	 * */
	private String nome;
	/**
	 * Email
	 * */
	public String mail;
	/**
	 * Hash da password utilizando o metodo hashCode da classe String
	 * */
	private int passwordHash;
	/**
	 * Número de identificação fiscal do utilizador
	 * */
	private String nif;
	/**
	 * Model.Carteira que guarda a quantia que o utilizador possui nas diversas moedas
	 * */
	private Carteira carteira;

	public User(String nome, String mail, int passwordHash, String nif) {
		this.nome = nome;
		this.mail = mail;
		this.passwordHash = passwordHash;
		this.nif = nif;
		this.carteira = new Carteira();
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

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Carteira getCarteira(){
		return this.carteira;
	}
}