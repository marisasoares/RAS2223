import java.time.LocalDateTime;

public class Jogo {

    private string id;
    private LocalDateTime date;
    private string desporto;
    private string equipaDaCasa;
    private string equipaOponente;
    private float pontuacaoEquipaCasa;
    private float pontuacaoEquipaOponente;

    public Jogo(string id, LocalDateTime date, string desporto, string equipaDaCasa, string equipaOponente, float pontuacaoEquipaCasa, float pontuacaoEquipaOponente) {
        this.id = id;
        this.date = date;
        this.desporto = desporto;
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = pontuacaoEquipaCasa;
        this.pontuacaoEquipaOponente = pontuacaoEquipaOponente;
    }

    public Jogo(string id, string desporto, string equipaDaCasa, string equipaOponente, float pontuacaoEquipaCasa, float pontuacaoEquipaOponente) {
        this.id = id;
        this.desporto = desporto;
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = pontuacaoEquipaCasa;
        this.pontuacaoEquipaOponente = pontuacaoEquipaOponente;
        this.date = LocalDateTime.now();
    }

    public Jogo(string id, string desporto, string equipaDaCasa, string equipaOponente) {
        this.id = id;
        this.desporto = desporto;
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = 0;
        this.pontuacaoEquipaOponente = 0;
        this.date = LocalDateTime.now();
    }

    public string getId() {
        return id;
    }

    public void setId(string id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public string getDesporto() {
        return desporto;
    }

    public void setDesporto(string desporto) {
        this.desporto = desporto;
    }

    public string getEquipaDaCasa() {
        return equipaDaCasa;
    }

    public void setEquipaDaCasa(string equipaDaCasa) {
        this.equipaDaCasa = equipaDaCasa;
    }

    public string getEquipaOponente() {
        return equipaOponente;
    }

    public void setEquipaOponente(string equipaOponente) {
        this.equipaOponente = equipaOponente;
    }

    public float getPontuacaoEquipaCasa() {
        return pontuacaoEquipaCasa;
    }

    public void setPontuacaoEquipaCasa(float pontuacaoEquipaCasa) {
        this.pontuacaoEquipaCasa = pontuacaoEquipaCasa;
    }

    public float getPontuacaoEquipaOponente() {
        return pontuacaoEquipaOponente;
    }

    public void setPontuacaoEquipaOponente(float pontuacaoEquipaOponente) {
        this.pontuacaoEquipaOponente = pontuacaoEquipaOponente;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "id=" + id +
                ", date=" + date +
                ", desporto=" + desporto +
                ", equipaDaCasa=" + equipaDaCasa +
                ", equipaOponente=" + equipaOponente +
                ", pontuacaoEquipaCasa=" + pontuacaoEquipaCasa +
                ", pontuacaoEquipaOponente=" + pontuacaoEquipaOponente +
                '}';
    }
}
