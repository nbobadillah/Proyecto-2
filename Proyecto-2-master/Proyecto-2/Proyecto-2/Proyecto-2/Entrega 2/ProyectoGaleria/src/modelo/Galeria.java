package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*; 
import fabrica.*;
import piezas.*;
import usuarios.*;
import exceptions.*;

public class Galeria {

	private String nombre;
	private ArrayList<Subasta> subastas;
	private ArrayList<Pieza> historialPiezas;
	private ArrayList<Pieza> piezasActuales;
	private ArrayList<Pieza> piezasAntiguas;
	private ArrayList<Usuario> usuarios;
	private Fabrica fabrica;

	public Galeria(String nombre, ArrayList<Subasta> subastas, ArrayList<Pieza> historialPiezas,
			ArrayList<Pieza> piezasActuales, ArrayList<Pieza> piezasAntiguas, ArrayList<Usuario> usuarios )
	{
		this.nombre = nombre;
		this.subastas = subastas;
		this.historialPiezas = historialPiezas;
		this.piezasActuales = piezasActuales;
		this.piezasAntiguas = piezasAntiguas;
		this.usuarios = usuarios;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setSubastas(ArrayList<Subasta> subastas) {
		this.subastas = subastas;
	}

	public void setHistorialPiezas(ArrayList<Pieza> historialPiezas) {
		this.historialPiezas = historialPiezas;
	}

	public void setPiezasActuales(ArrayList<Pieza> piezasActuales) {
		this.piezasActuales = piezasActuales;
	}

	public void setPiezasAntiguas(ArrayList<Pieza> piezasAntiguas) {
		this.piezasAntiguas = piezasAntiguas;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public ArrayList<Pieza> obtenerPiezasEnBodega (){

		ArrayList<Pieza> piezasBodega = new ArrayList<Pieza>();
		for (Pieza pieza: this.piezasActuales) {

			if ( pieza.isExhibida() == false) {
				piezasBodega.add(pieza); }

		}
		return piezasBodega;
	}

	public ArrayList<Pieza> obtenerPiezasEnExhibicion (){

		ArrayList<Pieza> piezasExhibidas = new ArrayList<Pieza>();
		for (Pieza pieza: this.piezasActuales) {

			if ( pieza.isExhibida()) {
				piezasExhibidas.add(pieza); }

		}
		return piezasExhibidas;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Subasta> getSubastas() {
		return subastas;
	}

	public ArrayList<Pieza> getHistorialPiezas() {
		return historialPiezas;
	}

	public ArrayList<Pieza> getPiezasActuales() {
		return piezasActuales;
	}

	public ArrayList<Pieza> getPiezasAntiguas() {
		return piezasAntiguas;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public Fabrica getFabrica() {
		return this.fabrica;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public boolean verificarUsuario(Usuario usuarioAVerificar) throws UsuarioInexistenteException {

		if ((this.usuarios.contains(usuarioAVerificar)) == false ) {

			throw new UsuarioInexistenteException(usuarioAVerificar.getNombre());
		}
		else {
			return true;
		}

	}

	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public void crearComprador(String login, String password,  String nombre, int valorMaximoCompras,
			ArrayList<Pieza> historialPiezas, ArrayList<Pieza> piezasActuales, int dinero, int telefono, Galeria galeria ) throws LoginException
	{
		getFabrica().crearComprador(login, password, nombre, valorMaximoCompras, historialPiezas, piezasActuales, dinero, telefono, galeria);
	}
	public void cargarGaleria(String archivo)
	{
		try
		{
			getFabrica().cargarGaleria(archivo, this);
		}
		catch (Exception e)
		{
		}
	}

	// ¡Crear usuario! y pieza. 

	public Usuario getAdministrador() {
		Usuario administrador = null;
		for ( Usuario usuario : this.usuarios) {
			if (usuario.getTipo() == "Administrador") {
				administrador = usuario;}
		}
		return administrador;
	}


	public Usuario getUnCajero() {

		Usuario cajero = null;
		for ( Usuario usuario : this.usuarios) {
			if (usuario.getTipo() == "Cajero") {
				cajero = usuario;}
		}
		return cajero;
	}

	public Usuario getUnOperador() {

		Usuario operador = null;
		for ( Usuario usuario : this.usuarios) {
			if (usuario.getTipo() == "Operador") {
				operador = usuario;}
		}
		return operador;
	}


	public void realizarConsignacion(Usuario propietario, Pieza piezaAConsignar, String fechaLimite, Galeria galeria, String exhibaVendaoSubasta) throws PropietarioErroneoException {

		((Administrador )this.getAdministrador()).registrarPiezaPorConsignacion(propietario, piezaAConsignar, fechaLimite, this, exhibaVendaoSubasta);

	}

	public void crearSubasta(ArrayList<Usuario> participantes, Usuario operador, HashMap<Pieza,HashMap<Usuario, 
			Integer>> registroSubasta, HashMap<Pieza, ArrayList<Integer>> piezasSubastadas) throws UsuarioInexistenteException {

		if (  ((Administrador )this.getAdministrador()).verificarUsuariosSubasta(participantes, this) == true  ) {

			Subasta nuevaSubasta = new Subasta(participantes, operador, registroSubasta, piezasSubastadas);
			this.subastas.add(nuevaSubasta);

		}
	}

	public void finalizarSubasta(Subasta subasta)  {

		subasta.finalizarSubasta(this);
	}

	
	/**
	

	public void mostrarMenu()
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Crear galería.\n");
		System.out.println("2: Crear usuario.\n");
		System.out.println("3: Crear pieza.\n");
		System.out.println("4: Ingresar como usuario.\n");
		System.out.println("5: Salvar galería.\n");
		System.out.println("6: Cargar galería.\n");
	}

	public void mostrarMenuUsuarios()
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Ingresar como empleado.\n");
		System.out.println("2: Ingresar como comprador.\n");
		System.out.println("3: Regresar al menú original.\n");

	}

	public void mostrarMenuEmpleado()
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Ingresar como administrador.\n");
		System.out.println("2: Ingresar como operador.\n");
		System.out.println("3: Ingresar como cajero.\n");

	}

	public void mostrarMenuComprador()
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Hacer una consignación.\n");
		System.out.println("2: Comprar una pieza.\n");
		System.out.println("3: Realizar una oferta en una subasta.\n");

	}

	public void mostrarMenuAdministrador()
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Registrar una pieza en el inventario.\n");
		System.out.println("2: Verificar una compra.\n");
		System.out.println("3: Verificar usuarios para una subasta.\n");
		System.out.println("4: Aumentar el valo de compra máximo de un comprador.\n");

	}

	public void mostrarMenuCajero()
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Vender una pieza.\n");

	}

	public void mostrarMenuOperador()
	{
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Registrar nueva oferta en una subasta.\n");

	}


	public static void main(String[] args ) {

		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Bienvenido a la prueba del funcionamiento de la aplicación.\n");
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Probaremos el funcionamiento de las siguientes funcionalidades: \n");
		System.out.println("\t Crear una galería.\n");
		System.out.println("\t Crear un usuario (comprador, empleado, administrador, operador, cajero).\n");
		System.out.println("\t Crear una pieza (escultura, fotografía, impresión, pintura, video).\n");
		System.out.println("\t Consignar una pieza.\n");
		System.out.println("\t Comprar una pieza.\n");
		System.out.println("\t Crear una subasta.\n");
		System.out.println("\t Ofertar en una subasta.\n");
		System.out.println("\t Salvar una galería creada.\n");
		System.out.println("\t Cargar una galería creada.\n");	

		Fabrica fabricaInicio= new Fabrica();
		Galeria galeriaInicio= fabricaInicio.crearGaleria("Galería Inicio", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
				new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
		galeriaInicio.mostrarMenu();
		Fabrica fabricaUno= new Fabrica();
		boolean centinela = true;
		while (centinela)
		{
			double eleccion= galeriaInicio.pedirNumeroAlUsuario("Por favor elija una de las siguientes opciones");

			if (eleccion == 1)
			{
				Galeria galeriaUno = fabricaUno.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
						new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
				System.out.println("Se ha creado la galería " + galeriaUno.getNombre() + " con " + galeriaUno.getUsuarios().size() 
						+ " usuarios registrados, " + galeriaUno.getPiezasActuales().size() 
						+ " piezas actuales, " + galeriaUno.getHistorialPiezas().size() 
						+ " piezas en el historial y " + galeriaUno.getPiezasAntiguas().size() 
						+ " piezas antiguas." );

			}
			else if (eleccion == 2)
			{
				Galeria galeriaUsuarios = fabricaUno.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
						new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
				try {
					Comprador primerComprador = fabricaUno.crearComprador("loginalice", "hola123", "Alice", 200,
							new ArrayList<Pieza>(), new ArrayList<Pieza>(), 0, 1234567890, galeriaUsuarios);
					System.out.println("------------------------------------------------------------------------------------------ \n");
					System.out.println("Se ha creado un comprador que a continuación se presentará: \n \n");
					System.out.println("Hola, soy " + primerComprador.getNombre() + " y tengo " + primerComprador.getDinero() + " de dinero, " + primerComprador.getPiezasActuales().size() + " piezas actuales y " + primerComprador.getHistorialPiezas().size() + " piezas en mi historial. Si te quieres "
							+ "poner en contacto conmigo, llámame al " + primerComprador.getTelefono() + " . Mi login es " + primerComprador.getLogin() + " y mi contraseña es " + primerComprador.getPassword() + ". Nos vemos.");
					Administrador administrador = fabricaUno.crearAdministrador("loginbob", "adios456",912345678, "Bob", galeriaUsuarios);
					System.out.println("------------------------------------------------------------------------------------------ \n");
					System.out.println("Se ha creado un administrador que a continuación se presentará: \n \n");
					System.out.println("Hola, soy " + administrador.getNombre() + ". Si te quieres poner en contacto conmigo, llámame al " + administrador.getTelefono() 
					+ " . Mi login es " + administrador.getLogin() + " y mi contraseña es " + administrador.getPassword() + ". Nos vemos.");
					Cajero cajero = fabricaUno.crearCajero("logineve", "saludos789",891234567, "Eve", galeriaUsuarios);
					System.out.println("------------------------------------------------------------------------------------------ \n");
					System.out.println("Se ha creado un cajero que a continuación se presentará: \n \n");
					System.out.println("Hola, soy " + cajero.getNombre() + ". Si te quieres poner en contacto conmigo, llámame al " + cajero.getTelefono() 
					+ " . Mi login es " + cajero.getLogin() + " y mi contraseña es " + cajero.getPassword() + ". Nos vemos.");
					Operador operador = fabricaUno.crearOperador("loginneo", "secreto",789123456, "Neo", galeriaUsuarios);
					System.out.println("------------------------------------------------------------------------------------------ \n");
					System.out.println("Se ha creado un operador que a continuación se presentará: \n \n");
					System.out.println("Hola, soy " + operador.getNombre() + ". Si te quieres poner en contacto conmigo, llámame al " + operador.getTelefono() 
					+ " . Mi login es " + operador.getLogin() + " y mi contraseña es " + operador.getPassword() + ". Nos vemos.");
					Empleado empleado = fabricaUno.crearEmpleado("logiThomasAnderson", "enlamatrix",789123456, "Thomas Anderson", galeriaUsuarios);
					System.out.println("------------------------------------------------------------------------------------------ \n");
					System.out.println("Se ha creado un empleado que a continuación se presentará: \n \n");
					System.out.println("Hola, soy " + empleado.getNombre() + ". Si te quieres poner en contacto conmigo, llámame al " + empleado.getTelefono() 
					+ " . Mi login es " + empleado.getLogin() + " y mi contraseña es " + empleado.getPassword() + ". Nos vemos.");
					System.out.println("------------------------------------------------------------------------------------------ \n");
					System.out.println("Ahora la galería tiene "+ galeriaUsuarios.getUsuarios().size() 
							+ " usuarios registrados, " + galeriaUsuarios.getPiezasActuales().size() 
							+ " piezas actuales, " + galeriaUsuarios.getHistorialPiezas().size() 
							+ " piezas en el historial y " + galeriaUsuarios.getPiezasAntiguas().size() 
							+ " piezas antiguas.");

				} catch (LoginException l)
				{
					System.out.println(l.getMessage());
				}
			}
			else if (eleccion == 3)
			{
				Galeria galeriaPiezas = fabricaUno.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
						new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
				try
				{
					Fabrica fabricaPiezas = new Fabrica();
					Comprador primerComprador = fabricaPiezas.crearComprador("loginPrueba", "Prueba", "Comprador de Prubea", 0,
							new ArrayList<Pieza>(), new ArrayList<Pieza>(), 0, 0, galeriaPiezas);
					Video video = fabricaPiezas.crearVideo("Video artístico", 2018, 2000 ,"Puerto Rico", "Autor artista", true, primerComprador, 
							"1", 1920, 1080, 152, ".mp4", false, "20/09/2024", true, false);
					System.out.println("------------------------------------------------------------------------------------------ \n");
					System.out.println("Se ha creado un video titulado " + video.getTitulo() + ", creado en el año" + video.getAño() 
					+ " por " + video.getAutores() + " con un valor de " + video.getValor() + ".");
					Pintura pintura = fabricaPiezas.crearPintura("Selene", 1880, 2000000 ,"Francia" , "Albert Aublet", true, primerComprador, 
							"1", 1920, 1080, false, "20/09/2024" , true, true);				System.out.println("------------------------------------------------------------------------------------------ \n");
							System.out.println("------------------------------------------------------------------------------------------ \n");
							System.out.println("Se ha creado una pintura titulada " + pintura.getTitulo() + ", creado en el año" + pintura.getAño() 
							+ " por " + pintura.getAutores() + " con un valor de " + pintura.getValor() + ".");
							Impresion impresion = fabricaPiezas.crearImpresion("Tarea", 2024, 1 ,"Bogotá", "Epson", true, primerComprador,  "1", 1920, 1080, false, "20/09/2024" , true, true );
							System.out.println("------------------------------------------------------------------------------------------ \n");
							System.out.println("Se ha creado la impresión titulada " + impresion.getTitulo() + ", creado en el año" + impresion.getAño() 
							+ " por " + impresion.getAutores() + " con un valor de " + impresion.getValor() + ".");
							Fotografia fotografia = fabricaPiezas.crearFotografia("Fotografía artística", 1816, 2000 ,"Foto Japón", "fotógrafo", true, primerComprador, 
									"1", 1920, 1080, ".png", false, true, "20/09/2024", true, true );
							System.out.println("------------------------------------------------------------------------------------------ \n");
							System.out.println("Se ha creado una fotografía titulada " + fotografia.getTitulo() + ", creado en el año" + fotografia.getAño() 
							+ " por " + fotografia.getAutores() + " con un valor de " + fotografia.getValor() + ".");
							Escultura escultura = fabricaPiezas.crearEscultura("Escultura artística", -6000, 2000000 ,	"Mesopotamia", "Desonocido", true, primerComprador, 
									"1", 1920, 1080, 152, "Piedra", 376, false, false,true, "20/09/2024" ,false,  false);
							System.out.println("------------------------------------------------------------------------------------------ \n");
							System.out.println("Se ha creado una escultura titulada " + escultura.getTitulo() + ", creado en el año" + escultura.getAño() 
							+ " por " + escultura.getAutores() + " con un valor de " + escultura.getValor() + ".");
							System.out.println("------------------------------------------------------------------------------------------ \n");
							System.out.println("Las piezas del comprador " + primerComprador.getNombre() + " se acutalizaron. Ahora tiene " +
									primerComprador.getPiezasActuales().size() + " pieazs actuales y " +  primerComprador.getHistorialPiezas().size() + " piezas en el historial." );
				} catch (LoginException l)
				{
					System.out.println(l.getMessage());
				}
				}
				else if ( eleccion == 4) 
				{
					String rutaPersistencia =  galeriaInicio.pedirCadenaAlUsuario("Ingrese la ruta del archivo Prueba.json");
					Galeria galeriaCargar = fabricaUno.crearGaleria("Galeria Dolly", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
							new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());

					Fabrica fabricaCargar = new Fabrica();
					try
					{
						fabricaCargar.getPersistencia().cargarGaleria(rutaPersistencia, galeriaCargar);
	
						galeriaInicio.mostrarMenuUsuarios();
	
						boolean centinela2 = true;
						while (centinela2)
						{
							double eleccion2= galeriaInicio.pedirNumeroAlUsuario("Por favor elija una de las siguientes opciones");
	
							if(eleccion2 == 0) {
								centinela = false;
								centinela2 = false;
							}
	
							else if (eleccion2 == 3) {
								centinela2 = false;
							}
	
							else if(eleccion2 == 2) {
								galeriaInicio.mostrarMenuComprador();
	
								boolean centinelaComprador = true;
								while (centinelaComprador) {
	
									double eleccionComprador= galeriaInicio.pedirNumeroAlUsuario("Por favor elija una de las siguientes opciones");
	
									if (eleccionComprador == 0) {
										centinela = false;
										centinelaComprador = false;
										centinela2 = false;
									}
	
	
									else if (eleccionComprador == 1)	{
										//Usuario comprador =  galeriaCargar.getUnComprador();
										//((Comprador) comprador).consignarPieza(null, "20241231", galeriaCargar, "1");
	
										System.out.println("Hola, soy Alice. Hice una consignación a la galería de la pieza de tipo "
												+ "pinturada titulada Selene hasta la fehca límite de 2024/10/08. \n");
									}
	
									else if(eleccionComprador == 2) {
										System.out.println("Hola, soy Alice. Realice la compra de la pieza de tipo fotografía "
												+ "titulada Fotografía artística. \n");
									}
	
									else if(eleccionComprador == 3) {
										System.out.println("Hola, soy Alice. Realice una oferta en una subasta para la pieza de tipo "
												+ "escultura titulada Escultura Artística por el valor de 5 pesos. \n");
									}
								}
							}
	
							else if(eleccion2 ==1) {
								galeriaInicio.mostrarMenuEmpleado();
	
								boolean centinela3 = true;
								while (centinela3)
								{
									double eleccion3= galeriaInicio.pedirNumeroAlUsuario("Por favor elija una de las siguientes opciones");
	
									if (eleccion3 == 0) {
										centinela = false;
										centinela2 = false;
										centinela3 = false;
	
									}
	
									else if (eleccion3 == 1)
									{
										galeriaInicio.mostrarMenuAdministrador();
										boolean centinelaAdministrador = true;
										while (centinelaAdministrador)
										{
											double eleccionAdministrador= galeriaInicio.pedirNumeroAlUsuario("Por favor elija una de las siguientes opciones");
	
											if (eleccionAdministrador == 0) {
												centinela = false;
												centinela2 = false;
												centinela3 = false;
												centinelaAdministrador = false;
	
											}
											else if ( eleccionAdministrador == 1) {
												System.out.println("Hola, soy Bob. Soy el administrador de la galería. Realice el registro de la pieza "
														+ "de tipo pinturada titulada Selene, consignada por el comprador: Alice. \n");
											}
	
											else if (eleccionAdministrador == 2) {
												System.out.println("Hola, soy Bob. Soy el administrador de la galería. Verifique la compra realizada por Alice de la pieza de tipo fotografía "
														+ "titulada Fotografía artística. \n"); 
											}
											else if (eleccionAdministrador == 3) {
	
												System.out.println("Hola, soy Bob. Soy el administrador de la galería. Verifique el usuario Alice para que pueda participar en la subasta \n"); 
											}
	
											else if (eleccionAdministrador == 4) {
												System.out.println("Hola, soy Bob. Soy el administrador de la galería. Aumente el valor máximo de compras de Alice de 200 a 500 pesos \n"); 
											}
	
										}
									}
	
	
	
									else if (eleccion3 == 2) {
										galeriaInicio.mostrarMenuOperador();
	
										boolean centinelaOperador = true;
										while (centinelaOperador)
										{
											double eleccionOperador= galeriaInicio.pedirNumeroAlUsuario("Por favor elija una de las siguientes opciones");
	
											if (eleccionOperador == 0) {
												centinela = false;
												centinela2 = false;
												centinela3 = false;
												centinelaOperador = false;
	
											}
											else if ( eleccionOperador == 1) {
												System.out.println("Hola, soy Neo. Soy un operador. Registre una oferta de una subasta para la pieza de tipo "
														+ "escultura titulada Escultura Artística por el valor de 5 pesos realiza por la usuaria Alice. \n");
											}
										}
									}
	
									else if (eleccion3 == 3) {
										galeriaInicio.mostrarMenuCajero();
	
										boolean centinelaOperador = true;
										while (centinelaOperador)
										{
											double eleccionOperador= galeriaInicio.pedirNumeroAlUsuario("Por favor elija una de las siguientes opciones");
	
											if (eleccionOperador == 0) {
												centinela = false;
												centinela2 = false;
												centinela3 = false;
												centinelaOperador = false;
	
											}
											else if ( eleccionOperador == 1) {
												System.out.println("Hola, soy Eve. Soy un cajero. Ejecute la compra de la pieza de tipo "
														+ "	escultura titulada Escultura Artística por parte de la usuaria Alice. \n");
											}
										}
	
									}
	
								}
							}
						}
					}catch (LoginException l)
					{
					System.out.println(l.getMessage());
				}catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if (eleccion == 5)
			{
				Galeria galeriaGuardar = fabricaUno.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
						new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
				try {
					Fabrica fabricaGuardar = new Fabrica();
					Comprador primerComprador = fabricaGuardar.crearComprador("loginalice", "hola123", "Alice", 200,
							new ArrayList<Pieza>(), new ArrayList<Pieza>(), 0, 1234567890, galeriaGuardar);
					Administrador administrador = fabricaGuardar.crearAdministrador("loginbob", "adios456",912345678, "Bob", galeriaGuardar);
					Cajero cajero = fabricaGuardar.crearCajero("logineve", "saludos789",891234567, "Eve", galeriaGuardar);
					Operador operador = fabricaGuardar.crearOperador("loginneo", "secreto",789123456, "Neo", galeriaGuardar);
					Empleado empleado = fabricaGuardar.crearEmpleado("logiThomasAnderson", "enlamatrix",789123456, "Thomas Anderson", galeriaGuardar);
					Video video = fabricaGuardar.crearVideo("Video artístico", 2018, 2000 ,"Puerto Rico", "Autor artista", true, primerComprador, 
							"1", 1920, 1080, 152, ".mp4", false, true, false, "20/09/2024" );
					Pintura pintura = fabricaGuardar.crearPintura("Selene", 1880, 2000000 ,"Francia" , "Albert Aublet", true, primerComprador, 
							"1", 1920, 1080, false, true, true, "20/09/2024");
					Impresion impresion = fabricaGuardar.crearImpresion("Tarea", 2024, 1 ,"Bogotá", "Epson", true, primerComprador,  "1", 1920, 1080, false,  true, true,"26/04/2024" );
					Fotografia fotografia = fabricaGuardar.crearFotografia("Fotografía artística", 1816, 2000 ,"Foto Japón", "fotógrafo", true, primerComprador, 
							"1", 1920, 1080, ".png", false, true, true, true, "20/09/2024" );
					Escultura escultura = fabricaGuardar.crearEscultura("Escultura artística", -6000, 2000000 ,	"Mesopotamia", "Desonocido", true, primerComprador, 
							"1", 1920, 1080, 152, "Piedra", 376, false, false,true ,false,  false, "20/09/2024" );
					fabricaGuardar.getPersistencia().salvarGaleria("Prueba.json" , galeriaGuardar.getUsuarios(), fabricaGuardar.getPiezasCreadas());
					System.out.println("Se guardó la galería creada (Verificar en la carpeta Galería en Persistencia).");
				}catch (LoginException l)
				{
					System.out.println(l.getMessage());
				}catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if (eleccion == 6)
			{
				String rutaPersistencia =  galeriaInicio.pedirCadenaAlUsuario("Ingrese la ruta del archivo Prueba.json");
				Galeria galeriaGuardarDos = fabricaUno.crearGaleria("Galeria de Prueba", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
						new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
				Galeria galeriaCargar = fabricaUno.crearGaleria("Galeria Dolly", new ArrayList<Subasta>(), new ArrayList<Pieza>(),
						new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Usuario>());
				try {
					Fabrica fabricaGuardarDos = new Fabrica();
					Comprador primerComprador = fabricaGuardarDos.crearComprador("loginalice", "hola123", "Alice", 200,
							new ArrayList<Pieza>(), new ArrayList<Pieza>(), 0, 1234567890, galeriaGuardarDos);
					Administrador administrador = fabricaGuardarDos.crearAdministrador("loginbob", "adios456",912345678, "Bob", galeriaGuardarDos);
					Cajero cajero = fabricaGuardarDos.crearCajero("logineve", "saludos789",891234567, "Eve", galeriaGuardarDos);
					Operador operador = fabricaGuardarDos.crearOperador("loginneo", "secreto",789123456, "Neo", galeriaGuardarDos);
					Empleado empleado = fabricaGuardarDos.crearEmpleado("logiThomasAnderson", "enlamatrix",789123456, "Thomas Anderson", galeriaGuardarDos);
					/*
					Video video = fabricaGuardarDos.crearVideo("Video artístico", 2018, 2000 ,"Puerto Rico", "Autor artista", true, primerComprador, 
							"1", 1920, 1080, 152, ".mp4", false, true, false, "20/09/2024" );
					Pintura pintura = fabricaGuardarDos.crearPintura("Selene", 1880, 2000000 ,"Francia" , "Albert Aublet", true, primerComprador, 
							"1", 1920, 1080, false, true, true, "20/09/2024");
					Impresion impresion = fabricaGuardarDos.crearImpresion("Tarea", 2024, 1 ,"Bogotá", "Epson", true, primerComprador,  "1", 1920, 1080, false,  true, true,"26/04/2024" );	
					Fotografia fotografia = fabricaGuardarDos.crearFotografia("Fotografía artística", 1816, 2000 ,"Foto Japón", "fotógrafo", true, primerComprador, 
							"1", 1920, 1080, ".png", false, true, true, true, "20/09/2024" );
					Escultura escultura = fabricaGuardarDos.crearEscultura("Escultura artística", -6000, 2000000 ,	"Mesopotamia", "Desonocido", true, primerComprador, 
							"1", 1920, 1080, 152, "Piedra", 376, false, false,true ,false,  false, "20/09/2024" );
					fabricaGuardarDos.getPersistencia().salvarGaleria("Prueba.json" , fabricaGuardarDos.getUsuariosCreados(), fabricaGuardarDos.getPiezasCreadas());
					Fabrica fabricaCargar = new Fabrica();
					fabricaCargar.getPersistencia().cargarGaleria(rutaPersistencia, galeriaCargar);
					for (Usuario usuario: galeriaCargar.getUsuarios())
					{
						if (usuario.getTipo().equals("Comprador"))
						{
							Comprador comprador = (Comprador) usuario;
							System.out.println("Hola, soy " + comprador.getNombre() + " y tengo " + comprador.getDinero() + " de dinero. Si te quieres "
									+ "poner en contacto conmigo, llámame al " + comprador.getTelefono() + " . Mi login es " + comprador.getLogin() + " y mi contraseña es " + comprador.getPassword() +
									". Además, tengo " + comprador.getHistorialPiezas().size() + " piezas en el historial que son: \n");
							if (! comprador.getHistorialPiezas().isEmpty())
							{
								for (Pieza pieza : comprador.getHistorialPiezas())
								{
									System.out.println(pieza.getTitulo());
								}
								System.out.println(". \n");
							}
							System.out.println(" Y " + comprador.getPiezasActuales().size() + " piezas actuales que son: " );
							if (! comprador.getHistorialPiezas().isEmpty())
							{
								for (Pieza pieza : comprador.getHistorialPiezas())
								{
									System.out.println(pieza.getTitulo());
								}
								System.out.println(". \n");
							}
						}
						else if ("Empleado".equals(usuario.getTipo()))
						{
							System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
							+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
						}
						else if (usuario.getTipo().equals("Administrador"))
						{
							System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
							+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
						}
						else if (usuario.getTipo().equals("Operador"))
						{
							System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
							+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
						}
						else if (usuario.getTipo().equals("Cajero") )
						{
							System.out.println("Hola, soy " + usuario.getNombre() + " y soy de tipo " + usuario.getTipo() + ". Si te quieres poner en contacto conmigo, llámame al " + usuario.getTelefono() 
							+ " . Mi login es " + usuario.getLogin() + " y mi contraseña es " + usuario.getPassword() + ". Nos vemos.");
						}
					}
				}catch (LoginException l)
				{
					System.out.println(l.getMessage());
				}catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else if (eleccion == 0)
			{
				centinela=false;
			}
		}
	}
	*/
}



