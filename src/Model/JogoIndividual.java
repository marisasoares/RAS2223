package src.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class JogoIndividual extends Jogo{

    private float pontuacao;
    private ArrayList<Jogador> jogadores;

    public JogoIndividual(String id, LocalDateTime date, Desporto desporto, float pontuacao,ArrayList<Jogador> jogadores) {
        super(date, desporto);
        this.pontuacao = pontuacao;
        this.jogadores = jogadores;
    }

    public float getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }


    public ArrayList<String> getJogadores() {
        ArrayList<String> jogadoresString = new ArrayList<>();
        for (Jogador j : this.jogadores) {
            jogadoresString.add(j.toString());
        }
        return jogadoresString;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        ArrayList<Jogador> newJogadores = new ArrayList<>();
        for (Jogador j : jogadores) {
            newJogadores.add(j);
        }
        this.jogadores = newJogadores;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JogoIndividual)) {
            return false;
        }
        JogoIndividual jogoIndividual = (JogoIndividual) o;
        return pontuacao == jogoIndividual.pontuacao;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pontuacao);
    }

    @Override
    public String toString() {
        return "{" +
            " pontuacao='" + getPontuacao() + "'" +
            "}";
    }


}