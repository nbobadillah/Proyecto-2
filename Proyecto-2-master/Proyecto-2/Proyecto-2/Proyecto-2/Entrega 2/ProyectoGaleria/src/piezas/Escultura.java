package piezas;
import java.util.ArrayList;
import modelo.*;
import usuarios.*;

public class Escultura extends Pieza {
	private int alto;
	private int ancho;
	private int profundidad;
	private String materiales;
	private int peso;
	private boolean electricidad;
	private boolean otroDetalle;
	private static final String tipo="Escultura";
	
	
	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public String getMateriales() {
		return materiales;
	}

	public int getPeso() {
		return peso;
	}

	public boolean isElectricidad() {
		return electricidad;
	}

	public boolean isOtroDetalle() {
		return otroDetalle;
	}

	public Escultura(String titulo, int año, int valor, String lugar,String autores, boolean exhibida,
			Usuario propietario, String exhibaVendaoSubasta, int alto, int ancho, 
			int profundidad, String materiales, int peso, boolean electricidad, boolean otroDetalle, boolean consignacion, String fecha, boolean dispsubasta, boolean dispventa) {
		super(titulo, año, valor, lugar, autores, exhibida, propietario, exhibaVendaoSubasta, consignacion,fecha, dispsubasta,dispventa);
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad = profundidad;
		this.materiales = materiales;
		this.peso = peso;
		this.electricidad = electricidad;
		this.otroDetalle = otroDetalle;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
