package br.com.ramon.barros.repo.exceptions;

public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RepositoryException(String msg) {
		super(msg);
	}
	
	public RepositoryException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
