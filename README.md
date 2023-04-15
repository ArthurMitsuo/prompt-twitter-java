# prompt-twitter-java

##Introdução ao exercício

Atividade realizada como nota parcial para nota da matéria Desenvolvimento de Software do curso de Análise e Desenvolvimento de Sistemas, 3º período, da Universidade Positivo em Curitiba.

Abaixo os requisitos estipulados pelo professor Alexandre. Nenhuma questão abaixo foi estipulada por mim. Utilizando repositório para versionar o código e ficar visível.


##Projeto 1
Desenvolver um programa em Java para simular uma versão simples do Twitter. O programa
conterá duas classes, Usuario e Twitter (a classe de aplicação com o método main).

A classe Usuario deve ter como atributos obrigatórios nome, login, email e senha (pode-se criar
outros atributos se houver necessidade). O login será o identificador único do usuário, isto é, não
deve ser possível cadastrar usuários com o mesmo login.
Regras para os atributos:
* **Nome**: No mínimo 2 e no máximo 30 caracteres;
* **Login**: No mínimo 2 e no máximo 20 caracteres;
* **Email**: No mínimo 6 e no máximo 30 caracteres;
* **Senha**: No mínimo 6 e no máximo 15 caracteres;


A classe Twitter será responsável pelo fluxo de execução e deverá apresentar um menu com as
seguintes opções:

* 1- cadastrar usuário
* 2- listar usuários
* 3- logar usuário
* 4- deslogar usuário
* 5- tweetar
* 6- mostrar últimos N tweets do feed
* 7- remover tweet de um usuário
* 8- alterar senha de um usuário
* 9- remover um usuário
* 10- imprimir estatísticas
* 0- finalizar programa

Breve descrição das opções:

1-Criar um usuário com as informações básicas;
2-Listar os logins de todos os usuários cadastrados na plataforma;
3-Solicitar o login de um usuário e solicitar a senha para logar;
4-Solicitar o login de um usuário e deslogá-lo;
5-Selecionar um usuário pelo login, verificar se está logado e pedir para digitar um tweet (um tweet
deve ter entre 1 e 140 caracteres);
6-Solicitar uma quantidade N de tweets e mostrar na tela os últimos N tweets realizados na
plataforma. Não há necessidade de mostrar o login do usuário que tweetou, apenas a mensagem em
si;
7-Selecionar o login de um usuário, mostrar na tela todos os tweets deste usuário com um número
identificador e solicitar um identificador para remover este tweet. Um tweet removido do usuário
deve ser removido também do feed (a lista de todos os tweets);
8-Solicitar o login de um usuário, solicitar a senha deste usuário, solicitar a nova senha do usuário e
alterá-la;
9-Solicitar o login de um usuário, solicitar a senha deste usuário e removê-lo;
10-Imprimir na tela as seguintes estatísticas:
-Número total de usuários cadastrados;
-Número de usuários logados neste momento;
-Número de tweets no total;
-Número de tweets por usuário (listar todos os logins com suas respectivas quantidades)
-Login do usuário que mais tweetou e a quantidade de tweets deste usuário;
-Login do usuário que tweetou por último e o próprio tweet;

**Outras orientações**:
Importante: A ordem das opções deve ser a mesma que a indicada no enunciado.
**Tweet** é uma mensagem de 1 a 140 caracteres que um usuário pode publicar. Não há limites para o
número de tweets por usuário.
**Feed** é a área comum em que todos os tweets de todos os usuários são mostrados. No caso desta
aplicação, a ordem de impressão será a ordem de publicação. Os últimos tweets serão mostrados por
último.

É possível (e recomendado) criar métodos auxiliares nas classes para modularizar o código.
Utilizar apenas as estruturas de dados vistas em sala até o momento. Casos específicos devem ser
consultados.

Para a leitura das informações e das opções, não é preciso solicitar novamente a leitura caso haja
erro de digitação ou erro na validação. Por exemplo, se ao cadastrar um usuário digitou-se uma
senha com menos de 6 dígitos, a operação toda será abortada, uma mensagem de erro deve ser
impressa na tela e retornará à tela inicial com as opções. Outro exemplo: se foi selecionada a opção
para logar um usuário mas o login digitado não existe, deve-se imprimir na tela uma mensagem de
que o login não existe e retornar ao menu inicial.

É recomendado imprimir linhas em branco e/ou separadores para que a aplicação esteja visualmente
interessante e intuitiva. O grupo tem liberdade para imprimir as mensagens como achar mais
interessante.

Um vídeo com um exemplo de funcionamento estará disponível no BlackBoard.


**Entrega**:
Todos os membros do grupo devem enviar no BlackBoard um arquivo .zip contendo os arquivos
**Usuario.java, Twitter.java e relatorio.pdf**

O relatório deve conter no máximo 1 página e indicar se todas as opções estão funcionando de
acordo ou então descrever quais operações estão executando corretamente e quais não estão.
Eventuais problemas também devem estar descritos.

O documento deve conter também o diagrama UML com as duas classes, em que devem estar
presentes os atributos e métodos de cada classe (com exceção dos métodos getters, setters,
construtores e toString).
