package controller.buscador.filter;

import java.util.ArrayList;
import java.util.List;

import model.InformacoesLidas;
import model.InformacoesLidasBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.buscador.filter.Filter;
import controller.excecoes.ComandoInvalidoException;
import controller.excecoes.PropriedadeInvalidaException;

public class FilterTest {

	private Filter buscador;
	private List<InformacoesLidas> dados;
	
	@Before
	public void setUp() {
		buscador = new Filter();
		dados = new ArrayList<InformacoesLidas>();
	}
	
	@Test(expected = PropriedadeInvalidaException.class)
	public void deveLancarExcecaoQuandoNaoExisteAPropriedadeEnviada() throws Exception {
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").build());
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "João").adicionaInformacao("sobrenome", "Silva").build());
		buscador.executa(dados, "filter idade 25");
	}
	
	@Test
	public void deveRetonarOCabecalhoEUmaMensagemQuandoNaoHaRegistrosParaOComandoInformado() throws Exception {
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").adicionaInformacao("idade", "25").build());
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "João").adicionaInformacao("sobrenome", "Silva").adicionaInformacao("idade", "25").build());
		
		String retorno = buscador.executa(dados, "filter nome Maria");
		Assert.assertEquals("nome - sobrenome - idade\nNão há registros para o comando informado.", retorno);
	}
	
	@Test
	public void deveFiltrarValoresCompostos() throws Exception {
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio Kalbusch").adicionaInformacao("idade", "25").build());
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "João da Silva").adicionaInformacao("idade", "37").build());
		
		String retorno = buscador.executa(dados, "filter nome João da Silva");
		Assert.assertEquals("nome - idade\nJoão da Silva - 37", retorno);
	}

	@Test(expected = ComandoInvalidoException.class)
	public void naoDeveFiltrarPoisOComandoEstaIncompleto() throws Exception {
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio Kalbusch").adicionaInformacao("idade", "25").build());
		dados.add(new InformacoesLidasBuilder().adicionaInformacao("nome", "João da Silva").adicionaInformacao("idade", "37").build());
		
		buscador.executa(dados, "filter *");
	}
	
}
