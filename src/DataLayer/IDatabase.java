package DataLayer;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import Model.Jogo;
import Model.Aposta;
import Model.Desporto;
import Model.Utilizador;

public interface IDatabase {

    /*
     * Carrega os utilizadores da base de dados
     */
    public void loadUsersFromDB();

    /*
     * Carrega os jogos e desportos da base de dados
     */
    public void loadJogosAndSportsFromDB();

    /*
     * Carrega as apostas da base de dados
     */
    public void loadApostasFromDB();

    /*
     * Adiciona utilizador à base de dados
     */
    public boolean addUtilizador(Utilizador user);

    public boolean usernameExists(String username);

    public Utilizador getUtilizador(String username,String password);

    public String addJogo(Jogo jogo);

    public boolean addJogo(Jogo jogo,String id);

    public boolean removeJogo(String id);

    public String addDesporto(Desporto desporto);

    public boolean addDesporto(Desporto desporto,String id);

    public boolean removeDesporto(String id);

    public String addAposta(Aposta aposta);

    public boolean addAposta(Aposta aposta,String id);

    public boolean removeAposta(String id);

    public boolean existemApostadores();

    public boolean existemEspecialistas();

    public boolean existemAdministradores();

    public boolean existemJogos();

    public boolean existemDesportos();

    public boolean existemApostas();

    public boolean existemUtilizadores();
    /*
     * Adiciona objeto à base de dados com determinado id
     */
    public static <T> boolean addToDatabaseWithId(T object,String id, Map<String,T> m){
        boolean addedToMap = false;
        if(!m.containsKey(object)) {
            m.put(id, object);
            addedToMap = true;
        }
        return addedToMap;
    }

    /*
     * Adiciona objeto à base de dados gerando um novo id
     */
    public static <T> String addToDatabase(T object, Map<String,T> m){
        String id = geraIdentificadorUnico(m);
        if(object instanceof Jogo) ((Jogo) object).setId(id);
        if(object instanceof Aposta) ((Aposta) object).setId(id);
        if(object instanceof Desporto) ((Desporto) object).setId(id);
        m.put(id,object);
        return id;
    }

    /*
     * Remove objeto da base de dados com determinado id devolvendo true se o objeto existia
     */
    public static <T> Boolean removeFromDatase(String id, Map<String,T> m){
        boolean existed = false;
        if(m.containsKey(id)) existed = true;
        m.remove(id);
        return existed;
    }

    /*
     * Autentica utilizador
     */
    public boolean login(String username, String password);

    /*
     * Devolve um objeto da base de dados dado o seu id e o Map associado
     */
    public static <T> Object getObjectById(String id, Map<String,T> m){
        Object object = null;
        if(m.containsKey(id)) object = m.get(id);
        return object;
    }

    /*
     * Devolve true se um objeto com determinado id existe no Map associado, false caso contrário
     */
    public static <T> boolean existeObjetoById(String id,Map<String,T> m){
        return m.containsKey(id);
    }

    /*
     * Devolve true se o Map se encontra vazio
     */
    public static <T> boolean isEmpty(Map<String,T> m){
        return m.isEmpty();
    } 

    public ArrayList<String> listarJogos();

    public ArrayList<String> listarDesportos();

    public ArrayList<String> listarApostas();

    public ArrayList<String> listarApostadores();

    public ArrayList<String> listarAdministradores();

    public ArrayList<String> listarEspecialistas();

    /**
	 * Gera um identificador de 8 caracteres único
     * @param <T>
	 * @param m - Map onde se pretende criar um id unico
	 * @return id gerado
	 */
    public static <T> String geraIdentificadorUnico(Map<String,T> m){
        //Gerar um identificador aleatório
		String id;
		do {
			id = UUID.randomUUID().toString().substring(0, 8);
		} while (m.containsKey(id));
		return id;
    }
    
}
