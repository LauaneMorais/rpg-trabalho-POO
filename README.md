# ⚔️ Ascensão ao Olimpo - RPG de turnos

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing-blue?style=for-the-badge)
![UFS](https://img.shields.io/badge/UFS-COMP0395-red?style=for-the-badge)

> Este projeto foi desenvolvido como requisito avaliativo para a disciplina de **Programação Orientada a Objetos (POO)**, no Departamento de Computação da Universidade Federal de Sergipe (UFS). 
---

## 📖 Lore do jogo

No auge da disputa pelo trono do Olimpo, os descendentes dos deuses testam seu sangue e poder em uma arena eterna. O torneio segue regras rígidas de combate em turnos, onde apenas a estratégia e o domínio das habilidades divinas garantem a vitória.

---

## 🎮 Game design

Conforme a especificação do projeto, cada combatente possui características únicas que influenciam o fluxo das rodadas:

| Classe | Divindade | Atributo Único | Descrição da Mecânica |
| --- | --- | --- | --- |
| **Guerreiro** | **Ares** | `taxaRouboVida` | Recupera 25% do dano real causado ao oponente como HP. |
| **Tanque** | **Hefesto** | `vigor` | Possui 20% de chance de bloquear totalmente um ataque consumindo vigor. |
| **Atirador** | **Apolo** | `chanceCritico` | Tem 40% de chance de disparar uma flecha de luz que causa dano dobrado. |
| **Mago** | **Hécate** | `mana` | Alterna entre feitiços de alto dano e golpes físicos para recuperar energia. |
---
### Condições de Vitória
* A batalha ocorre em rodadas automáticas.
* Vence a equipe que eliminar todos os combatentes adversários primeiro.
---

### Modos de Visualização
* **🖥️ Interface Gráfica:** O jogo conta com uma GUI completa feita em **Swing**, exibindo as cartas dos personagens, barras de vida coloridas e logs de batalha em tempo real.
* **📜 Log de Combate:** Um painel de texto descreve narrativamente cada ação (ataques, bloqueios, críticos e mortes) conforme elas ocorrem.

---

## 🛠 Defesa técnica 

Para atender aos critérios de avaliação da disciplina, a arquitetura do software foca nos quatro pilares fundamentais:

### 1. Herança e abstração

Utilizamos a classe abstrata `SemiDeus` (no arquivo `SemiDeus.java`) como base para todos os personagens. Ela define atributos comuns (vida, ataque, defesa) e o contrato `public abstract void atacar(SemiDeus alvo)`, garantindo que cada classe filha implemente sua própria lógica de combate.

### 2. Polimorfismo

O polimorfismo é o coração do sistema de combate. Através da **Sobrescrita de Métodos** (`@Override`), o método `atacar()` executa comportamentos distintos dependendo do objeto em tempo de execução. Além disso, a classe `Equipes` gerencia coleções de `ArrayList<SemiDeus>`, tratando diferentes subclasses de forma genérica.

### 3. Encapsulamento

Todos os atributos da superclasse e subclasses são `private`. O controle de estado dos personagens é feito exclusivamente através de métodos **Getters e Setters**, garantindo a integridade dos dados (ex: impedindo que a vida fique negativa ou que o vigor ultrapasse o limite).

### 4. Tratamento de coleções e aleatoriedade

O projeto utiliza `ArrayList` para gerenciar as equipes de tamanho dinâmico e a classe utilitária `Dado` para simular a aleatoriedade dos ataques e alvos.

---
## 👥 Equipe de desenvolvimento

Projeto desenvolvido pelas discentes:

* [Larissa Cena](https://github.com/laristcena)
* [Lauane Morais](https://github.com/LauaneMorais)
* [Luiza Accioly](https://github.com/Acciolylu)
* [Maciele Ramos](https://github.com/macin-tx)

---
*Desenvolvido para a disciplina de Programação Orientada a Objetos (COMP0395) - UFS.*
