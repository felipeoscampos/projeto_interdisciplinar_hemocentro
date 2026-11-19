import java.util.ArrayList;
import java.util.List;

public class Analise {
    private int codigo;
    private StatusAnalise status;
    private Coleta coleta;
    private Tecnico tecnico;
    private List<Producao> producao;

    public Analise() {
        this.codigo = 0;
        this.status = StatusAnalise.EM_ANALISE;
        this.coleta = new Coleta();
        this.tecnico = new Tecnico();
        this.producao = new ArrayList<>();
    }

    public Analise(int codigo, StatusAnalise status, Coleta coleta, Tecnico tecnico) {
        this.codigo = codigo;
        this.status = status;
        this.coleta = (coleta != null) ? coleta : new Coleta();
        this.tecnico = (tecnico != null) ? tecnico : new Tecnico();
        this.producao = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public StatusAnalise getStatus() {
        return status;
    }

    public void setStatus(StatusAnalise status) {
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

    public void adicionarProducao(Hemocomponente hemocomponente, double volumeMl) {
        this.producao.add(new Producao(this, hemocomponente, volumeMl));
    }
}
