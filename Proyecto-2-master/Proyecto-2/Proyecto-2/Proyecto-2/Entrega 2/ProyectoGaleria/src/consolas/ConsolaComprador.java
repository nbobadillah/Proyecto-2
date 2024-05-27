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

	public class ConsolaComprador extends Consola{
		private static final String TIPO_COMPRADOR = "Comprador";
	
	
		public void mostrarMenu(){
			System.out.println("------------------------------------------------------------------------------------------\n");
			System.out.println("Opciones disponibles:\n\n");
			System.out.println("0: Salir de la aplicación.\n");
			System.out.println("1: Realizar consignación.\n");
			System.out.println("2: Comprar Pieza.\n");
			System.out.println("3: Consultar dinero actual, valor máximo de compra y valor de mi colección.\n");
			System.out.println("4: Ver mis piezas actuales y consultar su estado.\n");
			System.out.println("5: Ver mi historial de piezas. \n");
			System.out.println("6: Participar en una subasta. \n");
			System.out.println("7: Solicitar un aumento en mi valor máximo de compra. \n");
			System.out.println("8: Ver la historia de un artista.\n");
			System.out.println("9: Ver la historia de una pieza.\n");
		}
		
		public static void mostrarMenuConsulta(){
			System.out.println("1: Sí.\n");
			System.out.println("2: No.\n");
		
		}
		

		
		public ConsolaComprador()
		{
			
		}
	}
	


