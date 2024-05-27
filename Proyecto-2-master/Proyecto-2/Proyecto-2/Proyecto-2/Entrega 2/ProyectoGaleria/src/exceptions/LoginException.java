package exceptions;

@SuppressWarnings("serial")
public class LoginException extends Exception{	
	
	public  LoginException(String login) {
		super("El login " + login + " ya está registrado. Por favor use otro login. \n");	
	}

}
