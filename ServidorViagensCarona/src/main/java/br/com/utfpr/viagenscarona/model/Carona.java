package br.com.utfpr.viagenscarona.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Carona{

    private int id;
	private String nome;
	private String contato;
	private String origem;
    private String destino;
    private String data;
    private int numPassageiros;
	private int tipo; //0 para passageiro e 1 para motorista

	public Carona(){}
	
	public Carona(int id, String nome, String contato) {
		super();
		this.id = id;
		this.nome = nome;
		this.contato = contato;
	}
	
    public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getContato() {
		return contato;
	}
	
	public void setContato(String contato) {
		this.contato = contato;
	}

    public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getDestino() {
		return destino;
	}

    public void setDestino(String destino) {
		this.destino = destino;
	}

    public String getData() {
		return data;
	}

    public void setData(String data) {
		this.data = data;
	}

    public int getNumPassageiros() {
		return numPassageiros;
	}
	
	public void setNumPassageiros(int numPassageiros) {
		this.numPassageiros = numPassageiros;
	}

	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}