//Linhas abaixo para quando estou utilizando o notebook (teclado não tem facilmente as barras indicadas)
//| \
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Twitter {
    static Scanner input = new Scanner(System.in);

    //variáveis estáticas utilizadas para fins de estatística de usabilidade
    static int usuariosLogados =0, totalTweets = 0;

    //variável que verifica se o user quer sair ou não
    private static Boolean verificacao = true;


    //ArrayList que salva os nomes de login para verificação
    private static ArrayList<String> logins = new ArrayList<String>();
    //ArrayList que salva os usuários como objetos usuários
    private static ArrayList<Usuario> arrayUser = new ArrayList<Usuario>();
    //ArrayList que salva os usuários que estão logados (obs.: método utilizado, pois podem dois ou mais user estarem logados ao mesmo tempo)
    private static ArrayList<Usuario> usersLogados = new ArrayList<Usuario>();

    //métodos de verificação de caracteres
    private static Boolean verificaCaracterNL(String item){
        int tS = item.length();
        if(tS >= 2 && tS <= 30){
            return true;
        }else{
            return false;
        }
    }
    private static Boolean verificaCaracterE(String item){
        int tS = item.length();
        if(tS >= 6 && tS <= 30){
            return true;
        }else{
            return false;
        }
    }
    private static Boolean verificaCaracterS(String item){
        if(item.length() >= 6 && item.length() <= 15){
            System.out.println(item.length());
            return true;
        }else{
            System.out.println(item.length());
            System.out.println("Digite entre 6 e 15 caracteres para a senha!");
            return false;
        }
    }

//Bloco feito para adicionar o login em uma ArrayList, antes verifica se ele já existe    
    private static int verificaLogin(String login){
        Iterator<String> iter = logins.iterator();
        while(iter.hasNext()){
            String item = iter.next();
            if(item.equals(login)){
                System.out.println("tem");
                return 1; 
            }else{
                System.out.println("nao tem");
            }
        }
        return 0;
    }
    private static int adicionaLogin(String login){
        Iterator<String> iter = logins.iterator();
        if(!iter.hasNext()){
            logins.add(login);
            System.out.println("Cadastrado");
            return 0;
        }else{
            while(iter.hasNext()){
                //String item = iter.next();
                if(verificaLogin(login) == 0){
                    System.out.println("Cadastrado");
                    logins.add(login);
                    return 0;
                }else{
                    //System.out.println(iter);
                    System.out.println("Login impossivel");
                    return 1;
                }
            }
        }        
        return 1;
    }

//Bloco que instância um novo usuário e o retorna, verificando os caracteres;
    static Usuario constroiUsuario(){
        String nome, login, email, senha;
        Boolean verificaB;
        int verifica;

        do{
            System.out.println("NOME: ");
            nome = input.nextLine();
            verificaB = verificaCaracterNL(nome);
        }while(!verificaB);
        
        do{  
            System.out.println("LOGIN: ");
            login = input.next();
            //Limpa o buffer
            input.nextLine();
            verificaB = verificaCaracterNL(login);
            verifica = adicionaLogin(login);
        }while(verifica != 0 || !verificaB);
        
        do{
            System.out.println("EMAIL: ");
            email = input.next();
            verificaB = verificaCaracterE(email);
        }while(!verificaB);
        
        do{
            System.out.println("SENHA (de 6 a 15 caracteres): ");
            senha = input.next();
            verificaB = verificaCaracterS(senha);
        }while(!verificaB);

        Usuario user = new Usuario(nome, login, email, senha);

        return user;
    }

    //Bloco para adicionar o usuário retornado pela função anterior no ArrayList estático
    public static void populaArray(){
       Usuario user = constroiUsuario();

       arrayUser.add(user);
    }
    
    //Método para listar os usuários cadastrados
    public static void getUsuario(){
        for(Usuario user: arrayUser){
            System.out.printf("\nNome: %s\nEmail: %s\nLogin: %s\n------\n", user.getNome(), user.getEmail(), user.getLogin());
        }
    }

    private static Usuario logarUser(){
        //FAZER FUNÇÃO de verificar SE O USER JÁ FOI LIGADO

        //Iterator<Usuario> iter = arrayUser.iterator();
        Boolean validacao = true;
        while(true){
            String login, senha;
            int tentativa=1, quantidade=0;
            
            for(Usuario user:arrayUser){
                quantidade++;
            }
            if(quantidade ==0){
                System.out.println("Nenhum user cadastrado");
                Usuario userTeste =new Usuario("12651961", "126519610320", "12651961", "12651961");
                return userTeste;
            }

            System.out.print("Qual seu usuário: ");
            login = input.next();
            
            System.out.printf("Qual a senha do user %s: ", login);
            senha = input.next();

            for(Usuario user : arrayUser){
                if(user.validaSenha(senha) && user.getLogin().equals(login)){
                    System.out.println("*****\nLogin efetuado\n*****");
                    return user;
                }
            }
            System.out.println("*****\nLogin ou senha inválidos\n*****");
            while(tentativa != 1 || tentativa != 2){
                System.out.println("\nAções possiveis:\n1 - Tentar de novo\n2 - Voltar para o Menu");
                tentativa = input.nextInt();
                if(tentativa == 1){
                    break;
                }else if(tentativa == 2){
                    validacao = false;
                    break;
                }
                else if(tentativa != 1){
                    System.out.println("Opção não existente");  
                };
            }
            if(!validacao){
                break;
            }     
        }
        //Em teoria nunca vai precisar ser retornado, mas para fins de não dar erro no método no VsCode
        Usuario userTeste=new Usuario(null, null, null, null);
        return userTeste;
    }

//Adiciona o user que foi
    public static void adicionaUserLogado(){
        Usuario user = logarUser();
        if(user.getNome().equals(null)){
            return;
        }
        
        usersLogados.add(user);
    } 
    
//Bloco para tirar o usuario selecionado da ArrayList usersLogados, deslogando ele
    public static void deslogarUsuarios(){
        Iterator<Usuario> iter = usersLogados.iterator();

        //String abaixo apenas inicializa a variável como não vazia, apenas para não acusar erro
        String opcao = "oi";
        Boolean maisUmaVerificacao = true;

        ArrayList<String> nomes = new ArrayList<String>();
        if(!iter.hasNext()){
            maisUmaVerificacao = false;
            System.out.println("*****\nNenhum user logado - operação impossivel\n*****\n");
        }


        System.out.println("\n*****\nLista de usuarios: ");
        for(Usuario user : usersLogados){
            System.out.printf("* %s\n", user.getLogin());
            nomes.add(user.getLogin());
        }        
        while(maisUmaVerificacao){
            System.out.printf("Qual usuário gostaria de deslogar (digite username)? ");
            opcao = input.next();
            //Limpa o buffer
            input.nextLine();
            for(Usuario user : usersLogados){
                if(user.getNome().equals(opcao)){
                    maisUmaVerificacao = false;
                }   
            }
        }
        while(iter.hasNext()){
            Usuario item = iter.next();
            for(String nome : nomes){
                if(item.getLogin().equals(opcao)){
                    
                    System.out.println("User "+item.getLogin()+" deslogado");
                    iter.remove();
                }
            }
        }
    }

//Bloco para selecionar usuário logado
    public static String selecionaUserLogado(){
        Iterator<Usuario> iter = usersLogados.iterator();
        int quantidade = 0, opcao, quantidadeAux = 0;
        if(!iter.hasNext()){
            System.out.println("*****\nNenhum usuário logado, operação impossível\n*****\n");
            return "!@#$%&**&%$#@!";
        }
        do{
            System.out.print("Selecione o numero do usuário logado: ");
            
            while(iter.hasNext()){
                Usuario item = iter.next();

                System.out.printf("\n%d - %s\n",quantidade+1, item.getNome());
                quantidade++;
            }
            opcao = input.nextInt()-1;
            //limpa o buffer
            input.nextLine();
        }while(opcao <= 0 && opcao > quantidade+1);
        System.out.println("OPÇÃO DIGITADA: "+opcao);
        
        for(Usuario user: usersLogados){
            if(quantidadeAux == opcao){
                return user.getNome();
            }else{
                System.out.println("AINDA NÃO");
            }
            quantidadeAux++;
        }
        //apenas para satisfazer o método
        return "ISSO DAQUI";
    }

//Bloco para o usuário logado selecionado digitar um tweet de 1 de 140 caracteres.
    public static void tweeta(){
        String tweet;
        int tamanho;
        String user = selecionaUserLogado();
        if(user == "!@#$%&**&%$#@!"){
            return;
        }
        Iterator<Usuario> iter = arrayUser.iterator();

        
        do{
            System.out.println(user+" digite o seu tweet(de 1 a 140 caracteres):");
            tweet = input.nextLine();
            tamanho = tweet.length();
        }while(tamanho <1 && tamanho >140);
        
        while(iter.hasNext()){
            Usuario item = iter.next();
            if(item.getLogin().equals(user)){
                item.setTweet(tweet);
            }
        }
    }


//Bloco destinado ao menu inicial
    public static void menuInicial(){

        System.out.printf("Menu Principal\n1 - Cadastrar usuario\n2 - Listar usuarios\n3 - Logar usuario\n4 - Deslogar\n5 - Tweetar\n6- mostrar últimos tweets do feed\n...\n ");
        int opcao = input.nextInt();
        //limpa o buffer
        input.nextLine();

        switch (opcao){
            case 0:
                System.out.println("Saindo...");
                verificacao = false;
                break;
            case 1:
                System.out.println("Cadastrar");
                populaArray();
                break;
            case 2:
                System.out.println("Listando usuários cadastrados: ");
                getUsuario();
                break;
            case 3:
                System.out.println("Logar");
                adicionaUserLogado();
                break;
            case 4:
                System.out.println("Deslogar");
                deslogarUsuarios();
                break;
            case 5:
                System.out.println("Tweetar");
                tweeta();
                break;
            
            default:
                System.out.println("\n****\nOPÇÃO INEXISTENTE\n****");
                break;
        }
    }
    public static void main(String args[]){
        while(verificacao){
            menuInicial();
        }       
    }//arthur osmario felipe
}