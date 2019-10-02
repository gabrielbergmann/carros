# Carros
  **API RESTful que cadastra, edita e exclui carros de uma base de dados.**
  
  O projeto foi desenvolvido durante a realização do curso [API RESTful com SpringBoot](https://www.udemy.com/course/springboot-essencial/).
  O projeto foi desenvolvido em Java, utilizando a base dados MySql.
  No projeto, temos autenticação de usuários utilizando JWT. Autenticado, o usuário pode cadastrar, editar e excluir os carros na base de dados, o projeto permite também, a realização de importação de imagem com o Firebase.

# Pré-requitos
  * Java*
  * MySql
  
  ## Este projeto utiliza as seguintes bibliotecas:
  * Maven
  * SpringBoot
  * ModelMapper
  * H2DB
  * FireBase
  * JWT
  * Lombok
  
A IDEA utilizada no projeto foi IntelliJ IDEA.

# Intalação e execução

Baixar o projeto e importar no IntelliJ como Maven project
Para executar o projeto localmente, devemos criar a base de dados <carros> no MySql.
Executar o arquivo 'CarrosApplication.java'.
Atenção, há dois profiles, caso queira, pode-se adicionar o profile --dev, aos java arguments, por padrão, ele executa em prod.
