package br.com.ascensao.model;

public class FilhoHecate extends SemiDeus{

    private Double mana;//atributo particular 
    private Double custoManaFeitico = 10.0;
    private Double danoFetico;
    private Double recuperacaoMana;

        public FilhoHecate(){

        }
    
        public FilhoHecate(String nome) {
        super(nome, 80.0, 5.0, 3.0);
        this.mana = 50.0;
        this.danoFetico = 20.0;
        this.recuperacaoMana = 15.0;
    }


    @Override
    public void atacar(SemiDeus alvo) {
        if(this.mana >= custoManaFeitico){
            realizarFeitico(alvo);
        }else{
            realizarGolpeFisico(alvo);
        }
    }
//ataque de maior dano
    public void realizarFeitico(SemiDeus alvo){
        this.mana -= custoManaFeitico;
        double danoFinal = danoFetico*this.getModificadorDano();
        alvo.receberDano(danoFinal);
    }
//ataque de menor dano + revitalização da mana
    public void realizarGolpeFisico(SemiDeus alvo){
        Double danoFraco = this.getAtaqueBase()*this.getModificadorDano();
        this.mana +=recuperacaoMana;

        if(this.mana >50.0){ //garantir que não passe da mana maxima.
            this.mana =50.0;
        }

        alvo.receberDano(danoFraco);
    }

    public Double getMana() {
        return mana;
    }

    public void setMana(Double mana) {
        this.mana = mana;
    }

    public Double getCustoManaFeitico() {
        return custoManaFeitico;
    }

    public void setCustoManaFeitico(Double custoManaFeitico) {
        this.custoManaFeitico = custoManaFeitico;
    }

    public Double getDanoFetico() {
        return danoFetico;
    }

    public void setDanoFetico(Double danoFetico) {
        this.danoFetico = danoFetico;
    }

    public Double getRecuperacaoMana() {
        return recuperacaoMana;
    }

    public void setRecuperacaoMana(Double recuperacaoMana) {
        this.recuperacaoMana = recuperacaoMana;
    }


    

}
