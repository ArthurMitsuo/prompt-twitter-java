import java.util.ArrayList;
import java.util.Iterator;

public class Usuario{
    private String nome, login, email, senha;
    public static int quantidadeUsersCadastrados = 0;
    public ArrayList<String> tweets = new ArrayList<String>();

    public Usuario(){
        
    }
    public Usuario(String nome, String login, String email, String senha){
        this.nome = nome;
        this.login = login;  
        this.senha = senha;
        this.email = email;
        quantidadeUsersCadastrados++;
    }

    public Boolean validaSenha(String senha){
        if(senha.equals(this.senha)){
            return true;
        }
        return false;
    }
    public void setSenha(String senhaAntiga, String senhaNova){
        if(validaSenha(senhaAntiga) == true){
            System.out.println("*****\nSenha alterada com sucesso!!\n*****\n");
            this.senha = senhaNova;
        }else{
            System.out.println("*****\nSenha Incorreta, Impossível alterar!!\n*****\n");
        }
    }

    private Boolean verificaTweets(String tweet){
        Iterator<String> iter = tweets.iterator();

        while(iter.hasNext()){
            String item = iter.next();
            if(tweet.equals(item)){
                return false;
            }else{
                return true;
            }
            
        }
        return true;
    }
    
    public Boolean setTweet(String tweet){
        if(!verificaTweets(tweet)){
            return false;
            
        }else{
            tweets.add(tweet);
            System.out.println("TWEETADO");
            return true;
        }  
    }

    public ArrayList<String> getTweet(){
        Iterator<String> iter = tweets.iterator();

        while(!iter.hasNext()){
            System.out.println("Nenhum tweet do user selecionado");
            return tweets;
        }
        return tweets;
    }

    public void apagaTweet(int i, int f){
        Iterator<String> iter = tweets.iterator();
       

        if(!iter.hasNext()){
            System.out.println("User não possui tweets");
            return;
        }else{
            int quantidade = 1;
            while(iter.hasNext()){
                String item = iter.next();

                if(i <= quantidade && quantidade <= f){
                    iter.remove();
                    System.out.println("Tweets Apagados com sucesso");
                }if(quantidade>f){
                    return;
                }
                quantidade++;
            }
        }
    }

    public ArrayList<String> getTweetDeletado(int i, int f){
        Iterator<String> iter = tweets.iterator();

        ArrayList<String> retorno = new ArrayList<String>();

        int quantidade = 1;
        while(iter.hasNext()){
            String item = iter.next();

            if(i <= quantidade && quantidade <= f){
                retorno.add(item);
            }if(quantidade>f){
                break;
            }
            quantidade++;
        }
        return retorno;
    }
   

    public String getNome(){
        return nome;
    }
    public String getLogin(){
        return login;
    }
    public String getEmail(){
        return email;
    }
}