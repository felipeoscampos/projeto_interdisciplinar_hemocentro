public class Producao {
    private Analise analise;
    private Hemocomponente hemocomponente;
    private int volumeMl;

    public Producao() {
        analise = null;
        hemocomponente = null;
        volumeMl = 0;
    }

    public Producao(Analise analise, Hemocomponente hemocomponente, int volumeMl) {
        this.analise = analise;
        this.hemocomponente = hemocomponente;
        this.volumeMl = volumeMl;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public Hemocomponente getHemocomponente() {
        return hemocomponente;
    }

    public void setHemocomponente(Hemocomponente hemocomponente) {
        this.hemocomponente = hemocomponente;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(int volumeMl) {
        this.volumeMl = volumeMl;
    }

}
