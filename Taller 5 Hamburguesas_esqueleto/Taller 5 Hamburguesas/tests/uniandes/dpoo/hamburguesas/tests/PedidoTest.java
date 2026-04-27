package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

    private Pedido pedido;
    private ProductoMenu corral;
    private ProductoMenu papas;
    private String facturaEsperada;

    @BeforeEach
    public void setup() {
        this.pedido = new Pedido("Andres Aguirre", "Calle 123");
        this.corral = new ProductoMenu("corral", 14000);
        this.papas  = new ProductoMenu("papas medianas", 5500);
        this.facturaEsperada = "Cliente: Andres Aguirre\n" +
        					   "Dirección: Calle 123\n" +
        					   "----------------\n" +
        					   "corral\n" +
        					   "            14000\n" +
        					   "----------------\n" +
        					   "Precio Neto:  14000\n" +
        					   "IVA:          2660\n" +
        					   "Precio Total: 16660\n";
    }

    @Test
    public void getNombreClienteTest() {
        assertEquals("Andres Aguirre", pedido.getNombreCliente(), "El nombre del cliente no es el esperado");
    }

    @Test
    public void getIdPedidoTest() {
    	// verificacion de que dos pedidos no tengan el mismo ID
        Pedido otroPedido = new Pedido("Juan", "Calle 456");
        assertNotEquals(pedido.getIdPedido(), otroPedido.getIdPedido(), "Dos pedidos tienen el mismo ID" );
    }

    @Test
    public void getPrecioTotalPedidoVacioTest() {
        // Sin productos todo debe ser igual a 0
        assertEquals(0, pedido.getPrecioTotalPedido(), "El precio por defecto (sin productos )debe de ser 0");
    }


    @Test
    public void getPrecioTotalPedidoVariosProductosTest() {
        pedido.agregarProducto(corral);
        pedido.agregarProducto(papas);
        // Neto: 19500, IVA: 19500*0.19 = 3705, Total: 23205
        assertEquals(23205, pedido.getPrecioTotalPedido(), "El precio del pedido no es el esperado");
    }

    @Test
    public void generarTextoFacturaTest() {
        pedido.agregarProducto(corral);

        assertEquals(facturaEsperada, pedido.generarTextoFactura());
    }

    @Test
    public void guardarFacturaTest() throws Exception {
        pedido.agregarProducto(corral);

        // Se crea un archivo temporal
        File archivo = File.createTempFile("factura_test_PedidoTest", ".txt");
        archivo.deleteOnExit(); // Este archivo temporal se borra al terminar

        pedido.guardarFactura(archivo);

        // Leer el archivo y comparar con el texto esperado
        String contenido = new String(Files.readAllBytes(archivo.toPath()));
        assertEquals(pedido.generarTextoFactura(), contenido);
    }
}
