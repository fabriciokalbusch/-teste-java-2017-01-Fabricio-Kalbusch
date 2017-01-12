package controller.buscador;

import java.util.List;

import model.InformacoesLidas;
import controller.enumeradores.Comandos;
import controller.excecoes.ComandoInvalidoException;

public class BuscadorDados {

	private List<InformacoesLidas> dados;

	public BuscadorDados(List<InformacoesLidas> dados) {
		this.dados = dados;
	}

	public String busca(String comando) throws Exception {
		for (Comandos comandoEnum : Comandos.values()) {
			if (comando.startsWith(comandoEnum.getNomeComando())) {
				return comandoEnum.getBuscador().executa(dados, comando);
			}
		}
		throw new ComandoInvalidoException();
	}

}