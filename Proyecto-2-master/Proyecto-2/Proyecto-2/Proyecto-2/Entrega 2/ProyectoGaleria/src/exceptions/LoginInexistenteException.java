package exceptions;

@SuppressWarnings("serial")
public class LoginInexistenteException extends Exception{	
	
	public  LoginInexistenteException(String login) {
		super("El login" + login + " no está registrado. \n");	
	}

}
