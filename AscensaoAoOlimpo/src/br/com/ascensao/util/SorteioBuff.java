package br.com.ascensao.util;

import br.com.ascensao.model.*;
import java.util.ArrayList;
import java.util.List;

public class SorteioBuff {
    private static List<EfeitoDivino> listaBuffs = new ArrayList<>();

    static{
    listaBuffs.add(new BuffDionisio());
    listaBuffs.add(new BuffHermes());
    listaBuffs.add(new BuffAfrodite());
    listaBuffs.add(new BuffAthena());
    listaBuffs.add(new BuffPoseidon());
    listaBuffs.add(new BuffDemeter());
    }

    //MUDEI O METODO PRA CONSEGUIR RECEBER O NOME DO DEUS QUE FOI SORTEADOOOO, no mudei nada mais (lau s2)

    public static String aplicarBuffAleatorio(SemiDeus alvo) {//modificador statico para nn precisar criar objeto.
        System.out.println("buff concedido para " + alvo.getNome() + "!"); // facilitar visualmente na fase de testes
        int buffSorteado = Dado.rolar(listaBuffs.size())-1;

        EfeitoDivino efeitoSorteado = listaBuffs.get(buffSorteado);

        String nomeCompleto = efeitoSorteado.getClass().getSimpleName();
        String nomeDeus = nomeCompleto.replace("Buff", "");
    
    
        // chamamos o método aplicarBuff que agora retorna uma string explicativa, essa mensagem será enviada direto para o log (larissa)
        String mensagem = efeitoSorteado.aplicarBuff(alvo);
        
        return mensagem + " (Buff de " + nomeDeus + ")";
    }

  


}
