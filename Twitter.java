//Linhas abaixo para quando estou utilizando o notebook (teclado não tem facilmente as barras indicadas)
//| \
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;

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
    private static ArrayList<String> usersLogados = new ArrayList<String>();
    //ArrayLists que funcionam como feed, duas ArrayLists para ligar as posições dos textos aos usuários
    private static ArrayList<String> feedTweets = new ArrayList<String>();

  


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
                Usuario userTeste =new Usuario("12651961%$#@", "126519610320#@!$!#@!#", "12651961$#@$", "12651961$@#!");
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
        
        usersLogados.add(user.getLogin());
    } 

    public static Boolean verificaUserLogado(){
        Iterator<String> iter = usersLogados.iterator();
        if(!iter.hasNext()){
            System.out.println("*****\nNenhum usuário logado - operação impossível\n*****\n");
            //retorna se não há nenhum user logado
            return false;
        }else{
            return true;
        }
    }
    
//Bloco para tirar o usuario selecionado da ArrayList usersLogados, deslogando ele
    public static void deslogarUsuarios(){
        Iterator<String> iter = usersLogados.iterator();

        //String abaixo apenas inicializa a variável como não vazia, apenas para não acusar erro
        String opcao = "oi";
        Boolean maisUmaVerificacao = true;

        ArrayList<String> nomes = new ArrayList<String>();
        if(!verificaUserLogado()){
            verificaUserLogado();
            return;
        }


        System.out.println("\n*****\nLista de usuarios: ");
        for(String user : usersLogados){
            System.out.printf("* %s\n", user);
            nomes.add(user);
        }        
        while(maisUmaVerificacao){
            System.out.printf("Qual usuário gostaria de deslogar (digite username)? ");
            opcao = input.next();
            //Limpa o buffer
            input.nextLine();
            for(String user : usersLogados){
                if(user.equals(opcao)){
                    maisUmaVerificacao = false;
                }   
            }
        }
        while(iter.hasNext()){
            String item = iter.next();
            for(String nome : nomes){
                if(item.equals(opcao)){
                    
                    System.out.println("User "+item+" deslogado");
                    iter.remove();
                }
            }
        }
    }

//Método para selecionar usuário logado
    public static Usuario selecionaUserLogado(){
        Iterator<String> iter = usersLogados.iterator();
        Iterator<Usuario> iterUser = arrayUser.iterator();

        int valida = 0, quantidade = 1;
        String opcao, opcaoAux;
        
        do{
            System.out.print("Digite o numero correspondente do usuário logado que quer selecionar: ");
            
            while(iter.hasNext()){
                String item = iter.next();

                System.out.printf("\n%d - %s\n", quantidade, item);

            }
            opcao = input.next();
            //limpa o buffer
            input.nextLine();
            for(String user : usersLogados){
                if(opcao.equals(user)){
                    valida = 1;
                }else{
                    System.out.print("usuario inexistente");
                    System.out.println("Gostaria de sair?\nsim\nnao");
                    if(input.next().equals("sim")){
                       return new Usuario(); 
                    }
                    valida = 0;
                }
            }
        }while(valida == 0);


        while(iterUser.hasNext()){
            Usuario item = iterUser.next();
            
            if(opcao.equals(item.getLogin())){
                return item;
            }
        }
        
        //apenas para satisfazer o método, não é para retornar nunca
        return new Usuario();
    }


