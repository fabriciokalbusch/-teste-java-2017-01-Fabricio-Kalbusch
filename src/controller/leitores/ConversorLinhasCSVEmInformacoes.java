package controller.leitores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import model.InformacoesLidas;

public class ConversorLinhasCSVEmInformacoes {

	private static final String DELIMITADOR = ",";
	private static final int LINHA_CABECALHO = 0;

	public List<InformacoesLidas> converte(List<String> linhasLidas) {
		return criaInformacoes(linhasLidas, getPropriedades(linhasLidas));
	}

	private List<InformacoesLidas> criaInformacoes(List<String> linhasLidas, List<String> propriedadesCabecalho) {
		List<InformacoesLidas> informacoesLidas = new ArrayList<InformacoesLidas>();
		for (int i = 1; i < linhasLidas.size(); i++) {
			List<String> valores = Arrays.asList(linhasLidas.get(i).split(DELIMITADOR));
			informacoesLidas.add(criaPropriedades(propriedadesCabecalho, valores));
		}
		return informacoesLidas;
	}

	private InformacoesLidas criaPropriedades(List<String> propriedadesCabecalho, List<String> valores) {
		InformacoesLidas informacoes = new InformacoesLidas();
		informacoes.setPropriedadeValor(new LinkedHashMap<String, String>());
		for (int i = 0; i < propriedadesCabecalho.size(); i++) {
			informacoes.getPropriedadeValor().put(propriedadesCabecalho.get(i), valores.get(i));
		}
		return informacoes;
	}

	private List<String> getPropriedades(List<String> linhasLidas) {
		return Arrays.asList(linhasLidas.get(LINHA_CABECALHO).split(DELIMITADOR));
	}

}