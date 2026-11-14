import java.time.LocalDate;

public class Coleta {
    private int codigo;
    private LocalDate dataColeta;
    private LocalDate dataValidade;
    private StatusColeta status;
    private Doador doador;
    private Funcionario funcionario;
    private Pedido pedido;

    public Coleta() {
        codigo = 0;
        dataColeta = null;
        dataValidade = null;
        status = StatusColeta.DISPONIVEL;
        doador = null;
        funcionario = null;
        pedido = null;
    }

    public Coleta(int codigo, LocalDate dataColeta, LocalDate dataValidade, StatusColeta status, Doador doador, Funcionario funcionario, Pedido pedido) {
        this.codigo = codigo;
        this.dataColeta = dataColeta;
        this.dataValidade = dataValidade;
        this.status = status;
        this.doador = doador;
        this.funcionario = funcionario;
        this.pedido = pedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public StatusColeta getStatus() {
        return status;
    }

    public void setStatus(StatusColeta status) {
        this.status = status;
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
