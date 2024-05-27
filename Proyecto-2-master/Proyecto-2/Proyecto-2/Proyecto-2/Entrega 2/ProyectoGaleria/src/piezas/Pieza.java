package piezas;

import java.util.*;
import java.util.ArrayList;
import modelo.*;
import usuarios.*;




abstract public class Pieza {
	
	private String titulo;
	private int año;
	private String lugar;
	private String autores;
	private boolean exhibida;
	private int valor;
	private Usuario propietario;
	public static final String EXHIBA = "1";
	public static final String VENTA = "2";
	public static final String SUBASTA = "3";
	private String exhibaVendaoSubasta;	
	private boolean consignacion = false;
	private String fecha;
	private boolean dispsubasta;
	private boolean dispventa;

	public Pieza(String titulo, int año, int valor, String lugar, String autores, boolean exhibida, Usuario propietario,  String exhibaVendaoSubasta, boolean consignacion, String fecha, boolean dispsubasta, boolean dispventa) {
		this.titulo = titulo;
		this.año = año;
		this.lugar = lugar;
		this.valor = valor;
		this.autores = autores;
		this.propietario = propietario;
		this.exhibaVendaoSubasta = exhibaVendaoSubasta;
		this.consignacion = consignacion; 
		this.fecha = fecha;
		this.dispsubasta = dispsubasta;
		this.dispventa = dispventa;

	}

	

	public boolean isConsignacion() {
		return consignacion;
	}

	public void setConsignacion(boolean consignacion) {
		this.consignacion = consignacion;
	}
		

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	
	public String getTitulo() {
	return titulo;
	}

	public void setTitulo(String titulo) {
	this.titulo = titulo;
	}

	public int getValor() {
		return this.valor;
	}
	
	public int getAño() {
		return año;
	}	

	public void setAño(int año) {
		this.año = año;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
	this.lugar = lugar;
	}

	public String getAutores() {
		return autores;
	}	

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public boolean isExhibida() {
		return exhibida;
	}

	public void setExhibida(boolean exhibida) {
		this.exhibida = exhibida;
	}

	public boolean getExhibida() {
		return this.exhibida;
	}

	public Usuario getPropietario() {
		return propietario;
	}	

	public String getExhibaVendaoSubasta() {
		return exhibaVendaoSubasta;
	}

	public void setExhibaVendaoSubasta(String exhibaVendaoSubasta) {
		this.exhibaVendaoSubasta = exhibaVendaoSubasta;
	}

	public abstract String getTipo();{
		
	}

	public boolean isDispsubasta() {
		return this.dispsubasta;
	}
	
	public void setdispsubasta(boolean dispsubasta) {
		this.dispsubasta = dispsubasta;
	}


	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
		
	public boolean isDispventa() {
			return dispventa;
		}
		
	public void setDispventa(boolean dispventa) {
			this.dispventa = dispventa;
		}
	
	
}

