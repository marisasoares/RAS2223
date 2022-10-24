package src.Model;

import java.time.LocalDateTime;

public class ApostaSimples extends Aposta{

    public ApostaSimples( String id,LocalDateTime data, Jogo jogo,float valor , float oddCasa, float oddEmpate, float oddFora, float ganho) {
        super(id,data, jogo, valor, oddCasa, oddEmpate, oddFora,ganho);
    }

    public ApostaSimples(LocalDateTime data, Jogo jogo,float valor , float oddCasa, float oddEmpate, float oddFora,float ganho) {
        super(data, jogo, valor, oddCasa, oddEmpate, oddFora,ganho);
    }
}
