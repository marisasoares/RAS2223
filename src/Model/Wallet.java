package Model;

public class Wallet {

    /**
     * Identificador da carteira
     * */
    private int id;

    /**
     * Quantia em euros guardada nesta carteira
     * */
    private float euros;
    /**
     * Quantia em dolars guardada nesta carteira*/
    private float dollars;

    public Wallet() {
        this.id = Utils.geraIdentificadorUnicoInteger(Utils.wallets);
        Utils.wallets.put(id,id);
        this.euros = 0;
        this.dollars = 0;
    }

    public Wallet(float euros, float dollars) {
        this.id = Utils.geraIdentificadorUnicoInteger(Utils.wallets);
        Utils.wallets.put(id,id);
        this.euros = euros;
        this.dollars = dollars;
    }

    public float getEuros() {
        return this.euros;
    }

    public void setEuros(float euros) {
        this.euros = euros;
    }

    public float getDollars() {
        return this.dollars;
    }

    public void setDollars(float dollars) {
        this.dollars = dollars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Adicionar um valor em euros à carteira
     * */
    public void addEuros(float amount){
        this.setEuros(this.getEuros()+amount);
    }

    /**
     * Adicionar um valor em dollars à carteira
     * */
    public void addDollars(float amount){
        this.setDollars(this.getDollars()+amount);
    }
}
