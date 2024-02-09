# API-BoardCamp

<details open="open">
  <summary><h2 style="display: inline-block">📜 Sumário</h2></summary>

- [Sobre o projeto](#sobre-o-projeto)
- [Usando](#usando)
- [Rotas](#rotas)


</details>

## 📋 Sobre o projeto
### tecnologias e ferramentas

![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Java](https://img.shields.io/badge/java-%yellow.svg?style=for-the-badge&logo=Java&logoColor=%23F7DF1E)
![SpringBoot](https://img.shields.io/badge/springboot-green.svg?style=for-the-badge&logo=springboot&logoColor=white)
![PostegresSql](https://img.shields.io/badge/postegresql-%23007ACC.svg?style=for-the-badge&logo=postegresql&logoColor=white)


### Idealização do projeto
- Esse projeto foi pensado para ser uma Api de uma loja de aluguel de jogos de tabuleiro.

## 🏁 Usando

Link do deploy para testar em nuvem

```bash
https://api-boardcamp-java.onrender.com

```

Clone o repositorio

```bash
$ git clone https://github.com/igorhnovais/API-DevioFood

```

<a name="usando"></a>

## 🏁 Rotas

### -> games
1. Rota para adicionar um novo jogo:
    
    Route post: ```"/games"``` 

    Desrição: nela você consegue adicionar um novo jogo

    Status:
    ```bash
        201
    ```
    Entrada:
    ```bash
        {
            name: "Jogo da vida",
            image: "http://www.imagem.com.br/jogo_da_vida.jpg",
            stockTotal: 3,
            pricePerDay: 1500
        }
    ```
    
    Saída:
    ```bash
        [
            {
                id: 2,
                name: "Jogo da vida",
                image: "http://www.imagem.com.br/jogo_da_vida.jpg",
                stockTotal: 3,
                pricePerDay: 1500
            }
        ]
    ```

2. Rota para buscar todos os jogos da loja:
    
    Route get: ```"/games"``` 

    Desrição: nela você consegue visualizar todos os jogos existentes na loja. 

    Status:
    ```bash
        200
    ```
    
    Saída:
    ```bash
        [
            {
                id: 1,
                name: "Banco Imobiliario",
                image: "http://www.imagem.com.br/jogo_da_vida.jpg",
                stockTotal: 3,
                pricePerDay: 1500
            },
            {
                id: 2,
                name: "Jogo da vida",
                image: "http://www.imagem.com.br/jogo_da_vida.jpg",
                stockTotal: 3,
                pricePerDay: 1500
            }
        ]
    ```

### -> customers

1. Rota para adicionar um novo cliente:
    
    Route post: ```"/customers"``` 

    Desrição: nela você consegue adicionar um novo cliente

    Status:
    ```bash
        201
    ```
    Entrada:
    ```bash
        {
            name: "bruna",
            cpf: "0123456737"
        }
    ```
    
    Saída:
    ```bash
        {
            id: 1,
            name: "Bruna",
            cpf: "01234567890"
        }
    ```

2. Rota para buscar um cliente por id:
    
    Route get: ```"/customers/{id}"``` 

    Desrição: nela você consegue buscar por um cliente especifico atraves da url

    Status:
    ```bash
        200
    ```
    Entrada:
    ```bash
        https://api-boardcamp-java.onrender.com/customers/2
    ```
    
    Saída:
    ```bash
        {
            id: 2,
            name: "bruna",
            cpf: "01234567327"
        }
    ```

### -> rentals

1. Rota para adicionar um novo aluguel:
    
    Route post: ```"/rentals"``` 

    Desrição: nela você consegue registrar um novo aluguel, passando o id do usuario, id do jogo e a quantidade de dias que serão alugados.

    Status:
    ```bash
        201
    ```
    Entrada:
    ```bash
        {
            customerId: 1,
            gameId: 2,
            daysRented: 2
        }
    ```
    
    Saída:
    ```bash
        {
            id: 1,
            rentDate: '2021-06-20',
            daysRented: 3,
            returnDate: null, 
            originalPrice: 4500,
            delayFee: 0, 
            customer: {
            id: 1,
            name: 'Bruna',
                cpf: '01234567890'
            },
            game: {
            id: 2,
                name: 'Banco Imobiliário',
                image: 'http://www.imagem.com.br/banco.jpg',
                stockTotal: 3,
                pricePerDay: 1500
            }
        }
    ```

2. Rota para buscar todos os alugueis:
    
    Route get: ```"/rentals"``` 

    Desrição: nela você consegue buscar todos os alugueis existentes

    Status:
    ```bash
        200
    ```
    
    Saída:
    ```bash
        [
            {
                id: 1,
                rentDate: "08-02-2024",
                daysRented: 2,
                returnDate: null,
                originalPrice: 3000,
                delayFree: 0,
                customer: {
                id: 1,
                name: "Igor",
                cpf: "01234567323"
                },
                game: {
                id: 2,
                name: "Jogo da vida",
                image: "http://www.imagem.com.br/jogo_da_vida.jpg",
                stockTotal: 2,
                pricePerDay: 1500
                }
            }
        ]
    ```

3. Rota para atualizar um aluguel:
    
    Route put: ```"/rentals/{id}/return"``` 

    Desrição: nela você consegue buscar todos os alugueis existentes

    Status:
    ```bash
        200
    ```

    Entrada:
    ```bash
        https://api-boardcamp-java.onrender.com/rentals/1/return
    ```
    
    Saída:
    ```bash
        {
            id: 1,
            rentDate: '2021-06-20',
            daysRented: 3,
            returnDate: '2021-06-25', 
            originalPrice: 4500,
            delayFee: 3000, 
            customer: {
            id: 1,
            name: 'Bruna',
                cpf: '01234567890'
            },
            game: {
            id: 1,
                name: 'Banco Imobiliário',
                image: 'http://www.imagem.com.br/banco.jpg',
                stockTotal: 3,
                pricePerDay: 1500
            }
        }
    ```
