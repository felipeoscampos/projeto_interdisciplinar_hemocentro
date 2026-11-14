import java.util.ArrayList;
import java.util.List;

public class Analise {
    private int codigo;
    private String status;
    private Coleta coleta;
    private Tecnico tecnico;
    private List<Producao> producao;

    public Analise() {
        codigo = 0;
        status = "";
        coleta = null;
        tecnico = null;
        producao = new ArrayList<>();
    }

    public Analise(int codigo, String status, Coleta coleta, Tecnico tecnico) {
        this.codigo = codigo;
        this.status = status;
        this.coleta = coleta;
        this.tecnico = tecnico;
        this.producao = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta coleta) {
        this.coleta = coleta;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public List<Producao> getProducao() {
        return producao;
    }

    public void setProducao(List<Producao> producao) {
        this.producao = producao;
    }

    public void adicionarProducao(Hemocomponente hemocomponente, int volumeMl) {
        this.producao.add(new Producao(this, hemocomponente, volumeMl));
    }

}
