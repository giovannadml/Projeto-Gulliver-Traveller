package br.com.fiap.exception;

public class CommitException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommitException() {
		System.out.println("Erro ao realizar o commit das alterações");
	}
	
	public CommitException(String msg) {
		super(msg);
	}
}
