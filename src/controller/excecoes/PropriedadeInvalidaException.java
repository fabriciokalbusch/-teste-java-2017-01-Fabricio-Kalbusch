package controller.excecoes;

public class PropriedadeInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	public PropriedadeInvalidaException() {
		super("A propriedade expecificada é inválida.");
	}
	
}
