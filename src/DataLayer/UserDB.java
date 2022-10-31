package DataLayer;

import Model.*;

import java.util.HashMap;
import java.util.Map;

public class UserDB {

	/**
	 * Mapa de utilizadores
	 * */
	Map<String, User> utilizadores;

	public UserDB() {
		this.utilizadores = new HashMap<>();
	}

	/**
	 * Adicionar utilizador à base de dados
	 * @param nome nome do utilizador
	 * @param email email único do utilizador
	 * @param password password do utilizador
	 * @param nif Número de identificação fiscal do utilizador
	 * @param type 0 - Model.Apostador, 1 - Model.Especialista , 2 - Model.Administrador
	 * @return true se utilizador adicionado false caso erro
	 */
	public boolean addUser(String nome,String email, String password, String nif, int type) {
		boolean addedUser = false;
		if(!this.utilizadores.containsKey(email)){
			switch (type){
				case 1:
					Especialista especialista = new Especialista(nome,email,password.hashCode());
					this.utilizadores.put(email,especialista);
					break;
				case 2:
					Administrador administrador = new Administrador(nome,email,password.hashCode());
					this.utilizadores.put(email,administrador);
					break;
				default:
					Apostador apostador = new Apostador(nome,email,password.hashCode(),nif);
					this.utilizadores.put(email,apostador);
					break;
			}
			addedUser = true;
		}
		return addedUser;
	}

	/**
	 * Permite modificar a informação de um dado utilizador
	 * @param email email único do utilizador
	 * @param nome nome do utilizador
	 * @param password password do utilizador
	 * @return true se o utilizador existia e foi atualizado, false caso contrário
	 */
	public boolean addInfo(String email, String nome, String password) {
		boolean updatedInfo = false;
		if(this.utilizadores.containsKey(email)){
			User user = this.utilizadores.get(email);
			user.setNome(nome);
			user.setPassword(password);
			updatedInfo = true;
		}
		return updatedInfo;
	}

	/**
	 * Remove um utilizador da base de dados
	 * @param email email do utilizador a remover
	 */
	public void removeUser(String email) {
		this.utilizadores.remove(email);
	}

	/**
	 * Devolve um utilizador dado o seu email
	 * @param email o email do utilizador
	 */
	public User getUser(String email) {
		return this.utilizadores.get(email);
	}

	/**
	 * Diz se determinado utilizador existe
	 * @param id identificador do utilizador
	 * @return true se existir, false caso contrário
	 * */
	public boolean userExists(String id){
		return this.utilizadores.containsKey(id);
	}
}