//| \
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Twitter {
    static Scanner input = new Scanner(System.in); 
    private static ArrayList<String> logins = new ArrayList<String>();

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
            return false;
        }
    }

    
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
            System.out.println("SENHA: ");
            senha = input.next();
            verificaB = verificaCaracterS(nome);
        }while(!verificaB);

        Usuario user = new Usuario(nome, login, email, senha);
        return user;
    }



    public static void menuInicial(){
        System.out.printf("Menu\n1 - Cadastrar usuario\n2 - Listar usuarios\n3 - Logar usuario\n4 - deslogar\n... ");
        int opcao = input.nextInt();
        //limpa o buffer
        input.nextLine();

        switch (opcao){
            case 1:
                System.out.println("Cadastrar");
                constroiUsuario();
                break;
            case 2:
                System.out.println("Listar");
                break;
            case 3:
                System.out.println("Logar");
                break;
            case 4:
                System.out.println("Listar");
                break;
            default:
                System.out.println("SE FODER");
                break;
        }
        
    }
    public static void main(String args[]){
        menuInicial();
        
    }
}
