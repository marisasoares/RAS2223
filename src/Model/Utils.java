package Model;

import java.util.Map;
import java.util.UUID;

public interface Utils {
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
}
