package controller.buscador.filter;

import controller.excecoes.ComandoInvalidoException;

public class SeparadorComandoFilter {

	public DadosComandoFilter separa(String comando) throws ComandoInvalidoException {
		DadosComandoFilter dados = new DadosComandoFilter();
		String[] comandos = comando.split(" ");
		if (comandos.length < 3) {
			throw new ComandoInvalidoException();
		}
		dados.setPropriedade(getPropriedadeDoComando(comandos));
		dados.setValor(getValorDoComando(comandos));
		return dados;
	}
	
	private String getPropriedadeDoComando(String[] comandos) {
		return comandos[1];
	}

	private String getValorDoComando(String[] comandos) {
		StringBuilder valor = new StringBuilder();
		for (int i = 2 ; i < comandos.length; i++) {
			valor.append(comandos[i]);
			if (i < comandos.length - 1) {
				valor.append(" ");
			}
		}
		return valor.toString();
	}
	
}
