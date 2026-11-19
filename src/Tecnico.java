public class Tecnico extends Pessoa {
    private String coren;

    public Tecnico() {
        super();
        this.coren = "";
    }

    public Tecnico(int codigo, String nome, String cpf, String telefone, Endereco endereco, String coren) {
        super(codigo, nome, cpf, telefone, endereco);
        this.coren = coren;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }
}
