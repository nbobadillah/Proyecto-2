package modelo;

import java.util.*; 
import fabrica.*;
import piezas.*;
import usuarios.*;
import exceptions.*;


public class VentaPiezas {

	private Usuario comprador;
	private Pieza piezaAVender;
	
	
	public VentaPiezas(Usuario comprador, Pieza pieza) {
		this.comprador = comprador;
		this.piezaAVender = pieza;
		
	}
	
// Se verifica si la pieza está disponible para la venta
	public boolean verificarEstadoDeVenta(Pieza piezaAVender, Galeria galeria) throws VentaImposibleException {
		if ((galeria.getPiezasActuales().contains(piezaAVender) == false) || (piezaAVender.isDispventa() == false)){
			throw new VentaImposibleException(piezaAVender.getTitulo());
		}
		else {
			return true;
		}
	}
	
	
	public void venderPieza( Usuario  comprador, Pieza piezaAVender, Galeria galeria) throws DineroInsuficienteException {
		
	
			if (((Comprador)comprador).getDinero() < piezaAVender.getValor()) {
				throw new DineroInsuficienteException(comprador.getNombre());
				}
			else  {
				((Comprador)comprador).añadirPieza(piezaAVender);
				galeria.getPiezasActuales().remove(piezaAVender);
				piezaAVender.setConsignacion(false);
				piezaAVender.setdispsubasta(false);	
				piezaAVender.setExhibida(false);
				piezaAVender.setPropietario(comprador);
				
				int dineroActualizado = ((Comprador)comprador).getDinero() - piezaAVender.getValor();
				
				((Comprador)comprador).setDinero(dineroActualizado);
			}
			
			
			}
	
	
}
