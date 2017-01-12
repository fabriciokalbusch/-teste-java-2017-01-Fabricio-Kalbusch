package controller.leitores;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import model.InformacoesLidas;

import org.junit.Before;
import org.junit.Test;

import controller.leitores.ConversorLinhasCSVEmInformacoes;

public class ConversorLinhasCSVEmInformacoesTest {

	private ConversorLinhasCSVEmInformacoes conversor;
	
	@Before
	public void setUp() {
		conversor = new ConversorLinhasCSVEmInformacoes();
	}
	
	@Test
	public void deveConverterSomenteUmaLinhaDeValores() {
		String primeiraLinha = "nome,cidade,telefone,email,estado";
		String segundaLinha = "fabricio,sao jose,(48)99999-9999,fabricio@eu.com,sc";
		
		List<InformacoesLidas> informacoesLidas = conversor.converte(Arrays.asList(primeiraLinha, segundaLinha));
		
		assertEquals(1, informacoesLidas.size());
		assertEquals(5, informacoesLidas.get(0).getPropriedadeValor().size());
		assertEquals("fabricio", informacoesLidas.get(0).getPropriedadeValor().get("nome"));
		assertEquals("sao jose", informacoesLidas.get(0).getPropriedadeValor().get("cidade"));
		assertEquals("(48)99999-9999", informacoesLidas.get(0).getPropriedadeValor().get("telefone"));
		assertEquals("fabricio@eu.com", informacoesLidas.get(0).getPropriedadeValor().get("email"));
		assertEquals("sc", informacoesLidas.get(0).getPropriedadeValor().get("estado"));
	}
	
	@Test
	public void deveConverterSomenteDuasLinhasDeValores() {
		String primeiraLinha = "nome,cidade,telefone,email,estado";
		String segundaLinha = "fabricio,sao jose,(48)99999-9999,fabricio@eu.com,sc";
		String terceiraLinha = "kalbusch,florianopolis,,kalbusch@eu.com,sp";
		
		List<InformacoesLidas> informacoesLidas = conversor.converte(Arrays.asList(primeiraLinha, segundaLinha, terceiraLinha));
		
		assertEquals(2, informacoesLidas.size());
		assertEquals(5, informacoesLidas.get(0).getPropriedadeValor().size());
		assertEquals("fabricio", informacoesLidas.get(0).getPropriedadeValor().get("nome"));
		assertEquals("sao jose", informacoesLidas.get(0).getPropriedadeValor().get("cidade"));
		assertEquals("(48)99999-9999", informacoesLidas.get(0).getPropriedadeValor().get("telefone"));
		assertEquals("fabricio@eu.com", informacoesLidas.get(0).getPropriedadeValor().get("email"));
		assertEquals("sc", informacoesLidas.get(0).getPropriedadeValor().get("estado"));

		assertEquals("kalbusch", informacoesLidas.get(1).getPropriedadeValor().get("nome"));
		assertEquals("florianopolis", informacoesLidas.get(1).getPropriedadeValor().get("cidade"));
		assertEquals("", informacoesLidas.get(1).getPropriedadeValor().get("telefone"));
		assertEquals("kalbusch@eu.com", informacoesLidas.get(1).getPropriedadeValor().get("email"));
		assertEquals("sp", informacoesLidas.get(1).getPropriedadeValor().get("estado"));
	}
	
}