# ⚔️ Ascensão ao Olimpo - RPG de turnos

> **Este projeto foi desenvolvido como requisito avaliativo para a disciplina de Programação Orientada a Objetos (POO).** O objetivo é aplicar conceitos avançados de arquitetura de software em um cenário prático de Game Design.

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

## 🚀 Como rodar o projeto

Para compilar e executar o projeto, deve-se seguir os passos abaixo:

### Pré-requisitos

* JDK 17 ou superior instalado.
* Os arquivos de imagem devem estar na pasta `/br/com/ascensao/assets/`.

### Passo a Passo

1. **Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/ascensao-ao-olimpo.git
cd ascensao-ao-olimpo

```


2. **Compile o projeto:**
*(Certifique-se de estar na raiz da pasta `src`)*
```bash
javac br/com/ascensao/view/MenuPrincipal.java

```


3. **Execute o jogo:**
```bash
java br.com.ascensao.view.MenuPrincipal

```
