import java.util.HashMap;
import java.util.Map;

public class Apostador extends Utilizador{

    private Map<String,Aposta> apostas;
    private Carteira carteira;

    public Apostador(Map<String, Aposta> apostas, Carteira carteira) {
        this.apostas = apostas;
        this.carteira = carteira;
    }

    public Apostador(){
        this.apostas = new HashMap<>();
        this.carteira = new Carteira(0,0);
    }

    public Apostador(String username, String password, String email, Map<String, Aposta> apostas, Carteira carteira) {
        super(username, password, email);
        this.apostas = apostas;
        this.carteira = carteira;
    }

    public Map<String, Aposta> getApostas() {
        return apostas;
    }

    public void setApostas(Map<String, Aposta> apostas) {
        this.apostas = apostas;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public int getCarteira() {
        return carteira;
    }
}
