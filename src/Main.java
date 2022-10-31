import Model.Apostador;
import Model.RasBetFacade;
import UI.RasBetUI;
import UI.TextUI;

import java.io.IOException;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        RasBetFacade model = new RasBetFacade();
        System.out.println("Trying to retrive server data");
        String out = model.getServerData();
        if(out == null) System.out.println("[Network Error] Can't fetch data from \"http://ucras.di.uminho.pt/v1/games/\"");
        else System.out.println(out);

        for (int i = 1; i <= 20; i++){
            model.register("user" + i,"user"+i + "@gmail.com","12345678", String.valueOf(12345678+i),0);
            ((Apostador)model.usersDataBase.getUser("user"+i+"@gmail.com")).getCarteira().setEuros(new Random().nextFloat()*100);
            ((Apostador)model.usersDataBase.getUser("user"+i+"@gmail.com")).getCarteira().setDollars(new Random().nextFloat()*100);
        }
        model.register("especialista","esp@gmail.com","12345678","00000000",1);
        model.register("admin","admin@gmail.com","12345678","000000000",1);

        try {
            new RasBetUI(model).run();
        }
        catch (Exception e) {
            System.out.println("Erro fatal: " + e.getMessage() + " [" + e.toString() + "]");
        }
    }
}
