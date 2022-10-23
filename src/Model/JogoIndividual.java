package src.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class JogoIndividual extends Jogo{

    private float pontuacao;


    public JogoIndividual() {
    }

    public JogoIndividual(String id, LocalDateTime date, Desporto desporto, float pontuacao) {
        super(date, desporto);
        this.pontuacao = pontuacao;
    }

    public float getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }

    public JogoIndividual pontuacao(float pontuacao) {
        setPontuacao(pontuacao);
        return this;
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