# Diagrama de Classes — Hemocentro (Clean Code & Null-Safety)

```mermaid
classDiagram
    direction TB

    class Endereco {
        -int codigo
        -String logradouro
        -String numero
        -String cep
        -String cidade
        -String uf
        +Endereco()
        +Endereco(int, String, String, String, String, String)
        +getCodigo() int
        +setCodigo(int) void
        +getLogradouro() String
        +setLogradouro(String) void
        +getNumero() String
        +setNumero(String) void
        +getCep() String
        +setCep(String) void
        +getCidade() String
        +setCidade(String) void
        +getUf() String
        +setUf(String) void
    }

    class Pessoa {
        -int codigo
        -String nome
        -String cpf
        -String telefone
        -Endereco endereco
        +Pessoa()
        +Pessoa(int, String, String, String, Endereco)
        +getCodigo() int
        +setCodigo(int) void
        +getNome() String
        +setNome(String) void
        +getCpf() String
        +setCpf(String) void
        +getTelefone() String
        +setTelefone(String) void
        +getEndereco() Endereco
        +setEndereco(Endereco) void
    }

    class Doador {
        -TipoSanguineo tipoSanguineo
        +Doador()
        +Doador(int, String, String, String, Endereco, TipoSanguineo)
        +getTipoSanguineo() TipoSanguineo
        +setTipoSanguineo(TipoSanguineo) void
    }

    class Funcionario {
        -double salario
        +Funcionario()
        +Funcionario(int, String, String, String, Endereco, double)
        +getSalario() double
        +setSalario(double) void
    }

    class Tecnico {
        -String coren
        +Tecnico()
        +Tecnico(int, String, String, String, Endereco, String)
        +getCoren() String
        +setCoren(String) void
    }

    class TipoSanguineo {
        <<enumeration>>
        A_POSITIVO
        A_NEGATIVO
        B_POSITIVO
        B_NEGATIVO
        AB_POSITIVO
        AB_NEGATIVO
        O_POSITIVO
        O_NEGATIVO
        NAO_INFORMADO
        -String tipo
        +getTipo() String
    }

    class Hemocomponente {
        -String sigla
        -String nome
        +Hemocomponente()
        +Hemocomponente(String, String)
        +getSigla() String
        +setSigla(String) void
        +getNome() String
        +setNome(String) void
    }

    class StatusColeta {
        <<enumeration>>
        DISPONIVEL
        DESCARTADA
        ENVIADA
    }

    class StatusPedido {
        <<enumeration>>
        PENDENTE
        EM_ATENDIMENTO
        ATENDIDO
        CANCELADO
    }

    class StatusAnalise {
        <<enumeration>>
        EM_ANALISE
        APROVADA
        REPROVADA
    }

    class Hospital {
        -int codigo
        -String razaoSocial
        -String cnpj
        -Endereco endereco
        +Hospital()
        +Hospital(int, String, String, Endereco)
        +getCodigo() int
        +setCodigo(int) void
        +getRazaoSocial() String
        +setRazaoSocial(String) void
        +getCnpj() String
        +setCnpj(String) void
        +getEndereco() Endereco
        +setEndereco(Endereco) void
    }

    class PedidoSangue {
        -TipoSanguineo tipoSanguineo
        -int quantidadeSolicitada
        -int quantidadeAtendida
        +PedidoSangue()
        +PedidoSangue(TipoSanguineo, int, int)
        +getTipoSanguineo() TipoSanguineo
        +setTipoSanguineo(TipoSanguineo) void
        +getQuantidadeSolicitada() int
        +setQuantidadeSolicitada(int) void
        +getQuantidadeAtendida() int
        +setQuantidadeAtendida(int) void
        +getQuantidadeRestante() int
        +isAtendido() boolean
        +adicionarBaixa() void
    }

    class Pedido {
        -int numero
        -LocalDate dataSolicitacao
        -LocalDate dataEnvio
        -StatusPedido status
        -Hospital hospital
        -List~PedidoSangue~ itens
        +Pedido()
        +Pedido(int, LocalDate, LocalDate, StatusPedido, Hospital, List~PedidoSangue~)
        +adicionarItem(TipoSanguineo, int) void
        +darBaixa(Coleta) boolean
        +isTotalmenteAtendido() boolean
        +getNumero() int
        +setNumero(int) void
        +getDataSolicitacao() LocalDate
        +setDataSolicitacao(LocalDate) void
        +getDataEnvio() LocalDate
        +setDataEnvio(LocalDate) void
        +getStatus() StatusPedido
        +setStatus(StatusPedido) void
        +getHospital() Hospital
        +setHospital(Hospital) void
        +getItens() List~PedidoSangue~
        +setItens(List~PedidoSangue~) void
    }

    class Coleta {
        -int codigo
        -LocalDate dataColeta
        -LocalDate dataValidade
        -StatusColeta status
        -Doador doador
        -Funcionario funcionario
        -Pedido pedido
        +Coleta()
        +Coleta(int, LocalDate, LocalDate, StatusColeta, Doador, Funcionario, Pedido)
        +temPedido() boolean
        +getTipoSanguineo() TipoSanguineo
        +getCodigo() int
        +setCodigo(int) void
        +getStatus() StatusColeta
        +setStatus(StatusColeta) void
    }

    class Analise {
        -int codigo
        -StatusAnalise status
        -Coleta coleta
        -Tecnico tecnico
        -List~Producao~ producao
        +Analise()
        +Analise(int, StatusAnalise, Coleta, Tecnico)
        +isAprovada() boolean
        +getCodigo() int
        +setCodigo(int) void
        +getStatus() StatusAnalise
        +setStatus(StatusAnalise) void
        +adicionarProducao(Hemocomponente, double) void
    }

    class Producao {
        -Analise analise
        -Hemocomponente hemocomponente
        -double volumeMl
        +Producao()
        +Producao(Analise, Hemocomponente, double)
        +getAnalise() Analise
        +setAnalise(Analise) void
        +getHemocomponente() Hemocomponente
        +setHemocomponente(Hemocomponente) void
        +getVolumeMl() double
        +setVolumeMl(double) void
    }

    %% Heranca
    Pessoa <|-- Doador
    Pessoa <|-- Funcionario
    Pessoa <|-- Tecnico

    %% Relacionamentos
    Pessoa "*" --> "1" Endereco : Reside
    Hospital "*" --> "1" Endereco : Pertence
    Doador "*" --> "1" TipoSanguineo : Possui
    Coleta "*" --> "1" Doador : Realiza
    Coleta "*" --> "1" Funcionario : Coleta
    Coleta "*" --> "0..1" Pedido : Gera / Vinculada
    Coleta --> StatusColeta
    Analise "1" --> "1" Coleta : Possui
    Analise "*" --> "1" Tecnico : Faz
    Analise --> StatusAnalise
    Analise "1" *-- "*" Producao : Produz
    Producao "*" --> "1" Hemocomponente : Extrai
    Pedido "*" --> "1" Hospital : Realiza
    Pedido --> StatusPedido
    Pedido "1" *-- "1..*" PedidoSangue : Contém
    PedidoSangue "*" --> "1" TipoSanguineo : Requisita
```
