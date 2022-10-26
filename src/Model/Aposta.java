package Model;

import java.time.LocalDateTime;
import Model.Jogo;

public class Aposta{

    private LocalDateTime data;
    private String id;
    private Jogo jogo;
    private float valor;
    private float ganho;
    private float oddCasa;
    private float oddEmpate;
    private float oddFora;


    public Aposta(String id, LocalDateTime data, Jogo jogo,float valor, float oddCasa, float oddEmpate, float oddFora, float ganho) {
        this.data = data;
        this.id = id;
        this.jogo = jogo;
        this.valor = valor;
        this.oddCasa = oddCasa;
        this.oddEmpate = oddEmpate;
        this.oddFora = oddFora;
        this.ganho = ganho;
    }

    public Aposta(LocalDateTime data, Jogo jogo,float valor, float oddCasa, float oddEmpate, float oddFora, float ganho) {
        this.data = data;
        this.id = "NaN";
        this.jogo = jogo;
        this.valor = valor;
        this.oddCasa = oddCasa;
        this.oddEmpate = oddEmpate;
        this.oddFora = oddFora;
        this.ganho = ganho;
    }

    

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public float getValor() {
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }


    public float getOddCasa() {
        return this.oddCasa;
    }

    public void setOddCasa(float oddCasa) {
        this.oddCasa = oddCasa;
    }

    public float getOddEmpate() {
        return this.oddEmpate;
    }

    public void setOddEmpate(float oddEmpate) {
        this.oddEmpate = oddEmpate;
    }

    public float getOddFora() {
        return this.oddFora;
    }

    public void setOddFora(float oddFora) {
        this.oddFora = oddFora;
    }

    public float getGanho() {
        return this.ganho;
    }

    public void setGanho(float ganho) {
        this.ganho = ganho;
    }


    @Override
    public String toString() {
        return "{" +
            " data='" + getData() + "'" +
            ", id='" + getId() + "'" +
            ", jogo='" + getJogo() + "'" +
            ", valor='" + getValor() + "'" +
            ", oddCasa='" + getOddCasa() + "'" +
            ", oddEmpate='" + getOddEmpate() + "'" +
            ", oddFora='" + getOddFora() + "'" +
            ", ganho='" + getGanho() + "'" +
            "}";
    }
    
    

}
