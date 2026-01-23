package br.com.ascensao.model;
//CODIGO GERADO POR INTELIGENCIA ARTIFICIAL PARA OTIMIZAR TEMPO DE DESENVOLVIMENTO. CLASSE TEMPORARIA PARA TESTAR MÉTODOS E LÓGICAS
import br.com.ascensao.controller.*;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO O GRANDE TORNEIO (TESTE SIMPLES) ===");

        // 1. Criar a Arena
        Equipes arena = new Equipes();

        // 2. Formar os times (Isso vai imprimir "TORNEIO COMEÇA" e o tamanho da batalha)
        arena.formarEquipes();

        // 3. Iniciar o pau-a-pau (Loop de combate)
        // Se este método rodar até o fim e mostrar o VENCEDOR, seu código está funcionando.
        arena.iniciarCombate();
        
        System.out.println("=== FIM DA SIMULAÇÃO ===");
    }
}

//ANOTAÇÕES TESTES
/*
obs: testes sorteados com dado de 5 faces.

-Dando erro quando teoricamente equipe B deveria vencer.
    investigar























*/

