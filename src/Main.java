import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Enderecos e tabelas de apoio
        Endereco endPessoa = new Endereco(1, "Av. Brasil", "1500", "15010000", "Sao Jose do Rio Preto", "SP");
        Endereco endHospital = new Endereco(2, "Av. Brigadeiro Faria Lima", "5416", "15090000", "Sao Jose do Rio Preto",
                "SP");

        TipoSang tipoOpos = new TipoSang("O+");
        TipoSang tipoAneg = new TipoSang("A-");

        Hemocomponente ch = new Hemocomponente("CH", "Concentrado de Hemacias");
        Hemocomponente pfc = new Hemocomponente("PFC", "Plasma Fresco Congelado");
        Hemocomponente crio = new Hemocomponente("CRIO", "Crioprecipitado");
        Hemocomponente cp = new Hemocomponente("CP", "Concentrado de Plaquetas");

        // 2. Pessoas (Doador, Funcionario e Tecnico)
        Doador doador = new Doador(1, "Carlos Silva", "12345678901", "17991112222", endPessoa, tipoOpos);
        Funcionario funcionario = new Funcionario(2, "Mariana Souza", "98765432100", "17993334444", endPessoa, 4200.00);
        Tecnico tecnico = new Tecnico(3, "Roberto Lima", "45678912300", "17995556666", endPessoa, "12345");

        // 3. Coleta (inicia como DISPONIVEL e sem pedido)
        Coleta coleta = new Coleta(
                101,
                LocalDate.of(2026, 11, 8),
                LocalDate.of(2026, 12, 8),
                StatusColeta.DISPONIVEL,
                doador,
                funcionario,
                null);

        // 4. Analise da coleta e producao de hemocomponentes
        Analise analise = new Analise(501, "Aprovada", coleta, tecnico);
        analise.adicionarProducao(ch, 240);
        analise.adicionarProducao(pfc, 180);
        analise.adicionarProducao(crio, 20);
        analise.adicionarProducao(cp, 60);

        System.out.println("=== 1. COLETA E ANALISE REGISTRADAS ===");
        System.out.println("Coleta #" + coleta.getCodigo() + " | Status: " + coleta.getStatus());
        System.out.println("Doador: " + coleta.getDoador().getNome() + " (Tipo: "
                + coleta.getDoador().getTipoSanguineo().getTipo() + ")");
        System.out.println("Responsavel pela coleta: " + coleta.getFuncionario().getNome());
        System.out.println("Analise #" + analise.getCodigo() + " (" + analise.getStatus() + ") - Tecnico: "
                + analise.getTecnico().getNome()
                + " | COREN: " + analise.getTecnico().getCoren());
        System.out.println("Hemocomponentes produzidos:");
        for (Producao p : analise.getProducao()) {
            System.out.println("  - " + p.getHemocomponente().getSigla() + " (" + p.getHemocomponente().getNome()
                    + "): " + p.getVolumeMl() + " ml");
        }

        // 5. Hospital e Pedido
        Hospital hospital = new Hospital(1, "Hospital de Base", "12345678000199", endHospital);
        Pedido pedido = new Pedido();
        pedido.setNumero(9001);
        pedido.setDataSolicitacao(LocalDate.of(2026, 11, 8));
        pedido.setStatus("Em atendimento");
        pedido.setHospital(hospital);
        pedido.adicionarTipo(tipoOpos);
        pedido.adicionarTipo(tipoAneg);

        // 6. Despacho da bolsa para o pedido
        pedido.setDataEnvio(LocalDate.of(2026, 11, 9));
        pedido.setStatus("Enviado");
        coleta.setPedido(pedido);
        coleta.setStatus(StatusColeta.ENVIADA);

        System.out.println("\n=== 2. PEDIDO E ENVIO DA BOLSA ===");
        System.out.println("Pedido #" + pedido.getNumero() + " - " + pedido.getHospital().getRazaoSocial());
        System.out.print("Tipos solicitados: ");
        for (TipoSang t : pedido.getTipos()) {
            System.out.print(t.getTipo() + " ");
        }
        System.out.println("\nData de envio: " + pedido.getDataEnvio() + " | Status do pedido: " + pedido.getStatus());
        System.out.println("Coleta #" + coleta.getCodigo() + " agora esta com status: " + coleta.getStatus()
                + " (vinculada ao Pedido #" + coleta.getPedido().getNumero() + ")");
    }
}
