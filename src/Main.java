import UI.RasBetUI;
import UI.TextUI;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("\033[H\033[2J");
        try {
            new RasBetUI().run();
        }
        catch (Exception e) {
             System.out.println("Erro fatal: "+e.getMessage()+" ["+e.toString()+"]");
        }
    }
}
