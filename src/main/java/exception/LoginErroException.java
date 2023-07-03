package exception;

public class LoginErroException extends Exception{	
	private static final long serialVersionUID = 1L;

	public LoginErroException() {
		super("Login invalido: usuario ou senha incorreto!");
	}
}

