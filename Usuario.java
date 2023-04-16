
public class Usuario{
    private String nome, login, email, senha;
    public static int quantidade = 0;

    public Usuario(String nome, String login, String email, String senha){
        this.nome = nome;
        this.login = login;  
        this.senha = senha;
        this.email = email;
        quantidade++;
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