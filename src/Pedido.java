import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private LocalDate dataSolicitacao;
    private LocalDate dataEnvio;
    private StatusPedido status;
    private Hospital hospital;
    private List<PedidoSangue> itens;

    public Pedido() {
        this.numero = 0;
        this.dataSolicitacao = LocalDate.now();
        this.dataEnvio = null;
        this.status = StatusPedido.PENDENTE;
        this.hospital = new Hospital();
        this.itens = new ArrayList<>();
    }

    public Pedido(int numero, LocalDate dataSolicitacao, LocalDate dataEnvio, StatusPedido status, Hospital hospital, List<PedidoSangue> itens) {
        this.numero = numero;
        this.dataSolicitacao = dataSolicitacao;
        this.dataEnvio = dataEnvio;
        this.status = status;
        this.hospital = (hospital != null) ? hospital : new Hospital();
        this.itens = (itens != null) ? itens : new ArrayList<>();
    }

    public void adicionarItem(TipoSanguineo tipoSanguineo, int quantidadeBolsas) {
        for (PedidoSangue item : this.itens) {
            if (item.getTipoSanguineo() == tipoSanguineo) {
                item.setQuantidadeSolicitada(item.getQuantidadeSolicitada() + quantidadeBolsas);
                return;
            }
        }
        this.itens.add(new PedidoSangue(tipoSanguineo, quantidadeBolsas, 0));
    }

    public boolean darBaixa(Coleta coleta) {
        if (coleta == null) return false;

        TipoSanguineo tipo = coleta.getTipoSanguineo();
        for (PedidoSangue item : this.itens) {
            if (item.getTipoSanguineo() == tipo && !item.isAtendido()) {
                item.adicionarBaixa();
                coleta.setPedido(this);
                coleta.setStatus(StatusColeta.ENVIADA);

                if (isTotalmenteAtendido()) {
                    this.status = StatusPedido.ATENDIDO;
                    this.dataEnvio = LocalDate.now();
                } else {
                    this.status = StatusPedido.EM_ATENDIMENTO;
                }
                return true;
            }
        }
        return false;
    }

    public boolean isTotalmenteAtendido() {
        if (itens.isEmpty()) return false;
        for (PedidoSangue item : itens) {
            if (!item.isAtendido()) {
                return false;
            }
        }
        return true;
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

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = (hospital != null) ? hospital : new Hospital();
    }

    public List<PedidoSangue> getItens() {
        return itens;
    }

    public void setItens(List<PedidoSangue> itens) {
        this.itens = (itens != null) ? itens : new ArrayList<>();
    }
}
