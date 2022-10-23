package src.Model;

import java.util.ArrayList;
import java.util.Objects;

public class DesportoIndividual extends Desporto{
    private ArrayList<String> jogadores;
    
    public DesportoIndividual(String id, String nome, ArrayList<String> jogadores) {
        super(id, nome);
        this.jogadores = jogadores;
    }

    public DesportoIndividual(String nome, ArrayList<String> jogadores) {
        super(nome);
        this.jogadores = jogadores;
    }

    public ArrayList<String> getJogadores() {
        return this.jogadores;
    }

    public void setJogadores(ArrayList<String> jogadores) {
        this.jogadores = jogadores;
    }

    public DesportoIndividual jogadores(ArrayList<String> jogadores) {
        setJogadores(jogadores);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DesportoIndividual)) {
            return false;
        }
        DesportoIndividual desportoIndividual = (DesportoIndividual) o;
        return Objects.equals(jogadores, desportoIndividual.jogadores);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(jogadores);
    }

    @Override
    public String toString() {
        return "{" +
            " jogadores='" + getJogadores() + "'" +
            "}";
    }


    
}
