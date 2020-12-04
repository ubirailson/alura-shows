package br.com.alura.owasp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import br.com.alura.owasp.model.Usuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext
	private EntityManager em;
	
	public void salva(Usuario usuario) {
		transformaSenhaEmHash(usuario);
		em.persist(usuario);
	}
	private void transformaSenhaEmHash(Usuario usuario) {
		String salto = BCrypt.gensalt();
		String senhaHashed = BCrypt.hashpw(usuario.getSenha(), salto);
		usuario.setSenha(senhaHashed);
	}
	public Usuario procuraUsuario(Usuario usuario) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.email=:email ", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		Usuario usuarioRetornado = query.getResultList().stream().findFirst().orElse(null);
		
		if (validaSenhaComHashDoBanco(usuario, usuarioRetornado)) {
			return usuarioRetornado;
		}
		
		return null;
	}
	private boolean validaSenhaComHashDoBanco(Usuario usuario, Usuario usuarioRetornado) {
		if (usuarioRetornado==null) {
			return false;
		}
		
		return BCrypt.checkpw(usuario.getSenha(), usuarioRetornado.getSenha());
	}
	
//Versão do código com falha de segurança para sql injection
//	Connection connection = new ConnectionFactory().getConnection();
//
//	public void salva(Usuario usuario) {
//		String query = "insert into Usuario (email,senha,nome,nomeImagem) values ('"
//				+ usuario.getEmail()
//				+ "','"
//				+ usuario.getSenha()
//				+ "','"
//				+ usuario.getNome() + "','" + usuario.getNomeImagem() + "');";
//		try {
//			Statement statement = connection.createStatement();
//			statement.executeUpdate(query);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public Usuario procuraUsuario(Usuario usuario) {
//		String query = "SELECT * FROM Usuario WHERE email=" + "'"
//				+ usuario.getEmail() + "'" + " and senha=" + "'"
//				+ usuario.getSenha() + "';";
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet results = statement.executeQuery(query);
//			Usuario usuarioRetorno = new Usuario();
//			while (results.next()) {
//				usuarioRetorno.setEmail(results.getString("email"));
//				usuarioRetorno.setSenha(results.getString("senha"));
//				usuarioRetorno.setNomeImagem(results.getString("nomeImagem"));
//				usuarioRetorno.setNome(results.getString("nome"));
//				if(usuarioRetorno.getEmail().equals(usuario.getEmail())){
//					break;
//				}
//			}
//			if (usuarioRetorno.getEmail() == null
//					&& usuarioRetorno.getSenha() == null) {
//				return null;
//			} else {
//				return usuarioRetorno;
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
