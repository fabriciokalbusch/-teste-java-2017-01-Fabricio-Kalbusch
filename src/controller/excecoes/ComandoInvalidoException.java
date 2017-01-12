package controller.excecoes;

public class ComandoInvalidoException extends Exception {

	private static final long serialVersionUID = -4982358632777752265L;

	public ComandoInvalidoException() {
		super("O comando executado � inv�lido.\n"
			+ "Comandos aceitos:\n"
			+ " - count * - escreve no console a contagem total de registros importados (n�o deve considerar a linha de cabe�alho)\n"
			+ " - count distinct [propriedade] - escreve no console o total de valores distintos da propriedade (coluna) enviada\n"
			+ " - filter [propriedade] [valor] - escreve no console a linha de cabe�alho e todas as linhas em que a propriedade enviada possua o valor enviado");
	}

}