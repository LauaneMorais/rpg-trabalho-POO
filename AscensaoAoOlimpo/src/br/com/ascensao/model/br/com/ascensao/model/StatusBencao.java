package br.com.ascensao.model;

public class StatusBencao {

    private boolean estaAtordoado; // buff dionisio
    private double modificadorDano; // buff hermes e afrodite
    private double modificadorDefesa; // buff athena
    private boolean temReflexo; // buff poseidon
    private boolean frutaSagrada; // buff deméter


    public StatusBencao(){//construtor inicializando o metodo resetar, que seta os atributos para o estado neutro.
        resetar();
    }

    public void resetar(){
        this.estaAtordoado=false;
        this.modificadorDano=1.0;
        this.modificadorDefesa=1.0;
        this.temReflexo = false;
        this.frutaSagrada = false;
    }

    public boolean isEstaAtordoado() {
        return estaAtordoado;
    }

    public void setEstaAtordoado(boolean estaAtordoado) {
        this.estaAtordoado = estaAtordoado;
    }

    public double getModificadorDano() {
        return modificadorDano;
    }

    public void setModificadorDano(double modificadorDano) {
        this.modificadorDano = modificadorDano;
    }

    public double getModificadorDefesa() {
        return modificadorDefesa;
    }

    public void setModificadorDefesa(double modificadorDefesa) {
        this.modificadorDefesa = modificadorDefesa;
    }

    public boolean isTemReflexo() {
        return temReflexo;
    }

    public void setTemReflexo(boolean temReflexo) {
        this.temReflexo = temReflexo;
    }

    public boolean isFrutaSagrada() {
        return frutaSagrada;
    }

    public void setFrutaSagrada(boolean frutaSagrada) {
        this.frutaSagrada = frutaSagrada;
    }

    
}
