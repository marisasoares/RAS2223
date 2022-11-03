package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListMenu {
    private String titulo;
    private List<String> opcoes;

    /**
     * Construtor vazio de Menu.
     */
    public ListMenu(){
        this.titulo = "";
        this.opcoes = new ArrayList<>();
    }

    /**
     * Alterar o titulo do Menu.
     * @param titulo O novo titulo.
     */
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    /**
     * Adicionar uma opção ao menu.
     * @param opcao A nova opção.
     */
    public void adicionaOpcao(String opcao){
        this.opcoes.add(opcao);
    }

    /**
     * Remover uma opção do menu.
     * @param opcao A opção a remover.
     */
    public void removeOpcao(String opcao){
        this.opcoes.remove(opcao);
    }

    /**
     * Mostrar as opções do Menu.
     */
    public void show(boolean cursorVisible){
        System.out.println("\u001B[36m" + titulo + "\u001B[0m");
        int i = 1;
        for (String string : this.opcoes) {
            System.out.print("[\033[1;36m"+i+"\033[0m]");
            System.out.print(" - ");
            System.out.println(string);
            i++;
        }
        System.out.println("[\033[1;36m0\033[0m] - Sair");
        if(cursorVisible) System.out.print("> ");
    }
}
