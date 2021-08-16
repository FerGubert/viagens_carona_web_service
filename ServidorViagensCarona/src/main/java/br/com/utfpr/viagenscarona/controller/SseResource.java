package br.com.utfpr.viagenscarona.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.macias.sse.servlet3.ServletEventTarget;

@WebServlet(asyncSupported = true)
public class SseResource extends HttpServlet{
	
	ServletEventTarget target;
	static Map<String, ServletEventTarget> mapaClientes = new HashMap<String, ServletEventTarget>();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		target = new ServletEventTarget(req).ok().open();
		mapaClientes.put(req.getParameter("nomeCliente"), target);
    }

    public void enviaNotificacao(String nomeClienteTarget, String info) throws IOException{
    	ServletEventTarget channelTarget;
    	channelTarget = mapaClientes.get(nomeClienteTarget);
    	System.out.println(channelTarget);
    	channelTarget.send("givenEvent", info);
    }
	
}

