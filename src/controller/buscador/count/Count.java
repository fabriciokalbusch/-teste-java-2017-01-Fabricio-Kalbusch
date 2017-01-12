package controller.buscador.count;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.InformacoesLidas;
import controller.buscador.BuscadorRegistros;
import controller.enumeradores.Comandos;
import controller.excecoes.ComandoInvalidoException;
import controller.excecoes.PropriedadeInvalidaException;

public class Count implements BuscadorRegistros {

	private List<InformacoesLidas> dados;

	public String executa(List<InformacoesLidas> dados, String comando) throws Exception {
		this.dados = dados;
		if (comando.equals(Comandos.COUNT.getNomeComando() + " *")) {
			return String.valueOf(dados.size());
		} else if (comando.startsWith(Comandos.COUNT.getNomeComando() + " distinct")) {
			return retornaValoresDiferentesDaPropriedade(comando);
		}
		throw new ComandoInvalidoException();
	}

	private String retornaValoresDiferentesDaPropriedade(String comando) throws PropriedadeInvalidaException {
		String propriedade = comando.split(" ")[2];
		Set<String> valoresDiferentes = new HashSet<String>();
		for (InformacoesLidas dado : dados) {
			String valor = getValor(propriedade, dado);
			valoresDiferentes.add(valor);
		}
		return String.valueOf(valoresDiferentes.size());
	}

	private String getValor(String propriedade, InformacoesLidas dado) throws PropriedadeInvalidaException {
		String valor = dado.getPropriedadeValor().get(propriedade);
		if (valor == null) {
			throw new PropriedadeInvalidaException();
		}
		return valor;
	}
	
}