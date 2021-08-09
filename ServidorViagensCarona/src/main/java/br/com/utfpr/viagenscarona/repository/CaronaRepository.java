package br.com.utfpr.viagenscarona.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
import br.com.utfpr.viagenscarona.repository.entity.CaronaEntity;

public class CaronaRepository {
	 
	private final EntityManagerFactory entityManagerFactory;
 
	private final EntityManager entityManager;
 
	public CaronaRepository(){
 
		/*CRIANDO O NOSSO EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO persistence.xml */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_carona");
 
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * */
	public void Salvar(CaronaEntity caronaEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(caronaEntity);
		this.entityManager.getTransaction().commit();
	}
	
}