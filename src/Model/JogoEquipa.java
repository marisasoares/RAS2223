package src.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class JogoEquipa extends Jogo{
    private Equipa equipaDaCasa;
    private Equipa equipaOponente;
    private float pontuacaoEquipaCasa;
    private float pontuacaoEquipaOponente;



    public JogoEquipa(String id, LocalDateTime date, Desporto desporto, Equipa equipaDaCasa, Equipa equipaOponente, float pontuacaoEquipaCasa, float pontuacaoEquipaOponente) {
        super(id, date, desporto);
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = pontuacaoEquipaCasa;
        this.pontuacaoEquipaOponente = pontuacaoEquipaOponente;
    }

    public JogoEquipa(String id, LocalDateTime date, Desporto desporto, Equipa equipaDaCasa, Equipa equipaOponente){
        super(id, date, desporto);
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = 0;
        this.pontuacaoEquipaOponente = 0;
    }


    public Equipa getEquipaDaCasa() {
        return this.equipaDaCasa;
    }

    public void setEquipaDaCasa(Equipa equipaDaCasa) {
        this.equipaDaCasa = equipaDaCasa;
    }

    public Equipa getEquipaOponente() {
        return this.equipaOponente;
    }

    public void setEquipaOponente(Equipa equipaOponente) {
        this.equipaOponente = equipaOponente;
    }

    public float getPontuacaoEquipaCasa() {
        return this.pontuacaoEquipaCasa;
    }

    public void setPontuacaoEquipaCasa(float pontuacaoEquipaCasa) {
        this.pontuacaoEquipaCasa = pontuacaoEquipaCasa;
    }

    public float getPontuacaoEquipaOponente() {
        return this.pontuacaoEquipaOponente;
    }

    public void setPontuacaoEquipaOponente(float pontuacaoEquipaOponente) {
        this.pontuacaoEquipaOponente = pontuacaoEquipaOponente;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JogoEquipa)) {
            return false;
        }
        JogoEquipa jogoEquipa = (JogoEquipa) o;
        return Objects.equals(equipaDaCasa, jogoEquipa.equipaDaCasa) && Objects.equals(equipaOponente, jogoEquipa.equipaOponente) && pontuacaoEquipaCasa == jogoEquipa.pontuacaoEquipaCasa && pontuacaoEquipaOponente == jogoEquipa.pontuacaoEquipaOponente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipaDaCasa, equipaOponente, pontuacaoEquipaCasa, pontuacaoEquipaOponente);
    }

}