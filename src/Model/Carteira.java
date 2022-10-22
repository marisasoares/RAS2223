package src.Model;

public class Carteira {
    double euros;
    double dollars;

    public Carteira(double euros, double dollars) {
        this.euros = euros;
        this.dollars = dollars;
    }

    public double getEuros() {
        return euros;
    }

    public void setEuros(double euros) {
        this.euros = euros;
    }

    public double getDollars() {
        return dollars;
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }
}
