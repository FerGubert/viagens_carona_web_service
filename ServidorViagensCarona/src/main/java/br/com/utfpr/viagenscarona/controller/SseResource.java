package br.com.utfpr.viagenscarona.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

 
@Path("/receberNotificacao")
@Singleton
public class SseResource{
 
	@Context
	Sse sse;
	Map<String, SseEventSink> mapaClientes = new HashMap<String, SseEventSink>();
	
    @GET
    public void registraCliente(@Context SseEventSink sseEventSink, @QueryParam("nomeCliente") String nomeCliente) {
    	mapaClientes.put(nomeCliente, sseEventSink);
    	enviaNotificacao(nomeCliente);
    }
    
    @Produces("text/event-stream")
    public void enviaNotificacao(String nomeCliente) {
    	SseEventSink sseEventSink = mapaClientes.get(nomeCliente);
        OutboundSseEvent sseEvent = (OutboundSseEvent) sse.newEventBuilder()
	        .name("teste")
	        .mediaType(MediaType.APPLICATION_JSON_TYPE)
	        .data("Passageiro/Motorista")
	        .build();
	    sseEventSink.send(sseEvent);

    }
       

}