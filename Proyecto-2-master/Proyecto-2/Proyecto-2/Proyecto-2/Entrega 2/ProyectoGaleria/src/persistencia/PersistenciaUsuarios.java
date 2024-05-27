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
public class PersistenciaUsuarios {
	
	private static final String tipoAdministrador = "Administrador";
	private static final String tipoCajero = "Cajero";
	private static final String tipoComprador = "Comprador";
	private static final String tipoEmpleado = "Empleado";
	private static final String tipoOperador = "Operador";
	private static final String login = "Login";
	private static final String password = "Password";
	private static final String nombre = "Nombre";
	private static final String telefono = "Telefono";
	private static final String tipo = "Tipo ";

	public PersistenciaUsuarios() {

	}

	public void salvarUsuariosCreados (String archivo, ArrayList<Usuario> compradores, HashMap<Pieza, String> identificadorPieza) throws IOException
	{
		JSONObject jobject = new JSONObject( );
		salvarUsuarios(compradores, jobject, identificadorPieza);
		File carpeta = new File("C:\\Users\\naran\\Desktop\\workspace_eclipse\\Proyecto-1\\Entrega 2\\ProyectoGaleria\\Persistencia\\Usuarios");
		File nArchivo = new File(carpeta, archivo);
		PrintWriter pw = new PrintWriter( new FileWriter(nArchivo) );
		jobject.write( pw, 2, 0 );
		pw.close( );
	}


	public void salvarUsuarios(ArrayList<Usuario> usuarios, JSONObject jobject, HashMap<Pieza, String> identificadorPieza) 
	{
		JSONArray jUsuarios = new JSONArray( );
		for (Usuario usuario: usuarios) {

			if (tipoComprador.equals(usuario.getTipo()))
			{
				salvarComprador((Comprador) usuario, jUsuarios, identificadorPieza );
			}
			else if (tipoAdministrador.equals(usuario.getTipo()) || "Cajero".equals(usuario.getTipo()) || "Operador".equals(usuario.getTipo()))
			{
				salvarAdministradorOperadorCajero(usuario, jUsuarios);
			}
			else if (tipoEmpleado.equals(usuario.getTipo()))
			{
				salvarEmpleado((Empleado) usuario, jUsuarios);
			}
		}
		jobject.put("Usuarios", jUsuarios);
	}

	public void salvarComprador(Comprador comprador, JSONArray jCompradores, HashMap<Pieza, String> identificadorPieza)
	{
		JSONObject jComprador = new JSONObject( );
		jComprador.put( login, comprador.getLogin() );
		jComprador.put( password, comprador.getPassword() );
		jComprador.put( nombre, comprador.getNombre( ) );
		jComprador.put( "Dinero", comprador.getDinero( ) );
		jComprador.put( telefono, comprador.getTelefono() );
		jComprador.put( "Valor Maximo Compras", comprador.getValorMaximoCompras( ) );
		String piezasActuales = "";
		if (! comprador.getPiezasActuales().isEmpty())
		{
			for (Pieza pieza: comprador.getPiezasActuales())
			{
				piezasActuales = piezasActuales + "," + identificadorPieza.get(pieza);
				comprador.getHistorialPiezas().remove(pieza);
			}
			jComprador.put( "Piezas Actuales", piezasActuales );
		}
		else
		{
			jComprador.put( "Piezas Actuales", "Vacío");
		}
		String historialPiezas = "";
		if (! comprador.getHistorialPiezas().isEmpty())
		{
			for (Pieza pieza: comprador.getHistorialPiezas())
			{
				historialPiezas = historialPiezas + "," + identificadorPieza.get(pieza);
			}
			jComprador.put( "Historial Piezas", historialPiezas);
		}
		else
		{
			jComprador.put( "Historial Piezas", "Vacío");
		}
		jComprador.put( tipo, comprador.getTipo( ) );

		jCompradores.put( jComprador );
	}
	
	public void salvarAdministradorOperadorCajero(Usuario usuario, JSONArray jCompradores)
	{
		JSONObject jComprador = new JSONObject( );
		jComprador.put( login, usuario.getLogin() );
		jComprador.put( password, usuario.getPassword() );
		jComprador.put( nombre, usuario.getNombre( ) );
		jComprador.put( telefono, usuario.getTelefono() );
		jComprador.put( tipo, usuario.getTipo( ) );

		jCompradores.put( jComprador );
	}
	
	public void salvarEmpleado(Empleado empleado, JSONArray jCompradores)
	{
		JSONObject jComprador = new JSONObject( );
		jComprador.put( login, empleado.getLogin() );
		jComprador.put( password, empleado.getPassword() );
		jComprador.put( nombre, empleado.getNombre( ) );
		jComprador.put( telefono, empleado.getTelefono() );
		jComprador.put( tipo, empleado.getTipo( ) );

		jCompradores.put( jComprador );
	}

