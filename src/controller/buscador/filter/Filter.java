package controller.buscador.filter;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.InformacoesLidas;
import controller.buscador.BuscadorRegistros;
import controller.excecoes.ComandoInvalidoException;
import controller.excecoes.PropriedadeInvalidaException;

public class Filter implements BuscadorRegistros {

	private List<InformacoesLidas> dados;
	private DadosComandoFilter dadosComando;
	
	public String executa(List<InformacoesLidas> dados, String comando) throws Exception {
		this.dados = dados;
		this.dadosComando = separaComando(comando);
		
		StringBuilder retorno = new StringBuilder();
		retorno.append(criaCabecalho());
		retorno.append("\n");
		retorno.append(criaImpressaoDados());
		return retorno.toString();
	}
	
	private String criaCabecalho() {
		StringBuilder cabecalho = new StringBuilder();
		for (Iterator<String> propriedadeIterator = getPropriedades().iterator(); propriedadeIterator.hasNext() ;) {
			String propriedade = propriedadeIterator.next();
			cabecalho.append(propriedade);
			if (propriedadeIterator.hasNext()) {
				adicionaSeparador(cabecalho);
			}
		}
		return cabecalho.toString();
	}

	private Set<String> getPropriedades() {
		int primeiraPosicao = 0;
		return dados.get(primeiraPosicao).getPropriedadeValor().keySet();
	}

	private String criaImpressaoDados() throws Exception {
		StringBuilder impressaoDados = new StringBuilder();
		for (InformacoesLidas dado : dados) {
			if (getValor(dadosComando.getPropriedade(), dado).equals(dadosComando.getValor())) {
				impressaoDados.append(criaLinhaDeRespostas(dado));
				impressaoDados.append("\n");
			}
		}
		return trataRetorno(impressaoDados);
	}

	private DadosComandoFilter separaComando(String comando) throws ComandoInvalidoException {
		return new SeparadorComandoFilter().separa(comando);
	}

	private String getValor(String propriedade, InformacoesLidas dado) throws PropriedadeInvalidaException {
		String valor = dado.getPropriedadeValor().get(propriedade);
		if (valor == null) {
			throw new PropriedadeInvalidaException();
		}
		return valor;
	}
	
	private String criaLinhaDeRespostas(InformacoesLidas dado) {
		StringBuilder impressaoDados = new StringBuilder();
		for (Iterator<String> valoresIterator = dado.getPropriedadeValor().values().iterator(); valoresIterator.hasNext(); ) {
			String valor = valoresIterator.next();
			impressaoDados.append(valor);
			if (valoresIterator.hasNext()) {
				adicionaSeparador(impressaoDados);
			}
		}
		return impressaoDados.toString();
	}

	private void adicionaSeparador(StringBuilder impressaoDados) {
		impressaoDados.append(" - ");
	}

	private String trataRetorno(StringBuilder impressaoDados) {
		if (impressaoDados.toString().isEmpty()) {
			return "Não há registros para o comando informado.";
		}
		return impressaoDados.substring(0, impressaoDados.length() - 1).toString();
	}
	
}