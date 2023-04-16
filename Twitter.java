//| \
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Twitter {
    static Scanner input = new Scanner(System.in); 
    private static Boolean verificacao = true;
    //ArrayList que salva os nomes de login para verificação
    private static ArrayList<String> logins = new ArrayList<String>();
    //ArrayList que salva os usuários como objetos usuários
    private static ArrayList<Usuario> arrayUser = new ArrayList<Usuario>();
    //ArrayList que salva os usuários que estão logados (obs.: método utilizado, pois podem dois ou mais user estarem logados ao mesmo tempo)
    private static ArrayList<Usuario> userLogado = new ArrayList<Usuario>();

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
                System.out.println("nao tem");
                return 0;
            }else{
                System.out.println("tem");
                return 1;
            }
        }
        return 1;
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
                if(verificaLogin(login) == 1){
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

//Bloco que instância um novo usuário e o retorna;
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
            System.out.println("SENHA - de 6 a 15 caracteres: ");
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

    public static void getUsuario(){
        /* Iterator<arrayUser> iter = arrayUser.iterator();
        
        while(iter.hasNext()){
            System.out.printf("\nNome: %s\nEmail: %s\nLogin: %s\n-------", iter.getNome());
        } */

        for(Usuario user: arrayUser){
            System.out.printf("\nNome: %s\nEmail: %s\nLogin: %s\n------\n", user.getNome(), user.getEmail(), user.getLogin());
        }
    }


//Bloco destinado ao menu inicial
    public static void menuInicial(){

        System.out.printf("Menu\n1 - Cadastrar usuario\n2 - Listar usuarios\n3 - Logar usuario\n4 - deslogar\n... ");
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
                break;
            case 4:
                System.out.println("Listar");
                break;
            default:
                System.out.println("OPÇÃO INEXISTENTE");
                break;
        }
        
    }
    public static void main(String args[]){
        while(verificacao){
            menuInicial();
        }       
    }
}
