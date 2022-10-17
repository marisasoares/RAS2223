public class Aposta {

    private LocalDateTime data;
    private String id;
    private Jogo jogo;

    public Aposta(LocalDateTime data, String id, Jogo jogo) {
        this.data = data;
        this.id = id;
        this.jogo = jogo;
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
}
