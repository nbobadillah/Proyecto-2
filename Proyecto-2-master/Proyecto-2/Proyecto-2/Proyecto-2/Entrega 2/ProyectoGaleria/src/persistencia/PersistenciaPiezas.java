package persistencia;

import piezas.*; 
import usuarios.*;
import modelo.*;
import fabrica.*;
import exceptions.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersistenciaPiezas {
	
	private static final String tipoEscultura = "Escultura";
	private static final String tipoFotografia = "Fotografia";
	private static final String tipoImpresion = "Impresion";
	private static final String tipoPintura = "Pintura";
	private static final String tipoVideo = "Video";
	private static final String identificador = "Identificador";
	private static final String titulo = "Titulo";
	private static final String año = "Año";
	private static final String lugar = "Lugar";
	private static final String exhibida = "Exhibida";
	private static final String valor = "Valor";
	private static final String exhibaVendaoSubasta= "Exhiba venda o subasta";
	private static final String tipo = "Tipo";
	private static final String consignacion = "Consignacion";
	private static final String fecha = "Fecha";
	private static final String dispsubasta = "Dispsubasta";
	private static final String dispventa = "Dispventa";
	
	public PersistenciaPiezas() {

	}

	public void salvarPiezasCreadas (String archivo, ArrayList<Pieza> piezas) throws IOException
	{
		JSONObject jobject = new JSONObject( );
		HashMap<Pieza, String> identificadorPieza = new HashMap<Pieza,String>();
		salvarPiezas(piezas, jobject, identificadorPieza);
		File carpeta = new File("C:\\Users\\naran\\Desktop\\workspace_eclipse\\Proyecto-1\\Entrega 2\\ProyectoGaleria\\Persistencia\\Piezas");
		File nArchivo = new File(carpeta, archivo);
		PrintWriter pw = new PrintWriter( new FileWriter(nArchivo) );
		jobject.write( pw, 2, 0 );
		pw.close( );
	}


	public void salvarPiezas(ArrayList<Pieza> piezas, JSONObject jobject, HashMap<Pieza, String> identificadorPieza ) 
	{
		if (piezas.size()!=0)
		{
			int identificador = 0;
			JSONArray jPiezas = new JSONArray( );
			for (Pieza pieza: piezas) {
				String sIdentificador = String.valueOf(identificador);

				if (tipoVideo.equals(pieza.getTipo()))
				{
					salvarVideos((Video) pieza, jPiezas, sIdentificador, identificadorPieza );
				}
				else if (tipoPintura.equals(pieza.getTipo()))
				{
					salvarPinturas((Pintura) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				else if (tipoFotografia.equals(pieza.getTipo()))
				{
					salvarFotografias((Fotografia) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				else if (tipoImpresion.equals(pieza.getTipo()))
				{
					salvarImpresiones((Impresion) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				else if (tipoEscultura.equals(pieza.getTipo()))
				{
					salvarEsculturas((Escultura) pieza, jPiezas, sIdentificador, identificadorPieza);
				}
				identificador++;
			}
			jobject.put("Piezas", jPiezas);
		}
		else 
		{
			jobject.put("Piezas", "Vacío");
		}
	}

	public void salvarVideos (Video video, JSONArray jVideos, String sIdentificador,  HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jVideo = new JSONObject( );
		jVideo.put( identificador, sIdentificador );
		jVideo.put( titulo, video.getTitulo( ) );
		jVideo.put( año, video.getAño( ) );
		jVideo.put( lugar, video.getLugar( ) );
		jVideo.put( "Autores", video.getAutores( ) );
		jVideo.put( exhibida, video.getExhibida() );
		jVideo.put( valor, video.getValor( ) );
		jVideo.put( "Proprietario", video.getPropietario( ).getLogin() );
		jVideo.put( exhibaVendaoSubasta , video.getExhibaVendaoSubasta() );
		jVideo.put( tipo, video.getTipo( ) );
		jVideo.put( "Alto", video.getAlto( ) );
		jVideo.put( "Ancho", video.getAncho( ) );
		jVideo.put( "Duracion", video.getDuracion( ) );
		jVideo.put( "Formato", video.getFormato( ) );
		jVideo.put( consignacion, video.isConsignacion( ) );
		jVideo.put( fecha, video.getFecha( ) );
		jVideo.put( dispsubasta, video.isDispsubasta( ) );
		jVideo.put( dispventa, video.isDispventa( ) );
		identificadorPieza.put(video, sIdentificador);

		jVideos.put( jVideo );
	}

	public void salvarPinturas (Pintura pintura, JSONArray jPinturas, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jPintura = new JSONObject( );
		jPintura.put( identificador, sIdentificador );
		jPintura.put( titulo, pintura.getTitulo( ) );
		jPintura.put( año, pintura.getAño( ) );
		jPintura.put( lugar, pintura.getLugar( ) );
		jPintura.put( "Autores", pintura.getAutores( ) );
		jPintura.put( exhibida, pintura.getExhibida() );
		jPintura.put( valor, pintura.getValor( ) );
		jPintura.put( "Proprietario", pintura.getPropietario( ).getLogin() );
		jPintura.put( exhibaVendaoSubasta, pintura.getExhibaVendaoSubasta() );
		jPintura.put( tipo, pintura.getTipo( ) );
		jPintura.put( "Alto", pintura.getAlto( ) );
		jPintura.put( "Ancho", pintura.getAncho( ) );
		jPintura.put( consignacion, pintura.isConsignacion( ) );
		jPintura.put( fecha, pintura.getFecha( ) );
		jPintura.put( dispsubasta, pintura.isDispsubasta( ) );
		jPintura.put( dispventa, pintura.isDispventa( ) );
		identificadorPieza.put(pintura, sIdentificador);

		jPinturas.put( jPintura );
	}

	public void salvarImpresiones (Impresion impresion, JSONArray jImpresiones, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jImpresion = new JSONObject( );
		jImpresion.put( identificador, sIdentificador );
		jImpresion.put( titulo, impresion.getTitulo( ) );
		jImpresion.put( año, impresion.getAño( ) );
		jImpresion.put( lugar, impresion.getLugar( ) );
		jImpresion.put( "Autores", impresion.getAutores( ) );
		jImpresion.put( exhibida, impresion.getExhibida() );
		jImpresion.put( "Valor", impresion.getValor( ) );
		jImpresion.put( "Proprietario", impresion.getPropietario( ).getLogin() );
		jImpresion.put( exhibaVendaoSubasta, impresion.getExhibaVendaoSubasta() );
		jImpresion.put( tipo, impresion.getTipo( ) );
		jImpresion.put( "Alto", impresion.getAlto( ) );
		jImpresion.put( "Ancho", impresion.getAncho( ) );
		jImpresion.put( consignacion, impresion.isConsignacion( ) );
		jImpresion.put( fecha, impresion.getFecha( ) );
		jImpresion.put( dispsubasta, impresion.isDispsubasta( ) );
		jImpresion.put( dispventa, impresion.isDispventa( ) );
		identificadorPieza.put(impresion, sIdentificador);

		jImpresiones.put( jImpresion );
	}

	public void salvarFotografias (Fotografia fotografia, JSONArray jFotografias, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jFotografia = new JSONObject( );
		jFotografia.put( identificador, sIdentificador );
		jFotografia.put( titulo, fotografia.getTitulo( ) );
		jFotografia.put( año, fotografia.getAño( ) );
		jFotografia.put( lugar, fotografia.getLugar( ) );
		jFotografia.put( "Autores", fotografia.getAutores( ) );
		jFotografia.put( exhibida, fotografia.getExhibida() );
		jFotografia.put( valor, fotografia.getValor( ) );
		jFotografia.put( "Proprietario", fotografia.getPropietario( ).getLogin() );
		jFotografia.put( exhibaVendaoSubasta, fotografia.getExhibaVendaoSubasta() );
		jFotografia.put( tipo, fotografia.getTipo( ) );
		jFotografia.put( "Alto", fotografia.getAlto( ) );
		jFotografia.put( "Ancho", fotografia.getAncho( ) );
		jFotografia.put( "Formato", fotografia.getFormato( ) );
		jFotografia.put( "Enmarcado", fotografia.isEnmarcado( ) );
		jFotografia.put( consignacion, fotografia.isConsignacion( ) );
		jFotografia.put( fecha, fotografia.getFecha( ) );
		jFotografia.put( dispsubasta, fotografia.isDispsubasta( ) );
		jFotografia.put( dispventa, fotografia.isDispventa( ) );
		identificadorPieza.put(fotografia, sIdentificador);

		jFotografias.put( jFotografia );
	}

	public void salvarEsculturas (Escultura escultura, JSONArray jEsculturas, String sIdentificador, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jEscultura = new JSONObject( );
		jEscultura.put( identificador, sIdentificador );
		jEscultura.put( titulo, escultura.getTitulo( ) );
		jEscultura.put( año, escultura.getAño( ) );
		jEscultura.put( lugar, escultura.getLugar( ) );
		jEscultura.put( "Autores", escultura.getAutores( ) );
		jEscultura.put( exhibida, escultura.getExhibida() );
		jEscultura.put( valor, escultura.getValor( ) );
		jEscultura.put( "Proprietario", escultura.getPropietario( ).getLogin() );
		jEscultura.put( exhibaVendaoSubasta, escultura.getExhibaVendaoSubasta() );
		jEscultura.put( tipo, escultura.getTipo( ) );
		jEscultura.put( "Alto", escultura.getAlto( ) );
		jEscultura.put( "Ancho", escultura.getAncho( ) );
		jEscultura.put( "Profundidad", escultura.getProfundidad( ) );
		jEscultura.put( "Materiales", escultura.getMateriales( ) );
		jEscultura.put( "Peso", escultura.getPeso( ) );
		jEscultura.put( "Electricidad", escultura.isElectricidad( ) );
		jEscultura.put( "Otro detalle", escultura.isOtroDetalle( ) );
		jEscultura.put( consignacion, escultura.isConsignacion( ) );
		jEscultura.put( fecha, escultura.getFecha( ) );
		jEscultura.put( dispsubasta, escultura.isDispsubasta( ) );
		jEscultura.put( dispventa, escultura.isDispventa( ) );
		identificadorPieza.put(escultura, sIdentificador);

		jEsculturas.put( jEscultura );
	}

	public void cargarPiezas(JSONArray jPiezas, Galeria galeria, HashMap<String, Comprador> loginCompradores, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{ 
		int numeroPiezas = jPiezas.length();
		for (int i = 0 ; i < numeroPiezas ; i++)
		{
			JSONObject pieza = jPiezas.getJSONObject( i );
			String tipoPieza = pieza.getString(tipo);
			if (tipoVideo.equals(tipoPieza))
			{
				cargarVideo(pieza, galeria, loginCompradores, identificacionPieza );
			}
			else if (tipoPintura.equals(tipoPieza))
			{
				cargarPintura(pieza, galeria, loginCompradores, identificacionPieza);
			}
			else if (tipoFotografia.equals(tipoPieza))
			{
				cargarFotografia(pieza, galeria, loginCompradores, identificacionPieza);
			}
			else if (tipoImpresion.equals(tipoPieza))
			{
				cargarImpresion(pieza, galeria, loginCompradores, identificacionPieza);
			}
			else if (tipoEscultura.equals(tipoPieza))
			{
				cargarEscultura(pieza, galeria, loginCompradores, identificacionPieza);
			}
		}
	}

	public void cargarEscultura(JSONObject jEscultura, Galeria galeria, HashMap<String, Comprador> loginCompradores, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String nAutores = jEscultura.getString("Autores");
		String nLugar = jEscultura.getString(lugar);
		String nTitulo = jEscultura.getString(titulo);
		int nValor = jEscultura.getInt(valor);
		String nExhibaVendaoSubaste = jEscultura.getString(exhibaVendaoSubasta);
		int nAño = jEscultura.getInt(año);
		String loginComprador = jEscultura.getString("Proprietario");
		if (! loginCompradores.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = loginCompradores.get(loginComprador);
		boolean nExhibida = jEscultura.getBoolean(exhibida);
		int nAlto = jEscultura.getInt("Alto");
		int nAancho = jEscultura.getInt("Ancho");
		int nProfundidad = jEscultura.getInt("Profundidad");
		String nMateriales = jEscultura.getString("Materiales");
		int nPeso = jEscultura.getInt("Peso");
		boolean nElectricidad = jEscultura.getBoolean("Electricidad");
		boolean nOtroDetalle = jEscultura.getBoolean("Otro detalle");
		boolean nConsignacion = jEscultura.getBoolean(consignacion);
		String nFecha = jEscultura.getString(fecha);
		boolean nDispsubasta = jEscultura.getBoolean(dispsubasta);
		boolean nDispventa = jEscultura.getBoolean(dispventa);
		Escultura escultura = galeria.getFabrica().crearEscultura(nTitulo, nAño, nValor, nLugar, nAutores, nExhibida, nPropietario, nExhibaVendaoSubaste, nAlto, nAancho, nProfundidad, nMateriales, nPeso, nElectricidad, nOtroDetalle, nConsignacion, nFecha, nDispsubasta, nDispventa);
		identificacionPieza.put(jEscultura.getString(identificador), escultura);
	}

	public void cargarFotografia(JSONObject jFotografia, Galeria galeria, HashMap<String, Comprador> loginCompradores, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String nAutores = jFotografia.getString("Autores");
		String nLugar = jFotografia.getString(lugar);
		String nTitulo = jFotografia.getString(titulo);
		int nValor = jFotografia.getInt(valor);
		String nExhibaVendaoSubaste = jFotografia.getString(exhibaVendaoSubasta);
		int nAño = jFotografia.getInt(año);
		String loginComprador = jFotografia.getString("Proprietario");
		if (! loginCompradores.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = loginCompradores.get(loginComprador);
		boolean nExhibida = jFotografia.getBoolean(exhibida);
		int nAlto = jFotografia.getInt("Alto");
		int nAancho = jFotografia.getInt("Ancho");
		String nFormato = jFotografia.getString("Formato");
		boolean nEnmarcado = jFotografia.getBoolean("Enmarcado");
		boolean nConsignacion = jFotografia.getBoolean(consignacion);
		String nFecha = jFotografia.getString(fecha);
		boolean nDispsubasta = jFotografia.getBoolean(dispsubasta);
		boolean nDispventa = jFotografia.getBoolean(dispventa);
		Fotografia fotografia = galeria.getFabrica().crearFotografia(nTitulo, nAño, nValor, nLugar, nAutores, nExhibida, nPropietario, nExhibaVendaoSubaste, nAlto, nAancho, nFormato, nEnmarcado, nConsignacion, nFecha, nDispsubasta, nDispventa);
		identificacionPieza.put(jFotografia.getString(identificador), fotografia);

	}

	public void cargarImpresion(JSONObject jImpresion, Galeria galeria, HashMap<String, Comprador> loginCompradores, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String nAutores = jImpresion.getString("Autores");
		String nLugar = jImpresion.getString(lugar);
		String nTitulo = jImpresion.getString(titulo);
		int nValor = jImpresion.getInt(valor);
		String nExhibaVendaoSubaste = jImpresion.getString(exhibaVendaoSubasta);
		int nAño = jImpresion.getInt(año);
		String loginComprador = jImpresion.getString("Proprietario");
		if (! loginCompradores.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = loginCompradores.get(loginComprador);
		boolean nExhibida = jImpresion.getBoolean(exhibida);
		int nAlto = jImpresion.getInt("Alto");
		int nAncho = jImpresion.getInt("Ancho");
		boolean nConsignacion = jImpresion.getBoolean(consignacion);
		String nFecha = jImpresion.getString(fecha);
		boolean nDispsubasta = jImpresion.getBoolean(dispsubasta);
		boolean nDispventa = jImpresion.getBoolean(dispventa);
		Impresion impresion = galeria.getFabrica().crearImpresion(nTitulo, nAño, nValor, nLugar, nAutores, nExhibida, nPropietario, nExhibaVendaoSubaste, nAncho, nAlto, nConsignacion, nFecha, nDispsubasta, nDispventa);
		identificacionPieza.put(jImpresion.getString(identificador), impresion);

	}

	public void cargarPintura(JSONObject jPintura, Galeria galeria, HashMap<String, Comprador> loginCompradores, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String nAutores = jPintura.getString("Autores");
		String nLugar = jPintura.getString(lugar);
		String nTitulo = jPintura.getString(titulo);
		int nValor = jPintura.getInt(valor);
		String nExhibaVendaoSubaste = jPintura.getString(exhibaVendaoSubasta);
		int nAño = jPintura.getInt(año);
		String loginComprador = jPintura.getString("Proprietario");
		if (! loginCompradores.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = loginCompradores.get(loginComprador);
		boolean nExhibida = jPintura.getBoolean(exhibida);
		int nAlto = jPintura.getInt("Alto");
		int nAncho = jPintura.getInt("Ancho");
		boolean nConsignacion = jPintura.getBoolean(consignacion);
		String nFecha = jPintura.getString(fecha);
		boolean nDispsubasta = jPintura.getBoolean(dispsubasta);
		boolean nDispventa = jPintura.getBoolean(dispventa);
		Pintura pintura = galeria.getFabrica().crearPintura(nTitulo, nAño, nValor, nLugar, nAutores, nExhibida, nPropietario, nExhibaVendaoSubaste, nAncho, nAlto, nConsignacion, nFecha, nDispsubasta, nDispventa);
		identificacionPieza.put(jPintura.getString(identificador), pintura);

	}


	public void cargarVideo(JSONObject jVideo, Galeria galeria, HashMap<String, Comprador> loginCompradores, HashMap<String, Pieza> identificacionPieza) throws LoginInexistenteException
	{
		String nAutores = jVideo.getString("Autores");
		String nLugar = jVideo.getString(lugar);
		String nTitulo = jVideo.getString(titulo);
		int nValor = jVideo.getInt(valor);
		String nExhibaVendaoSubaste = jVideo.getString(exhibaVendaoSubasta);
		int nAño = jVideo.getInt(año);
		String loginComprador = jVideo.getString("Proprietario");
		if (! loginCompradores.containsKey(loginComprador))
		{
			throw new LoginInexistenteException(loginComprador);
		}
		Comprador nPropietario = loginCompradores.get(loginComprador);
		boolean nExhibida = jVideo.getBoolean(exhibida);
		int nAlto = jVideo.getInt("Alto");
		int nAncho = jVideo.getInt("Ancho");
		int nDuracion = jVideo.getInt("Duracion");
		String nFormato = jVideo.getString("Formato");
		boolean nConsignacion = jVideo.getBoolean(consignacion);
		String nFecha = jVideo.getString(fecha);
		boolean nDispsubasta = jVideo.getBoolean(dispsubasta);
		boolean nDispventa = jVideo.getBoolean(dispventa);
		Video video = galeria.getFabrica().crearVideo(nTitulo, nAño, nValor, nLugar, nAutores, nExhibida, nPropietario, nExhibaVendaoSubaste, nAncho, nAlto, nDuracion, nFormato, nConsignacion, nFecha, nDispsubasta, nDispventa);
		identificacionPieza.put(jVideo.getString(identificador), video);
	}

}



