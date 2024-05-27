package exceptions;



@SuppressWarnings("serial")
public class PropietarioErroneoException extends Exception{	
	
	public  PropietarioErroneoException (String propietario, String pieza)  {
			
		super("El usuario" + propietario + " no es el dueño de la pieza" + pieza + ".\n");	
	}

}
