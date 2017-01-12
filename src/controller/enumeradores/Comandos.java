package controller.enumeradores;

import controller.buscador.BuscadorRegistros;
import controller.buscador.count.Count;
import controller.buscador.filter.Filter;


public enum Comandos {

	COUNT("count", new Count()), 
	FILTER("filter", new Filter());
	
	private String nomeComando;
	private BuscadorRegistros buscador;

	private Comandos(String nomeComando, BuscadorRegistros buscador) {
		this.nomeComando = nomeComando;
		this.buscador = buscador;
	}
	
	public String getNomeComando() {
		return nomeComando;
	}

	public BuscadorRegistros getBuscador() {
		return buscador;
	}
	
}