public class Doador extends Pessoa {
    private TipoSang tipoSanguineo;

    public Doador() {
        super();
        tipoSanguineo = null;
    }

    public Doador(int codigo, String nome, String cpf, String telefone, Endereco endereco, TipoSang tipoSanguineo) {
        super(codigo, nome, cpf, telefone, endereco);
        this.tipoSanguineo = tipoSanguineo;
    }

    public TipoSang getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSang tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

}
