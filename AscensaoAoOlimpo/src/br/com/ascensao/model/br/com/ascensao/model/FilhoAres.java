package br.com.ascensao.model;

public class FilhoAres extends SemiDeus {

    private Double taxaRouboVida;

    public FilhoAres(){

    }

    

    public FilhoAres(String nome) {
        super(nome, 110.0, 20.0, 2.0);
        this.taxaRouboVida = 0.25;
    }

    @Override
    public void atacar(SemiDeus alvo) {
    Double danoFinal = this.getAtaqueBase()*this.getModificadorDano();//dano no inimigo
    alvo.receberDano(danoFinal);

    Double valorCura = danoFinal*taxaRouboVida;//habilidade única, quando causa dano se cura.
    this.curar(valorCura);
    }


    public Double getTaxaRouboVida() {
        return taxaRouboVida;
    }



    public void setTaxaRouboVida(Double taxaRouboVida) {
        this.taxaRouboVida = taxaRouboVida;
    }
    
    

}
