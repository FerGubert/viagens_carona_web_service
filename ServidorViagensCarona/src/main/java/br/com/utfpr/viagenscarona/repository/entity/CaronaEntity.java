package br.com.utfpr.viagenscarona.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="registro_interesse")
public class CaronaEntity{
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
 
	@Column(name="nome")	
	private String  nome;
 
	@Column(name="contato")
	private String contato;
	
	@Column(name="origem")
	private String origem;
	
	@Column(name="destino")
	private String destino;
	
	@Column(name="data")
	private String data;
	
	@Column(name="numPassageiros")
	private int numPassageiros;

	@Column(name="tipo")
	private int tipo;
	
	public CaronaEntity(){}
	
	public int getId() {
		return id;
	}
 
	public void setCodigo(int id) {
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