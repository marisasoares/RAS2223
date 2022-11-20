package com.rasbet.model;

public class Wallet {

    /**
     * Email do utilizador que possui a carteira
     */
    private String email;

    /**
     * Quantia em euros guardada nesta carteira
     * */
    private float euros;

    /**
     * Quantia em dolars guardada nesta carteira*/
    private float dollars;


    public Wallet(String email) {
        this.email = email;
        this.euros = 0;
        this.dollars = 0;
    }

    public Wallet(String email,float euros, float dollars) {
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Wallet{" +
                "email='" + email + '\'' +
                ", euros=" + euros +
                ", dollars=" + dollars +
                '}';
    }
}
