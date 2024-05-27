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

public class PersistenciaCentral {

	private PersistenciaPiezas persistenciaPiezas = new PersistenciaPiezas();
	private PersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios();
	
	public PersistenciaCentral()
	{
		
	}
	
	public void salvarGaleria(String archivo, ArrayList<Usuario> usuarios, ArrayList<Pieza> piezas) throws IOException
	{
		JSONObject jobject = new JSONObject( );
		HashMap<Pieza, String> identificadorPieza = new HashMap<Pieza, String>();
		persistenciaPiezas.salvarPiezas(piezas, jobject, identificadorPieza);
		persistenciaUsuarios.salvarUsuarios(usuarios, jobject, identificadorPieza);
		File carpeta = new File("C:\\Users\\naran\\Desktop\\workspace_eclipse\\Proyecto-1\\Entrega 2\\ProyectoGaleria\\Persistencia\\Galeria");
		File nArchivo = new File(carpeta, archivo);
		PrintWriter pw = new PrintWriter( new FileWriter(nArchivo) );
		jobject.write( pw, 2, 0 );
		pw.close( );
	}

	public void cargarGaleria(String archivo, Galeria galeria) throws IOException, LoginException, LoginInexistenteException 
	{
		HashMap<String, Comprador> loginCompradores = new HashMap<String, Comprador>();
		HashMap<String, Pieza> identificacionPieza = new HashMap<String, Pieza>();
		HashMap<Comprador , String> historialCompradores = new HashMap<Comprador, String>();
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
		JSONObject raiz = new JSONObject( jsonCompleto );

		JSONArray usuarios = raiz.getJSONArray( "Usuarios" ); 
		persistenciaUsuarios.cargarUsuarios(usuarios , galeria, loginCompradores, historialCompradores );
		JSONArray piezas = raiz.getJSONArray( "Piezas" );
		persistenciaPiezas.cargarPiezas(piezas, galeria, loginCompradores, identificacionPieza);
		for (Comprador comprador: historialCompradores.keySet())
		{
			String[] piezasHistorial = historialCompradores.get(comprador).split(",");
			for (String identificador : piezasHistorial)
			{
				comprador.añadirPiezaHistorial(identificacionPieza.get(identificador));
			}

		}
	}

	public PersistenciaPiezas getPersistenciaPiezas() {
		return persistenciaPiezas;
	}

	public void setPersistenciaPiezas(PersistenciaPiezas persistenciaPiezas) {
		this.persistenciaPiezas = persistenciaPiezas;
	}

	public PersistenciaUsuarios getPersistenciaUsuarios() {
		return persistenciaUsuarios;
	}

	public void setPersistenciaUsuarios(PersistenciaUsuarios persistenciaUsuarios) {
		this.persistenciaUsuarios = persistenciaUsuarios;
	}
	
	
	
}