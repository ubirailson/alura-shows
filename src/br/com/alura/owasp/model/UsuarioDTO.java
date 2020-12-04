package br.com.alura.owasp.model;

public class UsuarioDTO {

	private String email;
	private String senha;
	private String nome;
	private String nomeImagem;

	public Usuario montaUsuario() {
		Usuario usuario = new Usuario(email, senha, nome, nomeImagem);
		return usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

}
