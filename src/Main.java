import DataLayer.*;
import Model.*;
import UI.RasBetUI;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        RasBetFacade model = new RasBetFacade();
        Thread thread = new Thread(new APIFetcher(model));
        thread.start();
        APIFetcher.loadData(model);
        try {
            new RasBetUI(model).run();
        }
        catch (Exception e) {
            System.out.println("Erro fatal: " + e.getMessage() + " [" + e.toString() + "]");
            e.printStackTrace();
        }
    }



}
