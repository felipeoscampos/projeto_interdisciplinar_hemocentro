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
        this.codigo = 0;
        this.dataColeta = LocalDate.now();
        this.dataValidade = LocalDate.now().plusDays(35);
        this.status = StatusColeta.DISPONIVEL;
        this.doador = new Doador();
        this.funcionario = new Funcionario();
        this.pedido = new Pedido();
    }

    public Coleta(int codigo, LocalDate dataColeta, LocalDate dataValidade, StatusColeta status, Doador doador, Funcionario funcionario, Pedido pedido) {
        this.codigo = codigo;
        this.dataColeta = dataColeta;
        this.dataValidade = dataValidade;
        this.status = status;
        this.doador = (doador != null) ? doador : new Doador();
        this.funcionario = (funcionario != null) ? funcionario : new Funcionario();
        this.pedido = (pedido != null) ? pedido : new Pedido();
    }

    /**
     * Verifica se a coleta já foi vinculada a um pedido real de hospital (código > 0).
     */
    public boolean temPedido() {
        return this.pedido != null && this.pedido.getNumero() > 0;
    }

    public TipoSanguineo getTipoSanguineo() {
        return (doador != null) ? doador.getTipoSanguineo() : TipoSanguineo.NAO_INFORMADO;
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
        this.doador = (doador != null) ? doador : new Doador();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = (funcionario != null) ? funcionario : new Funcionario();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = (pedido != null) ? pedido : new Pedido();
    }
}
