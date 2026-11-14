public class Hospital {
    private int codigo;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;

    public Hospital() {
        codigo = 0;
        razaoSocial = "";
        cnpj = "";
        endereco = null;
    }

    public Hospital(int codigo, String razaoSocial, String cnpj, Endereco endereco) {
        this.codigo = codigo;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
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
