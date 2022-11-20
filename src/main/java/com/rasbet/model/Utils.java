package com.rasbet.model;

import java.util.*;

public interface Utils {

    Map<Integer,Integer> results = new HashMap<>();
    Map<Integer,Integer> sports = new HashMap<>();
    Map<String,String> games = new HashMap<>();
    Map<Integer,Integer> wallets = new HashMap<>();
    Map<Integer,Integer> transfers = new HashMap<>();
    Map<Integer,Integer> multipleIds = new HashMap<>();
    Map<Integer,Integer> notifications = new HashMap<>();
    Map<Integer,Integer> bets = new HashMap<>();


    /**
     * Gera um identificador de 8 caracteres único
     * @param <T> Objeto guardado no map
     * @param m - Map onde se pretende criar um id unico
     * @return id gerado
     */
    static <T> String geraIdentificadorUnico(Map<String, T> m){
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
    static <T> int geraIdentificadorUnicoInteger(Map<Integer, T> m){
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
    static <T> Object getObjectById(String id, Map<String, T> m){
        Object object = null;
        if(m.containsKey(id)) object = m.get(id);
        return object;
    }

    /*
     * Devolve true se um objeto com determinado id existe no Map associado, false caso contrário
     */
    static <T> boolean existeObjetoById(String id, Map<String, T> m){
        return m.containsKey(id);
    }

    /*
     * Devolve true se o Map se encontra vazio
     */
    static <T> boolean isEmpty(Map<String, T> m){
        return m.isEmpty();
    }

    static int InputInteger(Scanner scanner, int lowerLimit, int upperLimit){
        int input;
        while(true) {
            try {
                do {
                    input = Integer.parseInt(scanner.nextLine());
                }while(input < lowerLimit || input > upperLimit);
                break;
            }catch (NumberFormatException ignored) {
            }
        }
        return input;
    }
    static int InputInteger(Scanner scanner){
        int input;
        while(true) {
            try {
                    input = Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException ignored) {
            }
        }
        return input;
    }
    static float InputFloat(Scanner scanner){
        float input;
        while(true) {
            try {
                input = Float.parseFloat(scanner.nextLine());
                break;
            }catch (NumberFormatException ignored) {
            }
        }
        return input;
    }

}
