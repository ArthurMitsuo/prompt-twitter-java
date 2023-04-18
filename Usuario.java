import java.util.ArrayList;
import java.util.Iterator;
public class Usuario{
    private String nome, login, email, senha;
    public static int quantidadeUsersCadastrados = 0;
    public ArrayList<String> tweets = new ArrayList<String>();

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
    public void setSenha(String senha){
        if(validaSenha(senha) == true){

        }else{
            System.out.println("*****\nSenha Incorreta, Impossível alterar\n*****\n");
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