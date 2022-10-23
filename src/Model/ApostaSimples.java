package src.Model;

import java.time.LocalDateTime;

public class ApostaSimples extends Aposta{

    public ApostaSimples( String id,LocalDateTime data, Jogo jogo) {
        super(id,data, jogo);
    }

    public ApostaSimples(LocalDateTime data, Jogo jogo) {
        super(data, jogo);
    }
}
