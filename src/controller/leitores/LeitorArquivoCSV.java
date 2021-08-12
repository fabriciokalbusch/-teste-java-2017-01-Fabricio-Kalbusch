package controller.leitores;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.InformacoesLidas;

public class LeitorArquivoCSV {

	public List<InformacoesLidas> le(String localizacaoArquivo) throws FileNotFoundException {

		FileInputStream stream = null;
		BufferedReader br = null;
		try {
			stream = new FileInputStream(localizacaoArquivo);
			InputStreamReader reader = new InputStreamReader(stream, "UTF8");
			br = new BufferedReader(reader);
			String linha = br.readLine();
			List<String> linhasLidas = new ArrayList<String>();
			linhasLidas.add(linha);
			while (linha != null) {
				linha = br.readLine();
				if (linha != null && !linha.equals("")) {
					linhasLidas.add(linha);
				}
			}
			
			return new ConversorLinhasCSVEmInformacoes().converte(linhasLidas);
		} catch (FileNotFoundException arquivoNaoEncontrado) {
			throw new FileNotFoundException("Arquivo não encontrado.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
			}
		}
		return new ArrayList<InformacoesLidas>();
	}

}
