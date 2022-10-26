package UI;
import java.io.Serializable;
import java.util.*;

/**
 * Esta classe implementa um menu em modo texto.
 */
public class Menu implements Serializable{
    /** Functional interface para handlers. */
    public interface Handler {
         void execute();
    }

     /** Functional interface para pré-condições. */
     public interface PreCondition {
         boolean validate();
     }

    //Varíável de classe para suportar leitura

     private static Scanner is = new Scanner(System.in,"utf-8");

    // Variáveis de instância

     private String titulo;                   //Titulo do menu (opcional)
     private List<String> opcoes;             //Lista de opções
     private List<PreCondition> disponivel;   //Lista de pré-condições
     private List<Handler> handlers;          //Lista de handlers
     private String initialMessage = "";

     public void setTitulo(String titulo){
         this.titulo = titulo;
     }

    //Construtor

     /**
      * Constructor vazio para objectos da classe Menu.
      *
      * Cria um menu vazio, ao qual se podem adicionar opções.
      */
     public Menu() {
         this.titulo = "Menu";
         this.opcoes = new ArrayList<String>();
         this.disponivel = new ArrayList<PreCondition>();
         this.handlers = new ArrayList<Handler>();
     }

     /**
      * Constructor para objectos da classe Menu (com título e List de opções).
      *
      * Cria um menu de opções sem event handlers.
      * Utilização de listas é útil para definir menus dinâmicos.
      *
      * @param titulo O titulo do menu
      * @param opcoes Uma lista de Strings com as opções do menu.
      */
     public Menu(String titulo, List<String> opcoes, String initialMessage) {
         this.titulo = titulo;
         this.opcoes = new ArrayList<String>(opcoes);
         this.disponivel = new ArrayList<PreCondition>();
         this.handlers = new ArrayList<Handler>();
         this.initialMessage = initialMessage;
         this.opcoes.forEach(s-> {
             this.disponivel.add(()->true);
             this.handlers.add(()->opcaoInvalidaOuNaoDisponivel());
         });
     }

     private void opcaoInvalidaOuNaoDisponivel(){
        System.out.println("░░ Opção inválida ou não disponível ░░\n - Press enter to continue -");
        is.nextLine();
     }

     /**
      * Constructor para objectos da classe Menu (sem título e com List de opções).
      *
      * Cria um menu de opções sem event handlers.
      * Utilização de listas é útil para definir menus dinâmicos.
      *
      * @param opcoes Uma lista de Strings com as opções do menu.
      */
     public Menu(List<String> opcoes) { this("Menu", opcoes,""); }

     /**
      * Constructor para objectos da classe Menu (com título e array de opções).
      *
      * Cria um menu de opções sem event handlers.
      * Utilização de arrays é útil para definir menus estáticos. P.e.:
      *
      * new Menu(String[]{
      *     "Opção 1",
      *     "Opção 2",
      *     "Opção 3"
      * })
      *
      * @param titulo O titulo do menu
      * @param opcoes Um array de Strings com as opções do menu.
      */
     public Menu(String titulo, String[] opcoes) {
         this(titulo, Arrays.asList(opcoes),"");
     }

     /**
      * Constructor para objectos da classe Menu (sem título e com array de opções).
      *
      * Cria um menu de opções sem event handlers.
      * Utilização de arrays é útil para definir menus estáticos. P.e.:
      *
      * new Menu(String[]{
      *     "Opção 1",
      *     "Opção 2",
      *     "Opção 3"
      * })
      *
      * @param opcoes Um array de Strings com as opções do menu.
      */
     public Menu(String[] opcoes) {
         this(Arrays.asList(opcoes));
     }

    public Menu(String[] opcoes, String initialMessage) {
        this(Arrays.asList(opcoes));
        this.initialMessage = initialMessage;
    }

    //Métodos de instância

     /**
      * Adicionar opções a um Menu.
      *
      * @param name A opção a apresentar.
      * @param p A pré-condição da opção.
      * @param h O event handler para a opção.
      */
     public void option(String name, PreCondition p, Handler h) {
         this.opcoes.add(name);
         this.disponivel.add(p);
         this.handlers.add(h);
     }

     /**
      * Correr o menu uma vez.
      */
     public void runOnce() {
         int op;
         show();
         op = readOption();
        //testar pré-condição
         if (op>0 && !this.disponivel.get(op-1).validate()) {
            opcaoInvalidaOuNaoDisponivel();
         } else if (op>0) {
            //executar handler
             this.handlers.get(op-1).execute();
         }
     }

     /**
      * Correr o menu multiplas vezes.
      *
      * Termina com a opção 0 (zero).
      */
     public void run() {
        
         int op;
         do {
            System.out.println("\033[H\033[2J");
            System.out.print(initialMessage);
             show();
             op = readOption();
            //testar pré-condição
             if (op>0 && !this.disponivel.get(op-1).validate()) {
                System.out.println("──────────────────────────────────────────────");
                opcaoInvalidaOuNaoDisponivel();
             } else if (op>0) {
                //executar handler
                System.out.println("──────────────────────────────────────────────");
                this.handlers.get(op-1).execute();
             }
         } while (op != 0);
     }

     /**
      * Método que regista uma uma pré-condição numa opção do menu.
      *
      * @param i índice da opção (começa em 1)
      * @param b pré-condição a registar
      */
     public void setPreCondition(int i, PreCondition b) {
         this.disponivel.set(i-1,b);
     }

     /**
      * Método para registar um handler numa opção do menu.
      *
      * @param i indice da opção  (começa em 1)
      * @param h handlers a registar
      */
     public void setHandler(int i, Handler h) {
         this.handlers.set(i-1, h);
     }

    //Métodos auxiliares

     /** Apresentar o menu */
     private void show() {
        System.out.print("\033[0;36m");
        StringBuilder sb = new StringBuilder();
        sb.append("╔══");
        for (int i = 0; i < this.titulo.length(); i++) sb.append("═");
        sb.append("══╗");
        System.out.println(sb.toString());
        System.out.println("║  " + this.titulo + "  ╠════════════════════════════════════════════════");
        sb = new StringBuilder();
        sb.append("╚══");
        for (int i = 0; i < this.titulo.length(); i++) sb.append("═");
        sb.append("══╝");
        System.out.println(sb.toString());
        System.out.print("\033[0m");
         for (int i=0; i<this.opcoes.size(); i++) {
             System.out.print("[\033[1;36m"+(i+1)+"\033[0m]");
             System.out.print(" - ");
             System.out.println(this.disponivel.get(i).validate()?this.opcoes.get(i):"----");
         }
         System.out.println("[\033[1;36m0\033[0m] - Sair");
     }

     /** Ler uma opção válida */
     private int readOption() {
         int op;
         System.out.print("\033[1;36m>\033[0m ");
         try {
             String line = is.nextLine();
             op = Integer.parseInt(line);
         }
         catch (NumberFormatException e) {  //Não foi inscrito um int
             op = -1;
         }
         if (op<0 || op>this.opcoes.size()) {
             opcaoInvalidaOuNaoDisponivel();
             op = -1;
         }
         return op;
     }
}