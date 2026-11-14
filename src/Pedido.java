import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private LocalDate dataSolicitacao;
    private LocalDate dataEnvio;
    private String status;
    private Hospital hospital;
    private List<TipoSang> tipos;

    public Pedido() {
        numero = 0;
        dataSolicitacao = null;
        dataEnvio = null;
        status = "";
        hospital = null;
        tipos = new ArrayList<>();
    }

    public Pedido(int numero, LocalDate dataSolicitacao, LocalDate dataEnvio, String status, Hospital hospital, List<TipoSang> tipos) {
        this.numero = numero;
        this.dataSolicitacao = dataSolicitacao;
        this.dataEnvio = dataEnvio;
        this.status = status;
        this.hospital = hospital;
        this.tipos = tipos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<TipoSang> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoSang> tipos) {
        this.tipos = tipos;
    }

    public void adicionarTipo(TipoSang tipo) {
        tipos.add(tipo);
    }

}
