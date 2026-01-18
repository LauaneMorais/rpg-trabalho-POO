package br.com.ascensao.model;

public class MainTest {//classe para debug
    //INTELIGENCIA ARTIFICIAL USADA PARA GERAÇÃO DESSE CÓDIGO TESTE PARA TESTAR OS MÉTODOS E AÇÕES

    public static void main(String[] args) {
        System.out.println("=== INICIANDO BATERIA DE TESTES DO PROJETO ASCENSÃO ===\n");

        // 1. Instanciação dos Objetos
        // Criando um de cada para ver se os construtores funcionam
        FilhoApolo apolo = new FilhoApolo("Solaris");
        FilhoAres ares = new FilhoAres("Kratos");
        FilhoHecate hecate = new FilhoHecate("Morgana");
        FilhoHefesto hefesto = new FilhoHefesto("Vulcano");

        // 2. Imprimir Características (Ficha Técnica)
        System.out.println("--- 1. VERIFICAÇÃO DE STATUS INICIAIS ---");
        imprimirFicha(apolo);
        imprimirFicha(ares);
        imprimirFicha(hecate);
        imprimirFicha(hefesto);
        separador(); //INSTANCIAÇÃO E CARACTERISTICAS OKK

       /*// 3. Teste: Mecânica de Ares (Roubo de Vida)-- FUNCIONANDO
        System.out.println("--- 2. TESTE: FILHO DE ARES (ROUBO DE VIDA) ---");
        // Vamos tirar um pouco de vida do Ares primeiro para ver se ele cura
        ares.setPontosvida(50.0); 
        System.out.println("Vida inicial de " + ares.getNome() + ": " + ares.getPontosvida());
     
        ares.atacar(hefesto); 
        
        System.out.printf("\nVida de %s após ataque (esperado aumento): %.1f%n", ares.getNome(), ares.getPontosvida());
        separador(); */ 
        
        /*//4. Teste: Mecânica de Hefesto (Tanque/Vigor)--FUNCIONANDO
        System.out.println("--- 3. TESTE: FILHO DE HEFESTO (BLOQUEIO DE VIGOR) ---");
        System.out.println("Vigor atual de " + hefesto.getNome() + ": " + hefesto.getVigor());
        
        // Ataque 1: Hefesto tem vigor (deve bloquear o dano completamente e perder vigor)
        System.out.println(">> Ataque 1: " + apolo.getNome() + " ataca " + hefesto.getNome());
        apolo.atacar(hefesto);
        System.out.println("Vigor restante: " + hefesto.getVigor());
        System.out.println("Vida de " + hefesto.getNome() +  hefesto.getPontosvida());

        //Ataque 2: Vamos zerar o vigor para forçar ele a tomar dano
        hefesto.setVigor(0.0);
        System.out.println("\n>> Hack: Zerando vigor de " + hefesto.getNome() + " para teste...");
        System.out.println(">> Ataque 2: " + apolo.getNome() + " ataca " + hefesto.getNome() + " (Sem Vigor)");
        apolo.atacar(hefesto);
        separador();*/

        // 5. Teste: Mecânica de Hecate (Mana vs Físico)
        System.out.println("--- 4. TESTE: FILHO DE HECATE (GESTÃO DE MANA) ---");
        
        /*// Cenário A: Tem mana suficiente -> Usa Magia
        System.out.println("Mana atual: " + hecate.getMana());
        System.out.println(">> Cenário A: Ataque Mágico (Gasta Mana)");
        hecate.atacar(ares);
        System.out.println("Mana após magia: " + hecate.getMana());*/

        // Cenário B: Sem mana -> Ataque Físico e Recupera Mana
        hecate.setMana(5.0); // Forçando mana baixa (custo do feitiço é 10)
        System.out.println("\n>> Hack: Drenando mana de " + hecate.getNome() + " para 5.0...");
        System.out.println(">> Cenário B: Tentativa de ataque sem mana (Deve bater fraco e recuperar)");
        hecate.atacar(ares);
        System.out.println("Mana após ataque físico (Recuperou?): " + hecate.getMana());
        separador();

        /* // 6. Teste: Morte
        System.out.println("--- 5. TESTE DE MORTE ---");
        System.out.println("Vida atual de " + apolo.getNome() + ": " + apolo.getPontosvida());
        System.out.println("Aplicando 9999 de dano em " + apolo.getNome() + "...");
        apolo.receberDano(9999.0);
        System.out.println("Está vivo? " + apolo.estaVivo());
        
        System.out.println("\n=== FIM DOS TESTES ===");*/
    }

    // Método auxiliar para imprimir a ficha formatada
    public static void imprimirFicha(SemiDeus s) {
        System.out.println("CLASSE: " + s.getClass().getSimpleName());
        System.out.println("Nome: " + s.getNome());
        System.out.printf("HP: %.1f / %.1f\n", s.getPontosvida(), s.getPontosvidaMax());
        System.out.println("Ataque Base: " + s.getAtaqueBase());
        System.out.println("Defesa Base: " + s.getDefesaBase());
        
        // Verificações específicas para imprimir atributos únicos
        if(s instanceof FilhoApolo) {
            System.out.println("Específico: Chance Crítico (" + FilhoApolo.getChancecritico() + "%)");
        } else if (s instanceof FilhoHecate) {
            System.out.println("Específico: Mana (" + ((FilhoHecate) s).getMana() + ")");
        } else if (s instanceof FilhoHefesto) {
            System.out.println("Específico: Vigor (" + ((FilhoHefesto) s).getVigor() + ")");
        } else if (s instanceof FilhoAres) {
            System.out.println("Específico: Roubo de Vida (" + (((FilhoAres) s).getTaxaRouboVida()*100) + "%)");
        }
        System.out.println("--------------------------------");
    }

    public static void separador() {
        System.out.println("\n==================================================\n");
    }
}