package controller.buscador;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import model.InformacoesLidas;
import model.InformacoesLidasBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.excecoes.ComandoInvalidoException;


public class BuscadorDadosTest {

	private BuscadorDados buscador;
	private List<InformacoesLidas> dados;
	
	@Before
	public void setUp() {
		dados = new ArrayList<InformacoesLidas>();
		buscador = new BuscadorDados(dados);
	}
	
	@Test
	public void deveRetornarZeroCasoNaoPossuaRegistros() throws Exception {
		String quantidade = buscador.busca("count *");
		Assert.assertEquals("0", quantidade);
	}
	
	@Test
	public void deveRetornarQuePossuiCincoRegistros() throws Exception {
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("Registro", "1").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("Registro", "2").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("Registro", "3").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("Registro", "4").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("Registro", "5").build());
		String quantidade = buscador.busca("count *");
		Assert.assertEquals("5", quantidade);
	}
	
	@Test
	public void deveRetornarAQuantidadeDeLinhasDiferenteComAPropriedadeInformada() throws Exception {
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "João").adicionaInformacao("sobrenome", "Silva").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Maria").adicionaInformacao("sobrenome", "Souza").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Maria").adicionaInformacao("sobrenome", "Silva").build());
		
		String quantidade = buscador.busca("count distinct nome");
		Assert.assertEquals("3", quantidade);

		quantidade = buscador.busca("count distinct sobrenome");
		Assert.assertEquals("3", quantidade);
	}
	
	@Test
	public void deveRetornarTodasAsPropriedadesNaPrimeiraLinha() throws Exception {
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").adicionaInformacao("idade", "25").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "João").adicionaInformacao("sobrenome", "Silva").adicionaInformacao("idade", "25").build());
		
		String retorno = buscador.busca("filter idade 25");
		
		Assert.assertTrue(retorno.startsWith("nome - sobrenome - idade"));
//		Assert.assertThat(retorno, CoreMatchers.startsWith("nome - sobrenome - idade"));
	}
	
	@Test
	public void deveRetornarAsLinhasQueContemplamOFiltroDePropriedadeEValor() throws Exception {
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").adicionaInformacao("idade", "25").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "João").adicionaInformacao("sobrenome", "Silva").adicionaInformacao("idade", "30").build());
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Maria").adicionaInformacao("sobrenome", "Silva").adicionaInformacao("idade", "28").build());
		
		String retorno = buscador.busca("filter sobrenome Silva");
		
		String resultadoEsperado = "nome - sobrenome - idade"
								+ "\nJoão - Silva - 30"
								+ "\nMaria - Silva - 28";
		Assert.assertEquals(resultadoEsperado, retorno);

	}
	
	@Test
	public void deveLancarExcecaoPoisOComandoEstaIncorreto() throws Exception {
		adicionaDado(new InformacoesLidasBuilder().adicionaInformacao("nome", "Fabricio").adicionaInformacao("sobrenome", "Kalbusch").adicionaInformacao("idade", "25").build());
		try {
			buscador.busca("cont *");
			fail("O sistema encontrou um comando que não deveria ter encontrado.");
		} catch (ComandoInvalidoException e) {
			assertTrue(true);
		}
		
		try {
			buscador.busca("fiter nome Fabricio");
			fail("O sistema encontrou um comando que não deveria ter encontrado.");
		} catch (ComandoInvalidoException e) {
			assertTrue(true);
		}
	}
	
	private void adicionaDado(InformacoesLidas informacoes) {
		dados.add(informacoes);
	}
	
}
