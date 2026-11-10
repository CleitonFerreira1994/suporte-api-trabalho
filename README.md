# suporte-api-trabalho
# API de Gerenciamento de Chamados de Suporte

**Disciplina:** Linguagens para Aplicação Comercial
**Aluno:** Cleiton Ferreira de Jesus

## Estrutura e Arquitetura do Projeto
O projeto foi desenvolvido seguindo o padrão arquitetural MVC (Model-View-Controller) adaptado para APIs RESTful. A estrutura de pacotes separa as responsabilidades de forma coesa, garantindo a organização do código-fonte:
- **`model`**: Contém a classe de domínio principal (`Chamado`) e as enumerações de regras de negócio (`Status` e `Prioridade`).
- **`controller`**: Contém o `ChamadoController`, responsável por expor os endpoints HTTP, isolando o roteamento da aplicação.

## Análise Técnica

**1. Como o Spring Boot recebe e processa requisições HTTP:**
O Spring Boot inicializa um servidor web embutido (neste caso, o Apache Tomcat). Quando uma requisição HTTP chega pela rede, ela é interceptada pelo `DispatcherServlet`, que funciona como o *Front Controller* da aplicação. Este componente central analisa a rota da URL e o método HTTP (GET, POST, etc.) e encaminha a requisição para o método específico mapeado no Controller.

**2. Qual é a função do Controller na aplicação:**
A camada Controller atua como a interface de comunicação da API. Sua função é receber as requisições vindas dos clientes (frontend, Postman, REST Client), extrair parâmetros das rotas ou corpo da mensagem (utilizando anotações como `@PathVariable`, `@RequestParam` ou `@RequestBody`), executar o processamento e devolver os dados solicitados junto com o código de Status HTTP adequado (ex: 200 OK ou 201 Created).

**3. Como ocorre a conversão entre objetos Java e JSON:**
Essa conversão ocorre de maneira bidirecional e automática através da biblioteca **Jackson**, nativa do ecossistema `spring-boot-starter-web`. Quando a requisição envia um JSON (em um POST, por exemplo), o Jackson realiza a **desserialização**, instanciando automaticamente um objeto Java a partir das chaves do JSON. Quando o Controller retorna um objeto ou uma `List` no Java, ocorre a **serialização**, convertendo a estrutura de objetos para o formato de texto JSON enviado ao cliente.

**4. Por que os chamados cadastrados não permanecem armazenados após reiniciar a aplicação:**
O armazenamento foi implementado utilizando uma estrutura de dados em memória, especificamente uma `ArrayList`. Essa lista é mantida apenas na memória RAM alocada para a Máquina Virtual Java (JVM) durante o tempo de execução do projeto. Quando o servidor é encerrado ou reiniciado, o ciclo de vida da aplicação termina, a memória RAM é limpa pelo sistema operacional e todos os objetos inseridos perdem-se. Para obter a persistência definitiva dos dados entre reinicializações, a aplicação precisaria ser integrada a um SGBD (Sistema de Gerenciamento de Banco de Dados) físico utilizando frameworks de persistência como o Spring Data JPA.
