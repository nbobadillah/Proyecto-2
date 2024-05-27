package consolas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import fabrica.Fabrica;
import modelo.Galeria;

public abstract class Consola {

	private Fabrica fabrica = new Fabrica();
	private Galeria galeria;
	
	public void cargarGaleria() 
	{
		try {
		this.fabrica.cargarGaleria("Prueba.json", galeria); 
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void guardarGaleria() 
	{
		try {
		this.fabrica.salvarGaleria("Prueba.json", galeria); 
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	double pedirNumeroAlUsuario( String mensaje )
	{
		double valorResultado = Integer.MIN_VALUE;
		while( valorResultado == Integer.MIN_VALUE )
		{
			try
			{
				System.out.print( mensaje + ": " );
				BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
				String input = reader.readLine( );
				double numero = Double.parseDouble( input );
				valorResultado = numero;
			}
			catch( NumberFormatException nfe )
			{
				System.out.println( "El valor digitado no es un entero" );
			}
			catch( IOException e )
			{
				System.out.println( "Error leyendo de la consola" );
			}
		}
		return valorResultado;
	}

	public String pedirCadenaAlUsuario( String mensaje )
	{
		try
		{
			System.out.print( mensaje + ": " );
			BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
			String input = reader.readLine( );
			return input;
		}
		catch( IOException e )
		{
			System.out.println( "Error leyendo de la consola" );
		}
		return "error";
	}
	
	public abstract void mostrarMenu();
	

}




