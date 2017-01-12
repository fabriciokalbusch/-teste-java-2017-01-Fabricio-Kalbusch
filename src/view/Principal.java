package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.InformacoesLidas;
import controller.buscador.BuscadorDados;
import controller.leitores.LeitorArquivoCSV;

public class Principal {
	
	private static final String COMANDO_SAIDA = "sair";

	public static void main(String[] args) {
		System.out.println("Buscador de Registros CSV");
		System.out.print("Indique o caminho do arquivo CSV:");
		Scanner leitor = new Scanner(System.in);
		String caminho = leitor.nextLine();
		LeitorArquivoCSV leitorCSV = new LeitorArquivoCSV();
		BuscadorDados buscador = new BuscadorDados(leArquivo(caminho, leitorCSV));
		String comando;
		do {
			System.out.print("Insira o comando desejado:");
			comando = leitor.nextLine();
			busca(buscador, comando);
		} while (!comando.equals(COMANDO_SAIDA));
		leitor.close();
	}

	private static List<InformacoesLidas> leArquivo(String caminho, LeitorArquivoCSV leitorCSV) {
		try {
			
			return leitorCSV.le(caminho);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<InformacoesLidas>();
	}

	private static void busca(BuscadorDados buscador, String comando) {
		if (comando.equals(COMANDO_SAIDA)) {
			return;
		}
		try {
			System.out.println(buscador.busca(comando));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}