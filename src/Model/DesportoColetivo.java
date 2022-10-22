package src.Model;

import java.util.Map;
import java.util.Objects;

public class DesportoColetivo extends Desporto{
    private Map<String,Equipa> listaEquipas;
    

    public DesportoColetivo() {
    }

    public DesportoColetivo(String id, String nome, Map<String,Equipa> listaEquipas) {
        super(id, nome);
        this.listaEquipas = listaEquipas;
    }

    public Map<String,Equipa> getListaEquipas() {
        return this.listaEquipas;
    }

    public void setListaEquipas(Map<String,Equipa> listaEquipas) {
        this.listaEquipas = listaEquipas;
    }

    public DesportoColetivo listaEquipas(Map<String,Equipa> listaEquipas) {
        setListaEquipas(listaEquipas);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DesportoColetivo)) {
            return false;
        }
        DesportoColetivo desportoColetivo = (DesportoColetivo) o;
        return Objects.equals(listaEquipas, desportoColetivo.listaEquipas);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(listaEquipas);
    }

    @Override
    public String toString() {
        return "{" +
            " listaEquipas='" + getListaEquipas() + "'" +
            "}";
    }



}
