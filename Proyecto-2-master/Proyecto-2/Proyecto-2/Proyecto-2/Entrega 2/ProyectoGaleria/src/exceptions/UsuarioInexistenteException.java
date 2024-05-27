package exceptions;


@SuppressWarnings("serial")
public class UsuarioInexistenteException extends Exception{	
	
	public  UsuarioInexistenteException(String usuario) {
		super("El usuario" + usuario + " no se encuentra registrado en esta galeria. \n");	
	}

}