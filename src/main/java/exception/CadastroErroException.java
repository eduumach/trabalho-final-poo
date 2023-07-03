package exception;

public class CadastroErroException extends Exception {
    private static final long serialVersionUID = 1L;

    public CadastroErroException() {
        super("Cadastro invalido: usuario ja existe!");
    }
}
