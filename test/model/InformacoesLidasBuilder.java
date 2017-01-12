package model;

import java.util.LinkedHashMap;
import java.util.Map;

import model.InformacoesLidas;

public class InformacoesLidasBuilder {

	private Map<String, String> propriedadeValor;
	
	public InformacoesLidasBuilder() {
		propriedadeValor = new LinkedHashMap<String, String>();
	}
	
	public InformacoesLidasBuilder adicionaInformacao(String propriedade, String valor) {
		propriedadeValor.put(propriedade, valor);
		return this;
	}
	
	public InformacoesLidas build() {
		InformacoesLidas informacoes = new InformacoesLidas();
		informacoes.setPropriedadeValor(propriedadeValor);
		return informacoes;
	}
	
}
