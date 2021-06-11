package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Hotel;

public interface HotelDAO extends GenericDAO<Hotel, Integer> {
	
	Hotel buscarPorEndereco(String endereco);
	
	List<Hotel> getAll();
	
	List<Hotel> buscarPorCategoria(String categoria);
		
	
	

}