//Bloco para o usuário logado selecionado digitar um tweet de 1 de 140 caracteres.
    public static void tweeta(){
        Date dataHoraAtual = new Date();
        String tweet, dataDia = new SimpleDateFormat("dd/MM/YYYY").format(dataHoraAtual), dataHora = new SimpleDateFormat("HH:MM").format(dataHoraAtual);
        int tamanho;

        Iterator<Usuario> iter = arrayUser.iterator();

        if(!verificaUserLogado()){
            verificaUserLogado();
            return;
        }

        Usuario user = selecionaUserLogado();
        if(user.getLogin().equals(null)){
            return;
        }
        
        do{
            System.out.println("@"+user.getLogin()+" digite o seu tweet(de 1 a 140 caracteres):");
            tweet = input.nextLine();
            tamanho = tweet.length();
        }while(tamanho <1 && tamanho >140);
        
        while(iter.hasNext()){
            Usuario item = iter.next();
            if(item.getLogin().equals(user.getLogin())){
                if(item.setTweet(tweet+"\n"+dataDia+" - "+dataHora)){
                    //item.setTweet(tweet+"\n"+dataDia+" - "+dataHora);
                    feedTweets.add(tweet+"\n "+dataDia+" - "+dataHora+"\n@"+item.getLogin());
                }else{
                    System.out.println("*****\nTweet Repetido, não é possível prosseguir\n*****");
                }
            }
        }
    }
//método para mostrar os últimos tweets (mais recentes)
    public static void mostraTweets(){
        if(feedTweets.size() == 0){
            System.out.println("*****\nNenhum tweet feito, voltando ao menu\n*****");
            return;
        }

        System.out.print("Digite a quantidade de últimos tweets que quer ver, no total de "+feedTweets.size()+": ");
        int opcao = input.nextInt();

        if(opcao <= 0){
            System.out.println("*****\nVoltando ao menu\n*****");
            return;
        }
        if(opcao > feedTweets.size()){
            for(int i = feedTweets.size()-1; i >= 0;i--){
                System.out.println(feedTweets.get(i));    
            }
        }else{
            for(int i = feedTweets.size()-1; i >= (feedTweets.size()-1)-opcao;i--){
                System.out.println(feedTweets.get(i));    
            }
        }
    }
//Apaga o tweet do user que foi selecionado, que já estava logado
    public static void apagaTweet(){
        if(!verificaUserLogado()){
            verificaUserLogado();
            return;
        }
        Iterator<String> iterFeed = feedTweets.iterator();
        Iterator<Usuario> iterUser = arrayUser.iterator();
        
        Usuario user = selecionaUserLogado();

        if(user.getLogin().equals(null)){
            return;
        }

        ArrayList<String> tweetUser = user.getTweet();
        int quantidade = 1, opcaoFrom, opcaoTo;

        System.out.println("De qual tweet até qual gostaria de deletar?\nCaso desista, digite 0 e 0");
        for(String tweet: tweetUser){
            System.out.println(quantidade+". "+tweet+"\n---------");
            quantidade++;
        }
        do{
            System.out.print("De: ");
            opcaoFrom = input.nextInt();

            System.out.print("Até: ");
            opcaoTo = input.nextInt();

            if(opcaoFrom == 0 && opcaoTo == 0){
                System.out.println("Voltando ao menu");
                return;
            }
        }while(opcaoFrom < opcaoTo && opcaoTo <=quantidade);
        
        quantidade = 1;

        while(iterUser.hasNext()){
            Usuario item = iterUser.next();
            if(user.getLogin().equals(item.getLogin())){
                item.apagaTweet(opcaoTo, opcaoFrom);
                while(iterFeed.hasNext()){
                    String itemFeed = iterFeed.next();
                    if(opcaoFrom <= quantidade && quantidade <= opcaoTo){
                        for(String tweet:tweetUser){
                            if(tweet.equals(itemFeed)){
                                feedTweets.remove(itemFeed);
                            }
                        } 
                    } 
                    quantidade++;
                }
            }
            quantidade++;
        }
    }

//Bloco destinado ao menu inicial
    public static void menuInicial(){

        System.out.printf("Menu Principal\n1 - Cadastrar usuario\n2 - Listar usuarios\n3 - Logar usuario\n4 - Deslogar\n5 - Tweetar\n6- Mostrar últimos tweets do feed\n7- Remover tweet de um usuário\n...\n ");
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
            case 6:
                System.out.println("Mostrar");
                mostraTweets();
                break;
            case 7:
                System.out.println("Apagar tweet");
                apagaTweet();
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