package src.Model;

import java.util.Objects;

public class Desporto {
    String id;
    String nome;


    public Desporto() {
    }

    public Desporto(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Desporto id(String id) {
        setId(id);
        return this;
    }

    public Desporto nome(String nome) {
        setNome(nome);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Desporto)) {
            return false;
        }
        Desporto desporto = (Desporto) o;
        return Objects.equals(id, desporto.id) && Objects.equals(nome, desporto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }


}
