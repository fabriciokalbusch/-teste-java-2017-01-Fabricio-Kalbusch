package controller.buscador;

import java.util.List;

import model.InformacoesLidas;

public interface BuscadorRegistros {

	String executa(List<InformacoesLidas> dados, String comando) throws Exception;
	
}
