# DailyMovie

Aplicação para sugestão de filmes.

### Como começar

#### Gradle
Gradle é a ferramenta de build e controle de dependências do projeto. <br />
É necessário a instalação do Gradle.
Todas dependências podem ser encontradas no arquivo build.gradle na raíz do projeto.

* [Documentação oficial do Gradle](https://docs.gradle.org)

#### Spring Boot
Spring boot é o framework utilizado no projeto para um rápido desenvolvimento. <br />
O framework é importado como dependência do projeto através do Gradle.

* [Guia de referência do Plugin do Gradle para Spring Boot](https://docs.spring.io/spring-boot/docs/2.4.0/gradle-plugin/reference/html/)

#### IDE
Após instalação do Gradle, o projeto pode ser importado em uma IDE de escolha - como IntelliJ -.

### Arquitetura

#### Banco de Dados
Como banco de dados é utilizado H2, com suporte próprio para arquivos. <br />
Não é necessário instalação do H2. <br />
Para acessar a interface gráfica do banco de dados, basta iniciar a aplicação e acessar http://localhost:8080/h2 em qualquer navegador. <br />
As configurações de acesso estão no arquivo application.properties no diretório resources. <br />
As tabelas são criadas na primeira vez que a aplicação é iniciada, os scripts estão no diretório resources nos arquivos schema.sql e data.sql.

![Console do H2](/h2-console.png?raw=true) <br />

* [Documentação do H2 DB](https://www.h2database.com/html/features.html)

#### Backend

Para persistência de dados no banco de dados, é utilizado JPA. <br />
As interfaces de persistência estão no diretório repository. <br />
As entidades utilizadas pelo JPA para mapeamento de colunas e tabelas estão no diretório model. <br />

##### Thread
Para evitar espera longa do usuário durante o processamento de uma sugestão,
é aberta uma thread para salvar a mesma na tabela de histórico.

* [Tutorial Spring Data & H2](http://fullstackninja.com.br/h2-database-com-spring-data/) <br />

No diretório service estão as classes que fazem a abstração entre a interface gráfica e regras de negócio,
comunicação com os repositórios, requisições para APIs externas, entre outros.

##### OMDB API

A aplicação utiliza a API OMDB para requisitar informações sobre os títulos de sugestão.

* [Documentação da API OMDB](http://www.omdbapi.com/)

##### HTTP Client

Retrofit é utilizado para realizar requisições à APIs externas. <br />
A configuração se encontra no diretório configuration. <br />
As chamadas à APIs externas estão localizadas no diretório client.

* [Retrofit - Exemplos e Documentação](https://square.github.io/retrofit/)

#### Frontend

Para interface gráfica foi utilizado Swing. <br />
Toda a criação da interface está no arquivo DailyMovieApplication. <br />
O usuário possui duas abas, na primeira é onde é feita a sugestão do filme, e na
segunda é fornecido o histórico de sugestões.