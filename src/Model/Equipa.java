package Model;

import java.util.ArrayList;

public class Equipa {
        private String id;
        private String nome;
        private ArrayList<String> jogadores;

        public Equipa(String id, String nome, ArrayList<String> jogadores) {
            this.id = id;
            this.nome = nome;
            this.jogadores = jogadores;
        }

        public Equipa( String nome, ArrayList<String> jogadores) {
            this.id = "NaN";
            this.nome = nome;
            this.jogadores = jogadores;
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

        public ArrayList<String> getJogadores() {
            return this.jogadores;
        }

        public void setJogadores(ArrayList<String> jogadores) {
            this.jogadores = jogadores;
        }

        @Override
        public String toString() {
            return "{" +
                " id='" + getId() + "'" +
                ", nome='" + getNome() + "'" +
                ", jogadores='" + getJogadores() + "'" +
                "}";
        }

}
