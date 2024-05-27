package exceptions;


@SuppressWarnings("serial")
public class DineroOfrecidoInsuficienteException extends Exception{	
	
	public  DineroOfrecidoInsuficienteException() {
		super("El dinero ofrecido por la pieza es menor al valor inicial de la subasta. \n");	
	}

}