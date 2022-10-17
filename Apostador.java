

public class Apostador : Utilizador{

    private Map<String,Aposta> apostas;
    private int carteira;

    public Apostador(Map<String, Aposta> apostas, int carteira) {
        this.apostas = apostas;
        this.carteira = carteira;
    }

    public Apostador(){
        this.apostas = new HashMap<>();
        this.carteira = 0;
    }

    public Apostador(String username, String password, String email, Map<String, Aposta> apostas, int carteira) {
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

    public void setCarteira(int carteira) {
        this.carteira = carteira;
    }

    public int getCarteira() {
        return carteira;
    }
}
