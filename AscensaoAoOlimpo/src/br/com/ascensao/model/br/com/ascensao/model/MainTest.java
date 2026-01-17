package br.com.ascensao.model;
public class MainTest {//classe para debug
    //INTELIGENCIA ARTIFICIAL USADA PARA GERAÇÃO DESSE CÓDIGO TESTE PARA TESTAR OS MÉTODOS E AÇÕES.

    public static void main(String[] args) {

        System.out.println("-> Criando os combatentes...");
        FilhoHefesto tanque = new FilhoHefesto("Vulcano");
        FilhoHecate mago = new FilhoHecate("Morgana");
        FilhoApolo arqueiro = new FilhoApolo("Legolas");
        FilhoAres guerreiro = new FilhoAres("Kratos");

        // Imprime status iniciais para conferência
        System.out.println("Guerreiros criados com sucesso!");
        System.out.println("--------------------------------------------------\n");


        // --------------------------------------------------------------------
        // TESTE 2: LÓGICA DO TANQUE (FILHO DE HEFESTO)
        // Regra: Se tiver Vigor, bloqueia o dano totalmente.
        // --------------------------------------------------------------------
        System.out.println("-TESTE 1: TANQUE (BLOQUEIO) ---");
        System.out.println("Tanque Vida Inicial: " + tanque.getPontosvida() + " | Vigor: " + tanque.getVigor());
        
        System.out.println(">> Ataque de 50 de dano no Tanque...");
        tanque.receberDano(50.0); // Deve gastar vigor e NÃO perder vida
        
        System.out.println("Tanque Vida Final: " + tanque.getPontosvida() + " | Vigor: " + tanque.getVigor());
        System.out.println("(Esperado: Vida 120.0 e Vigor menor que 50)");
        System.out.println("--------------------------------------------------\n");


        // --------------------------------------------------------------------
        // TESTE 3: LÓGICA DO MAGO (FILHO DE HÉCATE)
        // Regra: Gasta Mana para dar muito dano. Se Mana < custo, medita.
        // --------------------------------------------------------------------
        System.out.println("TESTE 2: MAGO (MANA E ATAQUE) ---");
        System.out.println("Mago Mana Inicial: " + mago.getMana());
        
        System.out.println(">> Mago ataca Tanque (Feitiço)...");
        mago.atacar(tanque); // Deve gastar mana
        
        System.out.println("Mago Mana Final: " + mago.getMana());
        System.out.println("(Esperado: Mana reduzida em 10)");
        System.out.println("--------------------------------------------------\n");


        // --------------------------------------------------------------------
        // TESTE 4: LÓGICA DO GUERREIRO (FILHO DE ARES)
        // Regra: Roubo de Vida (Lifesteal). Recupera vida ao atacar.
        // --------------------------------------------------------------------
        System.out.println("--- TESTE 3: GUERREIRO (LIFESTEAL) ---");
        
        // 1. Tiramos vida do guerreiro primeiro para ele ter o que curar
        guerreiro.receberDano(50.0); 
        System.out.println("Vida do Kratos após apanhar: " + guerreiro.getPontosvida());
        
        // 2. Ele ataca o Arqueiro
        System.out.println(">> Kratos ataca Legolas...");
        guerreiro.atacar(arqueiro);
        
        System.out.println("Vida do Kratos após atacar: " + guerreiro.getPontosvida());
        System.out.println("(Esperado: A vida deve ter subido um pouco devido ao roubo de vida)");
        System.out.println("--------------------------------------------------\n");


        // --------------------------------------------------------------------
        // TESTE 5: LÓGICA DO ARQUEIRO (FILHO DE APOLO)
        // Regra: Chance de Crítico (Aleatório).
        // --------------------------------------------------------------------
        System.out.println("TESTE 4: ARQUEIRO (CRÍTICO) ---");
        System.out.println(">> Disparando 5 flechas para testar a sorte...");
        
        for(int i = 1; i <= 5; i++) {
            System.out.print("Tiro " + i + ": ");
            arqueiro.atacar(mago); // Observe o console para ver se sai "CRÍTICO" ou "comum"
        }
        System.out.println("--------------------------------------------------\n");
        
        System.out.println("=== FIM DOS TESTES ===");
    }
}
