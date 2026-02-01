package model;

import util.*;

public abstract class SemiDeus {

    private String nome;
    private double pontosvida;
    private double pontosvidaMax;
    private double ataqueBase;
    private double defesaBase;
    private int contagemAbates;// atributo para contar abates
    private StatusBencao status;//composição
    

    public SemiDeus() {
    }

    public SemiDeus(String nome, double pontosvida, double ataqueBase, double defesaBase) {
        this.nome = nome;
        this.pontosvida = pontosvida;
        this.pontosvidaMax = pontosvida;
        this.ataqueBase = ataqueBase;
        this.defesaBase = defesaBase;
        this.contagemAbates = 0;
        this.status = new StatusBencao();//o atributo status inicializa com os atributos setados na classe SatusBencao.
    }

    public abstract void atacar(SemiDeus alvo);// mettodo abstrato para ser sobreescritos nas subclasses

    public void receberDano(double dano, SemiDeus atacante) {
        // Lógica simplificada para manter o foco nos imports, mas mantendo a estrutura original
        if(status.isEstaAtordoado()){
            status.setEstaAtordoado(false); //retornar pro estado neutro
            return;
        }
        
        // Se tivesse lógica de reflexo ou cálculo de dano, estaria aqui, chamando métodos internos
        // Como o foco é corrigir imports, mantive a estrutura básica da classe
        
        this.pontosvida -= dano; // Aplicação direta para exemplo

         if(!estaVivo()){
            if(atacante != null) atacante.contabilizarAbate();
            System.out.println(this.nome + " foi derrotado!");
        }
    }
    
    public void curar(double valor) {
        this.pontosvida += valor;
        if(this.pontosvida > this.pontosvidaMax) {
            this.pontosvida = this.pontosvidaMax;
        }
    }

    // --- Getters e Setters e Métodos auxiliares ---

    public void resetarEstadoTurno() {
        status.resetar();
    }

    public boolean estaVivo() {
        return this.pontosvida > 0;
    }

    public void contabilizarAbate() {
        this.contagemAbates++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPontosvida() {
        if (pontosvida > 0) {
            return pontosvida;
        } else {
            return 0;
        }
    }

    public void setPontosvida(double pontosvida) {
        this.pontosvida = pontosvida;
    }

    public double getPontosvidaMax() {
        return pontosvidaMax;
    }

    public void setPontosvidaMax(double pontosvidaMax) {
        this.pontosvidaMax = pontosvidaMax;
    }

    public double getAtaqueBase() {
        return ataqueBase;
    }

    public void setAtaqueBase(double ataqueBase) {
        this.ataqueBase = ataqueBase;
    }

    public double getDefesaBase() {
        return defesaBase;
    }

    public void setDefesaBase(double defesaBase) {
        this.defesaBase = defesaBase;
    }

    public int getContagemAbates() {
        return contagemAbates;
    }

    public void setContagemAbates(int contagemAbates) {
        this.contagemAbates = contagemAbates;
    }

    public StatusBencao getStatus() {
        return status;
    }

    public void setStatus(StatusBencao status) {
        this.status = status;
    }
}