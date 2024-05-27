package piezas;

import java.util.ArrayList;
import modelo.*;
import usuarios.*;

public class Video extends Pieza {

	private int alto;
	private int ancho;
	private int duracion;
	private String formato;
	private static final String tipo= "Video";
	
	public Video(String titulo, int año, int valor, String lugar, String autores, boolean exhibida,
			Usuario propietario, String exhibaVendaoSubasta, int alto, int ancho,
			int duracion, String formato, boolean consignacion, String fecha, boolean dispsubasta, boolean dispventa) {
		super(titulo, año, valor, lugar, autores, exhibida, propietario, exhibaVendaoSubasta, consignacion, fecha, dispsubasta, dispventa);
		this.alto = alto;
		this.ancho = ancho;
		this.duracion = duracion;
		this.formato = formato;
	}
		
	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public int getDuracion() {
		return duracion;
	}

	public String getFormato() {
		return formato;
	}
	
	public String getTipo() {
		return this.tipo;
		
	}
	
}