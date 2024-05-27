package exceptions;

@SuppressWarnings("serial")
public class DineroInsuficienteException extends Exception{	
	
	public  DineroInsuficienteException (String usuario)  {
			
		super("El usuario" + usuario + "no cuenta con el dinero suficiente para comprar esta pieza.\n");
	}
	
	public  DineroInsuficienteException ()  {
		
		super("El usuario no cuenta con el dinero suficiente para realizar dicha oferta.\n");
	}
	

}
