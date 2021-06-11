package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {
	
	void transaction();
	
	Usuario BuscarPorId(int codigo);
	
	void delete(int codigo);

	List<Usuario> listarTodos();

	boolean existe(String email);

	String criar(Usuario usuario);
	
	void update(Usuario usuario);
}
