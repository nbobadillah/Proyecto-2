package usuarios;

import java.util.ArrayList;

import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;

public class Comprador extends Usuario{
	
	private int dinero;
	private int telefono;
	private int valorMaximoCompras;
	private ArrayList<Pieza> historialPiezas;
	private ArrayList<Pieza> piezasActuales;
	private static final String tipo="Comprador";
	
	public Comprador(String login, String password,  String nombre, int valorMaximoCompras,
			ArrayList<Pieza> historialPiezas, ArrayList<Pieza> piezasActuales, int dinero, int telefono) {
		super(login, password, nombre, telefono);
		this.dinero = dinero;
		this.telefono = telefono;
		this.valorMaximoCompras = valorMaximoCompras;
		this.historialPiezas = historialPiezas;
		this.piezasActuales = piezasActuales;	}

	public int getValorMaximoCompras() {
		return valorMaximoCompras;
	}

	public void setValorMaximoCompras(int valorMaximoCompras) {
		this.valorMaximoCompras = valorMaximoCompras;
	}

	public ArrayList<Pieza> getHistorialPiezas() {
		return historialPiezas;
	}

	public void setHistorialPiezas(ArrayList<Pieza> historialPiezas) {
		this.historialPiezas = historialPiezas;
	}

	public ArrayList<Pieza> getPiezasActuales() {
		return piezasActuales;
	}

	public void setPiezasActuales(ArrayList<Pieza> piezasActuales) {
		this.piezasActuales = piezasActuales;
	}

	public int getDinero() {
		return dinero;
	}
	
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public  String revisarEstadoPieza( Pieza pieza) {
		String estado = null;
		if (pieza.isExhibida() == true) {
			estado = "La pieza se encuentra exhibida";
		}
		else {
			estado = "La pieza se encuentra en bodega";
		}
		return estado;
	}
	
	public void añadirPieza(Pieza pieza){
		this.historialPiezas.add(pieza);
		this.piezasActuales.add(pieza);
	}
	
	public void añadirPiezaHistorial(Pieza pieza){
		this.historialPiezas.add(pieza);
	}
	
	public void consignarPieza(Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta ) throws PropietarioErroneoException {
			galeria.realizarConsignacion(this, piezaAConsignar, fechaLimite, galeria, exhibaVendaoSubasta);	
	}
	
	public void comprarPieza(Pieza piezaAcomprar, Galeria galeria) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
		((Cajero)galeria.getUnCajero()).venderPieza(this, piezaAcomprar, galeria);
	}
	
	public void realizarOfertaEnSubasta(Pieza piezaSubastada, int valorOfertado, Subasta subasta ) throws DineroOfrecidoInsuficienteException, DineroInsuficienteException {
		( (Operador )subasta.getOperador() ).registrarOferta(valorOfertado, piezaSubastada, this, subasta);
	}
}