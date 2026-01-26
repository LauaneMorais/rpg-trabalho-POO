package br.com.ascensao.model;
//CODIGO GERADO POR INTELIGENCIA ARTIFICIAL PARA OTIMIZAR TEMPO DE DESENVOLVIMENTO. CLASSE TEMPORARIA PARA TESTAR MÉTODOS E LÓGICAS
import br.com.ascensao.controller.*;

public class MainTest {
//CRIAR OUTRO CODIGO PARA TESTAR.
    public static void main(String[] args) {

        // 1. Cria a base de dados (Model)
        Equipes arena = new Equipes();

        // 2. Cria o controlador do jogo (Controller)
        BatalhaController jogo = new BatalhaController(arena);

        // 3. Roda o sistema
        jogo.iniciarCombate();
    }
        
}


//ANOTAÇÕES TESTES
/*
obs: testes sorteados com dado de 5 faces.

-Dando erro quando teoricamente equipe B deveria vencer.
    investigar




/*System.out.println("=== INICIANDO O GRANDE TORNEIO (TESTE SIMPLES) ===");

        // 1. Criar a Arena
        Equipes arena = new Equipes();

        // 2. Formar os times (Isso vai imprimir "TORNEIO COMEÇA" e o tamanho da batalha)
        arena.formarEquipes();

        // 3. Iniciar o pau-a-pau (Loop de combate)
        // Se este método rodar até o fim e mostrar o VENCEDOR, seu código está funcionando.
        arena.iniciarCombate();
        
        System.out.println("=== FIM DA SIMULAÇÃO ===");
    } */




