import java.util.List;

public class Caminho {
    private List<Casa> casas;

    public Caminho(List<Casa> casas) {
        this.casas = casas;
    }

    // Verifica se todas as casas do caminho (exceto a primeira e a última) estão livres
    public boolean estaLivre() {
        for (int i = 1; i < casas.size() - 1; i++) {
            if (casas.get(i).estaOcupada()) {
                return false;
            }
        }
        return true;
    }

    public Casa casaInicial() {
        return casas.get(0);
    }

    public Casa casaFinal() {
        return casas.get(casas.size() - 1);
    }

    public List<Casa> getCasas() {
        return casas;
    }
}
