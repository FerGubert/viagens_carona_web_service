package br.com.utfpr.viagenscarona.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.utfpr.viagenscarona.repository.entity.CaronaEntity;

public class CaronaRepository {
	 
	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
 
	public CaronaRepository(){
 
		/*CRIA O EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO persistence.xml */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_carona");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		
	}
	
	/**
	 * CRIA UM NOVO REGISTRO DE INTERESSE NO BANCO DE DADOS
	 * */
	public void Salvar(CaronaEntity caronaEntity){
		
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(caronaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * CONSULTA CARONA COM MESMA ORIGEM, DESTINO E DATA 
	 * MAS DO TIPO OPOSTO E RETORNO O PRIMEIRO REGISTRO ENCONTRADO
	 * */
	public CaronaEntity GetCaronaMatch(String origem, String destino, String data, int tipo){
		
		Query query = this.entityManager.createNativeQuery("select * from registro_interesse " +
				"where origem = :paramOrigem " +
				"and destino = :paramDestino " +
				"and data = :paramData " +
				"and tipo = :paramTipo limit 1");
        query.setParameter("paramOrigem", origem);
        query.setParameter("paramDestino", destino);
        query.setParameter("paramData", data);
        query.setParameter("paramTipo", tipo);
        
        List<Object[]> objs = query.getResultList();
        if(objs.size() == 0)
        	return null;
        
        Object[] firstObj = objs.get(0);
        CaronaEntity entityFind = new CaronaEntity();
        entityFind.setId((Integer)firstObj[0]);
        entityFind.setNome((String)firstObj[1]);
        entityFind.setContato((String)firstObj[2]);
        entityFind.setOrigem((String)firstObj[3]);
        entityFind.setDestino((String)firstObj[4]);
        entityFind.setData((String)firstObj[5]);
        entityFind.setNumPassageiros((Integer)firstObj[6]);
        entityFind.setTipo((Integer)firstObj[7]);
		
        return entityFind;

	}
	
	/**
	 * EXCLUI UM REGISTRO DE INTERESSE
	**/
	public void Excluir(Integer id){
 
		CaronaEntity carona = this.GetCarona(id);
		
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(carona);
		this.entityManager.getTransaction().commit();
 
	}
	
	/**
	 * ALTERA UM REGISTRO DE INTERESSE
	 * */
	public void Alterar(CaronaEntity caronaEntity){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(caronaEntity);
		this.entityManager.getTransaction().commit();
	}
	
	/**
	 * CONSULTA UM REGISTRO DE INTERESSE PELO ID
	 * */
	public CaronaEntity GetCarona(Integer id){
 
		return this.entityManager.find(CaronaEntity.class, id);
	}
	
	/**
	 * CONSULTA CARONAS OFERTADAS COM MESMA ORIGEM, DESTINO E DATA 
	 * */
	public int GetCaronasDisponiveis(String origem, String destino, String data){
		
		Query query = this.entityManager.createNativeQuery("select * from registro_interesse " +
				"where origem = :paramOrigem " +
				"and destino = :paramDestino " +
				"and data = :paramData " +
				"and tipo = 1");
        query.setParameter("paramOrigem", origem);
        query.setParameter("paramDestino", destino);
        query.setParameter("paramData", data);
        
        List<Object[]> objs = query.getResultList();		
        return objs.size();
		
	}
}