package usuarios;
import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;


public class Operador extends Empleado{
	
	private static final String tipo="Operador";
	
	public Operador(String login, String password, int telefono, String nombre) {
		
		super(login, password, telefono, nombre);
		// TODO Auto-generated constructor stub
	}
	
	public String getTipo() {
		
		return this.tipo;
	}
	
	public ArrayList <Pieza> mostrarPiezasASubastar(Galeria galeria){
		
		ArrayList <Pieza> piezasasubastar = new ArrayList<Pieza>();
		
		ArrayList <Pieza> piezasbodega = galeria.obtenerPiezasEnBodega();
		
		ArrayList <Pieza> piezasexhibidas = galeria.obtenerPiezasEnExhibicion();
		
		for (Pieza piezaactual: piezasbodega) {
			
			boolean dispsubasta = piezaactual.isDispsubasta();
			
			if(dispsubasta) {
				
				piezasasubastar.add(piezaactual);
				
			}
			
		}
		
		for (Pieza piezaactual: piezasexhibidas) {
			
			boolean dispsubasta = piezaactual.isDispsubasta();
			
			if(dispsubasta) {
				
				piezasasubastar.add(piezaactual);
				
			}
			
		}
		
		return piezasasubastar;
		
	}
	
	public void mostrarValorInicial (ArrayList <Pieza> piezas) {
		
		for(Pieza piezaactual: piezas) {
			
			int valorinicial = piezaactual.getValor();
			
			System.out.println("El valor inicial de ");
			
			System.out.println(piezaactual);
			
			System.out.println("Es de: ");
			
			System.out.println(valorinicial);
			
			System.out.println("\n");
			
		}
			
	}
	
	public void registrarOferta(int valorofrecido, Pieza pieza, Usuario usuario, Subasta subasta) throws DineroOfrecidoInsuficienteException, DineroInsuficienteException {
		
		subasta.registrarOferta(usuario, valorofrecido, pieza);
		
	}
	
}
	

