public class Doador extends Pessoa {
    private TipoSanguineo tipoSanguineo;

    public Doador() {
        super();
        this.tipoSanguineo = TipoSanguineo.NAO_INFORMADO;
    }

    public Doador(int codigo, String nome, String cpf, String telefone, Endereco endereco, TipoSanguineo tipoSanguineo) {
        super(codigo, nome, cpf, telefone, endereco);
        this.tipoSanguineo = (tipoSanguineo != null) ? tipoSanguineo : TipoSanguineo.NAO_INFORMADO;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }
}
