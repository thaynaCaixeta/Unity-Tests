package br.com.caelum.auction.domain;

public class User {

	private int id;
	private String name;
	
	public User(String name) {
		this(0, name);
	}

	public User(int id, String nome) {
		this.id = id;
		this.name = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return name;
	}
	
	
	
}
