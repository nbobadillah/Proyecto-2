package exceptions;

@SuppressWarnings("serial")
public class VentaImposibleException extends Exception{	
	
	public  VentaImposibleException(String pieza) {
		super("La pieza" + pieza + " no se encuentra disponible para la venta. \n");	
	}

}