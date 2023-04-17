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
        int tS = item.length();
        if(tS >= 6 && tS <= 15){
            return true;
        }else{
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
    
            System.out.println("VERIFICA "+verifica);
        }while(verifica != 0 || !verificaB);
        
        do{
            System.out.println("EMAIL: ");
            email = input.next();
            verificaB = verificaCaracterE(email);
        }while(!verificaB);
        
        do{
            System.out.println("SENHA (de 6 a 15 caracteres): ");
            senha = input.next();
            verificaB = verificaCaracterS(nome);
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
                return userTeste=new Usuario("12651961", "126519610320", "12651961", "12651961");
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
        Usuario userTeste=new Usuario("12651961", "126519610320", "12651961", "12651961");
        return userTeste;
    }
//Adiciona o user que foi
    public static void adicionaUserLogado(){
        Usuario user = logarUser();
        if(user.getNome().equals("12651961")){
            return;
        }

        usersLogados.add(user);
    }
    
    
//Bloco para tirar o usuario selecionado da ArrayList usersLogados, deslogando ele
    public static void deslogarUsuarios(){
        Iterator<Usuario> iter = usersLogados.iterator();
        int quantidade = 0, opcao;
        //puramente para salar o nome dos users logados em uma array para mostrar na opção
        for(Usuario user : usersLogados){
            System.out.println(user);
            quantidade++;
        }
        if(quantidade == 0){
            System.out.println("Nenhum user logado");
            return;
        }
        String[] nomes = new String[quantidade];

        for(int i = 0; i < nomes.length; i++){
            for(Usuario user : usersLogados){
                if(i == 0){
                    nomes[i] = user.getLogin();
                }
                if(nomes[i].equals(user.getLogin())){
                    break;
                }
                nomes[i] = user.getLogin();
                break;
            }
        }

        System.out.println("\n*****\nLista de usuarios: ");
        for(int i= 0 ; i<nomes.length; i++){
            System.out.printf("%d - %s\n", i+1, nomes[i]);
        }
        System.out.printf("Qual usuário gostaria de deslogar (digite o numero)? ");
        opcao = input.nextInt();

        while(iter.hasNext()){
            Usuario item = iter.next();
            if(item.getNome().equals(nomes[opcao-1])){
                iter.remove();
                System.out.println("User deslogado");
            }
        }
    }

//Bloco destinado ao menu inicial
    public static void menuInicial(){

        System.out.printf("Menu Principal\n1 - Cadastrar usuario\n2 - Listar usuarios\n3 - Logar usuario\n4 - deslogar\n... ");
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
    }
}