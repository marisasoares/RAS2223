
public class Jogo {

    private String id;
    private LocalDateTime date;
    private String desporto;
    private String equipaDaCasa;
    private String equipaOponente;
    private float pontuacaoEquipaCasa;
    private float pontuacaoEquipaOponente;

    public Jogo(String id, LocalDateTime date, String desporto, String equipaDaCasa, String equipaOponente, float pontuacaoEquipaCasa, float pontuacaoEquipaOponente) {
        this.id = id;
        this.date = date;
        this.desporto = desporto;
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = pontuacaoEquipaCasa;
        this.pontuacaoEquipaOponente = pontuacaoEquipaOponente;
    }

    public Jogo(String id, String desporto, String equipaDaCasa, String equipaOponente, float pontuacaoEquipaCasa, float pontuacaoEquipaOponente) {
        this.id = id;
        this.desporto = desporto;
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = pontuacaoEquipaCasa;
        this.pontuacaoEquipaOponente = pontuacaoEquipaOponente;
        this.date = LocalDateTime.now();
    }

    public Jogo(String id, String desporto, String equipaDaCasa, String equipaOponente) {
        this.id = id;
        this.desporto = desporto;
        this.equipaDaCasa = equipaDaCasa;
        this.equipaOponente = equipaOponente;
        this.pontuacaoEquipaCasa = 0;
        this.pontuacaoEquipaOponente = 0;
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

    public String getDesporto() {
        return desporto;
    }

    public void setDesporto(String desporto) {
        this.desporto = desporto;
    }

    public String getEquipaDaCasa() {
        return equipaDaCasa;
    }

    public void setEquipaDaCasa(String equipaDaCasa) {
        this.equipaDaCasa = equipaDaCasa;
    }

    public String getEquipaOponente() {
        return equipaOponente;
    }

    public void setEquipaOponente(String equipaOponente) {
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
