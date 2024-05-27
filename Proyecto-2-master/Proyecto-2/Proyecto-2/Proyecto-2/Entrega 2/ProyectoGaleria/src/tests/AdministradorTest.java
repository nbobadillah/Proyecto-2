package tests;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;

import usuarios.Administrador;
import usuarios.Comprador;
import usuarios.Operador;
import usuarios.Usuario;
import fabrica.*;
import piezas.*;
import modelo.*;
import exceptions.*;

public class AdministradorTest {
    private static Operador operador;
    private static Galeria galeria;
    private static Comprador comprador;
    private static Comprador comprador2;
    private static Pieza pintura;
    private static Subasta subasta;
    private static Pieza escultura;
    private static Administrador administrador;
    private static ArrayList<Usuario> usuarios;

    
    @Before
    
    public void setUp() throws LoginException {

        Fabrica fabrica = new Fabrica();

        ArrayList<Pieza> piezas = new ArrayList<Pieza>();
        ArrayList<Subasta> subastas = new ArrayList<Subasta>();
        usuarios = new ArrayList<Usuario>();

        galeria = fabrica.crearGaleria("GaleriaPrueba", subastas, new ArrayList<Pieza>(), new ArrayList<Pieza>(), new ArrayList<Pieza>(), usuarios);

        operador = new Operador("diamante", "password", 1234567890, "John Doe");
        administrador = fabrica.crearAdministrador("zafiro", "password", 316292029, "Roberto", galeria);
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
    
    public void testGetTipo(){
        assertEquals("Administrador", administrador.getTipo());
    }

    @Test
    
    public void testRegistrarPiezas() {
        administrador.registrarPiezas(escultura, galeria);
        assertTrue(galeria.getPiezasActuales().contains(escultura));
        assertTrue(galeria.getHistorialPiezas().contains(escultura));
    }

    @Test
    
    public void testDevoluciondePiezas() {
        Pieza eliminable = new Pintura("Eliminable", 2005, 2000, null, null, true, comprador, null, 50, 100, true, null, true, true);
        galeria.getPiezasActuales().add(eliminable);
        administrador.devoluciondePiezas(eliminable, galeria, comprador);       
        assertFalse(galeria.getPiezasActuales().contains(eliminable));
        assertTrue(galeria.getPiezasAntiguas().contains(eliminable));
    }

    @Test
    
    public void testVerificarUsuariosSubastaTrue() throws UsuarioInexistenteException {
        assertTrue(administrador.verificarUsuariosSubasta(usuarios, galeria));
    }

    @Test
    
    public void testVerificarUsuariosSubastaUsuarioInexistente() throws UsuarioInexistenteException {
        ArrayList<Usuario> usuariosMalos = new ArrayList<Usuario>();
        Comprador compradorMalo = new Comprador("tomate", "password", "Luka", 4000, new ArrayList<Pieza>(), new ArrayList<Pieza>(), 10000, 4123912);
        usuariosMalos.add(compradorMalo);
        assertThrows(UsuarioInexistenteException.class, () -> administrador.verificarUsuariosSubasta(usuariosMalos, galeria));
    }

    @Test
    
    public void testVerificacionDeCompraValida() throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
        administrador.verificacionDeCompra(pintura, comprador, galeria);
    }

    @Test
    
    public void testVerificacionDeCompraUsuarioInexistente() throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
        Comprador comprador3 = new Comprador("chicarron", "123123", "Gerardo", 15000, null, null, 10000, 0);
        assertThrows(UsuarioInexistenteException.class, () -> administrador.verificacionDeCompra(pintura, comprador3, galeria));
    }

    @Test
    
    public void testVerificacionDeCompraDineroInsuficiente() throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
        assertThrows(DineroInsuficienteException.class, () -> administrador.verificacionDeCompra(pintura, comprador2, galeria));
    }

    @Test
    
    public void testVerificacionDeCompraVentaImposible() throws UsuarioInexistenteException, DineroInsuficienteException, VentaImposibleException {
        assertThrows(VentaImposibleException.class, () -> administrador.verificacionDeCompra(escultura, comprador, galeria));
    }

    @Test
    
    public void testRegistrarPiezaPorConsignacionValida() throws PropietarioErroneoException{
        administrador.registrarPiezaPorConsignacion(comprador, pintura, "2021-10-10", galeria, "1");
    }

    @Test
    
    public void testRegistrarPiezaPorConsignacionPropietarioErroneo(){
        Comprador comprador3 = new Comprador("chicarron", "123123", "Gerardo", 15000, new ArrayList<Pieza>(), new ArrayList<Pieza>(), 10000, 0);
        assertThrows(PropietarioErroneoException.class, () -> administrador.registrarPiezaPorConsignacion(comprador3, pintura, "2021-10-10", galeria, "1"));
    }

    @Test
    
    public void testAumentarValorMaximo(){
        administrador.aumentarValorMaximo(comprador, 20000);
        assertEquals(20000, ((Comprador) comprador).getValorMaximoCompras());
    }



}
