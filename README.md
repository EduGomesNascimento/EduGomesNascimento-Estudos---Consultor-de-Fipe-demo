# Consultor de FIPE (estudo)

Projeto de estudo em Java + Spring Boot: uma aplicação de linha de comando que
consulta a tabela FIPE por API e lista marcas, modelos e valores de veículos.

## Tecnologias

- Java 17
- Spring Boot 3.2 (`CommandLineRunner`, sem servidor web)
- Jackson / Gson para converter o JSON da API

## Estrutura

| Caminho (em `demo/src/main/java/Consultor/Fipe/com/`) | Função |
|---|---|
| `ConsultorFipeApplication.java` | Ponto de entrada; abre o menu |
| `Main/Principal.java` | Menu interativo no terminal |
| `Services/APImanager.java` | Chamadas HTTP à API |
| `Services/ConverteDados.java` | Conversão de JSON para objetos |
| `Models/` | Records dos dados retornados |

## Rodar

```bash
cd demo
./mvnw spring-boot:run      # Windows: mvnw.cmd spring-boot:run
```
