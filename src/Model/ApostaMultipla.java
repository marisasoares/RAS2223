package Model;

import java.time.LocalDateTime;
import Model.Jogo;


public class ApostaMultipla extends Aposta{

    public ApostaMultipla(String id,LocalDateTime data,  Jogo jogo,float valor , float oddCasa, float oddEmpate, float oddFora, float ganho) {
        super( id, data, jogo, valor, oddCasa, oddEmpate, oddFora,ganho);
    }

    public ApostaMultipla(LocalDateTime data, Jogo jogo, float valor ,float oddCasa, float oddEmpate, float oddFora,float ganho) {
        super(data, jogo, valor, oddCasa, oddEmpate, oddFora,ganho);
    }
}
