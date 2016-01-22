package br.com.guarani.rta.dao;

import java.util.List;

import br.com.guarani.rta.entidade.Entity;

//Assinaturas dos métodos.

public interface Dao<T extends Entity, I>
{

	List<T> findAll();


	T find(I id);


	T save(T newsEntry);


	void delete(I id);

}