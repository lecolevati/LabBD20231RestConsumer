package br.edu.fateczl.springrestconsumer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Usuario {

	private int id;
	private String login;
	private String nome;
	private String senha;
	private Depto depto;
	
}
