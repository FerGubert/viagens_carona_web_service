package br.com.utfpr.viagenscarona.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;

import br.com.utfpr.viagenscarona.model.Carona;
import br.com.utfpr.viagenscarona.repository.entity.CaronaEntity;
import br.com.utfpr.viagenscarona.repository.CaronaRepository;

/**
 * Essa classe expõe os métodos para serem acessados via http
 * */
@Path("/service")
public class ServidorController{
	
	private final CaronaRepository repository = new CaronaRepository();
	
	/**
	 * Método para testar uma requisição simples
	 * */
	@GET	
	@Produces("application/json; charset=UTF-8")
	@Path("/teste")
	public String Teste(){
 
		return "Hello World";
 
	}
	
	/**
	 * Esse método cadastra o registro de interesse,
	 * verifica se existe match com alguma carona já cadastrada,
	 * se for o caso notifica os clientes envolvidos
	 * e faz o controle da quantidade de assentos do motorista envolvido
	 * */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/plain")
	@Path("/registrarInteresse")
	public int RegistrarInteresse(Carona carona){
 
		CaronaEntity entityNew = new CaronaEntity();
		 
		try{
 
			entityNew.setNome(carona.getNome());
			entityNew.setContato(carona.getContato());
			entityNew.setOrigem(carona.getOrigem());
			entityNew.setDestino(carona.getDestino());
			entityNew.setData(carona.getData());
			entityNew.setNumPassageiros(carona.getNumPassageiros());
			entityNew.setTipo(carona.getTipo());
 
			repository.Salvar(entityNew);
 
		} catch (Exception e) {
 
			System.out.println("Erro ao cadastrar um registro " + e.getMessage());
			return 0;
		}
		
		// verifica match
		int tipo_busca = 1 - entityNew.getTipo();
		CaronaEntity entityFind = new CaronaEntity();
		entityFind = repository.GetCaronaMatch(entityNew.getOrigem(), entityNew.getDestino(), entityNew.getData(), tipo_busca);
		if(entityFind == null)
			return 1;
		
		// TODO notifica clientes
	
		// guarda a entidade que corresponde ao motorista
		CaronaEntity entityDriver = new CaronaEntity();
		if(entityNew.getTipo() == 1)
			entityDriver = entityNew;
		else
			entityDriver = entityFind;
		
		// verifica a quantidade de assentos
		if(entityDriver.getNumPassageiros() - 1 == 0) {
			
			// exclui registro
			try {
				repository.Excluir(entityDriver.getId());
			}catch (Exception e) {
				System.out.println("Erro ao excluir o registro! " + e.getMessage());
				return 0;
			}
			
		}else{
			
			// update registro
			entityDriver.setNumPassageiros(entityDriver.getNumPassageiros() - 1);
			try{
				repository.Alterar(entityDriver);
			} catch (Exception e) {
				System.out.println("Erro ao alterar o registro " + e.getMessage());
				return 0;
		 
			}
		}
		
		return 1;

	}
	
	/**
	 * Esse método busca as caronas disponíveis
	 * de acordo com os parâmetros de interesse
	 * */
	@GET
	@Produces("text/plain")
	@Path("/consultarCaronas/{origem}{destino}{data}")
	public String ConcultarCaronas(@PathParam("origem") String origem, @PathParam("destino") String destino, @PathParam("data") String data){
		
		int quantidadeCaronas = repository.GetCaronasDisponiveis(origem, destino, data);
		
		return "EXISTEM " + quantidadeCaronas + " CARONAS DISPONIVEIS.";
	}
	
	/**
	 * Esse método cancela um registro de interesse
	 * de acordo com o id e o nome
	 * */
	@DELETE
	@Produces("text/plain")
	@Path("/cancelarRegistroInteresse/{id}")
	public String CancelarRegistroInteresse(@PathParam("id") Integer id){
		System.out.println(id);
		try {
			repository.Excluir(id);
		}catch (Exception e) {
			System.out.println("Erro ao excluir o registro! " + e.getMessage());
			return "ERRO AO CANCELAR REGISTRO DE INTERESSE.";
		}
		
		return "CANCELAMENTO REALIZADO COM SUCESSO.";
	}
	
	/*@GET
	@Path("prices")
	@Produces("text/event-stream")
	public void getStockPrices(@Context SseEventSink sseEventSink, @Context Sse sse) {
	    //...
	}*/
	
}