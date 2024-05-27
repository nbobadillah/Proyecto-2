package fabrica;
import exceptions.*; 
import usuarios.*;
import piezas.*;
import modelo.*;
import persistencia.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class Fabrica {

	private int cantidadPiezasCreadas;
	private ArrayList<Pieza> piezasCreadas = new ArrayList<Pieza>();
	private int cantidadUsariosCreados;
	private ArrayList<Usuario> usuariosCreados = new ArrayList<Usuario>();
	private int cantidadCompradoresCreados;
	private ArrayList<Usuario> compradoresCreados = new ArrayList<Usuario>();
	private int cantidadEmpleadosCreados;
	private ArrayList<Usuario> empleadosCreados= new ArrayList<Usuario>();
	private int cantidadOperadoresCreados;
	private ArrayList<Usuario> operadoresCreados = new ArrayList<Usuario>();
	private int cantidadCajerosCreados;
	private ArrayList<Usuario> cajerosCreados = new ArrayList<Usuario>();
	private int cantidadAdministradoresCreados;
	private ArrayList<Usuario> administradoresCreados = new ArrayList<Usuario>();
	private int cantidadEsculturasCreadas;
	private ArrayList<Pieza> esculturasCreadas = new ArrayList<Pieza>();
	private int cantidadFotografiasCreadas;
	private ArrayList<Pieza> fotografiasCreadas = new ArrayList<Pieza>();
	private int cantidadImpresionesCreadas;
	private ArrayList<Pieza> impresionesCreadas = new ArrayList<Pieza>();
	private int cantidadPinturasCreadas;
	private ArrayList<Pieza> pinturasCreadas = new ArrayList<Pieza>();
	private int cantidadVideosCreados;
	private ArrayList<Pieza> videosCreados = new ArrayList<Pieza>();
	private int cantidadComprasCreadas;
	private ArrayList<VentaPiezas> comprasCreadas;
	private PersistenciaCentral persistencia = new PersistenciaCentral();
	private ArrayList<String> logins = new ArrayList<String>();
	
	public Fabrica() {
		
	}
	
	public Galeria crearGaleria(String nombre, ArrayList<Subasta> subastas, ArrayList<Pieza> historialPiezas,
			ArrayList<Pieza> piezasActuales, ArrayList<Pieza> piezasAntiguas, ArrayList<Usuario> usuarios ) {
		return new Galeria(nombre, subastas,  historialPiezas, piezasActuales, piezasAntiguas, usuarios );
	}
	
	public Comprador crearComprador(String login, String password,  String nombre, int valorMaximoCompras,
			ArrayList<Pieza> historialPiezas, ArrayList<Pieza> piezasActuales, int dinero, int telefono, Galeria galeria ) throws LoginException  
	{

		if (this.logins.contains(login))
		{
			throw new LoginException(login);
		}
		else 
		{
			Comprador nComprador = new Comprador(login, password,nombre, valorMaximoCompras, historialPiezas, piezasActuales, dinero, telefono); 
			this.logins.add(login);
			this.cantidadUsariosCreados++;
			this.usuariosCreados.add(nComprador);
			this.cantidadCompradoresCreados++;
			this.compradoresCreados.add(nComprador);
			galeria.agregarUsuario(nComprador);
			return nComprador;
		}
	}
	
	public Empleado crearEmpleado(String login, String password,int telefono, String nombre, Galeria galeria  ) throws LoginException {
		if (this.logins.contains(login))
		{
			throw new LoginException(login);
		}
		else 
		{
			Empleado nEmpleado = new Empleado(login, password, telefono, nombre); 
			this.logins.add(login);
			this.cantidadUsariosCreados++;
			this.usuariosCreados.add(nEmpleado);
			this.cantidadEmpleadosCreados++;
			this.empleadosCreados.add(nEmpleado);
			galeria.agregarUsuario(nEmpleado);
			return nEmpleado;
		}
	}
	
		public Operador crearOperador(String login, String password,int telefono, String nombre,  Galeria galeria  ) throws LoginException {
		
		if (this.logins.contains(login))
		{
			throw new LoginException(login);
		}
		else 
		{
			Operador nOperador = new Operador(login, password, telefono, nombre);  
			this.logins.add(login);
			this.cantidadUsariosCreados++;
			this.usuariosCreados.add(nOperador);
			this.cantidadCajerosCreados++;
			this.cajerosCreados.add(nOperador);
			galeria.agregarUsuario(nOperador);
			return nOperador;
		}
	}
	
	public Cajero crearCajero(String login, String password,int telefono, String nombre,  Galeria galeria  ) throws LoginException {
		
		if (this.logins.contains(login))
		{
			throw new LoginException(login);
		}
		else 
		{
			Cajero nCajero = new Cajero(login, password, telefono, nombre );  
			this.logins.add(login);
			this.cantidadUsariosCreados++;
			this.usuariosCreados.add(nCajero);
			this.cantidadCajerosCreados++;
			this.cajerosCreados.add(nCajero);
			galeria.agregarUsuario(nCajero);
			return nCajero;
		}
	}
	
	public Administrador crearAdministrador(String login, String password, int telefono, String nombre,  Galeria galeria ) throws LoginException {
		
		if (this.logins.contains(login))
		{
			throw new LoginException(login);
		}
		else 
		{
			Administrador nAdministrador = new Administrador(login, password, telefono, nombre); 
			this.logins.add(login);
			this.cantidadUsariosCreados++;
			this.usuariosCreados.add(nAdministrador);
			this.cantidadAdministradoresCreados++;
			this.administradoresCreados.add(nAdministrador);
			galeria.agregarUsuario(nAdministrador);
			return nAdministrador;
		}
		
	}
	
	public Escultura crearEscultura(String titulo, int año, int valor, String lugar, String autores, boolean exhibida,
			Comprador creador, String exhibaVendaoSubasta, int alto, int ancho, 
			int profundidad, String materiales, int peso, boolean electricidad, boolean otroDetalle, boolean consignacion,  String fecha, boolean dispsubasta, boolean dispventa) {
		Escultura nEscultura  = new Escultura(titulo, año, valor, lugar, autores, exhibida,
				creador,  exhibaVendaoSubasta, alto, ancho, 
				profundidad, materiales, peso, electricidad, otroDetalle, consignacion,  fecha, dispsubasta, dispventa); 
		this.cantidadPiezasCreadas++;
		this.piezasCreadas.add(nEscultura);
		this.cantidadEsculturasCreadas++;
		this.esculturasCreadas.add(nEscultura);
		creador.añadirPieza(nEscultura);
		return nEscultura;
	}
	
	public Fotografia crearFotografia(String titulo, int año, int valor, String lugar, String autores, boolean exhibida,
			Comprador creador, String exhibaVendaoSubasta, int alto, int ancho, String formato, boolean enmarcado,
			boolean consignacion,  String fecha, boolean dispsubasta, boolean dispventa) {
		Fotografia nFotografia  = new Fotografia(titulo, año, valor, lugar, autores, exhibida,
				 creador, exhibaVendaoSubasta, alto, ancho, formato, enmarcado, consignacion, fecha, dispsubasta, dispventa); 
		this.cantidadPiezasCreadas++;
		this.piezasCreadas.add(nFotografia);
		this.cantidadFotografiasCreadas++;
		this.fotografiasCreadas.add(nFotografia);
		creador.añadirPieza(nFotografia);
		return nFotografia;
	}
	
	public Impresion crearImpresion(String titulo, int año, int valor, String lugar, String autores, boolean exhibida,
			Comprador creador, String exhibaVendaoSubasta, int ancho, int alto, boolean consignacion, String fecha, boolean dispsubasta, boolean dispventa) {
		Impresion nImpresion  = new Impresion(titulo, año, valor, lugar, autores, exhibida,
				creador,  exhibaVendaoSubasta, ancho, alto, consignacion, fecha, dispsubasta, dispventa); 
		this.cantidadPiezasCreadas++;
		this.piezasCreadas.add(nImpresion);
		this.cantidadImpresionesCreadas++;
		this.impresionesCreadas.add(nImpresion);
		creador.añadirPieza(nImpresion);
		return nImpresion;
	}
	
	public Pintura crearPintura(String titulo, int año, int valor, String lugar, String autores, boolean exhibida,
			Comprador creador, String exhibaVendaoSubasta, int ancho, int alto, boolean consignacion,  String fecha, boolean dispsubasta, boolean dispventa) {
		Pintura nPintura  = new Pintura(titulo, año, valor, lugar, autores, exhibida,
				creador, exhibaVendaoSubasta, ancho, alto, consignacion, fecha, dispsubasta, dispventa); 
		this.cantidadPiezasCreadas++;
		this.piezasCreadas.add(nPintura);
		this.cantidadPinturasCreadas++;
		this.pinturasCreadas.add(nPintura);
		creador.añadirPieza(nPintura);
		return nPintura;
	}
	
	public Video crearVideo(String titulo, int año, int valor, String lugar, String autores, boolean exhibida,
			Comprador creador, String exhibaVendaoSubasta, int ancho, int alto,int duracion, String formato,
			boolean consignacion,  String fecha, boolean dispsubasta, boolean dispventa) {
		Video nVideo  = new Video(titulo, año, valor, lugar, autores, exhibida,
				 creador, exhibaVendaoSubasta, ancho, alto, duracion, formato, consignacion, fecha, dispsubasta, dispventa); 
		this.cantidadPiezasCreadas++;
		this.piezasCreadas.add(nVideo);
		this.cantidadVideosCreados++;
		this.videosCreados.add(nVideo);
		creador.añadirPieza(nVideo);
		return nVideo;
	}
	
	public void salvarPiezas (String archivo) throws IOException
	{
		
		persistencia.getPersistenciaPiezas().salvarPiezasCreadas(archivo, piezasCreadas);
		
	}
	
	

	public int getCantidadPiezasCreadas() {
		return cantidadPiezasCreadas;
	}

	public ArrayList<Pieza> getPiezasCreadas() {
		return piezasCreadas;
	}

	public int getCantidadUsariosCreados() {
		return cantidadUsariosCreados;
	}

	public ArrayList<Usuario> getUsuariosCreados() {
		return usuariosCreados;
	}

	public int getCantidadCompradoresCreados() {
		return cantidadCompradoresCreados;
	}

	public ArrayList<Usuario> getCompradoresCreados() {
		return compradoresCreados;
	}

	public int getCantidadEmpleadosCreados() {
		return cantidadEmpleadosCreados;
	}

	public ArrayList<Usuario> getEmpleadosCreados() {
		return empleadosCreados;
	}

	public int getCantidadOperadoresCreados() {
		return cantidadOperadoresCreados;
	}

	public ArrayList<Usuario> getOperadoresCreados() {
		return operadoresCreados;
	}

	public int getCantidadCajerosCreados() {
		return cantidadCajerosCreados;
	}

	public ArrayList<Usuario> getCajerosCreados() {
		return cajerosCreados;
	}

	public int getCantidadAdministradoresCreados() {
		return cantidadAdministradoresCreados;
	}

	public ArrayList<Usuario> getAdministradoresCreados() {
		return administradoresCreados;
	}

	public int getCantidadEsculturasCreadas() {
		return cantidadEsculturasCreadas;
	}

	public ArrayList<Pieza> getEsculturasCreadas() {
		return esculturasCreadas;
	}

	public int getCantidadFotografiasCreadas() {
		return cantidadFotografiasCreadas;
	}

	public ArrayList<Pieza> getFotografiasCreadas() {
		return fotografiasCreadas;
	}

	public int getCantidadImpresionesCreadas() {
		return cantidadImpresionesCreadas;
	}

	public ArrayList<Pieza> getImpresionesCreadas() {
		return impresionesCreadas;
	}

	public int getCantidadPinturasCreadas() {
		return cantidadPinturasCreadas;
	}

	public ArrayList<Pieza> getPinturasCreadas() {
		return pinturasCreadas;
	}

	public int getCantidadVideosCreados() {
		return cantidadVideosCreados;
	}

	public ArrayList<Pieza> getVideosCreados() {
		return videosCreados;
	}

	public int getCantidadComprasCreadas() {
		return cantidadComprasCreadas;
	}

	public ArrayList<VentaPiezas> getComprasCreadas() {
		return comprasCreadas;
	}

	public PersistenciaCentral getPersistencia() {
		return persistencia;
	}
	
	public ArrayList<String> getLogin(){
		return this.logins;
	}
	
	public void salvarGaleria(String archivo, Galeria galeria) throws IOException
	{
			persistencia.salvarGaleria(archivo, galeria.getUsuarios(), this.piezasCreadas);
	}
	
	public void cargarGaleria(String archivo, Galeria galeria) throws IOException, LoginException, LoginInexistenteException
	{
			persistencia.cargarGaleria(archivo, galeria);
	}
	
	}
