package src.Model;

import java.time.LocalDateTime;

public class Jogo {

    private String id;
    private LocalDateTime date;
    private Desporto desporto;


    public Jogo(){
        
    }
    

    public Jogo(LocalDateTime date, Desporto desporto) {
        this.id = "NaN";
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
        return "ID do jogo: " + id +
               "\nDate: " + date +
               "\nDesporto: \n" + desporto +
               '\n';
    }
}
