# MyAutoBlogChallenge (Java)

### Requisitos de sistema
* JDK 11;
* Maven;
* IDE ou editor de texto de preferência;
* Postman ou ferramenta de preferência para interação com APIs;
* Conta Github.

### Descrição do desafio 
Neste desafio serão avaliados os conhecimentos do candidato no campo do desenvolvimento de sistemas Web, código limpo e otimização. Destacando-se o conhecimento das seguintes tecnologias:

- Java;
- Spring Boot;
- API RESTFul;
- JWT;
- Conexão e manipulação de banco de dados com Spring Data JPA.

O objetivo desta tarefa é a manutenção de um sistema que armazena usuários e suas respectivas postagens, para tal, serão requeridas tarefas em uma api RESTful em Spring Boot. 

A persistência dos dados é feita com uma base H2 (memória). Para ver os dados salvos em memória (com a aplicação rodando), basta acessar http://localhost:8080/h2-console e preencher os dados de login com os dados presentes no arquivo de propriedades.

A seguir serão descritos os requisitos do sistema proposto.

### Requisitos já implementados

1. Usuários:

- 1.1. A comunicação entre as requisições da aplicação devem ser realizadas utilizando-se o formato JSON.
- 1.2. Usuário deve ser capaz de cadastrar-se no sistema provendo as informações básicas de nome, email e senha. Não deve ser possível a existência de dois usuários de ids diferentes com o mesmo endereço de email. A senha deve ser salva como hash SHA256.
- 1.3. Usuário deve ser capaz de logar-se no sistema fornecendo email e senha (hash SHA256). A Resposta da API deve conter um token JWT que será utilizado para acesso a rotas privadas.
- 1.4. O usuário deverá realizar operações privadas na aplicação utilizando-se de um mecanismo de Bearer Token que deve ser enviado através de header nas requisições de listagem, criação, edição, exclusão de postagens.

2. Postagens:

- 2.1. Usuário deve ser capaz de criar postagens contendo título, texto da postagem; além destes campos, a hora de criação da postagem também deve ser armazenada, porém, esta deve ser obtida de forma automatizada.
- 2.2. Usuário deve ser capaz de capturar detalhes de uma determinada postagem.
- 2.3. Usuário deve ser capaz de listar as postagens criadas por ele.
- 2.4. Usuário deve ser capaz de editar atributos de uma postagem que tenha criado com exceção dos campos id e data de criação da postagem.
- 2.5. Usuário deve ser capaz de excluir uma postagem que tenha criado.

Atenção:
Rotas de criação/listagem/update/remoção de postagens devem ser implementadas de forma privada sendo acessíveis somente através de passagem de token de autenticação no header Authorization da requisição.

### Questões da Avaliação

1. Corrigir bug de privacidade de dados relacionado ao login do usuário (requisito 1.2). A senha não está sendo salva como hash SHA256.
2. Data da postagem não está sendo salva (requisito 2.1).
3. Implementar requisito 2.2 do módulo de Postagens.
4. Corrigir bug relacionado à listagem de posts (requisito 2.3). Estão sendo listados posts de todos os usuários e não apenas do usuário logado.
5. Implementar requisito 2.4 do módulo de Postagens.
6. Implementar requisito 2.5 do módulo de Postagens.
7. Ampliar TTL/expiration do JWT para 24h (alteração do requisito 1.3).

### Rotas da aplicação

Para facilitar o entendimento do desafio proposto, abaixo uma sugestão para as rotas da aplicação:

```
- [POST] {{baseURL}}/users = Rota de criação de usuários 
- [POST {{baseURL}}/users/signIn = Rota de autenticação de usuários 
- [GET {{baseURL}}/users/logged = Rota de consulta do usuário logado 
- [GET] {{baseURL}}/posts = Rota de listagem de posts do usuário 
- [POST] {{baseURL}}/posts = Rota de criação de posts do usuário 
- [PUT] {{baseURL}}/posts/:postId = Rota para atualização de posts do usuário 
- [DELETE] {{baseURL}}/posts/:postId = Rota para exclusão de posts do usuário
```

Para facilitar as consultas foi colocada uma collection do postman contendo todas essas rotas na pasta resources/postman da aplicação, basta importa-las.
