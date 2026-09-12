# 🃏 BlackJack - Sistema de Cassino em Java

Projeto desenvolvido em **Java** com o objetivo de aplicar conceitos de **Programação Orientada a Objetos (POO)** através da implementação de um sistema baseado no jogo BlackJack (21).

O sistema possui gerenciamento de usuários, jogadores, partidas, apostas, carteira, histórico e persistência de dados.

## 🎯 Objetivo

O projeto tem como objetivo simular um sistema de BlackJack utilizando uma arquitetura organizada em diferentes camadas, separando as responsabilidades da aplicação.

Além da lógica do jogo, o sistema implementa funcionalidades como autenticação de usuários, controle de saldo, apostas, histórico de partidas e persistência de dados.

## ⚙️ Funcionalidades

- Cadastro e gerenciamento de usuários
- Sistema de login
- Jogadores e administradores
- Gerenciamento de carteira e saldo
- Sistema de apostas
- Criação e gerenciamento de mesas
- Sistema de partidas de BlackJack
- Baralho e cartas
- Histórico de partidas
- Ranking de jogadores
- Persistência de dados em JSON
- Tratamento de exceções
- Menu interativo pelo terminal

## 🧠 Conceitos utilizados

Durante o desenvolvimento foram aplicados conceitos de Programação Orientada a Objetos, como:

- Classes e objetos
- Encapsulamento
- Herança
- Polimorfismo
- Abstração
- Interfaces
- Enumerações
- Tratamento de exceções
- Collections
- Separação de responsabilidades
- Persistência de dados

## 📁 Estrutura do projeto

```text
BlackJack2/
│
├── controller/
│   ├── AdminController.java
│   ├── ApostaController.java
│   ├── BaralhoController.java
│   ├── CarteiraController.java
│   ├── HistoricoController.java
│   ├── JogadorController.java
│   ├── LoginController.java
│   ├── MesaController.java
│   ├── PartidaController.java
│   └── UsuarioController.java
│
├── data/
│   ├── partidas.json
│   ├── ranking.json
│   └── usuarios.json
│
├── enums/
├── exception/
├── interfaces/
├── model/
├── persistence/
├── repository/
├── service/
├── util/
├── view/
│
├── Main.java
└── README.md