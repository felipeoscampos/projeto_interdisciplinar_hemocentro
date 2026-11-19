public class Producao {
    private Analise analise;
    private Hemocomponente hemocomponente;
    private double volumeMl;

    public Producao() {
        this.analise = new Analise();
        this.hemocomponente = new Hemocomponente();
        this.volumeMl = 0.0;
    }

    public Producao(Analise analise, Hemocomponente hemocomponente, double volumeMl) {
        this.analise = (analise != null) ? analise : new Analise();
        this.hemocomponente = (hemocomponente != null) ? hemocomponente : new Hemocomponente();
        this.volumeMl = volumeMl;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = (analise != null) ? analise : new Analise();
    }

    public Hemocomponente getHemocomponente() {
        return hemocomponente;
    }

    public void setHemocomponente(Hemocomponente hemocomponente) {
        this.hemocomponente = (hemocomponente != null) ? hemocomponente : new Hemocomponente();
    }

    public double getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(double volumeMl) {
        this.volumeMl = volumeMl;
    }
}
