package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Avaliacao;

public interface AvaliacaoDAO extends GenericDAO<Avaliacao, Integer> {

	public List<Avaliacao> listar(int codigo);
	
	public void transaction();
	
	public double mediaAvaliacoes(int codigo);

}
