package consolas;

import fabrica.Fabrica;
import modelo.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*; 
import fabrica.*;
import piezas.*;
import usuarios.*;
import exceptions.*;

public class ConsolaAdministrador extends Consola{
	private static final String TIPO_ADMINISTRADOR = "Administrador";

	public void mostrarMenu(){
		System.out.println("------------------------------------------------------------------------------------------\n");
		System.out.println("Opciones disponibles:\n\n");
		System.out.println("0: Salir de la aplicación.\n");
		System.out.println("1: Registrar nueva pieza.\n");
		System.out.println("2: Revisión y devolución de piezas consignadas en la galería.\n");
		System.out.println("3: Crear nueva subasta.\n");
		System.out.println("4: Mostrar subastas actuales.\n");
		System.out.println("5: Aumentar valor máximo de compras de un comprador. \n");
		System.out.println("6: Ver la historia de un artista.\n");
		System.out.println("7: Ver la historia de una pieza.\n");
		System.out.println("8: Ver la historia de un comprador. \n");
	}
	
	public static void mostrarMenuSubasta(){
		System.out.println("1: Sí.\n");
		System.out.println("2: No.\n");
}
	
	public static void mostrarMenuPieza(){
		System.out.println("1: Pintura.\n");
		System.out.println("2: Vídeo.\n");
		System.out.println("3: Impresión.\n");
		System.out.println("4: Escultura.\n");
		System.out.println("5: Fotografía.\n");
	
	}
	
	
	public ConsolaAdministrador(){
		
	}
	
}