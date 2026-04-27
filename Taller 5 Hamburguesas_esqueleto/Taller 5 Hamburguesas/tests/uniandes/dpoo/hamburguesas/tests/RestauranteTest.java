package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;

public class RestauranteTest {
	
	private Restaurante restaurante;
	private File archivoIngredientes;
	private File archivoMenu;
	private File archivoCombos;
	
	@BeforeEach
	public void setup() {
		restaurante = new Restaurante();
		
        archivoIngredientes = new File("data/ingredientes.txt");
        archivoMenu         = new File("data/menu.txt");
        archivoCombos       = new File("data/combos.txt");		
	}
	
	@Test
	public void cargarInformacionTest() throws Exception {
		restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
		
        assertFalse(restaurante.getIngredientes().isEmpty(), "Los ingredientes no se cargaron");
        assertFalse(restaurante.getMenuBase().isEmpty(),     "El menú base no se cargó");
        assertFalse(restaurante.getMenuCombos().isEmpty(),   "Los combos no se cargaron");	
	}
	
	@Test
    public void cargarIngredientesRepetidosTest() throws Exception {
        // Archivo con un ingrediente repetido
        File archivoRepetido = File.createTempFile("ingredientes_repetidos", ".txt");
        archivoRepetido.deleteOnExit();
        Files.write(archivoRepetido.toPath(), "tomate;1000\ntomate;1000\n".getBytes());

        assertThrows(IngredienteRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoRepetido, archivoMenu, archivoCombos);
        });
    }

    @Test
    public void cargarProductosRepetidosTest() throws Exception {
        // Archivo con un producto repetido
        File archivoRepetido = File.createTempFile("menu_repetido", ".txt");
        archivoRepetido.deleteOnExit();
        Files.write(archivoRepetido.toPath(), "corral;14000\ncorral;14000\n".getBytes());

        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoRepetido, archivoCombos);
        });
    }
    
    @Test
    public void iniciarPedidoTest() throws Exception {
        restaurante.iniciarPedido("Andres Aguirre", "Calle 123");
        assertNotNull(restaurante.getPedidoEnCurso(), "Debería haber un pedido en curso");
        assertEquals("Andres Aguirre", restaurante.getPedidoEnCurso().getNombreCliente());
    }

    @Test
    public void iniciarPedidoYaHayUnoTest() throws Exception {
        restaurante.iniciarPedido("Andres Aguirre", "Calle 123");

        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Juan", "Calle 456");
        });
    }

    @Test
    public void cerrarPedidoTest() throws Exception {
        // Crear carpeta facturas si no existe
        new File("./facturas").mkdirs();

        restaurante.iniciarPedido("Andres Aguirre", "Calle 123");
        restaurante.cerrarYGuardarPedido();

        assertNull(restaurante.getPedidoEnCurso(), "No debería haber pedido en curso");
    }

    @Test
    public void cerrarPedidoSinPedidoTest() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.cerrarYGuardarPedido();
        });
    }

    @Test
    public void getPedidoEnCursoNuloTest() {
        assertNull(restaurante.getPedidoEnCurso(), "Sin iniciar pedido debe ser null");
    }
    
    
    @Test
    public void cargarComboConProductoFaltanteTest() throws Exception {
        File archivoCombosInvalido = File.createTempFile("combos_invalido", ".txt");
        archivoCombosInvalido.deleteOnExit();
        
        PrintWriter writer = new PrintWriter(archivoCombosInvalido);
        writer.println("combo fake;10%;productoQueNoExiste;papas medianas");
        writer.close();

        assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombosInvalido);
        });
    }
}
