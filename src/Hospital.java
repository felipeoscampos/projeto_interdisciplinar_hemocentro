public class Hospital {
    private int codigo;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;

    public Hospital() {
        this.codigo = 0;
        this.razaoSocial = "";
        this.cnpj = "";
        this.endereco = new Endereco();
    }

    public Hospital(int codigo, String razaoSocial, String cnpj, Endereco endereco) {
        this.codigo = codigo;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = (endereco != null) ? endereco : new Endereco();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
