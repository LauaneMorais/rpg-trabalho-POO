package br.com.ascensao.model;
import br.com.ascensao.util.*;

public class FilhoHefesto extends SemiDeus{

    private double vigor;// particularidade dos filhos de hefesto
    private double custoVigorBloqueio = 5.0;
    private static final int chanceBloqueio = 20;
    private int chance;
    
    public FilhoHefesto() {
    }
    
    public FilhoHefesto(String nome) {
        super(nome, 120.0, 5.0, 15.0);
        this.vigor = 50.0;
    }

    @Override
    public void atacar(SemiDeus alvo) {
        
    double danoTotal= this.getAtaqueBase()*this.getModificadorDano();//se tiver buff de hermes/afrodite
    alvo.receberDano(danoTotal);
    }


    @Override
    public void receberDano(double dano) { //adicionei uma particularidade do personagem ao receber dano
        chance = Dado.rolar(100);
        if(this.vigor >= custoVigorBloqueio && chance <=chanceBloqueio){
        this.vigor -= custoVigorBloqueio;
        super.receberDano(0.0);

        System.out.println("Esta com sorte, vc tirou : "+chance +" e bloqueou completamente o ataque");
        
    }else{ super.receberDano(dano);}

    }


    public double getVigor() {
        return vigor;
    }

    public void setVigor(double vigor) {
        this.vigor = vigor;
    }

    public double getCustoVigorBloqueio() {
        return custoVigorBloqueio;
    }

    public void setCustoVigorBloqueio(double custoVigorBloqueio) {
        this.custoVigorBloqueio = custoVigorBloqueio;
    }

    public static int getChancebloqueio() {
        return chanceBloqueio;
    }

    public int getChance() {
        return chance;
    }
    
    
    

    
}
