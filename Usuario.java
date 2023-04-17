
public class Usuario{
    private String nome, login, email, senha;
    public static int quantidadeUsersCadastrados = 0;

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