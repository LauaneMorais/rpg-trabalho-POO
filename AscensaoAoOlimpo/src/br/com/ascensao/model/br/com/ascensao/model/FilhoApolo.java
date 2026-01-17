package br.com.ascensao.model;

public class FilhoApolo extends SemiDeus {

    private static final int chanceCritico= 60;//variavel global imutavel
    private Double multiplicador;//dobra a chance.


    public FilhoApolo(){

    }

    public FilhoApolo(String nome) {
        super(nome, 70.0, 15.0, 4.0);
        this.multiplicador = 2.0;
    }

    @Override
    public void atacar(SemiDeus alvo) {
        Double danoTotal = this.getAtaqueBase()*this.getModificadorDano();//dano base
        boolean foiCritico = false;

        if(Dado.rolar(100)>chanceCritico){ //se o numero sorteado no dado for >= 60 (40 numeros chance)
            danoTotal *= this.multiplicador;
            foiCritico=true;
        }

        if(foiCritico){
            System.out.println("CRÍTICO! " + this.getNome() + " acertou uma flecha de luz em um ponto vital!");//efeito 
        }

        alvo.receberDano(danoTotal);

    
    }

    public static int getChancecritico() {
        return chanceCritico;
    }

    public Double getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(Double multiplicador) {
        this.multiplicador = multiplicador;
    }

    

}
