package br.com.ascensao.model;


public abstract class SemiDeus {

    private String nome;
    private double pontosvida;
    private double pontosvidaMax;
    private double ataqueBase;
    private double defesaBase;
    private boolean estaAtordoado;    //buff dionisio
    private double modificadorDano;   //buff hermes/afrodite
    private boolean temReflexo;      //buff poseidon
    //falta adicionar demais buffs


    public SemiDeus() {
    }


    public SemiDeus(String nome, double pontosvida, double ataqueBase, double defesaBase) {
        this.nome = nome;
        this.pontosvida = pontosvida;
        this.pontosvidaMax = pontosvida; 
        this.ataqueBase = ataqueBase;
        this.defesaBase = defesaBase;
        this.estaAtordoado = false;
        this.modificadorDano = 1.0;
        this.temReflexo = false;
    }


    public abstract void atacar(SemiDeus alvo);//mettodo abstrato para ser sobreescritos nas subclasses

    public void receberDano(double dano) {
        double danoFinal = dano;

        if (this.temReflexo) {// dano se tiver a benção de poseidon
            danoFinal = dano / 2;
            System.out.println(this.nome + " recebeu a benção de Poseidon! Dano reduzido pela metade.");//- mostrar em tela?
        }

        if (this.defesaBase > 0) {//dano total = dano menos a defesa base do inimigo--funcionando
            danoFinal -= this.defesaBase;
            if (danoFinal < 0) {//tratar dano negativo.
                danoFinal = 0;
            }
        }
    
        this.pontosvida -= danoFinal;//atualiza pontos de vida
        
        if (this.pontosvida < 0) {// tratamento para vida nn ficar negativa
            this.pontosvida = 0.0;
        }

        System.out.printf( "%s recebeu %.1f  de dano. Vida restante: %.1f ",this.nome, danoFinal, this.pontosvida);//mostrar em algum lugar tudo isso?
    }

    public void curar(double valor) {
        this.pontosvida += valor;
        if (this.pontosvida > this.pontosvidaMax) {//tratamento para nn passar da vida maxima.--funcionando.
            this.pontosvida = this.pontosvidaMax;
        }
        System.out.println("\n "+this.nome + " recuperou " + valor + " de vida.");
    }


    //retornar estado inicial do turno
    public void resetarEstadoTurno() {
        this.estaAtordoado = false;
        this.modificadorDano = 1.0;
        this.temReflexo = false;
    }
    
    
    public boolean estaVivo() {
        return this.pontosvida > 0;
    }

    //alguns setters não são necessários, mas vou tirar depois.
    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public double getPontosvida() {
        return pontosvida;
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


    public boolean isTemReflexo() {
        return temReflexo;
    }


    public void setTemReflexo(boolean temReflexo) {
        this.temReflexo = temReflexo;
    }



}
