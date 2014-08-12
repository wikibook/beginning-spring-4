package sample.spring.chapter09.bankapp.exception;

public class BankAccountAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5276322437798759498L;

	public BankAccountAlreadyExistsException(String msg) {
		super(msg);
	}
}
