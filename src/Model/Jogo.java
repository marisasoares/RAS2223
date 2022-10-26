package Model;

import java.time.LocalDateTime;

public class Jogo {

    private String id;
    private LocalDateTime date;
    private Desporto desporto;

    public Jogo(LocalDateTime date, Desporto desporto) {
        this.id = "NaN";
        this.date = date;
        this.desporto = desporto;
    }

    public Jogo(String id, LocalDateTime date, Desporto desporto) {
        this.id = id;
        this.date = date;
        this.desporto = desporto;
    }

    public Jogo(Desporto desporto) {
        this.id = "NaN";
        this.desporto = desporto;
        this.date = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Desporto getDesporto() {
        return desporto;
    }

    public void setDesporto(Desporto desporto) {
        this.desporto = desporto;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", desporto='" + getDesporto() + "'" +
            "}";
    }
    
}
