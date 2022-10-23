package src.Model;

import java.time.LocalDateTime;

public class ApostaMultipla extends Aposta{

    public ApostaMultipla(String id,LocalDateTime data,  Jogo jogo) {
        super( id, data, jogo);
    }

    public ApostaMultipla(LocalDateTime data, Jogo jogo) {
        super(data, jogo);
    }
}
