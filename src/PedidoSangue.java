public class PedidoSangue {
    private TipoSanguineo tipoSanguineo;
    private int quantidadeSolicitada;
    private int quantidadeAtendida;

    public PedidoSangue() {
        this.tipoSanguineo = TipoSanguineo.NAO_INFORMADO;
        this.quantidadeSolicitada = 0;
        this.quantidadeAtendida = 0;
    }

    public PedidoSangue(TipoSanguineo tipoSanguineo, int quantidadeSolicitada, int quantidadeAtendida) {
        this.tipoSanguineo = (tipoSanguineo != null) ? tipoSanguineo : TipoSanguineo.NAO_INFORMADO;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.quantidadeAtendida = quantidadeAtendida;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public int getQuantidadeAtendida() {
        return quantidadeAtendida;
    }

    public void setQuantidadeAtendida(int quantidadeAtendida) {
        this.quantidadeAtendida = quantidadeAtendida;
    }

    public int getQuantidadeRestante() {
        return Math.max(0, quantidadeSolicitada - quantidadeAtendida);
    }

    public boolean isAtendido() {
        return quantidadeAtendida >= quantidadeSolicitada;
    }

    public void adicionarBaixa() {
        if (!isAtendido()) {
            this.quantidadeAtendida++;
        }
    }
}
