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

public class VentaTest {
    
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

        operador = new Operador("diamante", "password", 1234567890, "John Doe");
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
    
    public void testVerificarEstadoDeVentaValido() throws VentaImposibleException {
        VentaPiezas venta = new VentaPiezas(comprador, pintura);
        assertTrue(venta.verificarEstadoDeVenta(pintura, galeria));
    }

    @Test
    
    public void testVerificarEstadoDeVentaImposible() throws VentaImposibleException {
        VentaPiezas venta = new VentaPiezas(comprador, pintura);
        pintura.setDispventa(false);
        assertThrows(VentaImposibleException.class, () -> { venta.verificarEstadoDeVenta(pintura, galeria);});
       
    }

    @Test
    
    public void testVenderPiezaValido() throws DineroInsuficienteException {
        VentaPiezas venta = new VentaPiezas(comprador, pintura);
        venta.venderPieza(comprador, pintura, galeria);
        assertEquals(8000, comprador.getDinero());
    }

    @Test
    
    public void testVenderPiezaDineroInsuficiente() {
        VentaPiezas venta = new VentaPiezas(comprador2, pintura);
        assertThrows(DineroInsuficienteException.class, () -> {venta.venderPieza(comprador2, pintura, galeria);});       
    }
}
