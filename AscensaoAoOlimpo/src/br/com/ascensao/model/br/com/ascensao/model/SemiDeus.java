package br.com.ascensao.model;

import br.com.ascensao.util.*;

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
        if(status.isEstaAtordoado()){
            status.setEstaAtordoado(false); //retornar pro estado neutro
            return;
        }
        
        if(status.isTemReflexo()){
            aplicarReflexoEscudo(dano,atacante);
            return;
        }
        
        double danoFinal = calcularDanoFinal(dano);
        aplicarDano(danoFinal);
        

         if(!estaVivo()){
            contabilizarMorte(atacante);
            System.out.println(this.nome + " MORREU");
         }
        }

        private void aplicarReflexoEscudo(double dano, SemiDeus atacante){
            double danoRefletido = dano/2;
            status.setTemReflexo(false);//volta para o estado neutro após uso

            if(atacante != null){
                System.out.println("O golpe voltou contra"+ atacante.getNome()+" !");
                atacante.receberDano(danoRefletido, null); //null para evitar loop
            }
        }

        private double calcularDanoFinal(double dano){
            double defesaTotal = this.defesaBase*status.getModificadorDefesa();
            double danoFinal = dano-defesaTotal;

            if(danoFinal<0){
                return danoFinal = 0.0; 
            }

            return danoFinal;

        }

        private void aplicarDano(double dano){
            this.pontosvida -= dano;

            if(this.pontosvida<0){
                this.pontosvida=0.0;
            }

            System.out.printf("%s recebeu %.1f  de dano. Vida restante: %.1f \n", this.nome, dano, this.pontosvida);
        }

        private void contabilizarMorte(SemiDeus atacante){
            if(atacante!=null){
                atacante.contabilizarAbate();;
                verificarKills(atacante);
            }
        }

        private void verificarKills(SemiDeus atacante){
            if(atacante.getContagemAbates()>0 && atacante.getContagemAbates()%5==0){
                System.out.println(atacante.getNome()+" eliminou "+ atacante.getContagemAbates()+" inimigos!");
            

            if(ChanceBuff.Chance()){
                SorteioBuff.aplicarBuffAleatorio(atacante);
            }else{
                System.out.println("Você não foi considerado digno de bençãos pelos deuses");
            }
        }
    }

        public void curar(double valor) {
            if (status.isFrutaSagrada()) {// buff de deméter no mecanismo de cura
            this.pontosvida = this.pontosvida * 1.5;
        }

        this.pontosvida += valor;

        if (this.pontosvida > this.pontosvidaMax) {// tratamento para nn passar da vida maxima.--funcionando.
            this.pontosvida = this.pontosvidaMax;
        }
        System.out.println(this.nome + " recuperou " + valor + " de vida.");
    }


    // retornar estado inicial do turno
    public void resetarEstadoTurno() {
        status.resetar();
      
    }

    public boolean estaVivo() {
        return this.pontosvida > 0;
    }

    public void contabilizarAbate() {// metodo para contar abates.
        this.contagemAbates++;
    }

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

}// alguns setters não são necessários, mas vou tirar depois.

    
    