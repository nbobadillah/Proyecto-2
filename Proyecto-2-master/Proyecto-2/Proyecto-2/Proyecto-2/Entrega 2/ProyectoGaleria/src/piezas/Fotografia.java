package piezas;

import java.util.ArrayList;
import modelo.*;
import usuarios.*;

public class Fotografia extends Pieza{
	private int alto;
	private int ancho;
	private String formato;
	private boolean enmarcado;
	private static final String tipo = "Fotografia";
	
	public Fotografia(String titulo, int año, int valor, String lugar, String autores, boolean exhibida,
			Usuario propietario, String exhibaVendaoSubasta, int ancho, int alto, String formato, boolean enmarcado, boolean consignacion, String fecha, boolean dispsubasta, boolean dispventa) {
		super(titulo, año, valor, lugar, autores, exhibida, propietario, exhibaVendaoSubasta, consignacion, fecha, dispsubasta, dispventa);
		this.alto = alto;
		this.ancho = ancho;
		this.formato = formato;
		this.enmarcado = enmarcado;
	}
	
	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public String getFormato() {
		return formato;
	}

	public boolean isEnmarcado() {
		return enmarcado;
	}

	public String getTipo() {
		return this.tipo;
	}	

}
