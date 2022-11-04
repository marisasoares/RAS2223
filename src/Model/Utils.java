package Model;

import java.util.*;

public interface Utils {

    public static Map<Integer,Integer> results = new HashMap<>();
    public static Map<Integer,Integer> sports = new HashMap<>();
    public static Map<String,String> games = new HashMap<>();
    public static Map<Integer,Integer> wallets = new HashMap<>();
    public static Map<Integer,Integer> transfers = new HashMap<>();
    public static Map<Integer,Integer> multipleIds = new HashMap<>();
    public static Map<Integer,Integer> bets = new HashMap<>();


    /**
     * Gera um identificador de 8 caracteres único
     * @param <T> Objeto guardado no map
     * @param m - Map onde se pretende criar um id unico
     * @return id gerado
     */
    public static <T> String geraIdentificadorUnico(Map<String,T> m){
        //Gerar um identificador aleatório
        String id;
        do {
            id = UUID.randomUUID().toString().substring(0, 8);
        } while (m.containsKey(id));
        m.put(id, (T) id);
        return id;
    }

    /**
     * Gera um identificador de 8 caracteres único
     * @param <T> Objeto guardado no map
     * @param m - Map onde se pretende criar um id unico
     * @return id gerado
     */
    public static <T> int geraIdentificadorUnicoInteger(Map<Integer,T> m){
        //Gerar um identificador aleatório
        int id;
        Random r = new Random();
        do {
            id = r.nextInt() & Integer.MAX_VALUE;;
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

    public static int InputInteger(Scanner scanner,int lowerLimit, int upperLimit){
        int input = 0;
        while(true) {
            try {
                do {
                    input = Integer.parseInt(scanner.nextLine());
                }while(input < lowerLimit || input > upperLimit);
                break;
            }catch (NumberFormatException e) {
                continue;
            }
        }
        return input;
    }
    public static int InputInteger(Scanner scanner){
        int input = 0;
        while(true) {
            try {
                    input = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e) {
                continue;
            }
        }
        return input;
    }
    public static float InputFloat(Scanner scanner){
        float input = 0;
        while(true) {
            try {
                input = Float.parseFloat(scanner.nextLine());
                break;
            }catch (NumberFormatException e) {
                continue;
            }
        }
        return input;
    }

}
