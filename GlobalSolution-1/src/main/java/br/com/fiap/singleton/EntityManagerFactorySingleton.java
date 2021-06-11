package br.com.fiap.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {

	private static EntityManagerFactory fabrica;
	
	private EntityManagerFactorySingleton() {
		super();
	}
	
	public static EntityManagerFactory getInstance() {
		if(fabrica == null) {
			fabrica = Persistence.createEntityManagerFactory("GlobalSolution-1-persistence-unit");
//			fabrica = Persistence.createEntityManagerFactory("oracle");

		}
		return fabrica;
	}
}
