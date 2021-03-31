package classes;

public class Usuario {
    private String login, senha, nomeDeUsuario;

    public Usuario(String login, String senha, String nomeDeUsuario) {
        this.login = login;
        this.senha = senha;
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setNomeDeUsuario(String nomeDeUsuario){
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }
    
    @Override //Comparador de usuários utilizando equals com o login
    public boolean equals(Object o){
        Usuario u = (Usuario) o;
        if (this.login.equals(u.login)){
            return true;
        }else{
            return false;
        }

    }
}
