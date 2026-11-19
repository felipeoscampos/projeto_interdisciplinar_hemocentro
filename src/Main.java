import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Endereços
        Endereco endPessoa = new Endereco(1, "Av. Brasil", "1500", "15010000", "Sao Jose do Rio Preto", "SP");
        Endereco endHospital = new Endereco(2, "Av. Brigadeiro Faria Lima", "5416", "15090000", "Sao Jose do Rio Preto", "SP");

        // Tipos sanguineos (O+, A-)
        TipoSanguineo tipoOpos = TipoSanguineo.O_POSITIVO;
        TipoSanguineo tipoAneg = TipoSanguineo.A_NEGATIVO;

        // Hemocomponentes (CH, PFC, CRIO, CP)
        Hemocomponente ch = new Hemocomponente("CH", "Concentrado de Hemacias");
        Hemocomponente pfc = new Hemocomponente("PFC", "Plasma Fresco Congelado");
        Hemocomponente crio = new Hemocomponente("CRIO", "Crioprecipitado");
        Hemocomponente cp = new Hemocomponente("CP", "Concentrado de Plaquetas");

        // Pessoas (Doador, Funcionario e Tecnico)
        Doador doador1 = new Doador(1, "Carlos Silva", "12345678901", "17991112222", endPessoa, tipoOpos);
        Doador doador2 = new Doador(2, "Fernanda Costa", "23456789012", "17992223333", endPessoa, tipoOpos);
        Doador doador3 = new Doador(3, "Lucas Oliveira", "34567890123", "17993334444", endPessoa, tipoAneg);

        Funcionario funcionario = new Funcionario(2, "Mariana Souza", "98765432100", "17993334444", endPessoa, 4200.00);
        Tecnico tecnico = new Tecnico(3, "Roberto Lima", "45678912300", "17995556666", endPessoa, "12345");

        // Coletas no estoque
        Coleta coleta1 = new Coleta(101, LocalDate.of(2026, 11, 8), LocalDate.of(2026, 12, 8), StatusColeta.DISPONIVEL, doador1, funcionario, null);
        Coleta coleta2 = new Coleta(102, LocalDate.of(2026, 11, 8), LocalDate.of(2026, 12, 8), StatusColeta.DISPONIVEL, doador2, funcionario, null);
        Coleta coleta3 = new Coleta(103, LocalDate.of(2026, 11, 8), LocalDate.of(2026, 12, 8), StatusColeta.DISPONIVEL, doador3, funcionario, null);

        // Analise da coleta e producao de hemocomponentes
        Analise analise = new Analise(501, StatusAnalise.APROVADA, coleta1, tecnico);
        analise.adicionarProducao(ch, 240);
        analise.adicionarProducao(pfc, 180);
        analise.adicionarProducao(crio, 20);
        analise.adicionarProducao(cp, 60);

        System.out.println("=== 1. COLETA E ANALISE REGISTRADAS ===");
        System.out.println("Coleta #" + coleta1.getCodigo() + " | Status: " + coleta1.getStatus());
        System.out.println("Doador: " + coleta1.getDoador().getNome() + " (Tipo: " + coleta1.getDoador().getTipoSanguineo().getTipo() + ")");
        System.out.println("Responsavel pela coleta: " + coleta1.getFuncionario().getNome());
        System.out.println("Analise #" + analise.getCodigo() + " (" + analise.getStatus() + ") - Tecnico: "
                + analise.getTecnico().getNome() + " | COREN: " + analise.getTecnico().getCoren());
        System.out.println("Hemocomponentes produzidos:");
        for (Producao p : analise.getProducao()) {
            System.out.println("  - " + p.getHemocomponente().getSigla() + " (" + p.getHemocomponente().getNome()
                    + "): " + p.getVolumeMl() + " ml");
        }

        // Hospital e Pedido (com quantidade de bolsas)
        Hospital hospital = new Hospital(1, "Hospital de Base", "12345678000199", endHospital);
        Pedido pedido = new Pedido();
        pedido.setNumero(9001);
        pedido.setDataSolicitacao(LocalDate.of(2026, 11, 8));
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setHospital(hospital);

        // Hospital solicita 2 bolsas de O+ e 1 bolsa de A-
        pedido.adicionarItem(tipoOpos, 2);
        pedido.adicionarItem(tipoAneg, 1);

        System.out.println("\n=== 2. PEDIDO DO HOSPITAL COM QUANTIDADE DE BOLSAS ===");
        System.out.println("Pedido #" + pedido.getNumero() + " - " + pedido.getHospital().getRazaoSocial());
        System.out.println("Status Inicial: " + pedido.getStatus());
        System.out.println("Itens solicitados:");
        for (PedidoSangue item : pedido.getItens()) {
            System.out.println("  - Tipo: " + item.getTipoSanguineo().getTipo() 
                    + " | Solicitadas: " + item.getQuantidadeSolicitada()
                    + " | Atendidas: " + item.getQuantidadeAtendida());
        }

        // Baixa nas bolsas do pedido utilizando as coletas
        System.out.println("\n=== 3. REALIZANDO BAIXA NO PEDIDO ===");
        
        System.out.println("Dando baixa com Coleta #101 (O+)...");
        pedido.darBaixa(coleta1);
        System.out.println("  -> Status do Pedido: " + pedido.getStatus() + " | Status da Coleta #101: " + coleta1.getStatus());

        System.out.println("Dando baixa com Coleta #102 (O+)...");
        pedido.darBaixa(coleta2);
        System.out.println("  -> Status do Pedido: " + pedido.getStatus() + " | Status da Coleta #102: " + coleta2.getStatus());

        System.out.println("Dando baixa com Coleta #103 (A-)...");
        pedido.darBaixa(coleta3);
        System.out.println("  -> Status do Pedido: " + pedido.getStatus() + " | Status da Coleta #103: " + coleta3.getStatus());
        System.out.println("  -> Data de Envio: " + pedido.getDataEnvio());

        System.out.println("\n=== 4. RESUMO FINAL DOS ITENS DO PEDIDO ===");
        for (PedidoSangue item : pedido.getItens()) {
            System.out.println("  - Tipo: " + item.getTipoSanguineo().getTipo() 
                    + " | Solicitadas: " + item.getQuantidadeSolicitada()
                    + " | Atendidas: " + item.getQuantidadeAtendida()
                    + " | Atendido? " + (item.isAtendido() ? "SIM" : "NAO"));
        }
        System.out.println("Pedido Totalmente Atendido? " + (pedido.isTotalmenteAtendido() ? "SIM" : "NAO"));
    }
}
