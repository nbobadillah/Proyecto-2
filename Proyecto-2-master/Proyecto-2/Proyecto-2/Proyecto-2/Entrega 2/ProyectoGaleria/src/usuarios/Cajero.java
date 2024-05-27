package usuarios;
import java.util.ArrayList;

import java.util.*; 
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;


public class Cajero extends Empleado {
	
	private static final String tipo = "Cajero";
	
	public Cajero(String login, String password, int telefono, String nombre) {
		super(login, password, telefono, nombre);
		// TODO Auto-generated constructor stub
	}

	public String getTipo() {
		
		return this.tipo;
	}

	public void venderPieza (Comprador comprador, Pieza pieza, Galeria galeria) throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
		
		((Administrador )galeria.getAdministrador()).verificacionDeCompra(pieza, comprador, galeria);
			
		}
	}

