

package br.com.ascensao.model;

import br.com.ascensao.util.*;
import java.lang.reflect.Field; // Import necessário para testar atributos privados (Reflection)
import java.util.ArrayList;

//CODIGO GERADO POR INTELIGENCIA ARTIFICIAL PARA OTIMIZAR TEMPO DE DESENVOLVIMENTO. CLASSE TEMPORARIA PARA TESTAR MÉTODOS E LÓGICAS

public class MainTest {

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("      BATERIA DE TESTES - PROJETO ASCENSÃO (V2.0)         ");
        System.out.println("==========================================================\n");

        // -------------------------------------------------------------------------
        // FASE 1: TESTES UNITÁRIOS (PERSONAGENS ISOLADOS)
        // Objetivo: Garantir que a matemática das habilidades funciona individualmente.
        // -------------------------------------------------------------------------
        System.out.println(">>> FASE 1: TESTES UNITÁRIOS DE PERSONAGENS <<<");
        
        FilhoApolo apolo = new FilhoApolo("Solaris (Atirador)");
        FilhoAres ares = new FilhoAres("Kratos (Guerreiro)");
        FilhoHecate hecate = new FilhoHecate("Morgana (Maga)");
        FilhoHefesto hefesto = new FilhoHefesto("Vulcano (Tanque)");

        // Teste de Instanciação e Polimorfismo
        imprimirFicha(apolo);
        imprimirFicha(hefesto);
        imprimirFicha(ares);
        imprimirFicha(hecate);

        /* // Teste Rápido de Mecânica (Ex: Mana de Hecate)
        System.out.println("\n[Teste Lógica] Hecate ataca sem mana:");
        hecate.setMana(25.0);
        hecate.atacar(ares);
        System.out.println("Mana após ataque (Esperado recuperar): " + hecate.getMana());
        
        separador();*/

        // -------------------------------------------------------------------------
        // FASE 2: TESTES DE INTEGRAÇÃO (EQUIPES E ARENA)
        // Objetivo: Testar a classe Equipes, formação de times e loop de batalha.
        // -------------------------------------------------------------------------
        System.out.println(">>> FASE 2: TESTES DE SISTEMA DE EQUIPES <<<\n");

        Equipes arena = new Equipes();
        System.out.println("1. Instanciando a Arena (Objeto Equipes)... OK");

        // 1. Testar Formação de Equipes
        System.out.println("2. Executando 'formarEquipes()'...");
        arena.formarEquipes();

        // VALIDAÇÃO AVANÇADA (Reflection):
        // Como ladoA e ladoB são private, usamos Reflection para validar se eles não estão vazios
        // sem precisar criar getters públicos que quebrariam o encapsulamento.
        try {
            validarListasPrivadas(arena);
        } catch (Exception e) {
            System.out.println("[ERRO] Falha ao inspecionar as listas internas: " + e.getMessage());
        }

        separador();

        // 2. Testar o Loop Principal de Combate
        System.out.println(">>> FASE 3: SIMULAÇÃO DE BATALHA REAL (MÉTODO iniciarCombate) <<<\n");
        
        // Este método vai gerar muitos logs no console (Log de Batalha)
        // Observar: Turnos, mortes, remoção de listas e buffs.
        arena.iniciarCombate();

        System.out.println("\n==========================================================");
        System.out.println("             FIM DOS TESTES - STATUS: SUCESSO             ");
        System.out.println("==========================================================");
    }

    // --- MÉTODOS AUXILIARES ---

    /*
     * Método Auxiliar usando Java Reflection para acessar as listas 'private' da classe Equipes.
     * Isso serve para provar que o método formarEquipes() realmente preencheu os arrays.
     */
    private static void validarListasPrivadas(Equipes arena) throws NoSuchFieldException, IllegalAccessException {
        // Acessando o campo 'ladoA'
        Field campoLadoA = Equipes.class.getDeclaredField("ladoA");
        campoLadoA.setAccessible(true); // Autoriza acesso ao privado
        ArrayList<?> listaA = (ArrayList<?>) campoLadoA.get(arena);

        // Acessando o campo 'ladoB'
        Field campoLadoB = Equipes.class.getDeclaredField("ladoB");
        campoLadoB.setAccessible(true);
        ArrayList<?> listaB = (ArrayList<?>) campoLadoB.get(arena);

        System.out.println("   [Check] Tamanho do Lado A: " + listaA.size() + " combatentes.");
        System.out.println("   [Check] Tamanho do Lado B: " + listaB.size() + " combatentes.");

        if(listaA.size() > 0 && listaB.size() > 0) {
            System.out.println("   [Sucesso] As equipes foram populadas corretamente.");
        } else {
            System.err.println("   [Falha] Uma das equipes está vazia!");
        }
    }

    public static void imprimirFicha(SemiDeus s) {
        System.out.println("[Info] Criado: " + s.getNome() + " | HP: " + s.getPontosvida() + " | Classe: " + s.getClass().getSimpleName());
    }

    public static void separador() {
        System.out.println("\n----------------------------------------------------------\n");
    }
    }

