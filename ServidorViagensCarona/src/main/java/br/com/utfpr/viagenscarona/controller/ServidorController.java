package br.com.utfpr.viagenscarona.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;

import br.com.utfpr.viagenscarona.model.Carona;
import br.com.utfpr.viagenscarona.repository.entity.CaronaEntity;
import br.com.utfpr.viagenscarona.repository.CaronaRepository;
import br.com.utfpr.viagenscarona.ServidorImpl;

/**
 * Essa classe vai expõe os métodos para serem acessados via http
 * 
 * @Path - Caminho para a chamada da classe que representa o nosso serviço
 * */
@Path("/service")
public class ServidorController{

	
	private final CaronaRepository repository = new CaronaRepository();
	
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@GET	
	@Produces("application/json; charset=UTF-8")
	@Path("/teste")
	public String Teste(){
 
		return "Hello World";
 
	}
	
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/registrarInteresse")
	public int RegistrarInteresse(Carona carona){
 
		CaronaEntity entity = new CaronaEntity();
		 
		try {
 
			entity.setNome(carona.getNome());
			entity.setContato(carona.getContato());
			entity.setOrigem(carona.getOrigem());
			entity.setDestino(carona.getDestino());
			entity.setData(carona.getData());
			entity.setNumPassageiros(carona.getNumPassageiros());
			entity.setTipo(carona.getTipo());
 
			repository.Salvar(entity);
 
			//return "Registro cadastrado com sucesso!";
 
		} catch (Exception e) {
 
			//return "Erro ao cadastrar um registro " + e.getMessage();
			return 0;
		}
		
		// verifica match e notifica clientes
		// remove caronas notificadas cheias
		
		return 1;

	}
	
}