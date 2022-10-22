import src.UI.TextUI;
import src.DataLayer.Database;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("\033[H\033[2J");
        try {
            new TextUI().run();
        }
        catch (Exception e) {
             System.out.println("Erro fatal: "+e.getMessage()+" ["+e.toString()+"]");
        }
    }
}
