package controller.buscador.count;

import java.util.ArrayList;
import java.util.List;

import model.InformacoesLidas;
import model.InformacoesLidasBuilder;

import org.junit.Before;
import org.junit.Test;

import controller.buscador.count.Count;
import controller.excecoes.ComandoInvalidoException;
import controller.excecoes.PropriedadeInvalidaException;

public class CountTest {
	
	private Count contador;
	private List<InformacoesLidas> dados;
	
	@Before
	public void setUp() {
		contador = new Count();
		dados = new ArrayList<InformacoesLidas>();
	}

	@Test(expected=PropriedadeInvalidaException.class)
	public void deveLancarExcecaoQuandoNaoEncontrarAPropriedadeEspecificada() throws Exception {
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").build());
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "João").adicionaInformacao("sobrenome", "Silva").build());
		contador.executa(dados, "count distinct idade");
	}
	
	@Test(expected=ComandoInvalidoException.class)
	public void deveLancarExcecaoCasoNaoEncontrarOComandoDeCountCorreto() throws Exception {
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "João").adicionaInformacao("sobrenome", "Silva").build());
		contador.executa(dados, "count idade");
	}
	
}
