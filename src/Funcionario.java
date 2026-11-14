public class Funcionario extends Pessoa {
    private double salario;

    public Funcionario() {
        super();
        salario = 0;
    }

    public Funcionario(int codigo, String nome, String cpf, String telefone, Endereco endereco, double salario) {
        super(codigo, nome, cpf, telefone, endereco);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