	public void cargarUsuarios(JSONArray jUsuarios, Galeria galeria, HashMap<String, Comprador> loginCompradores,
			HashMap<Comprador , String> historialCompradores) throws LoginException
	{
		Fabrica fabrica = galeria.getFabrica(); 
		int numeroCompradores = jUsuarios.length();
		for (int i = 0 ; i < numeroCompradores ; i++)
		{
			JSONObject usuario = jUsuarios.getJSONObject( i );
			String tipoUsuario = usuario.getString(tipo);
				if (tipoComprador.equals(tipoUsuario))
				{
					cargarComprador(usuario, galeria, loginCompradores, 
							historialCompradores, fabrica);
				}
				else if (tipoEmpleado.equals(tipoUsuario))
				{
					cargarAdministrador(usuario, galeria, fabrica);
				}
				else if (tipoAdministrador.equals(tipoUsuario))
				{
					cargarCajero(usuario, galeria, fabrica);						
				}
				else if (tipoCajero.equals(tipoUsuario))
				{
					cargarOperador(usuario, galeria, fabrica);
				}
				else if (tipoOperador.equals(tipoUsuario))
				{
					cargarEmpleado(usuario, galeria, fabrica);
				}
		}

	}
	
	public void cargarComprador(JSONObject jComprador, Galeria galeria, HashMap<String, Comprador> loginCompradores,
			HashMap<Comprador , String> historialCompradores, Fabrica fabrica) throws LoginException
	{
			String nLogin = jComprador.getString(login);
			String nPassword = jComprador.getString(password);
			String nNombre = jComprador.getString(nombre);
			int nDinero = jComprador.getInt("Dinero");
			int nValorMaximoCompras = jComprador.getInt("Valor Maximo Compras");
			int nTelefono = jComprador.getInt(telefono);
			ArrayList<Pieza> nHistorialPiezas = new ArrayList<Pieza>();
			ArrayList<Pieza> nPiezasActuales = new ArrayList<Pieza>();
			Comprador nComprador = fabrica.crearComprador(nLogin, nPassword, nNombre, nValorMaximoCompras, nHistorialPiezas, nPiezasActuales, nDinero, nTelefono, galeria);
			loginCompradores.put(nLogin, nComprador);
			String nHistorialPiezasString = jComprador.getString("Historial Piezas");
			if (! nHistorialPiezasString.equals("Vacío"))
			{
				historialCompradores.put(nComprador,nHistorialPiezasString );
			}
		}
	
	public void cargarAdministrador(JSONObject jAdministrador, Galeria galeria,
			Fabrica fabrica) throws LoginException
	{
			String nLogin = jAdministrador.getString(login);
			if (galeria.getUsuarios().contains(nLogin))
			{
				throw new LoginException(nLogin);
			}
			String nPassword = jAdministrador.getString(password);
			String nNombre = jAdministrador.getString(nombre);
			int nTelefono = jAdministrador.getInt(telefono);
			Administrador nAdministrador = fabrica.crearAdministrador(nLogin, nPassword,nTelefono, nNombre, galeria);
		}
	
	public void cargarCajero(JSONObject jCajero, Galeria galeria,
			Fabrica fabrica) throws LoginException
	{
		String nLogin = jCajero.getString(login);
		String nPassword = jCajero.getString(password);
		String nNombre = jCajero.getString(nombre);
		int nTelefono = jCajero.getInt(telefono);
		Cajero nCajero = fabrica.crearCajero(nLogin, nPassword,nTelefono, nNombre, galeria);
		}
	
	public void cargarOperador(JSONObject jOperador, Galeria galeria,
			Fabrica fabrica) throws LoginException
	{
			String nLogin = jOperador.getString(login);
			String nPassword = jOperador.getString(password);
			String nNombre = jOperador.getString(nombre);
			int nTelefono = jOperador.getInt(telefono);
			Operador nOperador = fabrica.crearOperador(nLogin, nPassword, nTelefono, nNombre, galeria);
		}

	public void cargarEmpleado(JSONObject jEmpleado, Galeria galeria,
			Fabrica fabrica) throws LoginException
	{
			String nLogin = jEmpleado.getString(login);
			String nPassword = jEmpleado.getString(password);
			String nNombre = jEmpleado.getString(nombre);
			int nTelefono = jEmpleado.getInt(telefono);
			Empleado nEmpleado = fabrica.crearEmpleado(nLogin, nPassword, nTelefono, nNombre, galeria);
		}	
}
