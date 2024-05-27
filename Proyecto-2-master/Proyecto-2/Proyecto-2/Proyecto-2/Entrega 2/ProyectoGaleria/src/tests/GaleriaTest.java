package tests;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;

import usuarios.Comprador;
import usuarios.Operador;
import usuarios.Usuario;
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;

public class GaleriaTest {

    private static Operador operador;
    private static Galeria galeria;
    private static Comprador comprador;
    private static Comprador comprador2;
    private static Pieza pintura;
    private static Subasta subasta;
    private static Pieza escultura;

    
    @Before
    
    public void setUp() throws LoginException {

        Fabrica fabrica = new Fabrica();

        ArrayList<Pieza> piezas = new ArrayList<Pieza>();
        ArrayList<Subasta> subastas = new ArrayList<Subasta>();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        galeria = fabrica.crearGaleria("GaleriaPrueba", subastas, piezas, piezas, piezas, usuarios);
        galeria.setFabrica(fabrica);

        operador = new Operador("diamante", "password", 1234567890, "John Doe");
        galeria.agregarUsuario(operador);
        Usuario administrador = fabrica.crearAdministrador("zafiro", "password", 316292029, "Roberto", galeria);
        Usuario cajero = fabrica.crearCajero("patata", "password", 316292029, "Maximo", galeria);
        comprador = fabrica.crearComprador("tomate", "password", "Luka", 4000, new ArrayList<Pieza>(), new ArrayList<Pieza>(), 10000, 4123912, galeria);
        comprador2 = fabrica.crearComprador("zanahoria", "password", "Lionel", 50, new ArrayList<Pieza>(), new ArrayList<Pieza>(), 50, 4123912, galeria);
        Usuario Empleado = fabrica.crearEmpleado("lechuga", "password", 316292029, "Pedro", galeria);
        
        Pieza fotografia = fabrica.crearFotografia("Foto", 2004, 1000, null, null, true, comprador, null, 50, 100, null, false, false, null, true, true);
        pintura = fabrica.crearPintura("Pintura", 2005, 2000, null, null, true, comprador, null, 50, 100, true, null, true, true);
        pintura.setExhibida(true);
        escultura = fabrica.crearEscultura("Escultura", 2006, 3000, null, null, false, comprador, null, 50, 50, 50, null, 50, false, false, false, null, true, true);
        
        HashMap<Pieza, ArrayList<Integer>> piezasSubastadas = new HashMap<Pieza, ArrayList<Integer>>();
        ArrayList<Integer> valores = new ArrayList<Integer>();
        valores.add(1000);
        valores.add(1000);
        piezasSubastadas.put(pintura, valores);

        HashMap<Pieza,HashMap<Usuario, Integer>> registro = new HashMap<Pieza,HashMap<Usuario, Integer>>();
        HashMap<Usuario, Integer> ofertas = new HashMap<Usuario, Integer>();
        registro.put(pintura, ofertas);

        subasta = new Subasta(usuarios, operador, registro, piezasSubastadas);
        subastas.add(subasta);
        
        galeria.setSubastas(subastas);
        ArrayList<Pieza> tempActuales = galeria.getPiezasActuales();
        tempActuales.add(pintura);
        galeria.setPiezasActuales(tempActuales);
    }

    @Test
    
    public void testObtenerPiezasEnBodega(){
        ArrayList<Pieza> piezasBodega = galeria.obtenerPiezasEnBodega();
        assertEquals(0, piezasBodega.size());
    }

    @Test
    
    public void testObtenerPiezasExhibicion(){
        ArrayList<Pieza> piezasExhibicion = galeria.obtenerPiezasEnExhibicion();
        assertEquals(1, piezasExhibicion.size());
    }

    @Test
    
    public void testVerificarUsuarioValido() throws UsuarioInexistenteException{
        assertTrue(galeria.verificarUsuario(comprador));
    }

    @Test
    
    public void testVerificarUsuarioInvalido() throws UsuarioInexistenteException{
        Comprador comprador3 = new Comprador("chicarron", "123123", "Gerardo", 15000, null, null, 10000, 0);
        assertThrows(UsuarioInexistenteException.class, () -> galeria.verificarUsuario(comprador3));
    }

    @Test
    
    public void testAgregarUsuario(){
        Comprador comprador3 = new Comprador("chicarron", "123123", "Gerardo", 15000, null, null, 10000, 0);
        galeria.agregarUsuario(comprador3);
        assertTrue(galeria.getUsuarios().contains(comprador3));
    }
    

    @Test
    
    public void testCrearCompradorValido() throws LoginException{
        galeria.crearComprador("perejil", "123123", "Gerardo", 15000, null, null, 10000, 0, galeria);
    }

    @Test
    
    public void testCrearCompradorInvalido() throws LoginException{
        assertThrows(LoginException.class, () -> galeria.crearComprador("zanahoria", "123123", "Gerardo", 15000, null, null, 10000, 0, galeria));
    }

    @Test
    
    public void testGetAdministrador(){
        Usuario administrador = galeria.getAdministrador();
        assertEquals("Roberto", administrador.getNombre());
    }

    @Test
    
    public void testGetCajero(){
        Usuario cajero = galeria.getUnCajero();
        assertEquals("Maximo", cajero.getNombre());
    }

    @Test
    
    public void testGetOperador(){
        Usuario operador = galeria.getUnOperador();
        assertEquals("John Doe", operador.getNombre());
    }

    @Test
    
    public void testRealizarConsignacionValida() throws PropietarioErroneoException{
        galeria.realizarConsignacion(comprador, pintura, "2021-10-10", galeria, "1");
    }

    @Test
    
    public void testRealizarConsignacionPropietarioError(){
        Comprador comprador3 = new Comprador("chicarron", "123123", "Gerardo", 15000, new ArrayList<Pieza>(), new ArrayList<Pieza>(), 10000, 0);
        assertThrows(PropietarioErroneoException.class, () -> galeria.realizarConsignacion(comprador3, pintura, "2021-10-10", galeria, "1"));
    }
    
    @Test
    
    public void testCrearSubastaValida() throws UsuarioInexistenteException{
        galeria.crearSubasta(galeria.getUsuarios(), operador, null, null);
    }

    @Test
    
    public void testCrearSubastaUsuarioInexistente() throws UsuarioInexistenteException{
        ArrayList<Usuario> usuariosMalos = new ArrayList<Usuario>();
        Comprador comprador3 = new Comprador("chicarron", "123123", "Gerardo", 15000, new ArrayList<Pieza>(), new ArrayList<Pieza>(), 10000, 0);
        usuariosMalos.add(comprador3);
        assertThrows(UsuarioInexistenteException.class, () -> galeria.crearSubasta(usuariosMalos, operador, null, null));
    }
}
